package common;

import java.io.Serializable;

public class AVLTree<Key extends Comparable, Value> implements Serializable {
	private Node root;
	private int size;

	class Node implements Serializable {
		// 键值，订单ID,String
		Key key;
		// 订单路径，String
		Value value;

		Node leftChild;
		Node rightChild;
		Node father;

		public Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}

	}

	// 获取结点高度
	private int getHeight(Node node) {
		if (node == null)
			return 0;
		else {

			int leftH = getHeight(node.leftChild);

			int rightH = getHeight(node.rightChild);

			if (leftH > rightH)
				return leftH + 1;
			else
				return rightH + 1;
		}
	}

	// 查找,外部接口
	public Value find(Key k) {
		Node node = root;
		return find(k, node);
	}

	// 查找,内部实现,两个订单号不可能相同
	private Value find(Key k, Node node) {
		if (node == null)
			return null;
		else if (k.equals(node.key))
			return node.value;
		else {
			Value leftV = find(k, node.leftChild);
			return leftV == null ? find(k, node.rightChild) : leftV;
		}
	}

	// 增加一个元素
	public boolean add(Key k, Value v) {
		Node node = root;
		if (root == null) {
			root = new Node(k, v);
			size++;
			return true;
		} else
			return add(node, k, v);

	}

	//
	private boolean add(Node node, Key k, Value v) {
		if (k.compareTo(node.key) < 0) {
			if (node.leftChild == null) {
				Node left = new Node(k, v);
				left.father = node;
				node.leftChild = left;
				size++;
				adjust(left);

				return true;
			} else
				return add(node.leftChild, k, v);

		} else if (k.compareTo(node.key) > 0) {
			if (node.rightChild == null) {
				Node right = new Node(k, v);
				right.father = node;
				node.rightChild = right;
				size++;
				adjust(right);

				return true;
			} else
				return add(node.rightChild, k, v);
		} else
			// 相同订单不对劲

			return false;

	}

	// 判断树的平衡情况
	private void adjust(Node buttom) {
		Node node = buttom;
		node = node.father;
		while (node.father != null) {
			node = node.father;
			int dH = getHeight(node.leftChild) - getHeight(node.rightChild);

			if (dH * dH > 1) {

				// 失去平衡,四种情况
				if (buttom.key.compareTo(node.key) < 0) {
					// 左树高
					if (buttom.key.compareTo(node.leftChild.key) < 0)
						// 右单旋
						rightSingleRotate(node);
					else
						// 右双旋
						rightDoubleRotate(node);
				} else {
					// 右树高
					if (buttom.key.compareTo(node.rightChild.key) > 0)
						// 左单旋{

						leftSingleRotate(node);

					else
						// 左双旋
						leftDoubleRotate(node);
				}
			}
		}
	}

	// 右树高，左单旋，参数为最小不平衡子树结点
	private void leftSingleRotate(Node node) {
		// 操作结点和右子树

		Node right = node.rightChild;

		node.rightChild = right.leftChild;

		right.leftChild = node;

		right.father = node.father;

		if (node.rightChild != null)
			node.rightChild.father = node;

		if (node != root) {

			if (node == node.father.leftChild) {

				right.father.leftChild = right;
			} else {
				right.father.rightChild = right;

			}
		} else
			root = right;

		node.father = right;

	}

	// 左树高，右单旋，参数位最小不平衡子树结点
	private void rightSingleRotate(Node node) {

		// 操作结点和右子树
		Node left = node.leftChild;

		node.leftChild = left.rightChild;
		left.rightChild = node;

		left.father = node.father;

		if (node.leftChild != null)
			node.leftChild.father = node;

		if (node != root) {

			if (node == node.father.rightChild)
				left.father.rightChild = left;
			else
				left.father.leftChild = left;
		} else
			root = left;

		node.father = left;
	}

	// 右树高，左双旋，参数为最小不平衡子树结点
	private void leftDoubleRotate(Node node) {
		// 右子树右单旋，结点左单旋
		rightSingleRotate(node.rightChild);
		leftSingleRotate(node);
	}

	// 左树高，右双旋，参数为最小不平衡子树结点
	private void rightDoubleRotate(Node node) {
		// 左子树左单旋，结点右单旋
		leftSingleRotate(node.leftChild);
		rightSingleRotate(node);
	}

	public int getSize() {
		return size;
	}

	public int getInfoByKeyword(String time) {
		return getInfoByKeyword(time, root);
	}

	private int getInfoByKeyword(String time, Node next) {
		if (next == null) {
			return 0;
		}

		int num = getInfoByKeyword(time, next.leftChild) + getInfoByKeyword(time, next.rightChild);
		if (((String) (next.key)).contains(time)) {
			num++;
		}
		return num;

	}

	// public void watch() {
	// watch(root);
	// }

	// private void watch(Node node) {
	// if (node != null) {
	// System.out.println(node.key);
	// watch(node.leftChild);
	// watch(node.rightChild);
	// }
	// }

	// 测试方法
	// public int maxHeight() {
	// Node node = root;
	// return getHeight(node);
	// }
	//
	// public Value rootValue() {
	// return root.value;
	// }

	// 测试
	// public static void main(String[] args) {
	// AVLTree<Integer, String> t = new AVLTree<Integer, String>();
	//
	// for (int i = 0; i < 10000; i++) {
	// t.add(i, i + "");
	//
	// }
	//
	// }
}
