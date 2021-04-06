package com.study.algorithms.sort.vo;

/**
 * Young氏矩阵
 */
public class YoungTableau {

    // 列数
    private int line;
    private int[] youngArr;
    private int youngSize;

    public YoungTableau(int line, int[] numArr) {
        if (line <= 0) {
            line = 1;
        }
        this.line = line;
        if (null == numArr) {
            numArr = new int[0];
        }
        youngSize = numArr.length;
        youngArr = new int[youngSize];
        for (int i = youngArr.length - 1; i >= 0; i--) {
            youngArr[i] = numArr[i];
            minHeapify(i);
        }
    }

    public int down(int index) {
        return index + line;
    }

    public int right(int index) {
        return index + 1;
    }

    public int top(int index) {
        return index - line;
    }

    public int left(int index) {
        return index - 1;
    }

    public boolean isLeft(int index) {
        return index % line == 0;
    }

    public boolean isRight(int index) {
        return (index + 1) % line == 0;
    }

    public int getYoungSize() {
        return this.youngSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < getYoungSize(); i++) {
            sb.append(youngArr[i]).append("\t");
            if (isRight(i)) {
                sb.append("\n");
            }
        }
        if (sb.toString().endsWith("\\n")) {
            sb.setLength(sb.length() - 2);
        }
        if (sb.toString().endsWith("\\t")) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    public int extractMin() {
        int result = youngArr[0];
        youngArr[0] = youngArr[getYoungSize() - 1];
        youngSize--;
        minHeapify(0);
        return result;
    }

    public void minHeapify(int index) {
        if (index < 0 || index >= getYoungSize()) {
            return ;
        }

        int ifyNum = youngArr[index];
        while (true) {
            int min = ifyNum;
            int minIndex = index;

            if (!isRight(index) && right(index) < getYoungSize() && youngArr[right(index)] < min) {
                min = youngArr[right(index)];
                minIndex = right(index);
            }

            if (down(index) < getYoungSize() && youngArr[down(index)] < min) {
                min = youngArr[down(index)];
                minIndex = down(index);
            }

            if (index == minIndex) {
                break;
            }

            youngArr[index] = min;
            index = minIndex;

        }

        youngArr[index] = ifyNum;

    }


    // TODO LILK 插入逻辑，二叉树的插入逻辑重新想一想

    public static void main(String[] args) {
        int[] numArr = new int[30];
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = (int) (Math.random() * 20);
        }
        YoungTableau youngTableau = new YoungTableau(5, numArr.clone());
        System.out.println(youngTableau.toString());
        System.out.println(youngTableau.extractMin());
        System.out.println(youngTableau.toString());
    }


}
