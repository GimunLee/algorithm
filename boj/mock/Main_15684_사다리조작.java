package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15684_��ٸ����� {
	private static int[] chkLineCnt; // �� ���μ����� ���� ���μ��� ��
	private static int N, H, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // ���μ��� �� (column)
		M = Integer.parseInt(st.nextToken()); // ���μ��� �� (row)
		H = Integer.parseInt(st.nextToken()); // ���μ����� ���μ��� ���� �� �ִ� ��

		int[][] map = new int[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
		}
		if (H == 0) {
			System.out.println(0);
			return;
		}
		ANSER = Integer.MAX_VALUE;
		solve(map, 1, 0); // 1���� �� �̾ƺ�
		if (ANSER == Integer.MAX_VALUE || ANSER > 3) {
			System.out.println("-1");
		} else {
			System.out.println(ANSER);
		}
	} // end of main

	private static int ANSER;

	// chooseC�� chooseC+1 ���̿� ��ٸ��� ���´�. (chooseC���� chooseC+1�� ���� ��)
	private static void solve(int[][] map, int chooseC, int cnt) {
		if (cnt > 3 || chooseC == N) {
			return;
		}

		if (check(map)) {
			ANSER = ANSER > cnt ? cnt : ANSER;
			return;
		}

		// ��ġ�� �� �ִٸ�
		for (int r = 1; r <= H; r++) { // �� ���μ����� ���� ��� ���θ� ����.
			if (map[r][chooseC - 1] != 1 && map[r][chooseC] != 1 && map[r][chooseC + 1] != 1) { // ������
				// �ٸ��� ���� ������.
				map[r][chooseC] = 1;
				chkLineCnt[chooseC]++;
				chkLineCnt[chooseC + 1]++;
				solve(map, chooseC, cnt + 1);
				map[r][chooseC] = 0;
				chkLineCnt[chooseC]--;
				chkLineCnt[chooseC + 1]--;
			}
		}
		solve(map, chooseC + 1, cnt);
	}

	private static boolean check(int[][] map) {
		for (int c = 1; c < N; c++) {
			int r = 1; // �׻� �������� ����
			int moveC = c;
			while (true) {
				if (map[r][moveC - 1] == 1) { // �������� �� �� �ִٸ�
					moveC--;
				} else if (map[r][moveC] == 1) { // ���������� �� �� �ִٸ�
					moveC++;
				}
				if (++r == H + 1) {
					break;
				}
			}
			if (moveC != c) {
				return false;
			}
		}
		return true;
	} // end of fucn(check)

	private static void print(int[][] map) {
		System.out.println();
		for (int r = 1; r < H + 1; r++) {
			for (int c = 1; c < N; c++) {
				System.out.print(" | ");
				System.out.print(map[r][c]);
				if (c == N - 1) {
					System.out.print(" | ");
				}
			}
			System.out.println();
		}
	} // end of func(print)
} // end of class
