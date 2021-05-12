package com.study.algorithms.choose;

import com.study.algorithms.sort.SortMain;
import com.study.algorithms.sort.SortUtil;
import com.study.common.TimeRecord;

import java.util.Random;

public class ChooseTestMain {

    public static void main(String[] args) {
        int[] numArr = new int[500000];
        Random random = new Random();
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = random.nextInt(10000);
//            numArr[i] = i;
        }
//        for (int i = numArr.length - 1; i >= 0; i--) {
//            numArr[i] = i;
//        }
//        SortUtil.getInstance().sysArr(numArr, "initArr");
        int[] answerArr = numArr.clone();
        int randomIndex = random.nextInt(answerArr.length);
        System.out.println("randomIndex:" + randomIndex);
        SortMain.answerSort(answerArr);
        quickChoose(numArr.clone(), answerArr, randomIndex);
        quickLoopChoose(numArr.clone(), answerArr, randomIndex);
    }

    private static void quickChoose(int[] numArr, int[] answerArr, int index) {
        TimeRecord record = new TimeRecord();
        int chooseValue = ChooseUtil.getInstance().quickChoose(numArr, index);
        record.recordStop("quickChoose");
        System.out.println(chooseValue == answerArr[index]);
    }

    private static void quickLoopChoose(int[] numArr, int[] answerArr, int index) {
        TimeRecord record = new TimeRecord();
        int chooseValue = ChooseUtil.getInstance().quickLoopChoose(numArr, index);
        record.recordStop("quickLoopChoose");
        System.out.println(chooseValue == answerArr[index]);
    }

}
