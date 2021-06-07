package com.study.algorithms.dataStructure.tree;

/**
 * 红黑树
 */
public class RedBlackTree<T extends Comparable, V> {

    private static final int RED = 0;
    private static final int BLACK = 1;

    private RbNode root;

    protected class RbNode {

        private T key;
        private V value;
        private RbNode p;
        private RbNode left;
        private RbNode right;
        private int color;

        public RbNode(T key, V value) {
            this.key = key;
            this.value = value;
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

        /**
         * 销毁该节点
         */
        public void withdraw() {
            if (null != p) {
                if (p.left == this) {
                    p.left = null;
                } else if (p.right == this) {
                    p.right = null;
                }
                p = null;
            }

            if (null != left && left.p == this) {
                left.p = null;
            }
            left = null;

            if (null != right && right.p == this) {
                right.p = null;
            }
            right = null;

        }

    }

    /**
     * 增加结点
     * @param key
     * @param value
     */
    public void insert(T key, V value) {
        RbNode addNode = new RbNode(key, value);
        if (null == this.root) {
            this.root = addNode;
            addNode.color = RED;
        } else {
            RbNode nowNode = root;
            while (true) {
                if (addNode.key == nowNode.key || addNode.key.equals(nowNode.key)) {
                    nowNode.value = addNode.value;
                    break;
                }
                if (addNode.key.compareTo(nowNode.key) < 0) {
                    if (null == nowNode.left) {
                        nowNode.left = addNode;
                        addNode.p = nowNode;
                        addNode.color = RED;
                        break;
                    } else {
                        nowNode = nowNode.left;
                        continue;
                    }
                } else {
                    if (null == nowNode.right) {
                        nowNode.right = addNode;
                        addNode.p = nowNode;
                        addNode.color = RED;
                        break;
                    } else {
                        nowNode = nowNode.right;
                        continue;
                    }
                }
            }
        }

        reInsertFixup(addNode);
    }

    private void reInsertFixup(RbNode node) {
        if (null == node) {
            return ;
        }
        RbNode p = node.p;
        while (null != p && p.isRed()) {
            p = node.p;
            RbNode pp = p.p;
            if (p == pp.left) {
                RbNode y = pp.right;
                if (null != y && y.isRed()) {
                    y.color = BLACK;
                    p.color = BLACK;
                    pp.color = RED;
                    node = pp;
                } else if (node == p.right) {
                    node = p;
                    leftRotate(node.key);
                } else {
                    p.color = BLACK;
                    pp.color = RED;
                    rightRotate(pp.key);
                }
            } else if (p == pp.right) {
                RbNode y = pp.left;
                if (null != y && y.isRed()) {
                    y.color = BLACK;
                    p.color = BLACK;
                    pp.color = RED;
                    node = pp;
                } else if (node == p.left) {
                    node = p;
                    rightRotate(node.key);
                } else {
                    p.color = BLACK;
                    pp.color = RED;
                    leftRotate(pp.key);
                }
            }
        }
        this.root.color = BLACK;
    }

    /**
     * 搜索key
     * @param key
     * @return
     */
    public V treeSearch(T key) {
        if (null == this.root) {
            return null;
        }
        RbNode result = treeSearch(this.root, key);
        return null == result ? null : result.value;
    }

    private RbNode treeSearchRoot(T key) {
        return treeSearch(this.root, key);
    }

    private RbNode treeSearch(RbNode node, T key) {
        if (null == node || null == key) {
            return null;
        }
        if (node.key.equals(key)) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return treeSearch(node.left, key);
        } else {
            return treeSearch(node.right, key);
        }
    }

    /**
     * 左旋
     * @param key
     */
    private void leftRotate(T key) {
        RbNode node = treeSearchRoot(key);
        if (null == node) {
            return;
        }
        RbNode right = node.right;
        if (null == right) {
            return;
        }
        RbNode p = (RbNode) node.p;
        if (null == p) {
            this.root = right;
        } else {
            if (p.left == node) {
                p.left = right;
            } else if (p.right == node) {
                p.right = right;
            }
        }
        right.p = node.p;
        node.p = right;
        node.right = null;

        RbNode rightLeft = right.left;
        right.left = node;

        if (null == rightLeft) {
            return;
        }
        rightLeft.p = node;
        node.right = rightLeft;
    }

    /**
     * 右旋
     * @param key
     */
    private void rightRotate(T key) {
        RbNode node = treeSearchRoot(key);
        if (null == node) {
            return;
        }
        RbNode left = node.left;
        if (null == left) {
            return;
        }
        RbNode p = (RbNode) node.p;
        if (null == p) {
            this.root = left;
        } else {
            if (p.right == node) {
                p.right = left;
            } else if (p.left == node) {
                p.left = left;
            }
        }
        left.p = node.p;
        node.p = left;
        node.left = null;

        RbNode leftRight = left.right;
        left.right = node;

        if (null == leftRight) {
            return;
        }
        leftRight.p = node;
        node.left = leftRight;
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

    public T getRootKey() {
        return null == root ? null : root.key;
    }

}
