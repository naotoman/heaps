package heaps;

import java.util.Arrays;

/**
 * <p>{@code PriorityQ}インターフェースのPairingHeapによる実装です。
 * 規定の計算量(Amortized)が達成できるように実装しました。
 */
public class PairingHeap implements PriorityQ {

	private int val[];

	private int size;

	private int leftCh[];
	private int rightSib[];
	private int leftSib[];
	private int root;


	/**
	 * 指定された要素からなるPairingHeapを構成します。{@code ary}中のindexがkeyに、要素がvalに対応します。
	 * @param ary ヒープに入れられる全要素
	 */
	public PairingHeap(int[] ary) {
		size = ary.length;
		val = new int[size];
		System.arraycopy(ary, 0, val, 0, size);
		leftCh = new int[size];
		rightSib = new int[size];//root以外で有効
		leftSib = new int[size];//root以外で有効.また、各階層上で最も左のノードではその親ノードを指す．
		Arrays.fill(leftCh, -1);
		Arrays.fill(rightSib, -1);
		root = -1;
		for(int i=0; i<size; i++) {
			root = merge(root, i);
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>時間計算量はO(1)です。
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 * <p>時間計算量はO(1)です。
	 */
	@Override
	public int value(int key) {
		return val[key];
	}

	/**
	 * {@inheritDoc}
	 * <p>時間計算量はO(1)です。
	 */
	@Override
	public int findMin() {
		return root;
	}

	/**
	 * {@inheritDoc}
	 * <p>時間計算量(Amortized)はO(logn)です。
	 */
	@Override
	public void deleteMin() {
		size--;
		root = mergePairs(leftCh[root]);
	}

	/**
	 * {@inheritDoc}
	 * <p>時間計算量(Amortized)はO(logn)です。
	 */
	@Override
	public void decreaseValue(int key, int d) {
		val[key] -= d;
		if(key == root) return;
		if(rightSib[key] >= 0) {
			leftSib[rightSib[key]] = leftSib[key];
		}
		if(leftCh[leftSib[key]] == key) {
			leftCh[leftSib[key]] = rightSib[key];
		}
		else {
			rightSib[leftSib[key]] = rightSib[key];
		}
		root = merge(key, root);
	}

	private int mergePairs(int n) {
		if(n < 0) return -1;
		if(rightSib[n] < 0) return n;
		int next = rightSib[rightSib[n]];
		return merge(merge(n, rightSib[n]), mergePairs(next));
	}

	private int merge(int a, int b) {
		if(a < 0) return b;
		if(b < 0) return a;
		if(val[a] > val[b]) {
			int tmp = a; a = b; b = tmp;//swap(a, b)
		}
		if(leftCh[a] >= 0) {
			leftSib[leftCh[a]] = b;
		}
		rightSib[b]= leftCh[a];
		leftCh[a]= b;
		leftSib[b] = a;
		return a;
	}

//	public void show() {
//		int N = 10;
//		System.out.println("root: " + root);
//		System.out.print("val: ");
//		for(int i=0; i<N; i++) {
//			System.out.print(val[i] + " ");
//		}
//		System.out.print("\nleftCH: ");
//		for(int i=0; i<N; i++) {
//			System.out.print(leftCh[i] + " ");
//		}
//		System.out.print("\nleftSib: ");
//		for(int i=0; i<N; i++) {
//			System.out.print(leftSib[i] + " ");
//		}
//		System.out.print("\nrightSib: ");
//		for(int i=0; i<N; i++) {
//			System.out.print(rightSib[i] + " ");
//		}
//	}

}
