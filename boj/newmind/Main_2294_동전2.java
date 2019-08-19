package boj.newmind;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2294_����2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken()); // ������ ����
		int k = Integer.parseInt(st.nextToken()); // ������ ��ġ
		int[] money = new int[n];

		int[] dp = new int[k + 1];

		for (int i = 0; i < n; i++) {
			money[i] = Integer.parseInt(br.readLine().trim());
		}

		Arrays.sort(money);

		for (int i = 0; i < n; i++) {
			int temp = 1;
			for (int j = money[i]; j < dp.length; j += money[i]) {
				dp[j] = temp++;
			}
		}
		
		System.out.println(Arrays.toString(dp));
		
		for (int i = 0; i < dp.length; i++) {
			if(dp[i] == 0) {
				dp[i] = Integer.MAX_VALUE;
			}
		}
		
		System.out.println(Arrays.toString(dp));
		
		for (int i = 0; i < n; i++) {
			for (int j = money[i]+1; j < dp.length; j++) {
				if(dp[j-money[i]] != Integer.MAX_VALUE) { // �Ǵ� ���
					dp[j] = Math.min(dp[j-money[i]]+1,dp[j]);
				}
			}
		}
		
		System.out.println(Arrays.toString(dp));

		if (dp[k] == Integer.MAX_VALUE) {
			dp[k] = -1;
		}
		System.out.println(dp[k]);
	}
}
