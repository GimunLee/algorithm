package swea.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4008_���ڸ���� {
	private static int[] op = new int[4];
	private static int[] numArr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			numArr = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				numArr[i] = Integer.parseInt(st.nextToken());
			}
			// -- end of Input

			// �����ڸ� �Ѱ��� �����鼭 ����,
			maxValue = Integer.MIN_VALUE;
			minValue = Integer.MAX_VALUE;
			solve(1, 0, numArr[0]); // numArr index, op index
			sb.append("#").append(tc).append(" ").append(maxValue - minValue).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int maxValue;
	private static int minValue;

	private static void solve(int numArrIdx, int opLen, int value) {

		if (opLen == numArr.length - 1) {
			if (value > maxValue) {
				maxValue = value;
			}
			if (value < minValue) {
				minValue = value;
			}
			return;
		}

		for (int i = 0; i < op.length; i++) {
			if (op[i] > 0) {
				op[i]--;
				int rightNum = numArr[numArrIdx];
				int tmpValue = value;
				value = calculate(i, value, rightNum); // �����
				solve(numArrIdx + 1, opLen + 1, value);
				value = tmpValue;
				op[i]++;
			}
		}
	} // end of func(solve)

	private static int calculate(int i, int value, int rightNum) {
		switch (i) {
		case 0: // '+'
			value += rightNum;
			break;
		case 1: // '-'
			value -= rightNum;
			break;
		case 2: // '*'
			value *= rightNum;
			break;
		case 3: // '/'
			value /= rightNum;
			break;
		}
		return value;
	}
} // end of class
