package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2283_RGB���� {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 1000

		int[][] p = new int[N + 1][3]; // �� ���� ��ĥ�ϴ� ���

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				p[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N + 1][3]; // 0:R, 1:G, 2:B

		for (int i = 1; i <= N; i++) { // ��ȭ��
			dp[i][0] = (dp[i - 1][1] < dp[i - 1][2] ? dp[i - 1][1] : dp[i - 1][2]) + p[i][0]; // i��° ���� �������� ĥ�ϴ� ���
			dp[i][1] = (dp[i - 1][0] < dp[i - 1][2] ? dp[i - 1][0] : dp[i - 1][2]) + p[i][1]; // i��° ���� �ʷϻ��� ĥ�ϴ� ���
			dp[i][2] = (dp[i - 1][0] < dp[i - 1][1] ? dp[i - 1][0] : dp[i - 1][1]) + p[i][2]; // i��° ���� �ʷϻ��� ĥ�ϴ� ���
		}

		int min = Integer.MAX_VALUE;

		for (int i = 0; i < 3; i++) {
			if (min > dp[N][i]) {
				min = dp[N][i];
			}
		}
		System.out.println(min);
	} // end of main
} // end of class
