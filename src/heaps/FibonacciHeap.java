package heaps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * <p>{@code PriorityQ}インターフェースのFibonacci Heapによる実装です。
 * 規定の計算量(Amortized)が達成できるように実装しました。しかし、実際はかなり遅いです。
 */
public class FibonacciHeap implements PriorityQ {

	private int val[];

	private int size;

	private int head;
	private int child[];
	private int parent[];
	private int right[];
	private int left[];

	private int deg[];

	private int memo[];
	private boolean mark[];


	/**
	 * 指定された要素からなるFibonacciHeapを構成します。{@code ary}中のindexがkeyに、要素がvalに対応します。
	 * <p>時間計算量はO(n)です。
	 * @param ary ヒープに入れられる全要素
	 */
	public FibonacciHeap(int[] ary) {
		size = ary.length;
		val = new int[size];
		System.arraycopy(ary, 0, val, 0, size);
		child = new int[size];
		parent = new int[size];
		right = new int[size];
		left = new int[size];
		Arrays.fill(child, -1);
		Arrays.fill(parent, -1);
		deg = new int[size];
		memo = new int[size];
		Arrays.fill(memo, -1);
		mark = new boolean[size];
		head = 0;
		for(int i=1; i<size; i++) {
			if(val[i] < val[head]) {
				head = i;
			}
			right[i-1] = i;
			left[i] = i-1;
		}
		left[0] = size-1;
		right[size-1] = 0;
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

	/**
	 * {@inheritDoc}
	 * <p>時間計算量(Amortized)はO(logn)です。
	 */
	@Override
	public void deleteMin() {
		if(size-- == 1) return;
		if(child[head] != -1) {
			int ch = child[head];
			parent[ch] = -1;
			while(right[ch] != child[head]) {
				ch = right[ch];
				parent[ch] = -1;
			}
		}
		int last = child[head];
		//ポインタ付け替え
		if(child[head] == -1) {
			right[left[head]] = right[head];
			left[right[head]] = left[head];
			last = right[head];
		}
		else if(right[head] != head) {
			int ch = child[head];
			right[left[head]] = child[head];
			left[right[head]] = left[ch];
			right[left[ch]] = right[head];
			left[ch] = left[head];
		}
		//consolidate
		int now = last;
		int next = right[now];
		do {
			now = next;
			next = right[now];
			int x = now;
			int d = deg[x];
			while(memo[d] != -1) {
				int y = memo[d];
				if(val[x] > val[y]) {
					int tmp = x; x = y; y = tmp;
				}
				link(y, x);
				memo[d] = -1;
				d++;
			}
			memo[d] = x;
			head = x;
		} while(now != last);
		//headの更新とmemoの初期化
		int minKey = head;
		int n = head;
		memo[deg[n]] = -1;
		while(right[n] != head) {
			n = right[n];
			memo[deg[n]] = -1;
			if(val[n] < val[minKey]) minKey = n;
		}
		head = minKey;
		assert deleteMinCheck();
	}

	/**
	 * {@inheritDoc}
	 * <p>時間計算量(Amortized)はO(1)です。
	 */
	@Override
	public void decreaseValue(int key, int d) {
		val[key] -= d;
		int y = parent[key];
		if(y != -1 && val[key] < val[y]) {
			cut(key, y);
			cascadingCut(y);
		}
		if(val[key] < val[head]) {
			head = key;
		}
	}

	private void cut(int x, int y) {
		right[left[x]] = right[x];
		left[right[x]] = left[x];
		child[y] = right[x];
		if(right[x] == x) {
			child[y] = -1;
		}
		deg[y]--;
		right[x] = head;
		left[x] = left[head];
		right[left[x]] = x;
		left[head] = x;
		parent[x] = -1;
		mark[x] = false;

	}

	private void cascadingCut(int y) {
		int z = parent[y];
		if(mark[y] && z != -1) {
			cut(y, z);
			cascadingCut(z);
		}
		else {
			mark[y] = true;
		}
	}

	private void link(int y, int x) {
		assert(x!=y && x!=-1 && y!=-1);
		right[left[y]] = right[y];
		left[right[y]] = left[y];
		parent[y] = x;
		if(child[x] == -1) {
			child[x] = y;
			left[y] = right[y] = y;
		}
		else {
			int ch = child[x];
			right[y] = ch;
			left[y] = left[ch];
			right[left[y]] = y;
			left[ch] = y;
		}
		deg[x]++;
		mark[y] = false;
	}

	/*
	 * deleteMinの後にルートが持つ子の数がdistinctになっているか調べる。
	 */
	private boolean deleteMinCheck() {
		Set<Integer> rc = new HashSet<>();
		rc.add(deg[head]);
		int now = right[head];
		while(now != head) {
			if(rc.contains(deg[now])) {
				return false;
			}
			rc.add(deg[now]);
			now = right[now];
		}
		return true;
	}

}
