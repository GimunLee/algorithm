package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_��ȣ�ʸ� {
	private static int D;
	private static int W;
	private static int K;
	private static int[][] map;
	private static int[][] tmp_map;
	private static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken()); // ��ȣ �ʸ��� �β� (3<= <=13)
			W = Integer.parseInt(st.nextToken()); // ��ȣ �ʸ��� ���� ũ�� (1<= <=20)
			K = Integer.parseInt(st.nextToken()); // �հ� ���� (1<= <=D)

			map = new int[D][W];
			tmp_map = new int[D][W];
			set = new int[D];

			// 0 : A, 1 : B
			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					tmp_map[r][c] = map[r][c];
				}
			} // end of for(input)

			ans = Integer.MAX_VALUE;

			if (checkFilm()) {
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}

			flag = false;
			permu(0);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString()); // ���� ���
	} // end of main

	private static boolean checkFilm() {
		int unChecked = W;
		// �˻� �����ϱ� (�ݺ���), ��� K������
		for (int c = 0; c < W; c++) {
			// �ϳ��� �� �˻� ����
			int r = 0;

			while (r < D) {
				int each_cnt_A = 0;
				int each_cnt_B = 0;
				while (r < D && map[r][c] == 0) {
					each_cnt_A++;
					r++;
				}
				if (each_cnt_A >= K) {
					unChecked--;
					break;
				}
				while (r < D && map[r][c] == 1) {
					each_cnt_B++;
					r++;
				}
				if (each_cnt_B >= K) {
					unChecked--;
					break;
				}
			}
		} // end of for(each column check)

		if (unChecked == 0) {
			return true;
		} else {
			return false;
		}
	} // end of func(checkFilm)

	private static int[] set;
	private static boolean flag;

	private static void permu(int len) {
		if (flag && len == D) {
			int tmp_ans = 0;
			for (int i = 0; i < D; i++) {
				if (set[i] != 0) {
					tmp_ans++;
					if (tmp_ans >= ans) {
						return;
					}
				}
			}

			// Type �ٲٱ�
			for (int i = 0; i < D; i++) {
				if (set[i] != 0) {
					int changeType = set[i] - 1;
					for (int j = 0; j < W; j++) {
						map[i][j] = changeType;
					}
				}
			}

			if (checkFilm()) { // ���� ���� ���
				ans = tmp_ans < ans ? tmp_ans : ans;
			}

			// ���󺹱�
			for (int i = 0; i < D; i++) {
				if (set[i] != 0) {
					for (int j = 0; j < W; j++) {
						map[i][j] = tmp_map[i][j];
					}
				}
			}
			return;
		}

		if (len == D) {
			return;
		}

		for (int i = 0; i < 3; i++) {
			set[len] = i;
			permu(len + 1);
			flag = true;
		}
	}
} // end of class
