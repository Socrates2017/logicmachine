package com.zrzhen.logicmachine.img;

import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.File;
import java.io.IOException;

public class Example {

    public static void main(String[] args) throws IOException {

        img2Gray();
    }

    public static void img2Gray() throws IOException {
        String filename ="D:\\XY.jpg";
        BufferedImage image;
        File f = new File(filename);
        image = ImageIO.read(f);

        int w = image.getWidth();
        int h = image.getHeight();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int ARGB = image.getRGB(j, i);
                int A = (ARGB >> 24) & 0xFF;
                int R = (ARGB >> 16) & 0xFF;
                int G = (ARGB >> 8) & 0xFF;
                int B = ARGB & 0xFF;

                int gray = (int) (R * 0.3 + G * 0.59 + B * 0.11);

                int ARGB2 = ((A << 24) & 0xFF000000)
                        | ((gray << 16) & 0x00FF0000)
                        | ((gray << 8) & 0x0000FF00)
                        | (gray & 0x000000FF);

                image.setRGB(j, i, ARGB2);
            }
        }
        ImageIO.write(image, "png", new File("D:\\XYGray.jpg"));

    }

}