package com.qiniu;

/**
 * Created by Administrator on 2016/4/12.
 */

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import com.sun.image.codec.jpeg.*;

public class WaterSet {
    /**
     * 给图片添加水印
     *
     * @param filePath         需要添加水印的图片的路径
     * @param markContent      水印的文字
     * @param markContentColor 水印文字的颜色
     * @param qualNum          图片质量
     * @return
     */
    public boolean createMark(String filePath, String markContent,
                              Color markContentColor, float qualNum) {
        ImageIcon imgIcon = new ImageIcon(filePath);
        Image theImg = imgIcon.getImage();
        int width = theImg.getWidth(null);
        int height = theImg.getHeight(null);
        BufferedImage bimage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bimage.createGraphics();
        g.setColor(markContentColor);
        g.setBackground(Color.white);
        g.drawImage(theImg, 0, 0, null);
        g.drawString(markContent, width / 5, height / 5); // 添加水印的文字和设置水印文字出现的内容
        g.dispose();
        try {
            FileOutputStream out = new FileOutputStream(filePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
            param.setQuality(qualNum, true);
            encoder.encode(bimage, param);
            out.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        pressImage("d:/watermark.png","d:/12.jpg",5,1);
    }

    /**
     * 图片水印
     *
     * @param pressimg  水印图片
     * @param targetimg 目标图片
     * @param location  位置：1、左上角，2、右上角，3、左下角，4、右下角，5、正中间
     * @param alpha     透明度
     */
    public static void pressImage(String pressimg, String targetimg, int location, float alpha) {
        try {  //读取目标图片
            File img = new File(targetimg);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            //水印文件
            Image src_biao = ImageIO.read(new File(pressimg));

            int width_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);  //如果水印图片高或者宽大于目标图片是做的处理,使其水印宽或高等于目标图片的宽高，并且等比例缩放
            int new_width_biao = width_biao;
            int new_height_biao = height_biao;
            if (width_biao > width) {
                new_width_biao = width;
                new_height_biao = (int) ((double) new_width_biao / width_biao * height);
            }
            if (new_height_biao > height) {
                new_height_biao = height;
                new_width_biao = (int) ((double) new_height_biao / height_biao * new_width_biao);
            }
            //根据位置参数确定坐标位置
            int x = 0;
            int y = 0;
            switch (location) {
                case 1:
                    break;
                case 2:
                    x = width - new_width_biao;
                    break;
                case 3:
                    y = height - new_height_biao;
                    break;
                case 4:
                    x = width - new_width_biao;
                    y = height - new_height_biao;
                    break;
                case 5:
                    x = (width - new_width_biao) / 2;
                    y = (height - new_height_biao) / 2;
                    break;
                default:
                    break;
            }
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            g.drawImage(src_biao, x, y, new_width_biao, new_height_biao, null);

            //水印文件结束
            g.dispose();
            ImageIO.write(image, "png", img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}