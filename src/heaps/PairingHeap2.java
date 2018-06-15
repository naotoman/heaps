package heaps;

/**
 * {@link heaps#PairingHeap}の中身の実装を代えた版。結果、実行時間はむしろ少し遅くなったので、
 * このクラスは使わないでください。
 */
public class PairingHeap2 implements PriorityQ {

	private static class Node {
		private int key, val;
		private Node child = null;
		private Node right = null;
		private Node left = null;

		private Node(int key, int val) {this.key = key; this.val = val;}
	}

	private Node[] node;//decrease-key用

	private int size;
	private Node root;


	public PairingHeap2(int[] ary) {
		size = ary.length;
		node = new Node[size];
		node[0] = new Node(0, ary[0]);
		root = node[0];
		for(int i=1; i<size; i++) {
			node[i] = new Node(i, ary[i]);
			root = merge(root, node[i]);
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int value(int key) {
		return node[key].val;
	}

	@Override
	public int findMin() {
		return root.key;
	}

	@Override
	public void deleteMin() {
		size--;
		root = mergePairs(root.child);
	}

	@Override
	public void decreaseValue(int key, int d) {
		Node n = node[key];
		n.val -= d;
		if(n == root) return;
		if(n.right != null) {
			n.right.left = n.left;
		}
		if(n.left.child == n) {
			n.left.child = n.right;
		}
		else {
			n.left.right = n.right;
		}
		root = merge(n, root);
	}

	private Node mergePairs(Node n) {
		if(n == null) return null;
		int num = 1;
		Node rgt = n;
		while(rgt.right != null) {
			num++;
			rgt = rgt.right;
		}
		Node rt = null;
		if(num%2 == 1) {
			rt = rgt;
			rgt = rt.left;
		}

		Node pa = n.left;
		while(rgt != pa) {
			Node next = rgt.left.left;
			rt = merge(merge(rgt.left, rgt), rt);
			rgt = next;
		}
		return rt;
	}

	private Node merge(Node a, Node b) {
		if(a == null) return b;
		if(b == null) return a;
		if(a.val > b.val) {
			Node tmp = a; a = b; b = tmp;
		}
		if(a.child != null) {
			a.child.left = b;
		}
		b.right = a.child;
		a.child = b;
		b.left = a;
		return a;
	}

}
