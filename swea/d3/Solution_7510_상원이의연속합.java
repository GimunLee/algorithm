package swea.d3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_7510_������ǿ����� {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 10^6
			int limit = (N / 2) + 1;
			int ans = 1;

			int sum = 0;
			for (int i = 1; i < limit; i++) {
				for (int j = i; j <= limit; j++) {
					sum += j;
					if (sum == N) { // ������ ���
						ans++;
						sum = 0;
						break;
					} else if (sum > N) {
						sum = 0;
						break;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main
} // end of class
