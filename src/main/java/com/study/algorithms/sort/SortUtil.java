package com.study.algorithms.sort;

/**
 * 排序工具
 */
public class SortUtil {

    private SortUtil() {}

    private static SortUtil sortUtil = new SortUtil();
    public static SortUtil getInstance() {
        return sortUtil;
    }

    /**
     * 堆排序
     * @param numArr
     */
    public void heapSort(int[] numArr) {
        // TODO LILK
    }

    public void MIN_HEAPIFY(int[] heapArr, int index) {

    }

    /**
     * 用二分法做一下插入排序
     * @param numArr
     */
    public void insertDiviSort(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }

        for (int i = 1; i < numArr.length; i++) {
            int resultIndex = 0;
            int num = numArr[i];
            if (num <= numArr[0]) {
                resultIndex = 0;
            } else if (num >= numArr[i - 1]) {
                continue;
            } else {
                int start = 0;
                int end = i - 1;
                while (true) {
                    if (start >= end) {
                        resultIndex = start;
                        break;
                    } else if (start + 1 == end) {
                        resultIndex = end;
                        break;
                    }

                    int middle = (start + end) / 2;
                    if (num < numArr[middle]) {
                        end = middle;
                        continue;
                    } else {
                        start = middle;
                    }
                }
            }

            for (int j = i - 1; j >= resultIndex; j--) {
                numArr[j + 1] = numArr[j];
            }
            numArr[resultIndex] = num;
        }
    }

    /**
     * 用递归做一下插入排序,用递归插入排序，数组一多就会堆溢出
     * @param numArr
     */
    @Deprecated
    public void insertRecursionSort(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }
        insertRecursionSort0(numArr, numArr.length - 1);
    }

    private void insertRecursionSort0(int[] numArr, int insertIndex) {
        if (insertIndex < 0) {
            return ;
        }
        insertRecursionSort0(numArr, insertIndex - 1);
        int num = numArr[insertIndex];

        int i = insertIndex - 1;
        while (i >= 0) {
            if (numArr[i] > num) {
                numArr[i + 1] = numArr[i];
                i--;
            } else {
                break;
            }
        }
        numArr[i + 1] = num;

    }

    /**
     * 分治模式在每层递归时都有三个步骤：
     * 1、分解原问题为若干子问题，这些子问题是原问题的规模较小的实例
     * 2、解决这些子问题，归并地求解各子问题。然而，若子问题地规模足够小，则直接求解
     * 3、合并这些子问题的解成原问题的解
     */

    /**
     * 归并排序升序
     * @param numArr
     */
    public void mergeAscSort(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }
        mergeAsc(0, numArr.length - 1, numArr);
    }

    private void mergeAsc(int start, int end, int[] numArr){
        if (start == end) {
            return ;
        }
        int startEnd = (end + start) / 2;
        mergeAsc(start, startEnd, numArr);
        int endStart = startEnd + 1;
        mergeAsc(endStart, end, numArr);
        mergeAsc0(numArr, start, end);
    }

    private void mergeAsc0(int[] numArr, int start, int end) {
        int length = end - start + 1;
        int startEnd = (end + start) / 2;

        int[] resultArr = new int[length];
        int i = start, j = startEnd + 1, index = 0;
        while (i <= startEnd && j <= end) {
            if (numArr[i] <= numArr[j]) {
                resultArr[index] = numArr[i];
                i++;
            } else {
                resultArr[index] = numArr[j];
                j++;
            }
            index++;
        }
        while (index < resultArr.length) {
            if (i > startEnd) {
                resultArr[index] = numArr[j];
                j++;
            } else {
                resultArr[index] = numArr[i];
                i++;
            }
            index++;
        }

        for (int k = start, indexResult = 0; k <= end; k++, indexResult++) {
            numArr[k] = resultArr[indexResult];
        }
    }

    /**
     * 思考用二分法完成插入排序。。。用二分法完成的插入排序会比归并和快排慢吗？
     * 用二分法完成插入排序的话，就会需要非常大的空间。因为就算找到插入位置，还是需要移动该位置
     * 后面的数，即循环次数不会变。
     */

    /**
     * 插入排序升序
     * @param numArr
     */
    public void insertAscSort(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }

        for (int i = 1; i < numArr.length; i++) {
            int num = numArr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (numArr[j] <= num) {
                    break;
                }
                numArr[j + 1] = numArr[j];
            }
            numArr[j + 1] = num;
        }
    }

    /**
     * 插入排序降序
     * @param numArr
     */
    public void insertDescSort(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }

        for (int i = 1; i < numArr.length; i++) {
            int num = numArr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (numArr[j] >= num) {
                    break;
                }
                numArr[j + 1] = numArr[j];
            }
            numArr[j + 1] = num;
        }

    }

    public void sysArr(int[] numArr, String sortMathod) {
        System.out.println("--------------------------" + sortMathod + "--------------------------");
        if (null == numArr || numArr.length == 0) {
            return ;
        }
        for (int num : numArr) {
            System.out.print(num);
            System.out.print(",");
        }
        System.out.println();
        System.out.println();
    }

}
