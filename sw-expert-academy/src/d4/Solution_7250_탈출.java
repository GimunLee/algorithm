package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_7250_Ż�� {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	private static int N;
	private static int M;
	private static int ANS;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken()); // �� ũ��
			M = Integer.parseInt(st.nextToken()); // �� ũ��
			int K = Integer.parseInt(st.nextToken()); // ���� ���� �����ϴ� �ð�
			ANS = 0;

			char[][] map = new char[N][M];
			int[][] s_visited = new int[N][M];
			int[][] v_visited = new int[N][M];
			Queue<Pair> q = new LinkedList<>();

			// Pair type / 0:��ı, 1:�Ǵ�, 2:��
			for (int r = 0; r < N; r++) {
				String input = br.readLine();
				for (int c = 0; c < M; c++) {
					map[r][c] = input.charAt(c);
					// ��ı�� ���
					if (map[r][c] == 'S') {
						q.add(new Pair(r, c, 0, K)); // ������ ť�� ����
						map[r][c] = 'A';
					}
					if (map[r][c] == 'V') {
						q.add(new Pair(r, c, 1, 0));
						map[r][c] = 'A';
					}
					if (map[r][c] == 'F') {
						q.add(new Pair(r, c, 2, 0));
					}
				}
			}

			int time = 1;
			go: while (!q.isEmpty()) {
				int term = q.size();
				for (int i = 0; i < term; i++) {
					Pair p = q.poll();
					// �Ǵ��϶�, ��ı�϶�, ���϶�, ������ �ٸ�.
					switch (p.type) {
					case 2: // ���϶�,
						for (int j = 0; j < dr.length; j++) {
							int nR = p.r + dr[j];
							int nC = p.c + dc[j];
							if (!inRange(nR, nC)) {
								continue;
							}

							// �� �����̸� ���� �ٴ´�.
							if (map[nR][nC] == 'A') {
								map[nR][nC] = 'F';
								q.add(new Pair(nR, nC, 2, 0));
							}
						}
						break;
					case 0: // ��ı�϶�,
						for (int j = 0; j < dr.length; j++) {
							int nR = p.r + dr[j];
							int nC = p.c + dc[j];
							int NK = p.k;

							if (!inRange(nR, nC)) {
								continue;
							}

							// �����
							if (map[nR][nC] == 'A') {
								s_visited[nR][nC] = time;
								q.add(new Pair(nR, nC, 0, K));
							}

							// ���� ���
							if (map[nR][nC] == 'W') {
								if (NK >= 0) {
									s_visited[nR][nC] = time;
									q.add(new Pair(nR, nC, 0, --NK));
								}
							}

							if (map[nR][nC] == 'E') {
								// �����ߴµ�, visited�� �ִ� ��� ���� -1 break;
								if (v_visited[nR][nC] != 0) {
									ANS = -1;
									break go;
								}
								// �����ߴµ�, visited�� ���� ��� ���� �� �ڸ� �� ��� break;
								if (v_visited[nR][nC] == 0) {
									s_visited[nR][nC] = time;
									ANS = time;
									break go;
								}
							}
						}
						break;
					case 1: // �Ǵ��϶�,
						for (int j = 0; j < dr.length; j++) {
							int nR = p.r + dr[j];
							int nC = p.c + dc[j];

							if (!inRange(nR, nC)) {
								continue;
							}

							if (v_visited[nR][nC] != 0) {
								continue;
							}

							// ���̰ų�, �����
							if (map[nR][nC] == 'A' || map[nR][nC] == 'F') {
								v_visited[nR][nC] = time;
								q.add(new Pair(nR, nC, 1, 0));
							}

							// �����ߴµ�, visited�� ���� ��� ���� -1 �ϰ� break;
							if (map[nR][nC] == 'E') {
								if (s_visited[nR][nC] == 0) {
									ANS = -1;
									break go;
								}
							}
						}
						break;
					}
//					System.out.println();
//					for (int j = 0; j < v_visited.length; j++) {
//						System.out.println(Arrays.toString(s_visited[j]));
//					}
				} // end of for of term
				time++;
			} // end of while of q
			sb.append('#').append(tc).append(' ').append(ANS).append('\n');
		} // end of for of TestCase
		System.out.println(sb.toString());

	} // end of main

	private static boolean inRange(int r, int c) {
		if (r > N - 1 || c > M - 1 || r < 0 || c < 0) {
			return false;
		}
		return true;
	}

} // end of class

class Pair {
	int r;
	int c;
	int type;
	int k;

	Pair(int r, int c, int type, int k) {
		this.r = r;
		this.c = c;
		this.type = type;
		this.k = k;
	}
}
