package test;

import heaps.FibonacciHeap;


public class FibonacciTest {

	private static final int N = 100;

	public static void main(String[] args) {

		System.out.println("Fibonnaci Test");

		int[] ary = new int[N];
		for(int i=0; i<N; i++) {
			ary[i] = (int)(Math.random() * 100);
		}

		FibonacciHeap hp = new FibonacciHeap(ary);
		
		for(int i=0; i<20; i++) {
		hp.decreaseValue((int)(Math.random()*N), (int)(Math.random()*50));
	}

		int val = -Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			int key = hp.findMin();
			if(hp.value(key) < val) {
				System.out.println("bug detected");
			}
			val = hp.value(key);
			System.out.print(val + " ");
			hp.deleteMin();
		}
	}
}
