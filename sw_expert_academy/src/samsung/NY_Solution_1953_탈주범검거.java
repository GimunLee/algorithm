package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BFS�� ¥�� 
 *
 */
public class NY_Solution_1953_Ż�ֹ��˰� {
	static int[][] map;
	static boolean[][] visited;
	static int L;
	static int Ans;

	// �� �� �� �� ��� �Ͽ� ���� ����
	static int[] dr = { -1, 1, 0, 0, 1, -1, -1, 1, -1, 1, 1, -1 };
	static int[] dc = { 0, 0, -1, 1, 1, -1, 1, -1, -1, 1, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			Ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int R = Integer.parseInt(st.nextToken()); // ���� ũ��
			int C = Integer.parseInt(st.nextToken()); // ���� ũ��
			int startR = Integer.parseInt(st.nextToken()); // ��Ȧ r
			int startC = Integer.parseInt(st.nextToken()); // ��Ȧ c
			L = Integer.parseInt(st.nextToken()); // �ҿ� �ð�

			map = new int[R][C];
			visited = new boolean[R][C];

			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < C; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(map[startR][startC], startR, startC, 1); // r, c, L : �ð�

//			System.out.println("========================");
//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(visited[i]));
//			}

			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (visited[r][c]) {
						Ans++;
					}

				}
			}

			sb.append('#').append(tc).append(' ').append(Ans).append('\n');
		}
		System.out.println(sb.toString());
	} // end of main

	private static void dfs(int type, int r, int c, int l) {
		visited[r][c] = true;
		if (l == L) { // �ҿ�ð��� �� ������,
			return;
		}

		if (type == 1) { // �����¿� �ͳ�
			for (int i = 0; i < 4; i++) {
				int nR = r + dr[i];
				int nC = c + dc[i];

				if (!inRange(nR, nC)) {
					return;
				}

				// ����ƴ��� Ȯ��,
				if (i == 0) { // ��
					if (map[nR][nC] == 0 || map[nR][nC] == 3 || map[nR][nC] == 4 || map[nR][nC] == 7) {
						continue;
					}
				}

				if (i == 1) { // ��
					if (map[nR][nC] == 0 || map[nR][nC] == 3 || map[nR][nC] == 5 || map[nR][nC] == 6) {
						continue;
					}
				}

				if (i == 2) { // ��
					if (map[nR][nC] == 0 || map[nR][nC] == 2 || map[nR][nC] == 6 || map[nR][nC] == 7) {
						continue;
					}
				}

				if (i == 3) { // ��
					if (map[nR][nC] == 0 || map[nR][nC] == 2 || map[nR][nC] == 4 || map[nR][nC] == 5) {
						continue;
					}
				}
				dfs(map[nR][nC], nR, nC, l + 1);
			}

		} else if (type == 2) {
			for (int i = 0; i < 2; i++) {
				int nR = r + dr[i];
				int nC = c + dc[i];

				if (!inRange(nR, nC)) {
					return;
				}

				// ����ƴ��� Ȯ��,
				if (i == 0) { // ��
					if (map[nR][nC] == 0 || map[nR][nC] == 3 || map[nR][nC] == 4 || map[nR][nC] == 7) {
						continue;
					}
				}

				if (i == 1) { // ��
					if (map[nR][nC] == 0 || map[nR][nC] == 3 || map[nR][nC] == 5 || map[nR][nC] == 6) {
						continue;
					}
				}

				dfs(map[nR][nC], nR, nC, l + 1);
			}
		} else if (type == 3) {
			for (int i = 2; i < 4; i++) {
				int nR = r + dr[i];
				int nC = c + dc[i];

				if (!inRange(nR, nC)) {
					return;
				}

				// ����ƴ��� Ȯ��,
				if (i == 2) { // ��
					if (map[nR][nC] == 0 || map[nR][nC] == 2 || map[nR][nC] == 6 || map[nR][nC] == 7) {
						continue;
					}
				}

				if (i == 3) { // ��
					if (map[nR][nC] == 0 || map[nR][nC] == 2 || map[nR][nC] == 4 || map[nR][nC] == 5) {
						continue;
					}
				}
				dfs(map[nR][nC], nR, nC, l + 1);
			}
		} else if (type == 4) {
			for (int i = 4; i < 6; i++) {
				int nR = r + dr[i];
				int nC = c + dc[i];

				if (!inRange(nR, nC)) {
					return;
				}

				// ����ƴ��� Ȯ��,
				if (i == 4) { // ���
					if (map[nR][nC] == 0 || map[nR][nC] == 2 || map[nR][nC] == 5) {
						continue;
					}
				}

				if (i == 5) { // ���
					if (map[nR][nC] == 0 || map[nR][nC] == 3 || map[nR][nC] == 7) {
						continue;
					}
				}
				dfs(map[nR][nC], nR, nC, l + 1);
			}
		} else if (type == 5) {
			for (int i = 6; i < 8; i++) {
				int nR = r + dr[i];
				int nC = c + dc[i];

				if (!inRange(nR, nC)) {
					return;
				}

				// ����ƴ��� Ȯ��,
				if (i == 6) { // �Ͽ�
					if (map[nR][nC] == 0 || map[nR][nC] == 2 || map[nR][nC] == 4) {
						continue;
					}
				}

				if (i == 7) { // ����
					if (map[nR][nC] == 0 || map[nR][nC] == 3 || map[nR][nC] == 6) {
						continue;
					}
				}
				dfs(map[nR][nC], nR, nC, l + 1);
			}
		} else if (type == 6) {
			for (int i = 8; i < 10; i++) {
				int nR = r + dr[i];
				int nC = c + dc[i];

				if (!inRange(nR, nC)) {
					return;
				}

				// ����ƴ��� Ȯ��,
				if (i == 8) { // ����
					if (map[nR][nC] == 0 || map[nR][nC] == 2 || map[nR][nC] == 7) {
						continue;
					}
				}

				if (i == 9) { // ����
					if (map[nR][nC] == 0 || map[nR][nC] == 5 || map[nR][nC] == 3) {
						continue;
					}
				}
				dfs(map[nR][nC], nR, nC, l + 1);
			}
		} else if (type == 7) {
			for (int i = 10; i < 12; i++) {
				int nR = r + dr[i];
				int nC = c + dc[i];

				if (!inRange(nR, nC)) {
					return;
				}

				// ����ƴ��� Ȯ��,
				if (i == 10) { // ����
					if (map[nR][nC] == 0 || map[nR][nC] == 6 || map[nR][nC] == 2) {
						continue;
					}
				}

				if (i == 11) { // �»�
					if (map[nR][nC] == 0 || map[nR][nC] == 3 || map[nR][nC] == 4) {
						continue;
					}
				}
				dfs(map[nR][nC], nR, nC, l + 1);
			}
		}
	} // end of dfs()

	private static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r > map.length - 1 || c > map[0].length - 1) {
			return false;
		}
		return true;
	} // end of inRange()

} // end of class
