package com.study.algorithms.choose;

import java.util.Random;

public class ChooseUtil {

    private ChooseUtil() {}
    private static final ChooseUtil chooseUtil = new ChooseUtil();
    public static ChooseUtil getInstance() {
        return chooseUtil;
    }

    public int quickLoopChoose(int[] numArr, int index) {
        if (null == numArr || index < 0) {
            return 0;
        }
        if (numArr.length == 1 || index >= numArr.length) {
            return numArr[numArr.length - 1];
        }

        numArr = numArr.clone();

        return quickLoopIndex(numArr, 0, numArr.length - 1, index);
    }

    private int quickLoopIndex(int[] numArr, int low, int high, int index) {
        while(true) {
            if (low == high) {
                return numArr[low];
            }
            int random = (int) (Math.random() * (high - low + 1)) + low;
            int randomValue = numArr[random];
            numArr[random] = numArr[high];
            numArr[high] = randomValue;
            int resultIndex = low;
            for (int i = low; i <= high; i++) {
                if (numArr[i] > randomValue) {
                    continue;
                }
                int changeValue = numArr[resultIndex];
                numArr[resultIndex] = numArr[i];
                numArr[i] = changeValue;
                resultIndex++;
            }
            // result维护的比randomValue大的数的最小索引，所以需要减1；
            resultIndex--;

            if (resultIndex == index) {
                return numArr[resultIndex];
            } else if (resultIndex > index) {
                high = resultIndex - 1;
            } else {
                low = resultIndex + 1;
            }

        }

    }

    public int quickChoose(int[] numArr, int index) {
        if (null == numArr || index < 0) {
            return 0;
        }
        if (numArr.length == 1 || index >= numArr.length) {
            return numArr[numArr.length - 1];
        }

        return quickIndex(numArr, 0, numArr.length - 1, index);
    }

    private int quickIndex(int[] numArr, int low, int high, int index) {
        if (low == high) {
            return numArr[low];
        }
        int random = (int) (Math.random() * (high - low + 1)) + low;
        int randomValue = numArr[random];
        numArr[random] = numArr[high];
        numArr[high] = randomValue;
        int resultIndex = low;
        for (int i = low; i <= high; i++) {
            if (numArr[i] > randomValue) {
                continue;
            }
            int changeValue = numArr[resultIndex];
            numArr[resultIndex] = numArr[i];
            numArr[i] = changeValue;
            resultIndex++;
        }
        // result维护的比randomValue大的数的最小索引，所以需要减1；
        resultIndex--;

        if (resultIndex == index) {
            return numArr[resultIndex];
        } else if (resultIndex > index) {
            return quickIndex(numArr, low, resultIndex - 1, index);
        } else {
            return quickIndex(numArr, resultIndex + 1, high, index);
        }
    }

}
