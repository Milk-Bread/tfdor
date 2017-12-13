package com.tfdor.tools.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author chepeiqing
 * @Mail chepeiqin@icloud.com
 * @Date 2017/1/22
 * @Time 下午5:21
 * @version V1.0.0
 */
public class Demo {


    /**
     * 加载本地图片
     * @param imgName
     * @return
     */
    public static BufferedImage loadImageLocal(String imgName) {
        try {
            return ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 生成新图片到本地
     */
    public static void writeImageLocal(String newImage, BufferedImage img) {
        if (newImage != null && img != null) {
            try {
                File outputfile = new File(newImage);
                ImageIO.write(img, "jpg", outputfile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void main(String [] argo) throws Exception {
        BufferedImage img1 = loadImageLocal("/Users/chepeiqing/Desktop/WeChat/images/20170102164158/14028286022017010216415910126324.jpg");
        BufferedImage img2 = loadImageLocal("/Users/chepeiqing/Desktop/微信开发/微信认证/WechatIMG1.png");
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();
        int w2 = img2.getWidth();
        int h2 = img2.getHeight();

        // 从图片中读取RGB
        int[] ImageArrayOne = new int[w1 * h1];
        ImageArrayOne = img1.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
        int[] ImageArrayTwo = new int[w2 * h2];
        ImageArrayTwo = img2.getRGB(0, 0, w2, h2, ImageArrayTwo, 0, w2);
        // 生成新图片
        BufferedImage DestImage = null;
        DestImage = new BufferedImage(w1,h1,BufferedImage.TYPE_INT_RGB);

        DestImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
        // 加载水印图片文件
        Graphics2D resizedG = DestImage.createGraphics();
        resizedG.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,(float) 1));
        resizedG.drawImage(img2, w1 / 2 - w2 / 2, h1 / 2 - h2 / 2, null);
        resizedG.dispose();

        writeImageLocal("/Users/chepeiqing/Desktop/WeChat/config/test.jpg",DestImage);
    }
}
