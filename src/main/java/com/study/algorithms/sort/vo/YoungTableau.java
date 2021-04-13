package com.study.algorithms.sort.vo;

import com.study.common.TimeRecord;

/**
 * Young氏矩阵
 */
public class YoungTableau {

    // 列数
    private int line;
    private int[] youngArr;
    private int youngSize;

    public YoungTableau(int line, int[] numArr) {
        if (line <= 0) {
            line = 1;
        }
        this.line = line;
        if (null == numArr) {
            numArr = new int[0];
        }
        youngSize = numArr.length;
        youngArr = new int[youngSize];
        for (int i = youngArr.length - 1; i >= 0; i--) {
            youngArr[i] = numArr[i];
            minHeapify(i);
        }
    }

    public int down(int index) {
        return index + line;
    }

    public int right(int index) {
        return index + 1;
    }

    public int top(int index) {
        return index - line;
    }

    public int left(int index) {
        return index - 1;
    }

    public boolean isLeft(int index) {
        return index % line == 0;
    }

    public boolean isRight(int index) {
        return (index + 1) % line == 0;
    }

    public int getYoungSize() {
        return this.youngSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append("\n");
        for (int i = 0; i < getYoungSize(); i++) {
            sb.append(youngArr[i]).append("\t");
            if (isRight(i)) {
                sb.append("\n");
            }
        }
        if (sb.toString().endsWith("\\n")) {
            sb.setLength(sb.length() - 2);
        }
        if (sb.toString().endsWith("\\t")) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

    public int extractMin() {
        int result = youngArr[0];
        youngArr[0] = youngArr[getYoungSize() - 1];
        youngSize--;
        minHeapify(0);
        return result;
    }

    public void minHeapify(int index) {
        if (index < 0 || index >= getYoungSize()) {
            return ;
        }

        int ifyNum = youngArr[index];
        while (true) {
            int min = ifyNum;
            int minIndex = index;

            if (!isRight(index) && right(index) < getYoungSize() && youngArr[right(index)] < min) {
                min = youngArr[right(index)];
                minIndex = right(index);
            }

            if (down(index) < getYoungSize() && youngArr[down(index)] < min) {
                min = youngArr[down(index)];
                minIndex = down(index);
            }

            if (index == minIndex) {
                break;
            }

            youngArr[index] = min;
            index = minIndex;

        }

        youngArr[index] = ifyNum;

    }

    public void maxHeapify(int index) {
        if (index <= 0 || index >= getYoungSize()) {
            return ;
        }

        int num = youngArr[index];
        while (true) {
            int maxNum = num;
            int maxIndex = index;
            if (!isLeft(index) && youngArr[left(index)] > maxNum) {
                maxNum = youngArr[left(index)];
                maxIndex = left(index);
            }
            if (top(index) >= 0 && youngArr[top(index)] > maxNum) {
                maxNum = youngArr[top(index)];
                maxIndex = top(index);
            }
            if (maxIndex == index) {
                break;
            }
            youngArr[index] = maxNum;
            index = maxIndex;
        }
        youngArr[index] = num;
    }

    public void insert(int num) {
        if (getYoungSize() >= youngArr.length) {
            int[] newArr = new int[youngArr.length + 10];
            System.arraycopy(youngArr, 0, newArr, 0, youngArr.length);
            youngArr = newArr;
        }
        youngArr[getYoungSize()] = num;
        youngSize++;
        maxHeapify(getYoungSize() - 1);
    }

    public boolean hasNum(int num) {
        int index = 0;
        while (index < getYoungSize() && index >= 0) {
            if (youngArr[index] == num) {
                return true;
            } else if (isRight(index)) {
                break;
            } else if (youngArr[index] < num) {
                index = right(index);
            } else {
                index = left(index);
                break;
            }
        }

        if (index < 0) {
            return false;
        }

        while (index < getYoungSize() && index >= 0) {
            while (index < getYoungSize() && index >= 0) {
                if (youngArr[index] == num) {
                    return true;
                } else if (youngArr[index] < num) {
                    index = down(index);
                } else {
                    break;
                }
                // 往下走超出上限，说明左边的都比num小，右边的都比num大，所以不存在相等的数
            }

            // 如果不是完整的矩阵就会出错，所以需要这一步
            boolean isChange = false;
            if (index >= getYoungSize()) {
                index = getYoungSize() - 1;
                isChange = true;
            }

            while (index < getYoungSize() && index >= 0) {
                if (youngArr[index] == num) {
                    return true;
                } else if (isLeft(index)) {
                    break;
                } else if (youngArr[index] > num) {
                    index = left(index);
                } else {
                    if (isChange) {
                        return false;
                    }
                    break;
                }
            }
        }

        return false;
    }

    public boolean hasNumAnswer(int num) {
        for (int arrNum : this.youngArr) {
            if (arrNum == num) {
                return true;
            }
        }
        return false;
    }
    
    public int get(int index) {
        return youngArr[index];
    }


    public static void main(String[] args) {
        int[] numArr = new int[3000000];
        for (int i = 0; i < numArr.length; i++) {
            int random = 0;
            if (i % 2== 0) {
                random = (int) (Math.random() * 900);
            } else {
                random = (int) (Math.random() * 100) + 901;
            }
            numArr[i] = random;
        }
        YoungTableau youngTableau = new YoungTableau(1700, numArr.clone());
        youngTableau.insert(901);
        System.out.println(youngTableau.get(youngTableau.getYoungSize() - 1));
        TimeRecord record = new TimeRecord();
        System.out.println(youngTableau.hasNumAnswer(900));
        record.recordStop("hasNumAnswer");
        System.out.println(youngTableau.hasNum(900));
        record.recordStop("hasNum");
    }


}
