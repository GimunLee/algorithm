package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3190_�� {
	private static int[] dr = { -1, 0, 1, 0 }; // �������
	private static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // ������ ũ��
		int K = Integer.parseInt(br.readLine()); // ����� ����
		int[][] map = new int[N + 1][N + 1];
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 5;
		}

		int L = Integer.parseInt(br.readLine()); // ���� �̵� ��ȯ (�ð�, ����)
		int[][] commandArray = new int[L][2];
		for (int l = 0; l < L; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int X = Integer.parseInt(st.nextToken()); // ����
			int D = st.nextToken().charAt(0) == 'L' ? -1 : 1; // ����
			commandArray[l][0] = X;
			commandArray[l][1] = D;
		}
		// -- end of input

		int r = 1; // ù ��ġ
		int c = 1;
		int curDir = 1;
		int[][] queue = new int[10001][2];
		int front = -1, rear = -1;
		queue[++rear][0] = 1;
		queue[rear][1] = 1;

		int commandIndex = 0;
		map[1][1] = 9;
		int time = 1;
		while (true) {
			int nR = r + dr[curDir]; // �Ӹ��� �� ��ġ
			int nC = c + dc[curDir];

			if (nR > 0 && nC > 0 && nR <= N && nC <= N) {
				if (map[nR][nC] == 9) {
					break;
				} else if (map[nR][nC] == 5) { // ����� ��,
					map[nR][nC] = 9;
				} else if (map[nR][nC] == 0) {
					map[nR][nC] = 9;
					// ���� �����
					int tR = queue[++front][0];
					int tC = queue[front][1];
					map[tR][tC] = 0;
				}
				queue[++rear][0] = nR;
				queue[rear][1] = nC;

				if (commandIndex < L && commandArray[commandIndex][0] == time) {
					int D = commandArray[commandIndex][1];
					int turnDir = curDir + D;
					if (turnDir > 3) {
						turnDir = 0;
					} else if (turnDir < 0) {
						turnDir = 3;
					}
					curDir = turnDir;
					commandIndex++;
				}
				time++;
				r = nR;
				c = nC;
			} else {
				break;
			}
		}
		System.out.println(time);
	}
}
