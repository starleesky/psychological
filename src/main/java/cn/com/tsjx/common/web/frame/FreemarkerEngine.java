package cn.com.tsjx.common.web.frame;

import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import freemarker.cache.ClassTemplateLoader;
import freemarker.ext.servlet.AllHttpScopesHashModel;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.HttpRequestParametersHashModel;
import freemarker.ext.servlet.HttpSessionHashModel;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerEngine {

	private Configuration configuration;

	public Configuration getConfiguration() {
		return configuration;
	}

	public FreemarkerEngine() {
		this.configuration = new Configuration();
		configuration.setTemplateLoader(new ClassTemplateLoader(this.getClass(), ""));
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		configuration.setTemplateUpdateDelay(0);
		configuration.setDefaultEncoding("UTF-8");
		configuration.setNumberFormat("0.########");
		configuration.setDateFormat("yyyy-MM-dd HH:mm:ss");
		configuration.setClassicCompatible(true);
	}

	protected void doRender(String name, Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response, ServletContext servletContext) throws Exception {
		// Expose model to JSP tags (as request attributes).
		exposeModelAsRequestAttributes(model, request);
		// Expose all standard FreeMarker hash models.
		SimpleHash fmModel = buildTemplateModel(model, request, response, servletContext);
		// Grab the locale-specific version of the template.
		Locale locale = request.getLocale();

		Template template = getConfiguration().getTemplate(name, locale);
		template.process(fmModel, response.getWriter());
	}

	protected void exposeModelAsRequestAttributes(Map<String, Object> model, HttpServletRequest request)
			throws Exception {
		for (Map.Entry<String, Object> entry : model.entrySet()) {
			String modelName = entry.getKey();
			Object modelValue = entry.getValue();
			if (modelValue != null) {
				request.setAttribute(modelName, modelValue);
			} else {
				request.removeAttribute(modelName);
			}
		}
	}

	private HttpSessionHashModel buildSessionModel(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return new HttpSessionHashModel(session, getObjectWrapper());
		} else {
			return new HttpSessionHashModel(null, request, response, getObjectWrapper());
		}
	}

	protected SimpleHash buildTemplateModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response, ServletContext servletContext) {
		AllHttpScopesHashModel fmModel = new AllHttpScopesHashModel(getObjectWrapper(), servletContext, request);
		fmModel.put(FreemarkerServlet.KEY_SESSION, buildSessionModel(request, response));
		fmModel.put(FreemarkerServlet.KEY_REQUEST, new HttpRequestHashModel(request, response, getObjectWrapper()));
		fmModel.put(FreemarkerServlet.KEY_REQUEST_PARAMETERS, new HttpRequestParametersHashModel(request));
		fmModel.putAll(model);
		return fmModel;
	}

	protected ObjectWrapper getObjectWrapper() {
		ObjectWrapper ow = getConfiguration().getObjectWrapper();
		return (ow != null ? ow : ObjectWrapper.DEFAULT_WRAPPER);
	}
}
