package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UP_Main_17070_�������ű�� {
	static final int[] dr = { 0, 1, 1 }; // ��(��, ��, �밢��)
	static final int[] dc = { 1, 0, 1 }; // ��(��, ��, �밢��)
	private static int[][] map; // ������ 2���� �迭 ����
	private static int N; // ������ ũ��

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // ������ ũ��
		map = new int[N + 1][N + 1]; // �ܰ��� �Ⱦ��� ������ ��ĭ���� ����

		for (int i = 0; i < map.length; i++) {
			map[i][N] = 1;
			map[N][i] = 1;
		}

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // end of for(input)

		// ������ ���� ��ġ (1,1) (1,2) ���� ����
		dfs(0, 1, 0, 0); // �迭�� 0���� �����ϱ� ������ �������� �־��� �ͺ��� 1�� ���� ����
		System.out.println(ans); // ���� ���
	} // end of main

	private static int ans = 0; // ������ ������ ����

	/** r:�� ��ǥ, c:����ǥ, pos:���� ��ġ(0:����, 1:����, 2:�밢), dir : �������� �� ����(0:��,1:��,2:�밢) */
	private static void dfs(int r, int c, int pos, int dir) {
		map[r][c] = 5;

		if (r == N - 1 && c == N - 1) { // ���������� ����������
			ans++; // ���� ����
			return;
		}

		// 3���� Ž�� ����
		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i];
			int nC = c + dc[i];

			// ���� ���
			if (map[nR][nC] == 1) {
				continue;
			}

			// �밢���� ��,
			if (i == 2 && (map[nR - 1][nC] == 1 || map[nR][nC - 1] == 1)) {
				continue;
			}

			// ������ ������ ������ ��, ���δ� �ȵ�
			if (pos == 0 && i == 1) {
				continue;
			}
			// ������ ������ ������ ��, ���δ� �ȵ�
			if (pos == 1 && i == 0) {
				continue;
			}

			// ��� ���ǿ� �����ϴ� ���, ���̿켱Ž��
			dfs(nR, nC, i, i);
		} // end of func(dfs)
	} // end of main
} // end of class
