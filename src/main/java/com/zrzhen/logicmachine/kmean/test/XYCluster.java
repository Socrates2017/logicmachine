package com.zrzhen.logicmachine.kmean.test;

import com.zrzhen.logicmachine.util.JsonUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

public class XYCluster extends KMeansBase<XYPoint> {
    @Override
    public double similarScore(XYPoint o1, XYPoint o2) {
        double distance = Math.sqrt((o1.getX() - o2.getX()) * (o1.getX() - o2.getX()) + (o1.getY() - o2.getY()) * (o1.getY() - o2.getY()));
        return distance * -1;
    }

    @Override
    public boolean equals(XYPoint o1, XYPoint o2) {
        return o1.getX() == o2.getX() && o1.getY() == o2.getY();
    }

    @Override
    public XYPoint getCenterT(List<XYPoint> list) {
        int x = 0;
        int y = 0;
        try {
            for (XYPoint xy : list) {
                x += xy.getX();
                y += xy.getY();
            }
            x = x / list.size();
            y = y / list.size();
        } catch (Exception e) {

        }
        return new XYPoint(x, y);
    }


    public static void main(String[] args) {

        int width = 600;
        int height = 400;
        int K = 10;
        int maxTimes = 10;
        int dataSetSize = 20000;
        int pointR = 5;

        XYCluster xyCluster = new XYCluster();
        xyCluster.setK(K);
        xyCluster.setMaxTimes(maxTimes);

        for (int i = 0; i < dataSetSize; i++) {
            int x = (int) (Math.random() * width) + 1;
            int y = (int) (Math.random() * height) + 1;
            xyCluster.addRecord(new XYPoint(x, y));
        }

        long a = System.currentTimeMillis();
        List<List<XYPoint>> cresult = xyCluster.clustering();
        List<XYPoint> center = xyCluster.getCenter();

        System.out.println(JsonUtil.entity2Json(center));
        long b = System.currentTimeMillis();
        System.out.println("耗时：" + (b - a) + "ms");
        try {
            byte[] bytes = ImgUtil.drawXYbeans(cresult, center, width, height, pointR);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(in);
            ImageIO.write(image, "png", new File("D:\\验证码3.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
