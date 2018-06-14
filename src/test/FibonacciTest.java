package test;

import heaps.FibonacciHeap;


public class FibonacciTest {

	private static final int N = 1000000;

	public static void main(String[] args) {
		
		System.out.println("Fibonnaci Test");

		int[] ary = new int[N];
		for(int i=0; i<N; i++) {
			ary[i] = (int)(Math.random() * 100000);
		}

		FibonacciHeap hp = new FibonacciHeap(ary);

		for(int i=0; i<N; i++) {
			int key = hp.findMin();
			int val = hp.value(key);
			//ystem.out.print(val + " ");
			hp.deleteMin();
		}
	}
}
