package com.study.algorithms.sort.vo;

import com.study.algorithms.sort.SortUseUtil;
import com.study.algorithms.sort.SortUtil;

/**
 * 最大堆：父节点都大于等于子节点
 */
public class MaxHeap {

    private int[] heapArr;
    private int heapSize;

    public MaxHeap(int[] numArr) {
        heapSize = numArr.length;
        heapArr = new int[numArr.length];
        for (int i = heapArr.length - 1; i >= 0; i--) {
            heapArr[i] = numArr[i];
            maxHeapify(i);
        }
    }

    public MaxHeap(int length) {
        heapArr = new int[length];
        heapSize = 0;
    }

    /**
     * 该构造器就是单纯的就数组仍如堆中，并不是构造最大堆
     * @param numArr
     * @param calMaxHeap
     */
    public MaxHeap(int[] numArr, boolean calMaxHeap) {
        heapSize = numArr.length;
        heapArr = new int[numArr.length];
        System.arraycopy(numArr, 0, heapArr, 0, heapSize);
    }

    public int parent(int index) {
        return (index + 1) / 2 - 1;
    }

    public int left(int index) {
        return (index + 1) * 2 - 1;
    }

    public int right(int index) {
        return (index + 1) * 2;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public int getSize() {
        return heapArr.length;
    }

    /**
     * 将指定位置的数下沉至合适的位置
     * @param index
     */
    private void maxHeapify(int index) {
        if (index >= getHeapSize()) {
            return ;
        }
        int changeIndex = -1;
        while (changeIndex != index) {
            if (left(index) < getHeapSize() && heapArr[left(index)] > heapArr[index]) {
                changeIndex = left(index);
            } else {
                changeIndex = index;
            }
            if (right(index) < getHeapSize() && heapArr[right(index)] > heapArr[changeIndex]) {
                changeIndex = right(index);
            }
            if (changeIndex != index) {
                int changeNum = heapArr[index];
                heapArr[index] = heapArr[changeIndex];
                heapArr[changeIndex] = changeNum;
                index = changeIndex;
                changeIndex = -1;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < getHeapSize(); i++) {
            sb.append(heapArr[i]).append(",");
        }
        if (sb.toString().endsWith(",")) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 是否是最大堆
     * @param index
     * @return
     */
    public boolean isMaxHeap(int index) {
        if (index >= getHeapSize()) {
            return true;
        }
        boolean leftMax = isMaxHeap(left(index));
        boolean rightMax = isMaxHeap(right(index));

        boolean result = leftMax && rightMax;
        if (result) {
            if (left(index) < getHeapSize() && heapArr[left(index)] > heapArr[index]) {
                result = false;
            }
            if (right(index) < getHeapSize() && heapArr[right(index)] > heapArr[index]) {
                result = false;
            }
        }
        return result;
    }

    /**
     * 返回升序数据
     * @return
     */
    public int[] ascArr() {
        int[] numArr = this.heapArr.clone();
        MaxHeap maxHeap = new MaxHeap(numArr, true);
        for (int i = numArr.length - 1; i >= 1; i--) {
            int num = maxHeap.heapArr[i];
            maxHeap.heapArr[i] = maxHeap.heapArr[0];
            maxHeap.heapArr[0] = num;
            maxHeap.heapSize--;
            maxHeap.maxHeapify(0);
        }
        maxHeap.heapSize = maxHeap.getSize();
        return maxHeap.getHeapArr();
    }

    public int[] getHeapArr() {
        return heapArr.clone();
    }

    public int get(int index) {
        return this.heapArr[index];
    }

    public int extraMax() {
        int result = this.heapArr[0];
        heapArr[0] = heapArr[getHeapSize() - 1];
        heapArr[getHeapSize() - 1] = result;
        heapSize--;
        maxHeapify(0);
        return result;
    }

    public void changeKey(int i, int num) {
        if (i < 0 || i >= getHeapSize()) {
            return ;
        }
        if (heapArr[i] > num) {
            heapArr[i] = num;
            maxHeapify(i);
        } else {
            while (true) {
                if (parent(i) < 0 || heapArr[parent(i)] >= num) {
                    break;
                }
                heapArr[i] = heapArr[parent(i)];
                i = parent(i);
            }
            heapArr[i] = num;
        }
    }

    public void insert(int num) {
        if (getHeapSize() >= getSize()) {
            int[] newHeapArr = new int[getSize() * 2];
            System.arraycopy(heapArr, 0, newHeapArr, 0, getHeapSize());
            heapArr = newHeapArr;
        }
        heapArr[getHeapSize()] = num;
        heapSize++;
        changeKey(getHeapSize() - 1, num);
    }


    public static void main(String[] args) {
        int[] numArr = new int[10];
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = (int) (Math.random() * 10);
        }
        MaxHeap maxHeap = new MaxHeap(numArr);
        System.out.println(maxHeap.toString());
        System.out.println(maxHeap.isMaxHeap(0));
        System.out.println();

        MaxHeap maxHeap1 = new MaxHeap(30);
        for (int num : numArr) {
            maxHeap1.insert(num);
        }
        System.out.println(maxHeap1.toString());
        System.out.println(maxHeap1.isMaxHeap(0));
    }
}
