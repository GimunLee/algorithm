package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1224. [S/W �����ذ� �⺻] 6���� - ����3 
 */
public class Solution_1224 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc =1; tc <= 10; tc++) {
			int cnt = Integer.parseInt(br.readLine());
			String[] tmp = br.readLine().split("");
			
			String[] array = new String[cnt];
			int index = 0;
			
			String[] stack = new String[cnt];
			int top = -1;
			
			for (int i = 0; i < tmp.length; i++) {
				String s = tmp[i];
				switch(s) {
				case "(":
					stack[++top] = tmp[i];
					break;
				case "*": // �켱���� 2
					// ���ÿ� ������ ���� ���� �������� ������ ������ ���
					while (top > -1 && stack[top].equals("*")) {
						array[index++] = stack[top];
						top--;
					}
					stack[++top] = s;
					break;
				case "+":
					// ���ÿ� ������ ���� ���� �������� ������ ������ ���
					while (top > -1 && (stack[top].equals("*") || stack[top].equals("+"))) {
						array[index++] = stack[top];
						top--;
					}
					stack[++top] = s;
					break;
				case ")":
					// '(' ���� ������ ���ÿ��� ������ ���
					while (top > -1 && !stack[top].equals("(")) {
						array[index++] = stack[top];
						top--;
					}

					if (stack[top].equals("(")) {
						top--;
					}
					break;
				default: // �����ڰ� �ƴ� ��� = ����(�ǿ�����)�� ���
					array[index++] = tmp[i];
					break;
				}
			}

//			for (int i = 0; i < index; i++) {
//				System.out.print(array[i] + " ");
//			}
//			System.out.println();
			
			int[] stack2 = new int[cnt];
			int top2 = -1;
			
			for (int i = 0; i < index; i++) {
				String c  = array[i];
				int num1, num2;
				switch(c) {
				case "+":
					num2 = stack2[top2--];
					num1 = stack2[top2--];
					stack2[++top2] = num1 + num2;
					break;
				case "*":
					num2 = stack2[top2--];
					num1 = stack2[top2--];
					stack2[++top2] = num1 * num2;
					break;
				default:
					stack2[++top2] = Integer.parseInt(array[i]);
					break;
				}
			}
			System.out.printf("#%d %s\n", tc, stack2[0]);	
		}
	} // end of main
} // end of class
