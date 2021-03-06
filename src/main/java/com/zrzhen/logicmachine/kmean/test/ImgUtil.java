package com.zrzhen.logicmachine.kmean.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * @author chenanlian
 */
public class ImgUtil {

    private static final Logger log = LoggerFactory.getLogger(ImgUtil.class);


    private static Random random = new Random();

    public static byte[] drawXYZ(List<List<XYZPoint>> pointList, List<XYZPoint> center, String d1, String d2, int d1width, int d2height, int pointR) {
        //默认背景为黑色
        BufferedImage image = new BufferedImage(d1width, d2height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics graphics = image.getGraphics();
        //默认填充为白色
        graphics.fillRect(0, 0, d1width, d2height);

        int k = pointList.size();
        Color[] colors = getColorList(k);

        for (int i = 0; i < k; i++) {
            List<XYZPoint> XYPointList = pointList.get(i);
            graphics.setColor(colors[i]);
            for (XYZPoint xYZPoint : XYPointList) {

                int x = getValue(xYZPoint, d1);
                int y = getValue(xYZPoint, d2);

                //graphics.drawOval(x, y, pointR, pointR);
                graphics.drawOval(x, y, pointR, pointR);
            }
        }

        //绘制中心
        Color centerColor = new Color(0, 0, 0);
        graphics.setColor(centerColor);
        for (XYZPoint xYZPoint : center) {
            int x = getValue(xYZPoint, d1);
            int y = getValue(xYZPoint, d2);
            graphics.fillOval(x, y, pointR, pointR);
        }

        byte[] out = null;
        //format:图片格式，“gif"等；
        //out:目标；特别的，如果目标为byte数组，则将其预设为ByteArrayOutputStream即可传入此方法，执行完后，只要toByteArray()即可获得byte[]
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {
            ImageIO.write(image, "png", byteArrayOutputStream);
            out = byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return out;
    }


    private static int getValue(XYZPoint xYZPoint, String d1) {
        int result = 0;
        switch (d1.toUpperCase()) {
            case "X":
                result = (int) xYZPoint.getX();
                break;
            case "Y":
                result = (int) xYZPoint.getY();
                break;

            default:
                result = (int) xYZPoint.getZ();
        }
        return result;
    }


    public static byte[] drawXYbeans(List<List<XYPoint>> cresult, List<XYPoint> center, int width, int height, int pointR) {
        //默认背景为黑色
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics graphics = image.getGraphics();
        //默认填充为白色
        graphics.fillRect(0, 0, width, height);

        int k = cresult.size();
        Color[] colors = getColorList(k);

        for (int i = 0; i < k; i++) {
            List<XYPoint> XYPointList = cresult.get(i);
            graphics.setColor(colors[i]);
            for (XYPoint xYPoint : XYPointList) {
                int x = (int) xYPoint.getX();
                int y = (int) xYPoint.getY();

                //graphics.drawOval(x, y, pointR, pointR);
                graphics.fillOval(x, y, pointR, pointR);
            }
        }

        //绘制中心
        Color centerColor = new Color(0, 0, 0);
        graphics.setColor(centerColor);
        for (XYPoint xYPoint : center) {
            int x = (int) xYPoint.getX();
            int y = (int) xYPoint.getY();
            graphics.fillOval(x, y, pointR, pointR);
        }

        byte[] out = null;
        //format:图片格式，“gif"等；
        //out:目标；特别的，如果目标为byte数组，则将其预设为ByteArrayOutputStream即可传入此方法，执行完后，只要toByteArray()即可获得byte[]
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {
            ImageIO.write(image, "png", byteArrayOutputStream);
            out = byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return out;
    }

    public static Color[] getColorList(int k) {
        Color[] result = new Color[k];
        for (int i = 0; i < k; i++) {
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            result[i] = color;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

        byte[] bytes = null;//createCode();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        BufferedImage image = ImageIO.read(in);
        ImageIO.write(image, "png", new File("D:\\验证码3.jpg"));

    }
}
