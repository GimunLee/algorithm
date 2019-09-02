package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2019.08.28.(��)
 * 2019.09.02.(��) Upload
 */
public class Solution_UP_5648_���ڼҸ�ùķ��̼� {
	private static int[] dy = { -1, 1, 0, 0 }; // ��, �� (row)
	private static int[] dx = { 0, 0, -1, 1 }; // ��, �� (column)

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(); // ������ �ѹ��� ����ϱ� ���� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		Ball[] fixedArray = new Ball[1001]; // ���ڵ��� ���� ��ġ�� ������ �迭
		Ball[] moveArray = new Ball[1001]; // ���ڵ��� ������ �� ���� �迭
		Ball[] crushArray = new Ball[1001]; // ���� ������ ������ �� �浹�ϴ��� üũ�ϴ� �迭

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // ���ڵ��� ����
			Ball[][] map = new Ball[2001][2001]; // ������ ����

			int fixedIndex = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = 1000 + Integer.parseInt(st.nextToken()); // ������ �����ϱ� ���� ó��
				int y = 1000 - Integer.parseInt(st.nextToken());
				// ������ ��ǥ y�� ���, ��ǻ�Ϳ����� row�Դϴ�. ������, ���� ��ǥ y�� ��ǻ�� row�� ������ �ݴ��̹Ƿ� ������ ���ݴϴ�.

				int dir = Integer.parseInt(st.nextToken()); // ���� ����
				int k = Integer.parseInt(st.nextToken()); // ���� ������ ����
				Ball ball = new Ball(x, y, dir, k); // ���� ����
				map[y][x] = ball; // ������ ������ �����ǿ� ����
				fixedArray[fixedIndex++] = ball; // ���ʿ��� ������ ��ġ�̹Ƿ� fixedArray�� ����
			} // end of for(Input)

			int ANS = 0; // ���� ���� ����

			while (fixedIndex != 0) {
				int moveIndex = 0; // ���ڵ��� �����̰� �����ϱ� ���� �ε��� �ʱ�ȭ
				for (int i = 0; i < fixedIndex; i++) { // ����� ���ڵ��� ����������
					Ball ball = fixedArray[i]; // ���� ����
					if (map[ball.y][ball.x] == null) { // fixedArray���� �ִµ� map�� null�̶�� �� �Ҹ�� ���ڶ�� ��
						continue;
					}
					int nX = ball.x + dx[ball.dir]; // ���� ��ġ (column)
					int nY = ball.y + dy[ball.dir]; // ���� ��ġ (row)

					if (nY > 2000 || nX > 2000 || nX < 0 || nY < 0) { // ���� ����� ���
						map[ball.y][ball.x] = null; // ���� ��ġ�� null�� �������
						continue;
					}

					// ���� ��ġ�� �̹� ���Ұ� �ְ�, ���� ������ ������ ���� ���� ������ ���� ��� (0.5�ʿ� ������ ���)
					if (map[nY][nX] != null && map[nY][nX].dir == nextBallDir(ball.dir)) {
						ANS += (map[nY][nX].k + ball.k); // ���� �Ҹ��ϸ鼭 ������ ����
						map[nY][nX] = null; // ���� ��ġ�� null
						map[ball.y][ball.x] = null; // ���� ��ġ�� null
						continue;
					}

					// ���� �̵� �߿� �浹���� �ʴ� �� ������ �� ����
					// �̵��Ѵٰ� �ش� map�� ���ڷ� ä���� ���� (�����̰� �浹�� �� �����Ƿ� ���� ��ġ�� �ƴ�)
					map[ball.y][ball.x] = null; // ���� ��ġ�� null�� ��
					ball.x = nX;
					ball.y = nY;
					moveArray[moveIndex++] = ball;
				}

				// ���ڵ��� ��������
				fixedIndex = 0; // ���� ��ġ �ʱ�ȭ
				int crushIndex = 0; // �����̰� ���� �浹�ϴ� ���Ҹ� �����ϱ� ���� �ʱ�ȭ
				// ���������̴ϱ� �ε����� ���
				for (int i = 0; i < moveIndex; i++) { // ������ ���ڵ��� Ž��
					Ball ball = moveArray[i]; // ���� ������ ����
					if (map[ball.y][ball.x] == null) { // ������ ��ġ�� null�̶��
						map[ball.y][ball.x] = ball; // �ش� ���ڸ� ����
						fixedArray[fixedIndex++] = ball; // ������ �ڸ��� ǥ�� (�ش� �ð��� �浹���� ����������, null���� üũ�ϱ� ������ �������)
					} else { // ���ڰ� ���� �ð��뿡 �浹�ϴ� ���
						crushArray[crushIndex++] = ball; // �浹�� �迭�� ����
						ANS += (map[ball.y][ball.x].k + ball.k); // ������ ���� (�� �ڸ��� �ִ� ���ڿ� ���� ����)
						map[ball.y][ball.x].k = 0; // �ٸ��� ���ڰ� �ͼ� �浹�� �� �����Ƿ� null�� ǥ���ϸ� �ȵ� (3�� �浹��)
					}
				} //

				// �浹�� �� ���ڵ��� �ѹ��� �����ֱ�
				for (int i = 0; i < crushIndex; i++) {
					Ball ball = crushArray[i];
					map[ball.y][ball.x] = null;
				}
			} // end of while
			sb.append("#").append(tc).append(" ").append(ANS).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main

	/** ���ڰ� ���� �� �������� ������ ��ȯ�ϴ� ������ */
	private static int nextBallDir(int dir) {
		if (dir == 0) {
			return 1;
		} else if (dir == 1) {
			return 0;
		} else if (dir == 2) {
			return 3;
		} else {
			return 2;
		}
	} // end of func(nextBallDir)

	private static class Ball {
		int x, y, dir, k;

		public Ball(int x, int y, int dir, int k) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}
	} // end of Ball
} // end of class
