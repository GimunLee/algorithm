package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [S/W �����ذ� �⺻] 4���� - ��ȣ ¦����
 */
public class Solution_1218_��ȣ¦���� {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 1; i <= 10; i++) { // Test Case Ƚ��
			int tc_len = Integer.parseInt(br.readLine()); // Test Case ����
			String[] stack = new String[100]; // ���� ����
			int top = 0; // ���� ����
			int j;
			String input = br.readLine();
			String[] array = input.split(""); // Test Case �迭

			for (j = 0; j < array.length; j++) {

				if (array[j].equals("(") || array[j].equals("[") || array[j].equals("{") || array[j].equals("<")) {
					stack[top++] = array[j];
				} else {
					String tmp = stack[--top];

					if (array[j] == null)
						break;

					if (array[j].equals(")") && tmp.equals("("))
						;
					else if (array[j].equals("]") && tmp.equals("["))
						;
					else if (array[j].equals("}") && tmp.equals("{"))
						;
					else if (array[j].equals(">") && tmp.equals("<"))
						;
					else
						break;
				}
			}
			if (top == 0 && j == array.length) {
				System.out.println("#" + i + " 1");
			} else {
				System.out.println("#" + i + " 0");
			}
		}
	}
}
