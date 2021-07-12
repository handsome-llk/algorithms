package com.study.algorithms.dataStructure.tree;

/**
 * 红黑树 -- 2-3树式的红黑树
 * 这颗红黑树的逻辑都是根据算法的2-3树实现的。算法中对红黑树的定义如下：
 * 1、红链接均为左链接
 * 2、没有任何一个结点同时和两条红链接相连
 * 3、该树是完美黑色平衡的，即任意空链接到根结点的路径上的黑链接数量相同
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
                node = node.right;

            } else {
                node = node.left;

            }
        }
        return null;
    }

    public void put(T key, V value) {
        RbNode newNode = put0(key, value);
        fixupInsertColor(newNode);
    }

    /**
     * 这个是按算法的理解写的
     * @param key
     * @param value
     */
    public void putA(T key, V value) {
        RbNode newNode = put0(key, value);
        fixupInsertColorA(newNode);
    }

    private RbNode put0(T key, V value) {
        if (null == key || null == value) {
            return null;
        }
        RbNode newNode = new RbNode(key, value);
        if (null == this.root) {
            this.root = newNode;
        }
        RbNode node = this.root;
        while (true) {
            if (isEuqalKey(node.key, newNode.key)) {
                node.value = newNode.value;
                newNode = node;
                break;
            } else if (node.key.compareTo(newNode.key) > 0) {
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
                    node.right = newNode;
                    newNode.p = node;
                    break;
                } else {
                    node = node.right;
                    continue;
                }
            }
        }

        return newNode;
    }

    /**
     * 算法的颜色修复思路
     *
     * 该
     *
     * @param node
     */
    private void fixupInsertColorA(RbNode node) {
        if (null == node) {
            return ;
        }

        while (true) {
            if (null == node) {
                break;
            }
            RbNode left = node.left;
            RbNode right = node.right;
            if (isRed(right) && isRed(left)) {
                left.color = BLACK;
                right.color = BLACK;
                node.color = RED;
                node = node.p;
            } else if (isRed(right) && !isRed(left)) {
                right.color = node.color;
                node.color = RED;
                rotateLeft(node);
                node = right;
            } else if (isRed(left) && isRed(left.left)) {
                left.color = node.color;
                node.color = RED;
                rotateRight(node);
                node = left;
            } else {
                node = node.p;
            }
        }

        this.root.color = BLACK;
    }

    private boolean isRed(RbNode node) {
        return null == node ? false : node.isRed();
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
        if (null == node) {
            return;
        }
        while (true) {
            RbNode p = node.p;
            if (null == p) {
                break;
            } else if (p.color == BLACK) {
                if (p.left == node) {
                    // 1、因为左节点是该结点是满足条件的，直接退出
                    break;
                } else if (p.left != null && p.left.color == RED) {
                    // 2、当兄弟结点也是红色时，向上递归
                    node.color = BLACK;
                    p.left.color = BLACK;
                    p.color = RED;
                    node = p;
                    continue;
                } else {
                    // 3、不存在兄弟结点时，左旋即可
                    p.color = RED;
                    node.color = BLACK;
                    rotateLeft(p);
                    break;
                }
            } else if (p.color == RED) {
                if (p.right == node) {
                    // 4、该处变化是为了变成5的情况
                    rotateLeft(p);
                    node = p;
                    continue;
                } else {
                    // 5、这里其实可以设计成2的情况，但是由于情况1的存在，所以这里直接将颜色做掉并向上递归
                    RbNode pp = p.p;
                    p.color = RED;
                    pp.color = BLACK;
                    node.color = BLACK;
                    rotateRight(pp);
                    node = p;
                    continue;
                }
            } else {
                throw new RuntimeException("color exception");
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
