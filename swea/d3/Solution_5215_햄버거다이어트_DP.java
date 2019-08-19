package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_�ܹ��Ŵ��̾�Ʈ_DP {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); // N, L

			int N = Integer.parseInt(st.nextToken()); // �ܹ��� ��� ��
			int L = Integer.parseInt(st.nextToken()); // ���� Į�θ�

			int[][] set = new int[N][2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				int score = Integer.parseInt(st.nextToken()); // ����
				int calory = Integer.parseInt(st.nextToken()); // Į�θ�

				set[i][0] = score; // ����
				set[i][1] = calory; // Į�θ�
			}

			int[] dp = new int[L + 1];

			for (int i = 0; i < set.length; i++) {
				for (int j = L; j >= set[i][1]; j--) {
					dp[j] = Math.max(dp[j], dp[j - set[i][1]] + set[i][0]);
				}
			}
			sb.append('#').append(tc).append(' ').append(dp[L]).append('\n');
		}
		System.out.println(sb.toString());
	}
}
