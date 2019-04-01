package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1949_�������� {
	private static int K;
	private static int N;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static int max;
	private static int ans;
	private static boolean[][] visited;
	private static boolean flag;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken()); // N : �� ũ��
			K = Integer.parseInt(st.nextToken()); // K : ���� ���� ����

			map = new int[N][N];
			max = Integer.MIN_VALUE;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (max < map[r][c]) {
						max = map[r][c];
					}
				}
			}

			ans = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						visited = new boolean[N][N];
						test = new int[N][N];
						flag = false;
						dfs(i, j, 1);
					}
				}
			}

			System.out.printf("#%d %d\n", tc, ans);
		} // end of for TestCase

	} // end of main

	static int[][] test;

	private static void dfs(int r, int c, int len) {
		if (visited[r][c]) { // ���� ����
			return;
		}

		if (ans < len) {
			ans = len;
		}
		visited[r][c] = true;

		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i];
			int nC = c + dc[i];

			// ������ ����ų�, �湮�� ���̸�
			if (nR < 0 || nC < 0 || nR >= N || nC >= N || visited[nR][nC]) {
				continue;
			}

			if (map[r][c] <= map[nR][nC]) { // ��ƾ� �Ǵ� ���,
				if (!flag) {
					for (int j = 1; j <= K; j++) {
						int temp = map[nR][nC];
						map[nR][nC] -= j;

						if (map[r][c] > map[nR][nC]) {
							flag = true;
							dfs(nR, nC, len + 1);
							flag = false;
						}
						map[nR][nC] = temp;
					}
				}
			} else { // ���� �ʾƵ� �� ��,
				dfs(nR, nC, len + 1);
			}
		}
		visited[r][c] = false;
	}
} // end of class
