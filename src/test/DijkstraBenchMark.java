package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import heaps.D_Heap;
import heaps.FibonacciHeap;
import heaps.PairingHeap;
import heaps.PriorityQ;


public class DijkstraBenchMark {

	private static class Edge {
		private int d;
		private int to;

		private Edge(int d, int to) {this.d = d; this.to= to;}
	}

	private static final int N = 10000;//頂点数
	private static final double DENS = 0.3;//有向グラフとしてみたときの辺密度（期待値）
	private static final int MAX_W = 20;

	private static List<List<Edge>> graph = new ArrayList<>(N);//有向グラフ（強連結であることを保証していない）

	private static int[] dist0 = new int[N];
	private static int[] dist1 = new int[N];
	private static int[] dist2 = new int[N];

	public static void main(String[] args) {
		makeGraph();

		System.out.println("D_Heap: " + test(0, dist0)/1000000.0);
		System.out.println("Pairing Heap: " + test(1, dist1)/1000000.0);
		System.out.println("Fibonacci Heap: " + test(2, dist2)/1000000.0);

		if(!Arrays.equals(dist0, dist1)) {System.out.println("0, 1");}
		if(!Arrays.equals(dist1, dist2)) {System.out.println("1, 2");}
//		for(int i=0; i<N; i++) {
//			System.out.print(dist0[i] + " ");
//		}
	}

	private static void makeGraph() {
		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i == j) continue;
				if(Math.random() < DENS) {
					int w = (int)(Math.random()*MAX_W)+1;
					Edge e = new Edge(w, j);
					graph.get(i).add(e);
				}
			}
		}
	}

	private static long test(int x, int[] dist) {
		Arrays.fill(dist, 1<<20);
		dist[0] = 0;
		PriorityQ hp = null;
		long start = System.nanoTime();
		switch(x) {
		case 0: hp = new D_Heap(2, dist); break;
		case 1: hp = new PairingHeap(dist); break;
		case 2: hp = new FibonacciHeap(dist); break;
		}
		while(hp.size() > 0) {
			int u = hp.findMin();
			for(Edge e : graph.get(u)) {
				int alt = hp.value(u) + e.d;
				if(hp.value(e.to) > alt) {
					hp.decreaseValue(e.to, hp.value(e.to) - alt);
					dist[e.to] = alt;
				}
			}
			hp.deleteMin();
		}
		long end = System.nanoTime();
		return end - start;
	}
}
