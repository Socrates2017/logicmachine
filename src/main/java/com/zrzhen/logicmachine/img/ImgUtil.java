package com.zrzhen.logicmachine.img;

public class ImgUtil {

    public static int[] handleByCommon(int[] inPixelsData, int srcW, int srcH, int destW, int destH) {
        int[][][] outputThreeDeminsionData = new int[destH][destW][4];
        float rowRatio = ((float) srcH) / ((float) destH);
        float colRatio = ((float) srcW) / ((float) destW);

        for (int row = 0; row < destH; row++) {

            double srcRow = ((float) row) * rowRatio;
            double j = Math.floor(srcRow);
            double t = srcRow - j;

            for (int col = 0; col < destW; col++) {

                double srcCol = ((float) col) * colRatio;
                double k = Math.floor(srcCol);
                double u = srcCol - k;

                double coffiecent1 = (1.0d - t) * (1.0d - u);
                double coffiecent2 = (t) * (1.0d - u);
                double coffiecent3 = t * u;
                double coffiecent4 = (1.0d - t) * u;

                for (int i = 0; i < 4; i++) {
                    outputThreeDeminsionData[row][col][i] = (int) (
                            coffiecent1 * getIndexPx(inPixelsData, srcW, (int) j, (int) k, i) +
                                    coffiecent2 * getIndexPx(inPixelsData, srcW, (int) (j + 1), (int) k, i) +
                                    coffiecent3 * getIndexPx(inPixelsData, srcW, (int) (j + 1), (int) (k + 1), i) +
                                    coffiecent4 * getIndexPx(inPixelsData, srcW, (int) j, (int) (k + 1), i)
                    );

                }
            }
        }
        return convertToOneDim(outputThreeDeminsionData,destW,destH);
    }


    private static double getIndexPx(int[] inPixelsData, int srcw, int h, int w, int index) {
        double result = 0.0d;
        switch (index) {
            case 0:
                //alpha
                result = (inPixelsData[(h * srcw) + w] >> 24) & 0xFF;
                break;
            case 1:
                //red
                result = (inPixelsData[(h * srcw) + w] >> 16) & 0xFF;
            case 2:
                //green
                result = (inPixelsData[(h * srcw) + w] >> 8) & 0xFF;
            case 3:
                //blue
                result = inPixelsData[(h * srcw) + w] & 0xFF;
        }
        return result;
    }
    public static int[] convertToOneDim(int[][][] data, int imgCols, int imgRows) {
        int[] oneDPix = new int[imgCols * imgRows];
        for (int row = 0, cnt = 0; row < imgRows; row++) {
            for (int col = 0; col < imgCols; col++) {
                //ABGR-->argb
                oneDPix[cnt] =
                        //alpha
                        ((data[row][col][0] << 24) & 0xFF000000)
                                | ((data[row][col][1] << 16) & 0x00FF0000)
                                | ((data[row][col][2] << 8) & 0x0000FF00)
                                | ((data[row][col][3]) & 0x000000FF);

                cnt++;
            }
        }
        return oneDPix;
    }

}













