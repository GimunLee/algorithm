package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * �Լ� ����, upload ����
 */

public class Solution_5650_�ɺ�����2 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	private static int[][] map;
	private static int N;
	private static ArrayList<Pair>[] wHole;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // N
			map = new int[N][N];
			Queue<Pair> q = new LinkedList<>();
			wHole = new ArrayList[5];

			for (int i = 0; i < wHole.length; i++) {
				wHole[i] = new ArrayList<>();
			}

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 0) {
						q.add(new Pair(r, c));
					}
					if (map[r][c] >= 6 && map[r][c] <= 10) {
						wHole[map[r][c] - 6].add(new Pair(r, c));
					}
				}
			} // end of for Input

			int max = Integer.MIN_VALUE;

			start: while (!q.isEmpty()) { // �������� ������
				Pair p = q.poll();
				int sR = p.r; // �����ϱ� ���� ������ R
				int sC = p.c; // �����ϱ� ���� ������ C

				dir: for (int i = 0; i < dr.length; i++) { // 4�������� ������
					int next_dir = i;
					int r = p.r;
					int c = p.c;
					int cnt = 0;
					here: while (true) {
						int nR = r + dr[next_dir];
						int nC = c + dc[next_dir];

						boolean isWall = inRange(nR, nC);
						// ������̰ų� ��Ȧ�̸� �����
						if (isWall && ((nR == sR && nC == sC) || map[nR][nC] == -1)) {
							max = (max < cnt) ? cnt : max;
							continue dir;
						}

						if (!isWall) { // ���̸�, 5�� ���� ����
							next_dir = block_dir[4][next_dir];
							r = nR;
							c = nC;
							cnt++;
							continue;
						}

						if (map[nR][nC] >= 1 && map[nR][nC] <= 5) { // ���̶��
							next_dir = block_dir[map[nR][nC]-1][next_dir];
							r = nR;
							c = nC;
							cnt++;
							continue here;
						}

						if (map[nR][nC] >= 6 && map[nR][nC] <= 10) { // ��Ȧ�̶��
							Pair wp = get_wHole(nR, nC);
							r = wp.r;
							c = wp.c;
							continue here;
						}
						// ��ġ �̵�
						r = nR; 
						c = nC;
						
					} // end of here:while
				} // end of dir:while
			} // end of start:while
			System.out.println("#" + tc + " " + max);
		} // end of for TestCase
	} // end of main

	// 0:��, 1:��, 2:��, 3:��
	private static int[][] block_dir = { 
			{ 2, 3, 1, 0 }, // 1�� ��
			{ 1, 3, 0, 2 }, // 2�� ��
			{ 3, 2, 0, 1 }, // 3�� ��
			{ 2, 0, 3, 1 }, // 4�� ��
			{ 2, 3, 0, 1 } // 5�� ��
	};

	/** �� ��ġ�� ��Ȧ�� ������, �ٸ� ��ġ�� ��Ȧ�� ��ǥ�� ��ȯ */
	private static Pair get_wHole(int r, int c) {
		int idx = map[r][c] - 6;
		if (wHole[idx].get(0).r == r && wHole[idx].get(0).c == c) {
			return new Pair(wHole[idx].get(1).r, wHole[idx].get(1).c);
		}else {
			return new Pair(wHole[idx].get(0).r, wHole[idx].get(0).c);
		}
	} // end of get_wHole()

	private static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N) {
			return false;
		}
		return true;
	} // end of inRange()

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
