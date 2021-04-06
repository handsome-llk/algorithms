package com.study.algorithms.sort.vo;

/**
 * 最大d堆
 */
public class DMaxHeap {

    private int heapSize;
    private int[] heapArr;
    // d叉
    private int d;

    public DMaxHeap(int d, int[] numArr) {
        this.d = d;
        if (null == numArr) {
            numArr = new int[0];
        }
        heapSize = numArr.length;
        heapArr = new int[heapSize];
        for (int i = heapArr.length - 1; i >= 0; i--) {
            heapArr[i] = numArr[i];
            maxHeapify(i);
        }
    }

    public DMaxHeap(int d, int[] numArr, boolean isMapHeap) {
        this.d = d;
        if (null == numArr) {
            numArr = new int[0];
        }
        heapSize = numArr.length;
        heapArr = new int[heapSize];
        for (int i = 0; i < heapSize; i++) {
            heapArr[i] = numArr[i];
        }
    }

    public int parent(int index) {
        if (index <= 0) {
            return -1;
        }
        return (index - 1) / d;
    }

    public int child(int index, int childIndex) {
        if (1 > childIndex || childIndex > d) {
            throw new RuntimeException("childIndex 不在子叶数范围内");
        }
        return d * index + childIndex;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public int extractMax() {
        if (getHeapSize() <= 0) {
            return 0;
        }

        int maxNum = heapArr[0];
        heapArr[0] = heapArr[getHeapSize() - 1];
        heapArr[getHeapSize() - 1] = maxNum;
        heapSize--;
        maxHeapify(0);
        return maxNum;
    }

    public void maxHeapify(int index) {
        if (index >= getHeapSize() || index < 0) {
            return ;
        }
        int num = heapArr[index];
        while (true) {
            if (index >= getHeapSize()) {
                break;
            }
            int maxNum = num;
            int maxIndex = index;
            for (int i = 1; i <= d; i++) {
                int childIndex = child(index, i);
                if (childIndex >= getHeapSize()) {
                    continue;
                }
                if (heapArr[childIndex] > maxNum) {
                    maxNum = heapArr[childIndex];
                    maxIndex = childIndex;
                }
            }
            if (maxIndex == index) {
                heapArr[index] = num;
                break;
            }
            heapArr[index] = maxNum;
            index = maxIndex;
        }
    }

    public void changeKey(int index, int num) {
        if (index >= getHeapSize() || index < 0 || heapArr[index] == num) {
            return ;
        }
        int oldNum = heapArr[index];
        heapArr[index] = num;
        if (oldNum > heapArr[index]) {
            maxHeapify(index);
        } else {
            while (true) {
                int parent = parent(index);
                if (parent < 0) {
                    break;
                }

                if (heapArr[parent] < num) {
                    heapArr[index] = heapArr[parent];
                    index = parent;
                } else {
                    break;
                }

            }
            heapArr[index] = num;
        }

    }

    public boolean isRighD(int index) {
        if (0 < index || index >= getHeapSize()) {
            return true;
        }

        boolean result = true;
        for (int i = 1; i <= d; i++) {
            boolean flag = isRighD(child(index, i));
            result = result && flag;
        }

        if (!result) {
            return result;
        }
        for (int i = 1; i <= d; i++) {
            if (child(index, d) >= getHeapSize()) {
                continue;
            }
            if (heapArr[index] > heapArr[child(index, d)]) {
                continue;
            }
            result = false;
            break;
        }

        return result;
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

}
