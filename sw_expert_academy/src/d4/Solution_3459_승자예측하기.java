package d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ������ A�� : A�� ������ 2x, B�� ū�� 2x+1
 * ������ B�� : A�� ū�� 2x+1, B�� ������ 2x
 */

public class Solution_3459_���ڿ����ϱ� {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) {
			long N = Long.parseLong(br.readLine().trim()); // N

			System.out.print("#" + tc);

			long A = 0;
			long B = 1;
			while (true) {
				A = (A * 2 + 1) * 2; // A �� �̻��̸� Alice ��
//				System.out.println(A);
				if (N < A) {
					System.out.println(" Bob");
					break;
				}

				B = (B * 2 + 1) * 2; // B �� �̻��̸� Bob ��
				if (N < B) {
					System.out.println(" Alice");
					break;
				}
//				System.out.println(B);
			}
		} // end of for of TestCase
	} // end of main
} // end of class
