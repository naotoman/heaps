package heaps;

import java.util.Arrays;

public class FibonacciHeap implements PriorityQ {

	private int val[];

	private int size;

	private int head;
	private int leftCh[];
	private int parent[];
	private int rightSib[];
	private int leftSib[];

	private int deg[];
	private int memo[];


	/**
	 * 指定された要素からなるFibonacciHeapを構成します。{@code ary}中のindexがkeyに、要素がvalに対応します。
	 * <p>時間計算量はO(n)です。
	 * @param ary ヒープに入れられる全要素
	 */
	public FibonacciHeap(int[] ary) {
		size = ary.length;
		val = new int[size];
		System.arraycopy(ary, 0, val, 0, size);
		leftCh = new int[size];
		parent = new int[size];
		rightSib = new int[size];
		leftSib = new int[size];
		Arrays.fill(leftCh, -1);
		Arrays.fill(parent, -1);
		deg = new int[size];
		memo = new int[size];
		Arrays.fill(memo, -1);
		head = 0;
		for(int i=1; i<size; i++) {
			if(val[i] < val[head]) {
				head = i;
			}
			rightSib[i-1] = i;
			leftSib[i] = i-1;
		}
		leftSib[0] = size-1;
		rightSib[size-1] = 0;
		memo[0] = head;
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
		return head;
	}

	@Override
	public void deleteMin() {

	}

	@Override
	public void decreaseValue(int key, int d) {
		// TODO Auto-generated method stub

	}

}
