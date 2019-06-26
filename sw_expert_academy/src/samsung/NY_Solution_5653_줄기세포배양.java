package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NY_Solution_5653_�ٱ⼼����� {

	static int[][] map;
	static int[][] timeCheck; // �ѹ� �湮�� ���� �湮 �� �� ������ üũ�� ����
	static boolean[][] isDie;
	static Queue<Cell> q;
	static int K;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int N = Integer.parseInt(st.nextToken()); // R : ����ũ��
			int M = Integer.parseInt(st.nextToken()); // C : ����ũ��
			K = Integer.parseInt(st.nextToken()); // K : �ð�

			map = new int[N + 2 * K][M + 2 * K];
			timeCheck = new int[N + 2 * K][M + 2 * K];
			isDie = new boolean[N + 2 * K][M + 2 * K];
			q = new LinkedList<Cell>();

			int CenterN = (N + 2 * K) / 2;
			int CenterM = (M + 2 * K) / 2;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < M; c++) {
					map[CenterN + r][CenterM + c] = Integer.parseInt(st.nextToken());
					timeCheck[CenterN + r][CenterM + c] = map[CenterN+r][CenterM + c];
					if (map[CenterN + r][CenterM + c] != 0) {
						q.add(new Cell(CenterN + r, CenterM + c)); // ������ ������ Queue�� �־���
					}
				}
			}

			// ���� ���� ����
			int Ans = bfs();

			sb.append('#').append(tc).append(' ').append(Ans).append('\n');
		} // end of for of TestCase
		System.out.println(sb.toString());
	} // end of main

	private static int bfs() {
		int time = 0;
		int totalCnt = 0;
		int dieCnt = 0;

		while (!q.isEmpty()) {
			if (time == K) { // ������ �ð��� ������,
				// ����ִ� �ٱ⼼��(��Ȱ������, Ȱ������)�� ���� ����
				return totalCnt - dieCnt;
			}
			time++;

			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Cell c = q.poll();

				// Ȱ��ȭ�� �ȵ�����
				timeCheck[c.r][c.c] -= 1;
				if (timeCheck[c.r][c.c] != 0) {
					q.add(new Cell(c.r, c.c));
					continue;
				}

				if (timeCheck[c.r][c.c] == (-1) * map[c.r][c.c]) {
					dieCnt++;
					isDie[c.r][c.c] = true;
					continue;
				}

				for (int d = 0; d < dc.length; d++) {
					int nR = c.r + dr[d];
					int nC = c.c + dc[d];

					// ������ �������
					if (nR < 0 || nC < 0 || nR > map.length || nC > map[0].length - 1) {
						continue;
					}

					// �̹� ���� ������
					if (isDie[nR][nC]) {
						continue;
					}

					// ���� Time�� ���� �ڸ��� ����Ϸ��� �ϴ���,
					if (map[nR][nC] != 0) {
						// �������� �ϴ� �Ͱ� �̹� �ִ� �Ͱ� ���Ͽ� ū ���� ����
						if (map[nR][nC] < map[c.r][c.c]) {
							map[nR][nC] = map[c.r][c.c];
							timeCheck[nR][nC] = map[c.r][c.c];
						}
					}

					// �ƹ��� ������� �ʾҴٸ� �ڽ��� ���
					if (map[nR][nC] == 0) {
						map[nR][nC] = map[c.r][c.c];
						timeCheck[nR][nC] = map[c.r][c.c];
						q.add(new Cell(nR, nC));
						totalCnt++;
					}

					q.add(new Cell(c.r, c.c)); // ���̱� ����
				}
			}
		}

		return -1;
	} // end of bfs()

} // end of class

class Cell {
	int r; // ��
	int c; // ��

	Cell(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
