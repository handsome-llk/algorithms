package com.study.algorithms.sort.vo;

/**
 * 最大优先队列：（需要支持以下操作）
 * 1、INSERT(S, x): 把元素x插入集合S中
 * 2、MAXIMUM(S): 返回S中具有最大键字的元素
 * 3、EXTRACT-MAX(S): 去掉并返回S中的具有最大键字的元素
 * 4、INCREASE-KEY(S, x， k): 将元素x的关键字值增加到k，这里假设k的值不小于x的原关键字值
 */
public class MaxQueue {

    private MaxHeap maxHeap;

    public MaxQueue(int[] numArr) {
        maxHeap = new MaxHeap(numArr);
    }

    public MaxQueue(int length) {
        maxHeap = new MaxHeap(length);
    }

    /**
     * MAXIMUM
     * @return
     */
    public int getMaxNum() {
        return maxHeap.get(0);
    }

    /**
     * EXTRACT-MAX
     * @return
     */
    public int extractMax() {
        return maxHeap.extraMax();
    }

    /**
     * INCREASE-KEY
     * @param i
     * @param num
     */
    public void changeKey(int i, int num) {
        maxHeap.changeKey(i, num);
    }

    /**
     * INSERT
     * @param num
     */
    public void insert(int num) {
        maxHeap.insert(num);
    }

}
