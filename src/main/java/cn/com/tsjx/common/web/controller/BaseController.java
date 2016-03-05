package cn.com.tsjx.common.web.controller;

import javax.servlet.http.HttpServletRequest;

import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.util.Utility;
import cn.com.tsjx.common.web.dto.IBaseDTO;

/**
 * 
 * 
 * 
 * xyl (2015-10-20 上午9:50:01)
 */
public abstract class BaseController {
	
	/**
	 * 
	 * 
	 * @param str_URL
	 * @return
	 * 
	 * xyl (2015-12-8 下午3:39:16)
	 */
	protected String redirect(String str_URL) {
		return "redirect:" + str_URL;
	}
	
	/**
	 * 
	 * 
	 * @param baseDTO
	 * @param defaultPath
	 * @return
	 * 
	 * xyl (2015-10-20 上午9:58:39)
	 */
	protected String getReturnPath(IBaseDTO baseDTO, String defaultPath) {
		String str_retPth;
		
		if (baseDTO != null && baseDTO.getReturnPath() != null && baseDTO.getReturnPath().length() > 0) {
			str_retPth = baseDTO.getReturnPath();
		} else {
			str_retPth = defaultPath;
		}
		
		return str_retPth;
	}
	
	protected void setValueToSession(HttpServletRequest req, String name, Object value) {
		req.getSession(true).setAttribute(name, value);
	}
	
	protected Object getValueFromSession(HttpServletRequest req, String name) {
		return req.getSession(true).getAttribute(name);
	}
	
	protected void setResultTrue(Result<?> result) {
		result.setResult(Boolean.TRUE);
		result.setMessage("操作成功！");
	}
	
	protected void setResultFalse(Result<?> result) {
		result.setResult(Boolean.FALSE);
		result.setMessage("操作失败！");
	}
	
	protected void setResultInvalidateParameter(Result<?> result) {
		Utility.setResultInvalidateParameter(result);
	}
	
}
