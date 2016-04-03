/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: DemoConcroller.java
 * Author:   muxing
 * Date:    2016/3/22 0:37
 * Description:
 */
package cn.com.tsjx.demo;

import cn.com.tsjx.common.util.json.JsonMapper;
import cn.com.tsjx.notice.entity.Notice;
import cn.com.tsjx.notice.service.NoticeService;
import cn.com.tsjx.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

	// 写入文件
	@Value("${file.uplaoddir}")
	String path;

	@Resource
	NoticeService noticeService;

	@RequestMapping(value = "/search")
	public String search() {
		return "/wap/search";
	}

	@RequestMapping(value = "/companyInfo")
	public String companyInfo() {
		return "/wap/company-info";
	}

	@RequestMapping(value = "/message")
	public String message(Model model, HttpSession httpSession) {
		Notice notice = new Notice();
		User user = (User) httpSession.getAttribute("user");
		notice.setUserId(user.getId());
		List<Notice> notices = noticeService.getUserAndAdminNotice(notice);
		model.addAttribute("notices", notices);
		return "/wap/message";
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

	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
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
		String path = "E:\\木星\\资料大全\\tsjx\\WebRoot";

		//当前月份
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

		File mkFile = new File(path + "/" + sdf.format(d));
		if (!mkFile.exists()) {
			mkFile.mkdir();
		}
		String fileEnd = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
		String src = "/images/" + sdf.format(d) + "/" + d.getTime() + "." + fileEnd;
		File source = new File(path + src);
		file.transferTo(source);
		//		createPreviewImage("E://test.png", "E://test2.png");
		UploadDto uploadDto = new UploadDto();
		uploadDto.setUrl(src);

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("{\"code\":1,\"url\":\"" + uploadDto.getUrl() + "\"}");
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
