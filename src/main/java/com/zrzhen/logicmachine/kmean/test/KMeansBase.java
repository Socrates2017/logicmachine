package com.zrzhen.logicmachine.kmean.test;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class KMeansBase<T> {

    /**
     * 待分类的数据
     */
    private List<T> inputData;
    /**
     * 要分的类别数量
     */
    private int K = 2;
    /**
     * 迭代的最大次数
     */
    private int maxTimes = 10;
    /**
     * 输出
     */
    private List<List<T>> outputData;
    /**
     * 质心
     */
    private List<T> center;

    public int getK() {
        return K;
    }

    public void setK(int K) {
        if (K < 1) {
            throw new IllegalArgumentException("K must greater than 0");
        }
        this.K = K;
    }

    public int getMaxTimes() {
        return maxTimes;
    }

    public void setMaxTimes(int maxTimes) {
        if (maxTimes < 10) {
            //throw new IllegalArgumentException("maxTimes must greater than 10");
        }
        this.maxTimes = maxTimes;
    }

    public List<T> getCenter() {
        return center;
    }

    /**
     * @Description: 对数据进行聚类
     */
    public List<List<T>> clustering() {
        if (inputData == null) {
            return null;
        }
        //初始K个点为数组中的前K个点
        int size = K > inputData.size() ? inputData.size() : K;
        List<T> centerT = new ArrayList<T>(size);
        //对数据进行打乱
        Collections.shuffle(inputData);

        for (int i = 0; i < size; i++) {
            centerT.add(inputData.get(i));
        }
        clustering(centerT, 0);
        return outputData;
    }

    /**
     * @Description: 一轮聚类
     */
    private void clustering(List<T> preCenter, int times) {
        if (preCenter == null || preCenter.size() < 2) {
            return;
        }
        //打乱质心的顺序
        Collections.shuffle(preCenter);
        List<List<T>> outputData = getListT(preCenter.size());
        for (T o1 : this.inputData) {
            //寻找最相似的质心
            int max = 0;
            double maxScore = similarScore(o1, preCenter.get(0));
            for (int i = 1; i < preCenter.size(); i++) {
                double maxScoreI = similarScore(o1, preCenter.get(i));
                if (maxScore < maxScoreI) {
                    maxScore = maxScoreI;
                    max = i;
                }
            }
            outputData.get(max).add(o1);
        }
        //计算本次聚类结果每个类别的质心
        List<T> nowCenter = new ArrayList<T>();
        for (List<T> list : outputData) {
            nowCenter.add(getCenterT(list));
        }
        //是否达到最大迭代次数
        if (times >= this.maxTimes || preCenter.size() < this.K) {
            this.outputData = outputData;
            return;
        }
        this.center = nowCenter;
        //判断质心是否发生移动，如果没有移动，结束本次聚类，否则进行下一轮
        if (isCenterChange(preCenter, nowCenter)) {
            clear(outputData);
            clustering(nowCenter, times + 1);
        } else {
            this.outputData = outputData;
        }
    }

    /**
     * @Description: 初始化一个聚类结果
     */
    private List<List<T>> getListT(int size) {
        List<List<T>> list = new ArrayList<List<T>>(size);
        for (int i = 0; i < size; i++) {
            list.add(new ArrayList<T>());
        }
        return list;
    }

    /**
     * @Description: 清空无用数组
     */
    private void clear(List<List<T>> lists) {
        for (List<T> list : lists) {
            list.clear();
        }
        lists.clear();
    }

    /**
     * @Description: 向模型中添加记录
     */
    public void addRecord(T value) {
        if (inputData == null) {
            inputData = new ArrayList<T>();
        }
        inputData.add(value);
    }

    /**
     * @Description: 判断质心是否发生移动
     */
    private boolean isCenterChange(List<T> preT, List<T> nowT) {
        if (preT == null || nowT == null) {
            return false;
        }
        for (T t1 : preT) {
            boolean bol = true;
            for (T t2 : nowT) {
                if (equals(t1, t2)) {//t1在t2中有相等的，认为该质心未移动
                    bol = false;
                    break;
                }
            }
            //有一个质心发生移动，认为需要进行下一次计算
            if (bol) {
                return bol;
            }
        }
        return false;
    }

    /**
     * @Description: o1 o2之间的相似度
     */
    public abstract double similarScore(T o1, T o2);

    /**
     * @Description: 判断o1 o2是否相等
     */
    public abstract boolean equals(T o1, T o2);

    /**
     * @Description: 求一组数据的质心
     */
    public abstract T getCenterT(List<T> list);
}
