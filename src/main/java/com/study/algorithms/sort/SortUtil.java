package com.study.algorithms.sort;

import com.study.algorithms.sort.vo.MaxHeap;
import com.study.algorithms.sort.vo.MaxQueue;

import java.util.Collections;
import java.util.Random;

/**
 * 排序工具
 */
public class SortUtil {

    private SortUtil() {}

    private static SortUtil sortUtil = new SortUtil();
    public static SortUtil getInstance() {
        return sortUtil;
    }
    private Random random = new Random();

    /**
     * 计数排序，考虑书中的写法.比起自己写的计数排序，这种计数排序可以保持数组的稳定性
     * @param numArr
     */
    public void countSort2(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }
        int maxNum = numArr[0];
        int minNum = numArr[0];
        for (int num : numArr) {
            if (num > maxNum) {
                maxNum = num;
            }
            if (num < minNum) {
                minNum = num;
            }
        }

        if (maxNum >= numArr.length || minNum < 0) {
            quickSortRandom(numArr);
            return ;
        }

        int[] indexArr = new int[maxNum + 1];
        for (int num : numArr) {
            indexArr[num] = indexArr[num] + 1;
        }

        for (int i = 1; i < indexArr.length; i++) {
            // 这一步是为了直接让存在数组中的数变成下标
            indexArr[i] = indexArr[i] + indexArr[i - 1];
        }

        int[] copyArr = new int[numArr.length];
        System.arraycopy(numArr, 0, copyArr, 0, numArr.length);

//        for (int i = 0; i < copyArr.length; i++) {
//            int index = indexArr[copyArr[i]];
//            numArr[index - 1] = copyArr[i];
//            indexArr[copyArr[i]] = index - 1;
//        }

        // 如果改用0 -> copuArr.length，就会破坏数组的稳定性
        for (int i = copyArr.length - 1; i >= 0; i--) {
            int index = indexArr[copyArr[i]];
            numArr[index - 1] = copyArr[i];
            indexArr[copyArr[i]] = index - 1;
        }
    }

    /**
     * 计数排序,线性排序，但是限制大
     * @param numArr
     */
    public void countSort(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }
        int maxNum = numArr[0];
        int minNum = numArr[0];
        for (int num : numArr) {
            if (num > maxNum) {
                maxNum = num;
            }
            if (num < minNum) {
                minNum = num;
            }
        }

        if (maxNum >= numArr.length || minNum < 0) {
            quickSortRandom(numArr);
            return ;
        }

        int[] bArr = new int[maxNum + 1];
        for (int num : numArr) {
            bArr[num] = bArr[num] + 1;
        }

        int index = 0;
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] == 0) {
                continue;
            }
            while (bArr[i] > 0) {
                numArr[index] = i;
                index++;
                bArr[i] = bArr[i] - 1;
            }
        }

        // TODO LILK
    }

    /**
     * 随机快速排序
     * @param numArr
     */
    public void quickSortRandom(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }
        quickSortRandom(numArr, 0, numArr.length - 1);
    }

    private void quickSortRandom(int[] numArr, int low, int high) {
        if (high <= low) {
            return ;
        }

        int index = quickRandom(numArr, low, high);
        quickSortRandom(numArr, low, index - 1);
        quickSortRandom(numArr, index + 1, high);
    }

    private int quickRandom(int[] numArr, int low, int high) {
        int randomIndex = low + random.nextInt(high - low + 1);
        int randomNum = numArr[randomIndex];
        numArr[randomIndex] = numArr[high];
        numArr[high] = randomNum;


        return quick2(numArr, low, high);
    }

    /**
     * 快速排序2
     * @param numArr
     */
    public void quickSort2(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }
        quickSort2(numArr, 0, numArr.length - 1);
    }

    private void quickSort2(int[] numArr, int low, int high) {
        if (high <= low) {
            return ;
        }

        int index = quick2(numArr, low, high);
        quickSort2(numArr, low, index - 1);
        quickSort2(numArr, index + 1, high);
    }

    private int quick2(int[] numArr, int low, int high) {
        int sortNum = numArr[high];
        // k代表比sortNum大的数的最小下标，这个算法其实就是维护两个比sortNum大的数的最小下标和最大下标
        int k = low;
        for (int i = low; i < high; i++) {
            if (numArr[i] < sortNum) {
                int temp = numArr[i];
                numArr[i] = numArr[k];
                numArr[k] = temp;
                k++;
            }
        }

        numArr[high] = numArr[k];
        numArr[k] = sortNum;
        return k;

//        // 思考为什么会是错的呢
//        int sortNum = numArr[high];
//        int k = 0;
//        for (int i = low; i <= high; i++) {
//            if (numArr[i] <= sortNum) {
//                int temp = numArr[i];
//                numArr[i] = numArr[k];
//                numArr[k] = temp;
//                // 因为少了这一步，就会导致交换后返回的k不是sortNum的正确索引
//                // 但是加了这一步效率就低了
//                if (i == high) {
//                    continue;
//                }
//                k++;
//            }
//        }
//        return k;
    }

    /**
     * 快速排序
     * @param numArr
     */
    public void quickSort(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }
        quickSort(numArr, 0, numArr.length - 1);
    }

    private void quickSort(int[] numArr, int low, int high) {
        if (high <= low) {
            return ;
        }

        int index = quick(numArr, low, high);
        quickSort(numArr, low, index - 1);
        quickSort(numArr, index + 1, high);
    }

    /**
     * 相对quick2慢的原因可能是quick的if太多了
     * @param numArr
     * @param low
     * @param high
     * @return
     */
    private int quick(int[] numArr, int low, int high) {
        int sortNum = numArr[low];
        int index = -1;
        while (true) {
            if (high <= low) {
                index = high;
                break;
            }

            while (high > low) {
                if (numArr[high] >= sortNum) {
                    high--;
                } else {
                    numArr[low] = numArr[high];
                    break;
                }
            }

            if (high <= low) {
                index = low;
                break;
            }

            while (high > low) {
                if (numArr[low] <= sortNum) {
                    low++;
                } else {
                    numArr[high] = numArr[low];
                    break;
                }
            }
        }
        numArr[index] = sortNum;
        return index;
    }

    /**
     * 堆排序
     * @param numArr
     */
    public void heapSort(int[] numArr) {
        if (null == numArr || numArr.length <= 1) {
            return ;
        }
        MaxHeap maxHeap = new MaxHeap(numArr);
        int[] reault = maxHeap.ascArr();
        System.arraycopy(reault, 0, numArr, 0, numArr.length);
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

    public boolean isRight(int[] answerArr, int[] destArr) {
        if (null == answerArr || null == destArr) {
            return false;
        }
        if (answerArr.length != destArr.length) {
            return false;
        }
        for (int i = 0; i < answerArr.length; i++) {
            if (answerArr[i] != destArr[i]) {
                System.out.println("index:" + i);
                return false;
            }
        }
        return true;
    }

}
