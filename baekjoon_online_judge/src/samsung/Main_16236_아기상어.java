package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16236_�Ʊ��� {
	static int[] dr = { -1, 0, 0, 1 }; // ���¿���
	static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 20

		int[][] map = new int[N][N];
		int[][] visited = new int[N][N];
		int[] cntFish = new int[7]; // 0�� �Ⱦ�
		int[][] queue = new int[200][2];
		int front = -1, rear = -1;

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) { // �Ʊ� ���
					queue[++rear][0] = r;
					queue[rear][1] = c;
					map[r][c] = 0;
				} else if (map[r][c] != 0) {
					cntFish[map[r][c]]++;
					// �� ������ ū �ְ� �� ����ŭ ���ٸ� �Ұ����� �����
				}
			}
		} // end of for(input)

		int time = 1;
		int cntEat = 0;
		int level = 2;

		while (rear != front) {

			int size = rear;
			while (front < size) {
				int r = queue[++front][0];
				int c = queue[front][1];

				for (int i = 0; i < dr.length; i++) { // �� �� �� �� Ȯ��
					int nR = r + dr[i];
					int nC = c + dc[i];

					if (nR < 0 || nC < 0 || nR >= N || nC >= N) { // ���� �ʰ�
						continue;
					}

					if (visited[nR][nC] != 0) {
						continue;
					}

					if (map[nR][nC] > level) { // �Ʊ� �� ���� �� ���� ���
						continue;
					}

					if (map[nR][nC] != 0 && map[nR][nC] != level) { // ���� ����� ���� �� �ִ� ������ ���,
						cntFish[map[nR][nC]]--;
						cntEat++;
						if (cntEat == level) {
							level++; // ������
							cntEat = 0;
						}
						map[nR][nC] = 0;
						// -- �ʱ�ȭ �۾�
						visited = new int[N][N];
						front = -1;
						rear = -1;
						queue[++rear][0] = nR;
						queue[rear][1] = nC;
						visited[nR][nC] = time;
						break;
					}else {
						queue[++rear][0] = nR;
						queue[rear][1] = nC;
						visited[nR][nC] = time;
					}

				}
			}
			time++;
		} // end of while(queue)

		System.out.println(time);
	}
}
