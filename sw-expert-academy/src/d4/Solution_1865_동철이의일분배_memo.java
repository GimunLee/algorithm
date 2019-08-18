package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1865_��ö�����Ϻй�_memo {
	public static int N;
	public static int p[][];
	public static double memo[] = new double[1 << 16];

	public static void main(String[] ar) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		long time = System.currentTimeMillis();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(in.readLine());
			p = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					p[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Arrays.fill(memo, -1); // ��ü�� ���� �����ϴ� �ͺ��� 40ms ����
			double res = f(0, 0);
			System.out.printf("#%d %.6f\n", testCase, res / Math.pow(100, N - 1));
		} // end of for testCase
		System.out.println(System.currentTimeMillis() - time + " ms");
	} // end of main

	/**
	 * step : N�� �������� �ϳ��� �����ذ��� �ܰ� flag : �̹� ������ �������� ��Ʈ�� ǥ���� �� �̹� ������ �������߿��� ������
	 * �ٲپ� �ִ�� ����� �ִ� ���� memo �迭�� ������ 12345 �� 21345 �� ������ ������ �������� 2��° step ����
	 * �������� ��� �ִ밪�� �ʿ��ϹǷ� 12 �� 21 �� ���� �ɼ� �ִ� ū���� �����Ѵ�. 1�� ����, 2�� ������ �����ϴ� ����� �ִ밪��
	 * 1,2 ���� ��Ʈ����ŷ �� flag�� index�� ����Ͽ� memo[flag] �� ����
	 */
	public static double f(int step, int flag) {
		if (step == N) {
			return 1;
		}
		if (memo[flag] >= 0) {
			return memo[flag];
		}
		double max = 0;
		for (int i = 0; i < N; i++) {
			int bitMask = 1 << i;
			if ((flag & bitMask) == 0) { // ������� ���� ���ڶ��
				double next = f(step + 1, flag | bitMask) * p[step][i];
				if (max < next) {
					max = next;
				}
			}
		}
		memo[flag] = max;
		return max;
	}
} // end of class