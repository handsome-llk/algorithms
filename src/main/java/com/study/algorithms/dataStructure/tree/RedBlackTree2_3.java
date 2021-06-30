package com.study.algorithms.dataStructure.tree;

/**
 * 红黑树 -- 2-3树式的红黑树
 */
public class RedBlackTree2_3<T extends Comparable, V> {

    private static int RED = 0;
    private static int BLACK = 1;

    private RbNode root;

    private class RbNode {

        private T key;
        private V value;
        private RbNode p;
        private RbNode left;
        private RbNode right;
        private int color;

        public RbNode(T key, V value) {
            this.key = key;
            this.value = value;
            this.color = RED;
        }

        public RbNode clone() {
            RbNode result = new RbNode(this.key, this.value);
            result.p = this.p;
            result.left = this.left;
            result.right = this.right;
            result.color = this.color;
            return result;
        }

        public boolean isRed() {
            return color == RED;
        }

    }

    /**
     * 中序遍历-递归
     */
    public void inorderTreeWalkRecursion() {
        inorderTreeWalkRecursion(this.root);
        System.out.println();
    }

    /**
     * 中序遍历-递归
     * @param centerNode
     */
    private void inorderTreeWalkRecursion(RbNode centerNode) {
        if (null == centerNode) {
            return;
        }

        inorderTreeWalkRecursion(centerNode.left);
        System.out.print(String.valueOf(centerNode.key) + (centerNode.isRed() ? "r" : "b") + " ");
        inorderTreeWalkRecursion(centerNode.right);
    }

    private V get(T key) {
        RbNode node = treeSearch(key);
        if (null == node) {
            return null;
        }
        return node.value;
    }

    private RbNode treeSearch(T key) {
        if (null == key) {
            return null;
        }
        RbNode node = this.root;
        while (null != node) {
            if (isEuqalKey(node.key, key)) {
                return node;

            } else if (node.key.compareTo(key) < 0) {
                node = node.left;

            } else {
                node = node.right;

            }
        }
        return null;
    }

    public void put(T key, V value) {
        if (null == key || null == value) {
            return ;
        }
        RbNode newNode = new RbNode(key, value);
        if (null == this.root) {
            this.root = newNode;
        }
        RbNode node = this.root;
        while (true) {
            if (isEuqalKey(node.key, newNode.key)) {
                node.value = newNode.value;
                break;
            } else if (node.key.compareTo(newNode.key) < 0) {
                RbNode left = node.left;
                if (null == left) {
                    node.left = newNode;
                    newNode.p = node;
                    break;
                } else {
                    node = node.left;
                    continue;
                }
            } else {
                RbNode right = node.right;
                if (null == right) {
                    node.right = node.right;
                    newNode.p = node;
                    break;
                } else {
                    node = node.right;
                    continue;
                }
            }
        }

        fixupInsertColor(newNode);
    }

    /**
     * 1、2-结点
     *  a、在左边
     *  b、在右边，左旋解决
     * 2、3-结点
     *
     * 修正添加时产生的颜色异常
     * @param node
     */
    private void fixupInsertColor(RbNode node) {
        // TODO LILK 添加结点颜色修复
        while (true) {
            if (null == node.p) {
                break;
            }


        }

        this.root.color = BLACK;
    }

    public boolean isEuqalKey(T key1, T key2) {
        if (null == key1 || null == key2) {
            return false;
        }
        return key1.equals(key2) || key1 == key2;
    }

    /**
     * 右旋
     * @param node
     */
    private void rotateRight(RbNode node) {
        if (null == node) {
            return ;
        }
        RbNode left = node.left;
        RbNode p = node.p;
        if (null == left) {
            return ;
        }

        node.left = left.right;
        if (null != node.left) {
            node.left.p = node;
        }
        left.right = node;
        node.p = left;
        left.p = p;

        if (null == left.p) {
            this.root = left;
        } else if (p.left == node) {
            p.left = left;
        } else {
            p.right = left;
        }
    }

    /**
     * 左旋
     * @param node
     */
    private void rotateLeft(RbNode node) {
        if (null == node) {
            return ;
        }
        RbNode right = node.right;
        RbNode p = node.p;
        if (null == right) {
            return ;
        }

        node.right = right.left;
        if (null != node.right) {
            node.right.p = node;
        }
        right.left = node;
        node.p = right;
        right.p = p;

        if (null == right.p) {
            this.root = right;
        } else if (p.left == node) {
            p.left = right;
        } else {
            p.right = right;
        }

    }

}
