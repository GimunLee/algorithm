package swea.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2019.09.02.(��) Upload
 * */
public class Solution_UP_4008_���ڸ���� {
	private static int[] op = new int[4]; // ������ (0 : '+' , 1 : '-', 2 : '*', 3 : '/')
	private static int[] numArr; // ���ڸ� �����ϴ� �迭

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(); // �ѹ��� ����ϱ� ���� �����ϴ� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // �Է��� �ޱ� ���� ����
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N : ������ ����
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < 4; i++) {
				op[i] = Integer.parseInt(st.nextToken()); // ������ ���� ����
			}

			numArr = new int[N]; // ���ڸ� ������ ���� ����

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				numArr[i] = Integer.parseInt(st.nextToken()); // �� ���� ����
			}
			// -- end of Input

			maxValue = Integer.MIN_VALUE; // ���� �� �ִ� ��� �� �ִ��� ������ ����
			minValue = Integer.MAX_VALUE; // ���� �� �ִ� ��� �� �ּڰ��� ������ ����
			// ����� �����ڸ� �̰� �ٷ� ����ϸ� ����
			solve(1, 0, numArr[0]); // 2���� ����ϸ� ����� (������ ���� �ε���, ���� �������� ��, ���ʰ� ������ ����� ��� ��)
			sb.append("#").append(tc).append(" ").append(maxValue - minValue).append("\n"); // ���� ���� ����
		} // end of for(TestCase)
		System.out.println(sb.toString()); // ���� ���
	} // end of main

	private static int maxValue; // �ִ�
	private static int minValue; // �ּڰ�

	/**
	 * numArrIdx : ������ ������ �ε���, opLen : ���� �������� ��, value : ������� ������ ���� ������ ���� ����Ͽ�
	 * �����ϴ� ���� (����)
	 */
	private static void solve(int numArrIdx, int opLen, int value) {
		if (opLen == numArr.length - 1) { // �����ڸ� ������ �ôٸ�,
			// value�� ������� �� �� ����
			if (value > maxValue) {
				maxValue = value;
			}
			if (value < minValue) {
				minValue = value;
			}
			return;
		}

		for (int i = 0; i < op.length; i++) {
			if (op[i] > 0) { // �̰��� �ϴ� �������� �ε����� ���� �� �� �ִٸ�
				op[i]--; // ������ ���
				int rightNum = numArr[numArrIdx]; // ������ ��
				int tmpValue = value; // ��� �Լ��� return �Ǿ��� ��, �� ������ �ϱ� ������ �����ֱ� ���� �ӽ� ����
				value = calculate(i, value, rightNum); // �� �� ���
				solve(numArrIdx + 1, opLen + 1, value); // ������ ��� ȣ��
				value = tmpValue; // �� ���󺹱� (��� ��츦 �غ��� ����)
				op[i]++; // �������� ������ �ٽ� �÷���
			}
		}
	} // end of func(solve)

	/** ������ �ε����� �����ϴ� �Լ� */
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
