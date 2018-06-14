package test;

import heaps.D_Heap;
import heaps.FibonacciHeap;
import heaps.PairingHeap;
import heaps.PriorityQ;


public class MainTest {

	private static final int N = 1000000;

	public static void main(String[] args) {

		int[] ary = new int[N];
		for(int i=0; i<N; i++) {
			ary[i] = (int)(Math.random() * 10000000);
		}

		System.out.println("D_Heap: " + test(0, ary)/1000000.0);
		System.out.println("Pairing Heap: " + test(1, ary)/1000000.0);
		System.out.println("Fibonacci Heap: " + test(2, ary)/1000000.0);

	}

	private static void seqDel(PriorityQ hp) {
		for(int i=0; i<N; i++) {
//			int key = hp.findMin();
//			int val = hp.value(key);
//			System.out.print(val + " ");
			hp.deleteMin();
		}
	}

	private static long test(int x, int[] ary) {
		PriorityQ hp = null;
		long start = System.nanoTime();
		switch(x) {
		case 0: hp = new D_Heap(2, ary); break;
		case 1: hp = new PairingHeap(ary); break;
		case 2: hp = new FibonacciHeap(ary); break;
		}
		seqDel(hp);
		long end = System.nanoTime();
		return end - start;
	}

}
