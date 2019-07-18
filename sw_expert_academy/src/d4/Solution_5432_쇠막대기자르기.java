package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * �踷��� �ڸ���
 */
public class Solution_5432_�踷����ڸ��� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine()); // Test Case ��

		for (int tn = 1; tn <= tc; tn++) {
			int sum = 0; // �߸� ����� ������ ��
			int open = 0; // ���� ��ȣ ������ ��

			char[] input = br.readLine().toCharArray(); // char�� ����

			for (int i = 0; i < input.length; i++) {
				char tmp = input[i];

				if (tmp == '(') {
					open++;
				}
				// ���� ���� ������ ��,
				else if (input[i - 1] == '(') { // 1. �������� ���
					open--;
					sum += (open);
				} else { // 2. �������� �ƴ� ���
					open--;
					sum += 1;
				}
			}
			System.out.println("#" + tn + " " + sum);
		}
	}
}
