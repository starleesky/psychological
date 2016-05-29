package cn.com.tsjx.util;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.util.ImageTool;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.FlatColorBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;


@Service("simpleCaptcha")
public class SimpleCaptcha {

    
    private static Random random = new Random();
    //宽度
	private static int _width = 90;
	//高度
	private static int _height = 35;


	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @param sessionName
	 * @throws Exception
	 */
	public static void showCaptcha(HttpServletRequest request, HttpServletResponse response,
			String sessionName) {
	    List<Color> colors = new ArrayList<Color>();
        colors.add(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
                20 + random.nextInt(110)));
        colors.add(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
                20 + random.nextInt(110)));
        colors.add(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
                20 + random.nextInt(110)));
        colors.add(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),
                20 + random.nextInt(110)));
        
        List<Font> fonts = new ArrayList<Font>();
        fonts.add(new Font("Geneva", 1, 28));
        fonts.add(new Font("Courier", 2, 28));
        fonts.add(new Font("Arial",3, 28));
        fonts.add(new Font("Times New Roman",4, 28));
        //WordRenderer wordRenderer = new ColoredEdgesWordRenderer(colors, fonts);
        WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
        
        Captcha captcha = new Captcha.Builder(_width, _height).addText(wordRenderer).gimp(new DropShadowGimpyRenderer())
                .addBackground(new FlatColorBackgroundProducer(ImageTool.getRandColor(160, 200))).build();

        request.getSession().setAttribute(sessionName, captcha.getAnswer());
        System.out.println(captcha.getAnswer());
        CaptchaServletUtil.writeImage(response, captcha.getImage());
	}
	
	   
    public static String getSmsCode(HttpServletRequest request, HttpServletResponse response,
            String mobile){  
        Random rad=new Random();  
        
        String result  = rad.nextInt(1000000) +"";  
        if(result.length()!=6){  
            return getSmsCode(request,response,mobile);  
        }  
        System.out.println(result);
        //发短信
        TaobaioSmsUtil.send(mobile, result);
        //存session
        request.getSession().setAttribute("smsCode", result);
        return result;  
    }
    
    
}
