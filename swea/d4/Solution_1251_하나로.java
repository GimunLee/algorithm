package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [S/W �����ذ� ����] 4���� - �ϳ���
 */
public class Solution_1251_�ϳ��� {
	static double Ans;

	static int[] p; // �θ� ������ �迭
	static int[] rank; // ��ũ�� ������ �迭, ���̳� �����̶�� �ϱ⿣ �ָ���

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int C = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= C; tc++) {
			Ans = 0;
			int N = Integer.parseInt(br.readLine().trim()); // ���� ��

			StringTokenizer st_x = new StringTokenizer(br.readLine(), " ");
			StringTokenizer st_y = new StringTokenizer(br.readLine(), " ");

			Node[] nd = new Node[N];
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st_x.nextToken());
				int y = Integer.parseInt(st_y.nextToken());

				nd[i] = new Node(i, x, y);
			}

			double duty = Double.parseDouble(br.readLine());
			
			int E = (N * (N - 1)) / 2; // �� ������ ��

			p = new int[N + 1];
			rank = new int[N + 1];

			Edge[] ed = new Edge[E];
			int index = 0;

			for (int i = 0; i < nd.length; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) {
						break;
					}

					double val = (double)(duty
							* (Math.pow(Math.abs(nd[i].x - nd[j].x), 2) + Math.pow(Math.abs(nd[i].y - nd[j].y), 2)));

					ed[index++] = new Edge(nd[i].index, nd[j].index, val);
				}
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
			System.out.println("#" + tc + " " + Math.round(Ans));
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

	public static class Node {
		int index;
		int x;
		int y;

		public Node(int index, int x, int y) {
			this.index = index;
			this.x = x;
			this.y = y;
		}
	}

	public static class Edge implements Comparable<Edge> {
		int a; // ����1
		int b; // ����2
		double val; // ����ġ

		public Edge(int a, int b, double val) {
			this.a = a;
			this.b = b;
			this.val = val;
		}

		@Override
		public int compareTo(Edge o) { // �� ����
			if(this.val - o.val < 0) {
				return -1;
			}else if(this.val - o.val > 0) {
				return 1;
			}else {
				return 0;
			}
		}
	}
} // end of class
