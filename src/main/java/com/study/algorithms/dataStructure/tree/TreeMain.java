package com.study.algorithms.dataStructure.tree;

import java.util.Random;

/**
 * 树专用测试类
 */
public class TreeMain {

    public static void main(String[] args) {
        Random random = new Random();
        testBinarySearchTree(random);
        System.out.println();
    }

    public static void testBinarySearchTree(Random random) {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree();
        int num = 100;
        for (int i = 0; i < num; i++) {
            int randomNum = random.nextInt(200);
            tree.insert(randomNum, String.valueOf(randomNum));
        }
        tree.inorderTreeWalkRecursion();
        System.out.println(tree.remove(50));
        tree.inorderTreeWalkRecursion();
        System.out.println(tree.remove(100));
        tree.inorderTreeWalkRecursion();
        System.out.println(tree.remove(150));
        tree.inorderTreeWalkRecursion();
    }


}
