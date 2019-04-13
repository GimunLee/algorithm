package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2115_����ä�� {
	private static int[][] map;
	private static int C;
	private static int M;
	private static int N;
	private static boolean[][] isChoice;
	private static ArrayList<Pair> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // N:���� ũ��
			M = Integer.parseInt(st.nextToken()); // M:������ �� �ִ� ������ ����
			C = Integer.parseInt(st.nextToken()); // C:���� ä���� �� �ִ� �ִ��

			map = new int[N][N];
			list = new ArrayList<>();
			comb_bucket = new int[2][M];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					list.add(new Pair(r, c));
				}
			}

			isChoice = new boolean[N][N];

			ans = Integer.MIN_VALUE;
			chooceBucket(0, 0);

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end of for TestCase
		System.out.println(sb.toString());
	} // end of main

	private static int tmp;
	private static int ans;
	private static int[][] comb_bucket;

	private static void chooceBucket(int idx, int len) {
		if (len == 2) {
			tmp = 0;
			dfs(comb_bucket[0], 0, 0, 0); // ������, ��������, ä���� �뷮, ����
			dfs(comb_bucket[1], 0, 0, tmp); // ������, ��������, ä���� �뷮, ����
			if (ans < tmp) {
				ans = tmp;
			}
			return;
		}

		here: for (int i = idx; i < N * N; i++) {
			Pair p = list.get(i);
			int r = p.r;
			int c = p.c;
			// 2���� üũ
			for (int j = 0; j < M; j++) {
				if (c + j >= N) {
					continue here;
				}

				if (isChoice[r][c + j]) {
					continue here;
				}
			}
			// ���� ä���
			for (int j = 0; j < M; j++) {
				comb_bucket[len][j] = i + j;
				isChoice[r][c + j] = true;
			}
			chooceBucket(i + M, len + 1);
			for (int j = 0; j < M; j++) {
				isChoice[r][c + j] = false;
			}
		}
	} // end of dfs()

	private static void dfs(int[] each_bucket, int idx, int honey, int benefit) {
		if (tmp < benefit) {
			tmp = benefit;
		}
		if (idx == M) { // ������ ä��������,
			return;
		}

		int r = list.get(each_bucket[idx]).r;
		int c = list.get(each_bucket[idx]).c;

		if (map[r][c] > C || honey + map[r][c] > C) { // �뷮�� ��������
			return;
		}
		
		dfs(each_bucket, idx + 1, honey + map[r][c], benefit + (int) Math.pow(map[r][c], 2)); // ���� ���
		dfs(each_bucket, idx + 1, honey, benefit); // �� ���� ���
	}

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
