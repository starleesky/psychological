package cn.com.tsjx.common.util.xml;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

public class XPathParser {

	private final Document document;
	private final XPath xpath;

	public XPathParser(Document document) {
		this.document = document;
		XPathFactory xpathFactory = XPathFactory.newInstance();
		this.xpath = xpathFactory.newXPath();
	}

	public XPathParser(String xml) {
		this(xml, false, null);
	}

	public XPathParser(Reader reader) {
		this(reader, false, null);
	}

	public XPathParser(InputStream inputStream) {
		this(inputStream, false, null);
	}

	public XPathParser(String xml, boolean validation) {
		this(xml, validation, null);
	}

	public XPathParser(Reader reader, boolean validation) {
		this(reader, validation, null);
	}

	public XPathParser(InputStream inputStream, boolean validation) {
		this(inputStream, validation, null);
	}

	public XPathParser(String xml, boolean validation, EntityResolver entityResolver) {
		this(new InputSource(new StringReader(xml)), validation, entityResolver);
	}

	public XPathParser(Reader reader, boolean validation, EntityResolver entityResolver) {
		this(new InputSource(reader), validation, entityResolver);
	}

	public XPathParser(InputStream inputStream, boolean validation, EntityResolver entityResolver) {
		this(new InputSource(inputStream), validation, entityResolver);
	}

	/**
	 * 构造对象
	 * @see DocumentBuilder#parse(InputSource)
	 * @see DocumentBuilderFactory#setValidating(boolean)
	 * @see DocumentBuilder#setEntityResolver(EntityResolver)
	 * @param inputSource
	 * @param validation
	 * @param entityResolver
	 */
	public XPathParser(InputSource inputSource, boolean validation, EntityResolver entityResolver) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(validation);
			factory.setNamespaceAware(false);
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(false);
			factory.setCoalescing(false);
			factory.setExpandEntityReferences(true);

			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setEntityResolver(entityResolver);
			builder.setErrorHandler(new DefaultHandler());
			this.document = builder.parse(inputSource);
		} catch (Exception e) {
			throw new ParserException("Error creating document instance. Cause: " + e, e);
		}

		XPathFactory xpathFactory = XPathFactory.newInstance();
		this.xpath = xpathFactory.newXPath();
	}

	public String evalString(String expression) {
		return this.evalString(document, expression);
	}

	public String evalString(Object root, String expression) {
		return (String) this.evaluate(expression, root, XPathConstants.STRING);
	}

	public Boolean evalBoolean(String expression) {
		return this.evalBoolean(document, expression);
	}

	public Boolean evalBoolean(Object root, String expression) {
		return (Boolean) this.evaluate(expression, root, XPathConstants.BOOLEAN);
	}

	public Short evalShort(String expression) {
		return this.evalShort(document, expression);
	}

	public Short evalShort(Object root, String expression) {
		return Short.valueOf(this.evalString(root, expression));
	}

	public Integer evalInteger(String expression) {
		return this.evalInteger(document, expression);
	}

	public Integer evalInteger(Object root, String expression) {
		return Integer.valueOf(this.evalString(root, expression));
	}

	public Long evalLong(String expression) {
		return this.evalLong(document, expression);
	}

	public Long evalLong(Object root, String expression) {
		return Long.valueOf(this.evalString(root, expression));
	}

	public Float evalFloat(String expression) {
		return this.evalFloat(document, expression);
	}

	public Float evalFloat(Object root, String expression) {
		return Float.valueOf(this.evalString(root, expression));
	}

	public Double evalDouble(String expression) {
		return this.evalDouble(document, expression);
	}

	public Double evalDouble(Object root, String expression) {
		return (Double) this.evaluate(expression, root, XPathConstants.NUMBER);
	}

	public List<XNode> evalNodes(String expression) {
		return this.evalNodes(document, expression);
	}

	public List<XNode> evalNodes(Object root, String expression) {
		List<XNode> result = new ArrayList<XNode>();
		NodeList nodeList = (NodeList) this.evaluate(expression, root, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			result.add(new XNode(nodeList.item(i), this));
		}
		return result;
	}

	public XNode evalNode(String expression) {
		return this.evalNode(document, expression);
	}

	public XNode evalNode(Object root, String expression) {
		Node node = (Node) this.evaluate(expression, root, XPathConstants.NODE);
		if (node == null) {
			return null;
		}
		return new XNode(node, this);
	}

	private Object evaluate(String expression, Object root, QName returnType) {
		try {
			return xpath.evaluate(expression, root, returnType);
		} catch (Exception e) {
			throw new ParserException("Error evaluating XPath.  Cause: " + e, e);
		}
	}
}
