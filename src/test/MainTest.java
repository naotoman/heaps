package test;

import heaps.D_Heap;

/**
 * テストするために用いたメイン関数を持つクラスです。
 * それ以上の意味はありません。
 */
public class MainTest {

	private static final int N = 30;

	public static void main(String[] args) {

		int[] ary = new int[N];
		for(int i=0; i<N; i++) {
			ary[i] = (int)(Math.random() * 100);
		}

		D_Heap hp = new D_Heap(2, ary);
//		hp.show();
		for(int i=0; i<5; i++) {
			hp.decreaseValue((int)(Math.random()*N), (int)(Math.random()*100) - 50);
		}
		for(int i=0; i<N; i++) {
			int key = hp.findMin();
			int val = hp.value(key);
			hp.deleteMin();
			System.out.print(val + " ");
		}
	}
}
