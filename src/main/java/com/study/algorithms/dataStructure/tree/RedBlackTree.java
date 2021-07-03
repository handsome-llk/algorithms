package com.study.algorithms.dataStructure.tree;

/**
 * 红黑树
 */
public class RedBlackTree<T extends Comparable, V> {

    private static final int RED = 0;
    private static final int BLACK = 1;

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

    /**
     * 这个红黑树插入的逻辑主要围绕着一个点：保证当前路径的黑色结点树不变 n
     * @param node
     */
    private void reInsertFixup(RbNode node) {
        if (null == node) {
            return ;
        }
        RbNode p = node.p;
        while (null != p && p.isRed()) {
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
            // 每次操作完，判断前需要更新p
            p = node.p;
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

    /**
     * 删除节点
     * @param key
     * @return
     */
    public V remove(T key) {
        RbNode node = treeSearchRoot(key);
        if (null == node) {
            return null;
        }
        RbNode left = node.left;
        RbNode right = node.right;
        RbNode x = null;
        if (null == left && null == right) {
            // 如果node为红色，且不存在子树，删了没影响
            rbTransplant(node, null);
            x = createNil();
            x.p = node.p;

        } else if (null == left) {
            // 如果node为红色，则不可能存在只有一个子树的情况
            rbTransplant(node, right);
            x = right;

        } else if (null == right) {
            rbTransplant(node, left);
            x = left;

        } else {
            // 右子树存在，则后继结点一定存在
            RbNode succeedNode = treeSearchRoot(getMin(right));
            // 这个后继结点不可能存在左子树
            if (null != succeedNode.right) {
                rbTransplant(succeedNode, succeedNode.right);
                x = succeedNode.right;
            } else {
                rbTransplant(succeedNode, null);
                x = createNil();
                x.p = succeedNode.p;
            }
            rbTransplant(node, succeedNode);
            succeedNode.right = right;
            right.p = succeedNode;
            succeedNode.left = left;
            left = succeedNode;
            succeedNode.color = node.color;
            // 若succeedNode为红色，则其一定没有子树，删了没有影响(不明白的话好好想想)
            node = succeedNode;
        }
        if (node.color == BLACK) {
            // node为红色则不需要调整，原因在上面列出来了
            rbDeleteFixup(x);
        }
        return node.value;
    }

    /**
     * 调整红黑树结构
     * @param node
     *
     * 记录一下我的解决思路。
     * 此处命名x即入参node,w为x的兄弟结点，p为x的父结点。只讨论x为nil结点的情况，不为nil很简单。
     * 1、w有子树，当场通过一些左旋右旋解决问题
     * 2、w没有子树，p为红色。把p变黑，w变红解决问题
     * 3、w没有子树，p为黑色。把w变红，向上递归
     *
     * TODO LILK 这个思路也有问题
     *
     */
    private void rbDeleteFixup(RbNode node) {
        // TODO LILK 调整红黑树结构
        if (node.key != null) {
            // node不为null结点，可以看出原父结点为黑色。从上面的删除逻辑看出来的。。
            node.color = BLACK;
            return ;
        }
        while (true) {
            if (!(node.color == BLACK && node.p != null)) {
                break;
            }
            if (node == node.p.left) {
                RbNode w = node.p.right;
                if (w.left == null && w.right == null) {
                    if (node.p.color == RED) {
                        node.p.color = BLACK;
                        w.color = RED;
                    } else {
                        w.color = RED;
                        node = node.p;
                        continue;
                    }

                } else if (w.right == null) {

                } else {

                }
            } else {
                // TODO LILK 右
            }

        }
    }

    public RbNode createNil() {
        RbNode node = new RbNode(null, null);
        node.color = BLACK;
        return node;
    }


    /**
     * 父结点连接替换
     * @param oldNode
     * @param newNode
     */
    private void rbTransplant(RbNode oldNode, RbNode newNode) {
        if (null == oldNode) {
            return ;
        }
        if (null == oldNode.p) {
            this.root = newNode;
        } else if (oldNode == oldNode.p.left) {
            oldNode.p.left = newNode;
        } else {
            oldNode.p.right = newNode;
        }

        if (null != newNode) {
            newNode.p = oldNode.p;
        }
    }

    /**
     * 获取对应节点的最小关键字元素
     * @param node
     * @return
     */
    private T getMin(RbNode node) {
        if (null == node) {
            return null;
        }
        RbNode p = node;
        RbNode left = p.left;
        while (null != left) {
            p = left;
            left = left.left;
        }
        return p.key;
    }

}
