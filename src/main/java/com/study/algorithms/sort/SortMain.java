package com.study.algorithms.sort;

import com.study.common.TimeRecord;

import java.util.Arrays;
import java.util.Random;

public class SortMain {

    public static void main(String[] args) {
        int[] numArr = new int[50000];
        Random random = new Random();
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = random.nextInt(100);
        }
        SortUtil.getInstance().sysArr(numArr, "initArr");
        answerSort(numArr.clone());
        insertSort(numArr.clone());
        mergeSort(numArr.clone());
        insertDiviSort(numArr.clone());
    }

    private static void insertDiviSort(int[] numArr) {
        TimeRecord record = new TimeRecord();
        SortUtil.getInstance().insertDiviSort(numArr);
        record.recordStop("insertDiviSort");
        SortUtil.getInstance().sysArr(numArr, "insertDiviSort");
    }

    @Deprecated
    private static void insertRecursionSort(int[] numArr) {
        TimeRecord record = new TimeRecord();
        SortUtil.getInstance().insertRecursionSort(numArr);
        record.recordStop("insertRecursionSort");
        SortUtil.getInstance().sysArr(numArr, "insertRecursionSort");
    }

    private static void mergeSort(int[] numArr) {
        TimeRecord record = new TimeRecord();
        SortUtil.getInstance().mergeAscSort(numArr);
        record.recordStop("mergeSort");
        SortUtil.getInstance().sysArr(numArr, "mergeSort");
    }

    private static void insertSort(int[] numArr) {
        TimeRecord record = new TimeRecord();
        SortUtil.getInstance().insertAscSort(numArr);
        record.recordStop("insertSort");
        SortUtil.getInstance().sysArr(numArr, "insertSort");
    }

    private static void answerSort(int[] numArr) {
        TimeRecord record = new TimeRecord();
        Arrays.sort(numArr);
        record.recordStop("answerSort");
        SortUtil.getInstance().sysArr(numArr, "answerSort");
    }

}
