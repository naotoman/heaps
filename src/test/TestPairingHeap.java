package test;

import heaps.PairingHeap;

/*
 * PairingHeapをテストするために用いたメイン関数を持つクラスです。
 * それ以上の意味はありません。
 */
public class TestPairingHeap {

	private static final int N = 100;

	public static void main(String[] args) {

		System.out.println("Pairing Test");

		int[] ary = new int[N];
		for(int i=0; i<N; i++) {
			ary[i] = (int)(Math.random() * 100);
		}

		PairingHeap hp = new PairingHeap(ary);

		//hp.show();

		for(int i=0; i<20; i++) {
			hp.decreaseValue((int)(Math.random()*N), (int)(Math.random()*50));
		}

		for(int i=0; i<N; i++) {
			int key = hp.findMin();
			int val = hp.value(key);
//			System.out.print(val + " ");
			hp.deleteMin();
		}
	}
}
