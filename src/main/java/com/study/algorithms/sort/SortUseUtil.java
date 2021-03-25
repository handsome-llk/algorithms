package com.study.algorithms.sort;


/**
 * 用来实现一些实际应用
 */
public class SortUseUtil {

    private SortUseUtil() {}

    private static SortUseUtil sortUseUtil = new SortUseUtil();
    public static SortUseUtil getInstance() {
        return sortUseUtil;
    }

    /**
     * 假设a、b长度相等
     * @param a
     * @param b
     */
    public int[][] calMatrix(int[][] a, int[][] b) {
        if (null == a || a.length == 0 || null == b || b.length == 0) {
            return new int[0][0];
        }

        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] + b[k][j];
                }
            }
        }
        return c;
    }

    public void sysMatrix(int[][] matrix) {
        if (null == matrix) {
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }


    /**
     * 线性查找最大子数组
     * @param numArr
     */
    public MaxChild findMaxChildN(int[] numArr) {
        MaxChild result = new MaxChild();
        result.maxSum = numArr[0];
        result.minIndex = 0;
        result.maxIndex = 0;
        if (null == numArr || numArr.length == 0) {
            return result;
        }

        MaxChild newMax = new MaxChild();
        newMax.maxSum = 0;
        newMax.minIndex = 0;
        newMax.maxIndex = 0;
        for (int i = 0; i < numArr.length; i++) {
            if (newMax.maxSum <= 0) {
                newMax.maxSum = numArr[i];
                newMax.minIndex = i;
            } else {
                newMax.maxSum += numArr[i];
            }
            newMax.maxIndex = i;

            if (newMax.maxSum > result.maxSum) {
                result.maxSum = newMax.maxSum;
                result.minIndex = newMax.minIndex;
                result.maxIndex = newMax.maxIndex;
            }
        }
        return result;
    }

    public MaxChild findMaxChildN2(int[] numArr) {
        MaxChild result = new MaxChild();
        result.maxSum = numArr[0];
        result.maxIndex = 0;
        result.minIndex = 0;
        for (int i = 0; i < numArr.length; i++) {
            MaxChild maxOne = findMiddleMax(numArr, 0, numArr.length - 1, i);
            if (result.maxSum < maxOne.maxSum) {
                result = maxOne;
            }
        }
        return result;
    }

    public void sysMaxChild(MaxChild maxChild) {
        System.out.println(maxChild.maxSum);
        System.out.println(maxChild.minIndex);
        System.out.println(maxChild.maxIndex);
    }

    /**
     * 寻找最大子数组
     * @param numArr
     * @param start
     * @param end
     * @return
     */
    public MaxChild findMaxChild(int[] numArr, int start, int end) {
        if (start == end) {
            MaxChild maxChild = new MaxChild();
            maxChild.maxSum = numArr[start];
            maxChild.minIndex = start;
            maxChild.maxIndex = end;
            return maxChild;
        }
        int middle = (start + end) / 2;
        MaxChild leftMax = findMaxChild(numArr, start, middle);
        MaxChild rightMax = findMaxChild(numArr, middle + 1, end);
        MaxChild middleMax = findMiddleMax(numArr, start, end, middle);
        return leftMax.maxSum > rightMax.maxSum ?
                leftMax.maxSum > middleMax.maxSum ? leftMax : middleMax
                : rightMax.maxSum > middleMax.maxSum ? rightMax : middleMax;
    }

    private MaxChild findMiddleMax(int[] numArr, int start, int end, int middle) {
        MaxChild result = new MaxChild();
        result.maxSum = numArr[middle];
        result.minIndex = middle;
        result.maxIndex = middle;
        int leftMax = 0;
        int leftSum = 0;
        for (int i = middle - 1; i >= start; i--) {
            leftSum += numArr[i];
            if (leftSum > leftMax) {
                leftMax = leftSum;
                result.minIndex = i;
            }
        }
        int rightMax = 0;
        int rightSum = 0;
        for (int i = middle + 1; i <= end; i++) {
            rightSum += numArr[i];
            if (rightSum > rightMax) {
                rightMax = rightSum;
                result.maxIndex = i;
            }
        }
        result.maxSum += leftMax + rightMax;
        return result;
    }

    public class MaxChild {
        public int maxSum;
        public int minIndex;
        public int maxIndex;
    }

}
