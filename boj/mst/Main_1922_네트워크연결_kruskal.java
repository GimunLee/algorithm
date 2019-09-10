package boj.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Prim : 340ms kruscal : 540ms (rank �߰�)
 */
public class Main_1922_��Ʈ��ũ����_kruskal {
	private static int N, M;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // ��ǻ���� ��
		M = Integer.parseInt(br.readLine().trim()); // ������ �� �ִ� ���� ��

		map = new int[N + 1][N + 1];
		Edge[] weight = new Edge[M];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken()); // ���� ��ǻ�� (s <-> e)
			int c = Integer.parseInt(st.nextToken()); // ���

			map[s][e] = c;
			map[e][s] = c;
			weight[i] = new Edge(s, e, c);
		}

		Arrays.sort(weight);

		p = new int[N + 1];
		rank = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			p[i] = i; // ���ʿ� �ڱ� �ڽ��� �θ�
		}

		int ANSER = kruscal(weight);
		System.out.println(ANSER);

	} // end of main

	private static int kruscal(Edge[] weight) {
		int ANSER = 0;
		int cnt = N - 1;
		for (int i = 0; i < weight.length; i++) {
			Edge edge = weight[i];
			int node1 = edge.s;
			int node2 = edge.e;

			int p1 = find(node1);
			int p2 = find(node2);
			if (p1 != p2) {
				union(p1, p2);
				ANSER += edge.c;
				if (--cnt == 0) {
					return ANSER;
				}
			}
		}
		return ANSER;
	}

	private static int[] p;

	/** ���� �׷� ã�� */
	private static int find(int num) {
		if (p[num] == num) {
			return p[num];
		} else {
			return p[num] = find(p[num]);
		}
	}

	private static int[] rank;

	private static void union(int num1, int num2) {

		int p1 = find(num1);
		int p2 = find(num2);

		if (p1 == p2) {
			return;
		}

		if (rank[p1] < rank[p2]) {
			p[p1] = p2;
		} else {
			if (rank[p2] == rank[p1]) {
				rank[p1]++;
			}
			p[p2] = p1;
		}

		/**
		 * ���� if (rank[p1] == rank[p2]) { p[p2] = p1; rank[p1]++; return; }
		 * 
		 * if (rank[p1] < rank[p2]) { p[p1] = p2; } else { p[p2] = p1; }
		 */
		return;
	}

	private static class Edge implements Comparable<Edge> {
		int s, e, c;

		public Edge(int s, int e, int c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return this.c - o.c;
		}
	}

} // end of Class
