package cn.com.tsjx.common.util.lang;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public final class ImageUtil {

	/**
	 * 产生验证码图片流
	 * @param code 编码
	 * @return 图片流
	 * @throws IOException
	 */
	public static InputStream createImage(String code) throws IOException {
		int width = code.length() * 15, height = 25;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.drawRect(0, 0, width, height);
		graphics.setColor(Color.WHITE);
		graphics.fillRect(1, 1, width, height);
		Random random = new Random();
		for (int i = 0; i < code.length() * 15; i++) {
			int x = random.nextInt(width - 2) + 1;
			int y = random.nextInt(height - 2) + 1;
			graphics.setColor(new Color(0xDC, 0xDC, 0xDC));
			graphics.drawRect(x, y, 1, 1);
		}
		for (int i = 0; i < code.length(); i++) {
			String rand = code.substring(i, i + 1);
			graphics.setFont(new Font(null, random.nextInt(4), 20));
			graphics.setColor(randomColor());
			graphics.drawString(rand, 13 * i + 6, 19);
		}
		graphics.dispose();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "JPEG", outputStream);
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
	
	/**
	 * 产生验证码图片流
	 * @param code 编码
	 * @return 图片流
	 * @throws IOException
	 */
	public static void createImage(String code,OutputStream outputStream) throws IOException {
		int width = code.length() * 15, height = 25;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.drawRect(0, 0, width, height);
		graphics.setColor(Color.WHITE);
		graphics.fillRect(1, 1, width, height);
		Random random = new Random();
		for (int i = 0; i < code.length() * 15; i++) {
			int x = random.nextInt(width - 2) + 1;
			int y = random.nextInt(height - 2) + 1;
			graphics.setColor(new Color(0xDC, 0xDC, 0xDC));
			graphics.drawRect(x, y, 1, 1);
		}
		for (int i = 0; i < code.length(); i++) {
			String rand = code.substring(i, i + 1);
			graphics.setFont(new Font(null, random.nextInt(4), 20));
			graphics.setColor(randomColor());
			graphics.drawString(rand, 13 * i + 6, 19);
		}
		graphics.dispose();
		ImageIO.write(image, "JPEG", outputStream);
	}

	private static Color randomColor() {
		Random random = new Random();
		int r = random.nextInt(0xFF - 0x32) + 0x32;
		int g = random.nextInt(0x98 - 0x27) + 0x27;
		int b = random.nextInt(0xB0 - 0x01) + 0x01;
		return new Color(r, g, b);
	}
}
