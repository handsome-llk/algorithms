package com.study.algorithms.dataStructure.tree;

import java.util.Random;

/**
 * 树专用测试类
 */
public class TreeMain {

    public static void main(String[] args) {
        Random random = new Random();
//        testBinarySearchTree(random);
//        testRedBlackTree(random);
        testRedBlackTree2_3(random);
        System.out.println();
    }

    public static void testRedBlackTree2_3(Random random) {
        RedBlackTree2_3<Integer, Integer> tree = new RedBlackTree2_3<>();
        int num = 10;
        for (int i = 0; i < num; i++) {
            int randomNum = random.nextInt(200);
//            randomNum = i + 1;
            tree.putA(randomNum, randomNum);
            System.out.print(randomNum + " ");
        }
        System.out.println();
        tree.inorderTreeWalkRecursion();
    }

    public static void testRedBlackTree(Random random) {
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<>();
        RedBlackTree2_3<Integer, Integer> tree2_3 = new RedBlackTree2_3<>();
        int num = 10;
        for (int i = 0; i < num; i++) {
            int randomNum = random.nextInt(200);
            randomNum = i + 1;
            tree.insert(randomNum, randomNum);
            System.out.print(randomNum + " ");
        }
        System.out.println();
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
