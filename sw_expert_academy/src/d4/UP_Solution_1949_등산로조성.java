package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UP_Solution_1949_�������� {
	private final static int[] dr = { -1, 1, 0, 0 }; // ��(����)
	private final static int[] dc = { 0, 0, -1, 1 }; // ��(�¿�)
	private static int[][] map; // ������
	private static boolean[][] visited; // Ž���ߴ��� Ȯ���ϴ� ����
	private static int N; // ������ ��� �� ũ��
	private static int K; // ������ ���� �� �ִ� �ִ� ���� 
	private static int max; // ���� ���� ���츮�� ������ ����
	private static int ans; // ���� ����

	private static boolean flag; // �ѹ� ��Ҵ��� �����ϴ� ����

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) { // Test Case �� ��ŭ �ݺ�
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // N : �� ũ��
			K = Integer.parseInt(st.nextToken()); // K : ���� ���� ����
			map = new int[N][N]; // ������ ����
			max = Integer.MIN_VALUE; // �ִ� ���츮 ���� ����

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (max < map[r][c]) { 
						max = map[r][c];
					}
				}
			} // end of for(input)

			ans = Integer.MIN_VALUE; // ���� ����

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == max) { // �ִ� ���츮���
						visited = new boolean[N][N]; 
						flag = false; // ù Ž������ ���� ���� ����
						dfs(r, c, 1); // ���̿켱Ž���� �Ѵ�.
					}
				}
			} // end of for(Test)
			sb.append("#"+tc + " " + ans + "\n"); // �ѹ��� ����ϱ� ���� (����ð� ����)
		} // end of for TestCase
		System.out.println(sb.toString());
	} // end of main

	/** len : ������ ���� */
	private static void dfs(int r, int c, int len) {
		if (visited[r][c]) { // ���� ����
			return;
		}

		ans = (ans < len)? len:ans; // ���� ����
		visited[r][c] = true;

		for (int i = 0; i < dr.length; i++) { // 4�������� Ž��
			int nR = r + dr[i]; 
			int nC = c + dc[i];

			// ������ ����ų�, �湮�� ���̸� �ٸ� ���� Ž��
			if (nR < 0 || nC < 0 || nR >= N || nC >= N || visited[nR][nC]) {
				continue;
			}

			if (map[r][c] <= map[nR][nC]) { // ��ƾ� �Ǵ� ���,
				if (!flag) { // �ѹ��� ���� ���� ���
					for (int j = 1; j <= K; j++) { // 1���� K���� ���� ��� ��츦 Ž���� 
						int temp = map[nR][nC]; // ��� �� ���� ����
						map[nR][nC] -= j;
						if (map[r][c] > map[nR][nC]) { // ���� ��ġ�� ���̰� �� �۴ٸ�
							flag = true; // ��Ҵٴ� ���� ǥ��
							dfs(nR, nC, len + 1); // ���� ��ġ�� Ž�� ����
							flag = false; // ������ ��찡 �ƴ� �� �����Ƿ� �� ��Ҵٰ� ǥ��
						}
						map[nR][nC] = temp; // ���츮 ���� ���� ����
					}
				}
			} else { // ���� �ʾƵ� �� ��,
				dfs(nR, nC, len + 1);
			}
		} 
		visited[r][c] = false;
	}
} // end of class
