package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import heaps.D_Heap;
import heaps.FibonacciHeap;
import heaps.PairingHeap;
import heaps.PairingHeap2;
import heaps.PriorityQ;


public class DijkstraBenchMark {

	private static class Edge {
		private int d;
		private int to;

		private Edge(int d, int to) {this.d = d; this.to= to;}
	}


	private static final Random rand = new Random(45290139L);

	private static final int N = 10000;//頂点数
	private static final int E = 30000000;//辺数
	private static final int MAX_W = 10;

	private static List<List<Edge>> graph = new ArrayList<>(N);//有向グラフ（強連結であることを保証していない）


	public static void main(String[] args) {
		makeGraph();
		System.out.println("graph created.");
		double mil = 1000000;

//		System.out.println("D_Heap: " + test(0)/mil);
//		System.out.println("Pairing Heap: " + test(1)/mil);
//		System.out.println("Pairing2: " + test(2)/mil);
//		System.out.println("Fibonacci Heap: " + test(3)/mil);

		long[] mins = new long[4];
		mins[0] = mins[1] = mins[2] = mins[3] = Long.MAX_VALUE;
		for(int i=0; i<4; i++) {
			for(int c=0; c<5; c++) {
				mins[i] = Math.min(test(i), mins[i]);
			}
		}

		System.out.println("D_Heap: " + mins[0]/mil);
		System.out.println("Pairing Heap: " + mins[1]/mil);
		System.out.println("Pairing2: " + mins[2]/mil);
		System.out.println("Fibonacci Heap: " + mins[3]/mil);

	}



	private static long test(int x) {
		int[] dist = new int[N];
		Arrays.fill(dist, 1<<25);
		dist[0] = 0;
		PriorityQ hp = null;
		long start = System.nanoTime();
		switch(x) {
		case 0: hp = new D_Heap(Math.max(2, E/N), dist); break;
		case 1: hp = new PairingHeap(dist); break;
		case 2: hp = new PairingHeap2(dist); break;
		case 3: hp = new FibonacciHeap(dist); break;
		}
		while(hp.size() > 0) {
			int u = hp.findMin();
			for(Edge e : graph.get(u)) {
				int alt = hp.value(u) + e.d;
				if(hp.value(e.to) > alt) {
					hp.decreaseValue(e.to, hp.value(e.to) - alt);
				}
			}
			dist[u] = hp.value(u);
//			System.out.print(dist[u] + " ");
			hp.deleteMin();
		}
		long end = System.nanoTime();
		return end - start;
	}

	private static void makeGraph() {
		int[] outDeg = new int[N];
		setOutDeg(outDeg);

		int[] vSeq = IntStream.range(0, N).toArray();

		for(int v=0; v<N; v++) {
			graph.add(new ArrayList<>(outDeg[v]));
			int top = N;
			for(int i=0; i<outDeg[v]; i++) {
				int x = rand.nextInt(top);
				top--;
				swap(vSeq, x, top);
				if(vSeq[top] == v) {i--; continue;}
				Edge e = new Edge(rand.nextInt(MAX_W)+1, vSeq[top]);
				graph.get(v).add(e);
			}
		}

	}

	private static void setOutDeg(int[] deg) {
		int top = N;
		for(int i=0; i<E; i++) {
			int v = rand.nextInt(top);
			deg[v]++;
			if(deg[v] == N-1) {
				top--;
				swap(deg, v, top);
			}
		}
		//shuffle
		for(int i=0; i<N; i++) {
			int x = rand.nextInt(N);
			swap(deg, i, x);
		}
	}

	private static void swap(int[] ma, int i, int j) {
		int tmp = ma[i];
		ma[i]= ma[j];
		ma[j] = tmp;
	}
}
