package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_������ {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			int[] price = new int[5]; // 0: �� �� / 1 : 1�� / 2 : 1�� / 3 : 3�� / 4 : 1��
			int[] use = new int[13]; // 0: �� ��

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int i = 1; i < 5; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i < 13; i++) {
				use[i] = Integer.parseInt(st.nextToken());
			}

			int[][] dp = new int[5][13];

			for (int i = 1; i < 13; i++) {
				if (use[i] != 0) { // �̿� ��¥�� ������
					dp[1][i] = (((dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1]) < dp[3][i - 1])
							? (dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1])
							: dp[3][i - 1]) + use[i] * price[1]; // 1��
					dp[2][i] = (((dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1]) < dp[3][i - 1])
							? (dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1])
							: dp[3][i - 1]) + price[2]; // 1�� �̿��

					if (dp[3][i] == 0) {
						int temp = (((dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1]) < dp[3][i - 1])
								? (dp[1][i - 1] < dp[2][i - 1] ? dp[1][i - 1] : dp[2][i - 1])
								: dp[3][i - 1]); // 3�� �̿��

						for (int j = 0; j < 3; j++) {
							if (i + j > 12) {
								break;
							}
							dp[3][i + j] = temp + price[3]; // 3�� �̿�� ä���
						}
					}

					dp[4][i] = price[4]; // 1�� �̿��

				} else { // �̿� ��¥�� �����Ƿ� ���� �״�� ��������
					dp[1][i] = dp[1][i - 1];
					dp[2][i] = dp[2][i - 1];
					dp[3][i] = dp[3][i - 1];
					dp[4][i] = dp[4][i - 1];
				}
			}

			int min = Integer.MAX_VALUE;

			for (int i = 1; i < 5; i++) {
				if (min > dp[i][12]) {
					min = dp[i][12];
				}
			}
			System.out.println("# " + tc + " " + min);

		} // end of testcase
	} // end of main
} // end of class
