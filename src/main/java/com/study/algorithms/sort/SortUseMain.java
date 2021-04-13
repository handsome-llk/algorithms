package com.study.algorithms.sort;

import com.study.common.TimeRecord;

public class SortUseMain {

    public static void main(String[] args) {
//        findMaxChild();
//        calMatrix();
    }

    private static void calMatrix() {
        int n = (int) Math.pow(2, 10);
        int[][] a = createRandomMatrix(n);
        int[][] b = createRandomMatrix(n);
        calMatrixN3(a.clone(), b.clone());
    }

    private static void calMatrixN3(int[][] a, int[][] b) {
        TimeRecord record = new TimeRecord();
        int[][] c = SortUseUtil.getInstance().calMatrix(a, b);
        record.recordStop();
        SortUseUtil.getInstance().sysMatrix(c);
        record.timeCon("calMatricN3");
    }

    private static int[][] createRandomMatrix(int n) {
        if (n <= 0) {
            return new int[0][0];
        }
        int[][] matrix = new int[n][n];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * 100);
            }
        }
        return matrix;
    }

    private static void findMaxChild() {
//        int[] numArr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int[] numArr = new int[500000];
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = (int) (Math.random() * 100);
            if (Math.random() <0.5) {
                numArr[i] = -numArr[i];
            }
        }
        SortUtil.getInstance().sysArr(numArr, "initArr");
        findMaxChild(numArr.clone());
//        findMaxChildN2(numArr.clone());
        findMaxChildN(numArr.clone());
    }

    /**
     * 线性查找最大子数组
     * @param numArr
     */
    public static long findMaxChildN(int[] numArr) {
        if (null == numArr || numArr.length == 0) {
            System.out.println(0);
            return 0;
        }
        TimeRecord record = new TimeRecord();
        SortUseUtil.MaxChild maxChild = SortUseUtil.getInstance().findMaxChildN(numArr);
        record.recordStop("findMaxChildN");
        SortUseUtil.getInstance().sysMaxChild(maxChild);
        return record.timeCon();
    }

    /**
     * 暴力求解最大子数组
     * @param numArr
     */
    public static long findMaxChildN2(int[] numArr) {
        if (null == numArr || numArr.length == 0) {
            System.out.println(0);
            return 0;
        }
        TimeRecord record = new TimeRecord();
        SortUseUtil.MaxChild maxChild = SortUseUtil.getInstance().findMaxChildN2(numArr);
        record.recordStop("findMaxChildN2");
        SortUseUtil.getInstance().sysMaxChild(maxChild);
        return record.timeCon();
    }

    /**
     * 寻找最大子数组
     * @param numArr
     */
    public static long findMaxChild(int[] numArr) {
        if (null == numArr || numArr.length == 0) {
            System.out.println(0);
            return 0;
        }
        TimeRecord record = new TimeRecord();
        SortUseUtil.MaxChild maxChild = SortUseUtil.getInstance().findMaxChild(numArr, 0, numArr.length - 1);
        record.recordStop("findMaxChild");
        SortUseUtil.getInstance().sysMaxChild(maxChild);
        return record.timeCon();
    }

}
