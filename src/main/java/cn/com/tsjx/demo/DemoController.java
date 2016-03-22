/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: DemoConcroller.java
 * Author:   muxing
 * Date:    2016/3/22 0:37
 * Description:
 */
package cn.com.tsjx.demo;

import cn.com.tsjx.common.util.json.JsonMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * DemoConcroller
 *
 * @author muxing
 * @date 2016/3/22
 */
@Controller
@RequestMapping("/wap")
public class DemoController {

	@RequestMapping(value = "/search")
	public String search() {
		return "/wap/search";
	}

	@RequestMapping(value = "/companyInfo")
	public String companyInfo() {
		return "/wap/company-info";
	}

	@RequestMapping(value = "/demo/page")
	@ResponseBody
	public String list(PageDto pageDto) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer stringBuffer = new StringBuffer("jsonp");
		stringBuffer.append(pageDto.getPageNo());
		stringBuffer.append("(");
		pageOutDto pageOutDto = new pageOutDto();

		stringBuffer.append(JsonMapper.getMapper().toJson(pageOutDto));
		stringBuffer.append(")");
		return stringBuffer.toString();
	}

	@RequestMapping(value = "/demo/upload", method = RequestMethod.POST)
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		response.setContentType("text/html;charset=utf-8");
		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file = multipartRequest.getFile("Filedata");
		// 获得文件名：
		String filename = file.getOriginalFilename();
		// 获得输入流：
		InputStream input = file.getInputStream();
		// 写入文件

		// 或者：
		File source = new File("E://test.png");
		file.transferTo(source);
		//		createPreviewImage("E://test.png", "E://test2.png");
		UploadDto uploadDto = new UploadDto();
		uploadDto.setUrl(
				"http://img.blog.csdn.net/20140912102539792?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvY2Fyb2x6aGFuZzg0MDY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center");

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{\"code\":1,\"msg\":\""+uploadDto.getUrl()+"\"}");
	}

	public static void createPreviewImage(String srcFile, String destFile) {
		try {
			File fi = new File(srcFile); // src
			File fo = new File(destFile); // dest
			BufferedImage bis = ImageIO.read(fi);

			int w = bis.getWidth();
			int h = bis.getHeight();
			double scale = (double) w / h;
			int nw = 120; // final int IMAGE_SIZE = 120;
			int nh = (nw * h) / w;
			if (nh > 120) {
				nh = 120;
				nw = (nh * w) / h;
			}
			double sx = (double) nw / w;
			double sy = (double) nh / h;

			AffineTransform transform = new AffineTransform();
			transform.setToScale(sx, sy);
			AffineTransformOp ato = new AffineTransformOp(transform, null);
			BufferedImage bid = new BufferedImage(nw, nh,
					BufferedImage.TYPE_3BYTE_BGR);
			ato.filter(bis, bid);
			ImageIO.write(bid, " jpeg ", fo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					" Failed in create preview image. Error:  "
							+ e.getMessage());
		}
	}
}
