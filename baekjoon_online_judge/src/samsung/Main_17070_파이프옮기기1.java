package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17070_�������ű��1 {
	static int[] dr = { 0, 1, 1 }; // ��, ��, �밢��
	static int[] dc = { 1, 0, 1 };
	private static int[][] map;
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim()); // ���� ũ��
		map = new int[N + 1][N + 1]; // �ܰ��� �Ⱦ���.

		for (int i = 0; i < map.length; i++) {
			map[i][N] = 1;
		}
		for (int i = 0; i < map[0].length; i++) {
			map[N][i] = 1;
		}

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()); // �� ä���
			}
		}

		// ���� ��ġ (1,1) (1,2) ����

		dfs(0, 1, 0, 0);
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		System.out.println(ans);

	} // end of main

	private static int ans = 0;

	/** r, c, pos : ���� ��ġ (0:����, 1:����, 2:�밢), dir : �� ����(0:��,1:��,2:�밢) */
	private static void dfs(int r, int c, int pos, int dir) {
		map[r][c] = 5;
//		System.out.println("r : " + r + ", " + "c : " + c);

		if (r == N-1 && c == N-1) { // ���������� ����������, ���� ���
//			System.out.println("*\t\t r : " + r + ", " + "c : " + c + ",  " + pos + ", " + dir);
			ans++;
			return;
		}

		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i];
			int nC = c + dc[i];

			if (map[nR][nC] == 1) {
				continue;
			}
			
			// �밢���� ��, 
			if (i == 2 && (map[nR - 1][nC] == 1 || map[nR][nC - 1] == 1)) {
				continue;
			}
			
			if(pos== 0 && i == 1) { // ������ ��, ���δ� �ȵ�
				continue;
			}
			if(pos== 1 && i == 0) { // ������ ��, ���δ� �ȵ�
				continue;
			}

			dfs(nR, nC, i, i);
		}

	} // end of main
} // end of class
