package swea.d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * �����Բ� �״�� ������, Ʋ��!
 */

public class Solution_5672_���������û�_Prof {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 2000
			char[] c = new char[N]; // �޹��� �̸� ������ �迭
			for (int i = 0; i < c.length; i++) {
				c[i] = br.readLine().charAt(0); // �빮�� �ѱ��ڸ�
			}
			char[] result = new char[N];

			int s = -1; // ���ʿ��� ���ڸ� ���� �� �ֵ��� index
			int e = N; // ���ʿ��� ����Ű�� index

			for (int i = 0; i < result.length; i++) { // �� �ܰ迡�� ���� �ϳ��� ���ϱ�
				if (c[s + 1] < c[e - 1]) {
					result[i] = c[++s];
				} else if (c[s + 1] > c[e - 1]) {
					result[i] = c[--e];
				} else { // �� ���ڰ� ���� ���ٸ�, ���� ���ڷ� ���ؼ� ����
					int j;
					for (j = 1; s + j < e - j && c[s + j] == c[e - j]; j++) {
						if (c[s + j] < c[e - j]) {
							result[i] = c[++s];
						} else if (c[s + j] > c[e - j]) {
							result[i] = c[--e];
						} else { // ���� ���̴ϱ� �ƹ��ų� ����
							result[i] = c[++s];
						}
					}
				}
			}
			System.out.println("#" + tc + " " + new String(result));
		} // end of for testCase
	} // end of main
} // end of class
