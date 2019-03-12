package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1808_�����ǰ��峭����_����ȫ {
	private static boolean[] num;
	private static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {

			num = new boolean[10];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 10; i++) {
				if (st.nextToken().charAt(0) == '1') {
					num[i] = true;
				}
			}
			int X = Integer.parseInt(br.readLine());

			min = Integer.MAX_VALUE;
			dfs(X, 0);
			if (min == Integer.MAX_VALUE) {
				min = -2;
			}
			System.out.println("#" + testcase + " " + (min + 1));

		}
	}

	private static void dfs(int x, int cnt) {
		// x�� ���� ������� �Է°������� ���� Ȯ��
		int temp = x;
		while (temp > 0 && num[temp % 10]) {
			temp /= 10;
		}
		if (temp == 0) {
			min = Math.min(min, cnt + (x + "").length());
			return;
		}

		// ���� ���������� ������ ���������� �� ã��.
		for (int i = 2; i < x / 2; i++) {
			if (x % i == 0) { // ������ ���� ���, ������ i ���� ������ �ִ� ������ Ȯ��

				int mod = i;
				while (mod > 0 && num[mod % 10]) {
					mod /= 10;
				}
				if (mod == 0) {
					dfs(x / i, cnt + 1 + ((i) + "").length());
				}

			}
		}
	}
}
