package com.zrzhen.logicmachine.kmean.test;

import com.zrzhen.logicmachine.util.JsonUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

public class XYZCluster extends KMeansBase<XYZPoint> {
    @Override
    public double similarScore(XYZPoint o1, XYZPoint o2) {
        double distance = Math.sqrt(
                (o1.getX() - o2.getX()) * (o1.getX() - o2.getX()) +
                        (o1.getY() - o2.getY()) * (o1.getY() - o2.getY()) +
                        (o1.getZ() - o2.getZ()) * (o1.getZ() - o2.getZ()));
        return distance * -1;
    }

    @Override
    public boolean equals(XYZPoint o1, XYZPoint o2) {
        return o1.getX() == o2.getX() && o1.getY() == o2.getY() && o1.getZ() == o2.getZ();
    }

    @Override
    public XYZPoint getCenterT(List<XYZPoint> list) {
        int x = 0;
        int y = 0;
        int z = 0;
        try {
            for (XYZPoint xy : list) {
                x += xy.getX();
                y += xy.getY();
                z += xy.getZ();
            }
            x = x / list.size();
            y = y / list.size();
            z = z / list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new XYZPoint(x, y, z);
    }


    public static void main(String[] args) {

        int xWidth = 600;
        int yWidth = 600;
        int zWidth = 600;

        int K = 10;
        int maxTimes = 100;
        int dataSetSize = 20000;
        int pointR = 10;

        XYZCluster xyCluster = new XYZCluster();
        xyCluster.setK(K);
        xyCluster.setMaxTimes(maxTimes);

        for (int i = 0; i < dataSetSize; i++) {
            int x = (int) (Math.random() * xWidth) + 1;
            int y = (int) (Math.random() * yWidth) + 1;
            int z = (int) (Math.random() * zWidth) + 1;
            xyCluster.addRecord(new XYZPoint(x, y, z));
        }

        long a = System.currentTimeMillis();
        List<List<XYZPoint>> cresult = xyCluster.clustering();
        List<XYZPoint> center = xyCluster.getCenter();

        System.out.println(JsonUtil.entity2Json(center));
        long b = System.currentTimeMillis();
        System.out.println("训练耗时：" + (b - a) + "ms");
        try {
            byte[] bytes = ImgUtil.drawXYZ(cresult, center, "x", "y", xWidth, yWidth, pointR);
            String xyPath = "D:\\XY.jpg";
            saveImg(bytes, xyPath);


            byte[] bytesXZ = ImgUtil.drawXYZ(cresult, center, "x", "Z", xWidth, yWidth, pointR);
            String xZPath = "D:\\XZ.jpg";
            saveImg(bytesXZ, xZPath);

            byte[] bytesYZ = ImgUtil.drawXYZ(cresult, center, "Y", "Z", xWidth, yWidth, pointR);
            String YZPath = "D:\\YZ.jpg";
            saveImg(bytesYZ, YZPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
        long c = System.currentTimeMillis();
        System.out.println("绘图耗时：" + (c - b) + "ms");
    }

    private static void saveImg(byte[] bytes, String path) {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        BufferedImage image = null;
        try {
            image = ImageIO.read(in);
            ImageIO.write(image, "png", new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
