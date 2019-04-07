package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_���μ��������ϱ�2 {
	private static int[][] map;
	private static ArrayList<Core> list_core;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 12
			map = new int[N][N];
			list_core = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken()); // ���� ȸ���� ���� ���� ����
					if (map[r][c] == 1) { // �ھ��� ��,
						if (r != 0 && c != 0 && r != N - 1 && c != N - 1) { // �ܰ��� Ž������ �ʴ´�.
							list_core.add(new Core(r, c));
						}
					}
				}
			} // end of input

			for (int i = 0; i < list_core.size(); i++) {
				System.out.println(list_core.get(i).r + ", " + list_core.get(i).c);
			}

			ans_core = 0;
			ans_len = 0;
			// 1. �ھ�� 4�������� ��������.
			dfs(0, 0, 0); // �ھ� �ε���, ����� �ھ� ����, ������ ����

		} // end of for TestCase

	} // end of main

	private static int ans_core;
	private static int ans_len;

	private static void dfs(int idx, int cnt_core, int len) {
		if (ans_len < len) { // ���� �� �� ����
			return;
		}

		if (idx == list_core.size()) { // ��� �ھ��� Ž���� ��������,
			if (cnt_core > ans_core) { // �ھ ���� ���� ����Ǿ���
				ans_len = ans_len > len ? len : ans_len; // ���� ���� ����
			}
		}

		int r = list_core.get(idx).r;
		int c = list_core.get(idx).c;

		for (int i = 0; i < dr.length; i++) { // ��, ��, ��, ��
			int nR = r + dr[i];
			int nC = c + dc[i];

			// ��ġ �� �� �ִ��� Ȯ��, ��ġ�ϰ� dfs ������, �ٽ� ���󺹱�

			
			// ��ġ ���ϸ� �״�� ��������
			

		}
	} // end of dfs()

	private static class Core {
		int r;
		int c;

		Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

} // end of class
