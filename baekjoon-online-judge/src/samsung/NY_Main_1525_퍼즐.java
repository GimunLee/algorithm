package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NY_Main_1525_���� {
	private static int[][] map;
	private static int[][] map_mv;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static boolean[][] isChange;

	private static final int[][] map_ans = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[3][3]; // �ʱ� ����
		map_mv = new int[3][3];
		isChange = new boolean[3][3];
		int r_zero = 0;
		int c_zero = 0;

		for (int r = 0; r < 3; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < 3; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				map_mv[r][c] = map[r][c]; // �������� ������ �״�� ����
				if (map[r][c] != map_ans[r][c]) {
					isChange[r][c] = true;
				}
				if (map[r][c] == 0) {
					r_zero = r;
					c_zero = c;
				}
			}
		} // end of input
		flag = 0;
		dfs(r_zero, c_zero);
		System.out.println(ans);

	} // end of main

	private static int ans = 0;
	private static int flag;
	private static void dfs(int r_zero, int c_zero) {
		flag ++;
		if(flag >= 1000) {
			ans = -1;
			return;
		}
		
		for (int i = 0; i < dr.length; i++) {
			int nR = r_zero + dr[i];
			int nC = c_zero + dc[i];

			if (nR < 0 || nC < 0 || nR >= 3 || nC >= 3) {
				continue;
			}

			// �´� �ڸ��� �ִ°��� �ٲٸ� �ȵ�. isChange�� Ȯ���Ͽ� �ٲ㵵 �Ǵ� ����̸� �ٲ㺽
			if(isChange[nR][nC]) {
				swap(r_zero, c_zero, nR, nC);
				ans++;
				if(map_mv[r_zero][c_zero] == map_ans[r_zero][c_zero]) {
					isChange[r_zero][c_zero] = false;
				}
				dfs(nR,nC);
			}
		}
	}

	/** ������ �ڸ��� �ٲ�. 0�϶��� ������ */
	private static void swap(int r, int c, int sR, int sC) {
		int tmp = map_mv[r][c];
		map_mv[r][c] = map_mv[sR][sC];
		map_mv[sR][sC] = tmp;
	} // end of swap()
} // end of class


// ���������� ���� �ʱ� �ڱ� ���·� ���ƿ��� ��� -1
//if(flag) {
//	boolean isEnd = false;
//	for (int r = 0; r < 3; r++) {
//		for (int c = 0; c < 3; c++) {
//			if (map_mv[r][c] != map[r][c]) {
//				isEnd = true;
//			}
//		}
//	}
//	
//	if (!isEnd) {
//		ans = -1;
//		return;
//	}
//}
//flag = true;
