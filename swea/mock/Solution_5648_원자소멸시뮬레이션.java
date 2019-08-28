package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 2019.08.28.(��)
 * */
public class Solution_5648_���ڼҸ�ùķ��̼� {
	private static int[] dy = { -1, 1, 0, 0 }; // ��, �� (row)
	private static int[] dx = { 0, 0, -1, 1 }; // ��, �� (column)

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		Ball[] originArray = new Ball[1001]; // �����̰� ������ ��ġ�� �迭
		Ball[] moveArray = new Ball[1001]; // ������ �� ���� �迭
		Ball[] tmpArray = new Ball[1001]; // ������ �� ���ҳ��� �浹�ϴ��� üũ�ϴ� �迭

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			Ball[][] map = new Ball[2001][2001];

			int originIndex = 0;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = 1000 + Integer.parseInt(st.nextToken());
				int y = 1000 - Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());

				Ball ball = new Ball(x, y, dir, k);
				map[y][x] = ball;
				originArray[originIndex++] = ball;
			} // end of for(Input)

			int ANS = 0;
			int moveIndex = 0;

			while (originIndex != 0) {
				moveIndex = 0;
				for (int i = 0; i < originIndex; i++) { // ������������
					Ball ball = originArray[i];

					if (map[ball.y][ball.x] == null) { // �� Ÿ���� ������ �������ٴ� ��
						continue;
					}

					int nX = ball.x + dx[ball.dir];
					int nY = ball.y + dy[ball.dir];

					if (nY > 2000 || nX > 2000 || nX < 0 || nY < 0) { // ���� ����� ���
						map[ball.y][ball.x] = null;
						continue;
					}

					// ���� �̹� ���ְ�, ���� ������ ������ �������� ���� ��� ( 0.5�ʿ� ������ ���)
					if (map[nY][nX] != null && map[nY][nX].dir == nextBallDir(ball.dir)) {
						ANS += (map[nY][nX].k + ball.k);
						map[nY][nX] = null;
						map[ball.y][ball.x] = null;
						continue;
					}

					// ���� �̵��߿� �浹���� �ʴ� �� ������ �� ����
					map[ball.y][ball.x] = null;
					ball.x = nX;
					ball.y = nY;
					moveArray[moveIndex++] = ball;
				} // ������ ��, ������ ���

				originIndex = 0;
				int tmpIndex = 0;
				// ���������̴ϱ� �ε����� ���
				for (int i = 0; i < moveIndex; i++) {
					Ball ball = moveArray[i];
					if (map[ball.y][ball.x] == null) {
						map[ball.y][ball.x] = ball;
						originArray[originIndex++] = ball;
					} else { // ���ڰ� ���� �ð��뿡 �浹�ϴ� ���
						tmpArray[tmpIndex++] = ball;
						ANS += (map[ball.y][ball.x].k + ball.k);
						map[ball.y][ball.x].k = 0; // �ٸ��� �ͼ� �浹�� �� �����Ƿ� ���ܳ����� (3�� �浹��)
					}
				}

				// �浹�� �� �����ֱ�
				for (int i = 0; i < tmpIndex; i++) {
					Ball ball = tmpArray[i];
					map[ball.y][ball.x] = null;
				}
			}
			sb.append("#").append(tc).append(" ").append(ANS).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main

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
