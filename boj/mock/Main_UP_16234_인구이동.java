package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_UP_16234_�α��̵� {
	private static int[] dr = { -1, 1, 0, 0 }; // �����¿� (������ ���� Ž���ϱ� ���� ���� ����)
	private static int[] dc = { 0, 0, -1, 1 };
	private static int N, L, R;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // �� ���� (N x N)
		// ���漱�� �����ϴ� �� ������ �α��� ���� L�� �̻� R�� ����
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N]; // �� ����

		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < map.length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // end of for(Input)

		int ANSER = solve(map); // �Է����� �־��� map ������ ���� ���ǿ� �°� Ž��
		System.out.println(ANSER); // ���� ���
	} // end of main

	/** ���� BFS�� Ž���ϸ鼭 �α��̵��� �� �� �ִٸ� �α��̵��� ���Ѻ��� ���� ��ȯ */
	private static int solve(int[][] map) {
		int ANSER = 0; // ���� ����
		int[][] queue = new int[2500][2]; // �� �������� ���� ���� ������ ���� ������ Queue
		int[][] moveQueue = new int[2500][2]; // �ѹ��� BFS�� ������ �α��� �̵����Ѻ� ���� ��ǥ�� ������ Queue

		boolean flag; // Ž���� ������ ���θ� �Ǻ��ϴ� ����, false�� ��� �α��̵��� �غ� ���

		while (true) { // �α� �̵��� �Ұ����� ������ �ݺ�
			boolean[][] visited = new boolean[N][N]; // BFS Ž����, �湮�� ������ �Ǻ��ϴ� ����
			flag = false; // �α� �̵��� �Ͼ�ٸ�, true�� �ٲ㼭 Ž���� ������

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c]) { // �̹� Ž���� �� ���̶��
						continue;
					}

					int front = -1, rear = -1; // Queue �ʱ�ȭ
					int mFront = -1, mRear = -1; // moveQueue �ʱ�ȭ

					queue[++rear][0] = r;
					queue[rear][1] = c; // Ž���� ������ ��ġ�� Queue�� ����

					int sum = 0, cnt = 0; // �� ������ ������ ������ �հ� ����

					while (front != rear) { // �� ������ ������ ���� ���� Ž���� ���� ������
						int cR = queue[++front][0]; // Queue���� ���� ���� ��ǥ�� ������
						int cC = queue[front][1];

						moveQueue[++mRear][0] = cR; // �α� �̵��� �Ͼ �� �����Ƿ� moveQueue�� ���� �� ��ǥ ����
						moveQueue[mRear][1] = cC;

						sum += map[cR][cC]; // ���� ���� �α����� sum�� ����
						cnt++; // ���� ����

						visited[cR][cC] = true; // �湮 ǥ��

						for (int dir = 0; dir < dr.length; dir++) { // ������ 4���� Ž����
							int nR = cR + dr[dir]; // ������ ���� ���� ��ġ
							int nC = cC + dc[dir];

							if (nR >= 0 && nC >= 0 && nR < N && nC < N) { // ���� ����� �ʴ´ٸ�,
								if (visited[nR][nC]) { // ���� ��ġ�� �湮�� ���̶��
									continue;
								}

								// ������ ���� ���� ������ �� �� �ִ��� Ȯ��
								int sub = Math.abs(map[nR][nC] - map[cR][cC]);
								if (L <= sub && sub <= R) { // �� ���� ���̰� L �̻� R ���϶��
									queue[++rear][0] = nR; // ������ ���̹Ƿ�, Queue�� ���� �� Ž����Ŵ
									queue[rear][1] = nC;
									visited[nR][nC] = true;
								}
							}
						} // end of for(Direction)
					} // end of while(�� ���� ���� ������ �� ����)

					if (cnt <= 1) { // ������ ���� �ڱ� �ڽ� �ۿ� ���ٸ�, ���� ��ǥ�� Ž��
						continue;
					}

					flag = true; // �̹� Ž������ �α� �̵��� ���������Ƿ�, flag�� true�� �ٲ���

					while (mFront != mRear) { // �α� �̵� ��� ������ �α� �̵� ��Ŵ
						map[moveQueue[++mFront][0]][moveQueue[mFront][1]] = sum / cnt;
					}
				}
			} // end of for(��� ��ǥ Ž��)

			if (!flag) { // �α� �̵��� �Ͼ�� �ʾҴٸ�,
				break; // �����ε� �Ͼ �� �����Ƿ� ����
			}
			ANSER++; // �α� �̵� Ƚ�� ����
		}
		return ANSER; // �α� �̵� Ƚ�� ��ȯ
	} // end of func(solve)
} // end of class
