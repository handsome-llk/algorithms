package com.study.algorithms.sort;

import com.study.common.TimeRecord;

import java.util.Arrays;
import java.util.Random;

public class SortMain {

    public static void main(String[] args) {
        int[] numArr = new int[500000];
        Random random = new Random();
//        for (int i = 0; i < numArr.length; i++) {
////            numArr[i] = random.nextInt(100);
//            numArr[i] = i;
//        }
        for (int i = numArr.length - 1; i >= 0; i--) {
            numArr[i] = i;
        }
        SortUtil.getInstance().sysArr(numArr, "initArr");
        int[] answerArr = numArr.clone();
        answerSort(answerArr);
//        insertSort(numArr.clone(), answerArr);
        mergeSort(numArr.clone(), answerArr);
//        insertDiviSort(numArr.clone(), answerArr);
        maxHeapSort(numArr.clone(), answerArr);
    }

    private static void maxHeapSort(int[] numArr, int[] answerArr) {
        TimeRecord record = new TimeRecord();
        SortUtil.getInstance().heapSort(numArr);
        record.recordStop("maxHeapSort");
        System.out.println(SortUtil.getInstance().isRight(numArr, answerArr));
        SortUtil.getInstance().sysArr(numArr, "maxHeapSort");
    }

    private static void insertDiviSort(int[] numArr, int[] answerArr) {
        TimeRecord record = new TimeRecord();
        SortUtil.getInstance().insertDiviSort(numArr);
        record.recordStop("insertDiviSort");
        System.out.println(SortUtil.getInstance().isRight(numArr, answerArr));
        SortUtil.getInstance().sysArr(numArr, "insertDiviSort");
    }

    @Deprecated
    private static void insertRecursionSort(int[] numArr, int[] answerArr) {
        TimeRecord record = new TimeRecord();
        SortUtil.getInstance().insertRecursionSort(numArr);
        record.recordStop("insertRecursionSort");
        System.out.println(SortUtil.getInstance().isRight(numArr, answerArr));
        SortUtil.getInstance().sysArr(numArr, "insertRecursionSort");

    }

    private static void mergeSort(int[] numArr, int[] answerArr) {
        TimeRecord record = new TimeRecord();
        SortUtil.getInstance().mergeAscSort(numArr);
        record.recordStop("mergeSort");
        System.out.println(SortUtil.getInstance().isRight(numArr, answerArr));
        SortUtil.getInstance().sysArr(numArr, "mergeSort");
    }

    private static void insertSort(int[] numArr, int[] answerArr) {
        TimeRecord record = new TimeRecord();
        SortUtil.getInstance().insertAscSort(numArr);
        record.recordStop("insertSort");
        System.out.println(SortUtil.getInstance().isRight(numArr, answerArr));
        SortUtil.getInstance().sysArr(numArr, "insertSort");
    }

    private static void answerSort(int[] numArr) {
        TimeRecord record = new TimeRecord();
        Arrays.sort(numArr);
        record.recordStop("answerSort");
        SortUtil.getInstance().sysArr(numArr, "answerSort");
    }

}
