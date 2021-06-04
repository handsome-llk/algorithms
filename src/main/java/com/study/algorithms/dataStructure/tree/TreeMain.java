package com.study.algorithms.dataStructure.tree;

import java.util.Random;

/**
 * 树专用测试类
 */
public class TreeMain {

    public static void main(String[] args) {
        Random random = new Random();
//        testBinarySearchTree(random);
        testRedBlackTree(random);
        System.out.println();
    }

    public static void testRedBlackTree(Random random) {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
        int num = 5;
        for (int i = 0; i < num; i++) {
            int randomNum = random.nextInt(200);
            tree.insert(randomNum, randomNum);
        }
        tree.inorderTreeWalkRecursion();
        tree.leftRotate(tree.getRootKey());
        tree.inorderTreeWalkRecursion();
        tree.rightRotate(tree.getRootKey());
        tree.inorderTreeWalkRecursion();
    }

    public static void testBinarySearchTree(Random random) {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree();
        int num = 5;
        for (int i = 0; i < num; i++) {
            int randomNum = random.nextInt(200);
            tree.insert(randomNum, String.valueOf(randomNum));
        }
        tree.inorderTreeWalkRecursion();
        System.out.println(tree.remove1(tree.getRootKey()));
        tree.inorderTreeWalkRecursion();
        System.out.println(tree.remove1(50));
        tree.inorderTreeWalkRecursion();
        System.out.println(tree.remove1(100));
        tree.inorderTreeWalkRecursion();
        System.out.println(tree.remove1(150));
        tree.inorderTreeWalkRecursion();
        System.out.println(tree.remove1(tree.getRootKey()));
        tree.inorderTreeWalkRecursion();
    }


}
