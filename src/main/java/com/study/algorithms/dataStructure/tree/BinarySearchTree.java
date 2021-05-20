package com.study.algorithms.dataStructure.tree;

import java.util.Comparator;

/**
 * 二叉搜索树
 */
public class BinarySearchTree<T extends Comparable, V> {

    private Node root;

    private class Node {
        private T key;
        private V value;
        private Node p;
        private Node left;
        private Node right;

        public Node(T key, V value) {
            this.key = key;
            this.value = value;
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

            if (null != left) {
                left.p = null;
                left = null;
            }

            if (null != right) {
                right.p = null;
                right = null;
            }

        }

    }

    /**
     * 这种插入逻辑是从根节点开始，所以可以保证每一个节点都大于等于其所有左子树，小于其所有右子树
     * @param key
     * @param value
     */
    public void insert(T key, V value) {
        Node addNode = new Node(key, value);
        if (null == this.root) {
            this.root = addNode;
        } else {
            Node nowNode = root;
            while (true) {
                if (addNode.key == nowNode.key || addNode.key.equals(nowNode.key)) {
                    nowNode.value = addNode.value;
                    break;
                }
                if (addNode.key.compareTo(nowNode.key) < 0) {
                    if (null == nowNode.left) {
                        nowNode.left = addNode;
                        addNode.p = nowNode;
                        break;
                    } else {
                        nowNode = nowNode.left;
                        continue;
                    }
                } else {
                    if (null == nowNode.right) {
                        nowNode.right = addNode;
                        addNode.p = nowNode;
                        break;
                    } else {
                        nowNode = nowNode.right;
                        continue;
                    }
                }
            }
        }
    }

    /**
     * 删除节点 TODO LILK 目测有问题
     * @param key
     * @return
     */
    public V remove(T key) {
        Node node = treeSearch(this.root, key);
        if (null == node) {
            return null;
        }
        Node p = node.p;
        Node left = node.left;
        Node right = node.right;
        Node newNode = null;

        if (null != left) {
            T newKey = getPrecursor(node.key);
            newNode = treeSearch(this.root, newKey);
        } else if (null != right) {
            T newKey = getSucceed(node.key);
            newNode = treeSearch(this.root, newKey);
        }

        if (null != newNode) {
            newNode.p = node.p;
            newNode.left = node.left;
            newNode.right = node.right;
        }

        node.withdraw();
        return node.value;
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
    private void inorderTreeWalkRecursion(Node centerNode) {
        if (null == centerNode) {
            return;
        }

        inorderTreeWalkRecursion(centerNode.left);
        System.out.print(centerNode.key + " ");
        inorderTreeWalkRecursion(centerNode.right);

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
        Node result = treeSearch(this.root, key);
        return null == result ? null : result.value;
    }

    private Node treeSearch(Node node, T key) {
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

    public T getMinKey() {
        return getMin(this.root);
    }

    public T getMaxKey() {
        return getMax(this.root);
    }

    /**
     * 获取对应节点的最小关键字元素
     * @param node
     * @return
     */
    private T getMin(Node node) {
        if (null == node) {
            return null;
        }
        Node p = node;
        Node left = p.left;
        while (null != left) {
            p = left;
            left = left.left;
        }
        return p.key;
    }

    /**
     * 获取对应节点的最大关键字元素
     * @param node
     * @return
     */
    private T getMax(Node node) {
        if (null == node) {
            return null;
        }
        Node p = node;
        Node right = p.right;
        while (null != right) {
            p = right;
            right = right.right;
        }
        return p.key;
    }

    /**
     * 寻找指定key的前驱
     * @param key
     * @return
     */
    public T getPrecursor(T key) {
        Node node = treeSearch(this.root, key);
        if (null == node) {
            return null;
        }
        Node left = node.left;
        if (null != left) {
            return getMax(left);
        }


        Node p = node.p;
        while (true) {
            if (null == p) {
                return null;
            }
            if (p.right == node) {
                return p.key;
            }
            node = p;
            p = p.p;
        }
    }

    /**
     * 寻找指定key的后继
     * @param key
     * @return
     */
    public T getSucceed(T key) {
        Node node = treeSearch(this.root, key);
        if (null == node) {
            return null;
        }
        Node right = node.right;
        if (null != right) {
            return getMin(right);
        }

        Node p = node.p;
        // 这个写法比获取前驱少了好多代码，可以思考下
        while (null != p && p.right == node) {
            node = p;
            p = p.p;
        }
        return null == p ? null : p.key;
    }

    /**
     * 节点替换
     * @param oldNode
     * @param newNode
     */
    private void replace(Node oldNode, Node newNode) {
        if (null == oldNode || null == newNode) {
            return;
        }
        newNode.p = oldNode.p;
        newNode.left = oldNode.left;
        newNode.right
        // TODO LILK 替换
    }

}
