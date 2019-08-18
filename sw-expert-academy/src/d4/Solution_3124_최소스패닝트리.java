package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * �ּ� ���д� Ʈ��
 */
public class Solution_3124_�ּҽ��д�Ʈ�� {
	static long Ans;
	static int[] p; // �θ� ������ �迭
	static int[] rank; // ��ũ�� ������ �迭, ���̳� �����̶�� �ϱ⿣ �ָ���

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= C; tc++) {
			Ans = 0;

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			p = new int[V + 1];
			rank = new int[V + 1];

			Edge[] ed = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				ed[i] = new Edge(start, end, weight);
			}

			Arrays.sort(ed);

			for (int i = 0; i < p.length; i++) {
				makeSet(i);
			}

			for (int i = 0; i < ed.length; i++) { // ����ġ�� ���� �������� �����ϱ�
				Edge e = ed[i];

				if (findSet(e.a) != findSet(e.b)) { // ���� �ٸ� ������ ��츸 ��ġ��
					unionSet(e.a, e.b);
					Ans += e.val;
				}
			}

			System.out.println("#" + tc + " " + Ans);
		}
	}

	/** ��� x�� �����ϴ� ���ο� ������ ���� */
	public static void makeSet(int x) {
		p[x] = x; // �θ� : �ڽ��� index�� ǥ�� or -1
//		rank[x] = 0; // �ʱⰪ 0�� // ���� ����
	}

	/** ��� x�� �����ϴ� ������ ��ǥ�ڸ� ���� */
	public static int findSet(int x) {
		if (x == p[x]) {
			return x;
		} else {
			p[x] = findSet(p[x]); // Path Compression
//			rank[x] = 1; // �� �ʿ䰡 ����. ��ǥ���� ����(��ũ)�� �˸� �ȴ�.
			return p[x];
		}
	}

	/** ��� x,y�� ��ǥ�ڸ� ���ؼ� �� ������ ���� */
	public static void unionSet(int x, int y) {
		int px = findSet(x); // ��ǥ�� �˾ƿ���
		int py = findSet(y);

		if (px != py) { // ���� �ٸ� ������ ��츸 ���ľ��Ѵ�. ���ѷ����� ��
//			p[py] = px; // �� ������ ��ǥ�ڸ� ��ġ��
			link(px, py);
		}
	}

	/** x�� ��ǥ���� ���հ� y�� ��ǥ���� ������ ��ħ, rank�� ���� */
	public static void link(int px, int py) {
		if (rank[px] > rank[py]) {
			p[py] = px; // ���� ���� ū �ʿ� ���δ�
		} else {
			p[px] = py;
			if (rank[px] == rank[py]) { // ���� ���� rank ���� �����Ѵ�.
				rank[py]++; // ������ ��ǥ�� ��ũ�� ������
			}
		}
	}

	public static class Edge implements Comparable<Edge> {
		int a; // ����1
		int b; // ����2
		int val; // ����ġ

		public Edge(int a, int b, int val) {
			this.a = a;
			this.b = b;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) { // �� ����
			return this.val - o.val;
		}
	}
} // end of class
