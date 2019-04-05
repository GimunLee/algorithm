package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ��ȣ��Ÿ ���� => MST (ũ�罺Į �˰��� Ȱ��) / ���� < ũ�罺Į makeSet(int x) ��� x�� �����ϴ� ���ο� ������
 * ���� findSet(int x) ��� x�� �����ϴ� ������ ��ǥ�ڸ� ���� union(int x, int y) ��� x,y �� ��ǥ�ڸ�
 * ���ؼ� �� ������ ���� link(int px, int py) x�� ��ǥ���� ���հ� y�� ��ǥ���� ������ ��ħ
 */
public class Solution_3289_���μ����� {
	static int[] p; // �θ� ������ �迭
	static int[] rank; // ��ũ�� ������ �迭, ���̳� �����̶�� �ϱ⿣ �ָ���

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			p = new int[n + 1];
			rank = new int[n + 1];

			for (int j = 1; j <= n; j++) {
				makeSet(j);
			}

			String ans = "";
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int chk = Integer.parseInt(st.nextToken());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());

				if (chk == 0) { // ������
					unionSet(num1, num2);
				} else { // �˻�
					if (findSet(num1) == findSet(num2)) {
						ans += "1";
					} else {
						ans += "0";
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end of for TestCase
		System.out.print(sb.toString());
	} // end of main

	/** p �迭 ��� */
	public static void printSet() {
		System.out.println();
		System.out.print("index : ");
		for (int i = 0; i < p.length; i++) {
			System.out.printf("%2d ", i);
		}
		System.out.print("\nparent: ");
		for (int i = 0; i < p.length; i++) {
			System.out.printf("%2d ", p[i]);
		}
		System.out.println();
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

} // end of class
