package cn.com.tsjx.common.web.action;

import java.io.InputStream;
import java.util.Random;

import com.opensymphony.xwork2.ActionContext;
import cn.com.tsjx.common.util.lang.ImageUtil;
import cn.com.tsjx.common.util.web.WebUtils;


/**
 * 验证码
 * @author liwei
 */
public class CheckCodeAction extends BaseAction {

	private static final long serialVersionUID = 7730859436245604068L;

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public String execute() throws Exception {
		Random random = new Random();
		String code = String.format("%1$05d", random.nextInt(100000));
		ActionContext.getContext().getSession().put(WebUtils.SESSION_CHECK_CODE, code);
		inputStream = ImageUtil.createImage(code);
		return SUCCESS;
	}
}
