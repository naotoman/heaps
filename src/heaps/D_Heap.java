package heaps;

/**
 * <p>{@code PriorityQ}インターフェースのd-ary heapによる実装です。
 * コンストラクタで何分木にするか指定します。
 */
public class D_Heap implements PriorityQ {

	private final int D;

	private int val[];

	private int size;
	private int hp[];
	private int hpRev[];


	/**
	 * 木の分岐数が第一引数の{@code D}であるヒープを構成します。
	 * 第二引数の{@code ary}は、ヒープに入れられる全要素です。{@code ary}中のindexがkeyに、要素がvalに対応します。
	 * <p>時間計算量はO(n)です。
	 * @param D 木の分岐数{@code (>=2)}
	 * @param ary ヒープに入れられる全要素
	 */
	public D_Heap(int D, int[] ary) {
		this.D = D;
		size = ary.length;
		val = new int[size];
		System.arraycopy(ary, 0, val, 0, size);
		hp = new int[size+1];
		for(int i=0; i<size; i++) {
			hp[i+1] = i;
		}
		if(size == 1) return;
		for(int k=(size+D-2)/D; k>=1; k--) {
			downward(k);
		}
		hpRev = new int[size];
		for(int i=1; i<=size; i++) {
			hpRev[hp[i]] = i;
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
		return hp[1];
	}

	/**
	 * {@inheritDoc}
	 * <p>時間計算量はO(D*(log_D(n)))です。
	 */
	@Override
	public void deleteMin() {
		hp[1] = hp[size];
		hpRev[hp[1]] = 1;
		size--;
		downward(1);
	}

	/**
	 * {@inheritDoc}
	 * <p>時間計算量は、d>0のときはO((log_D(n)))で、d<0のときはO(D*(log_D(n)))です。
	 */
	@Override
	public void decreaseValue(int key, int d) {
		val[key] -= d;
		if(d > 0) upward(hpRev[key]);
		else downward(hpRev[key]);
	}

	private void downward(int p) {
		int ch = D * (p - 1) + 2;
		if(ch > size) return;
		int rc = Math.min(D*p+1,  size);
		int minC = ch;
		for(; ch<=rc; ch++) {
			if(val[hp[ch]] < val[hp[minC]]) {
				minC = ch;
			}
		}
		if(val[hp[minC]] < val[hp[p]]) {
			swap(minC, p);
			downward(minC);
		}
	}

	private void upward(int c) {
		if(c == 1) return;
		int p = (size+D-2) / D;
		if(val[hp[c]] < val[hp[p]]) {
			swap(p, c);
			upward(p);
		}
	}

	private void swap(int i, int j) {
		int tmp = hp[i];
		hp[i] = hp[j];
		hp[j] = tmp;
		hpRev[hp[i]] = i;
		hpRev[hp[j]] = j;
	}

}
