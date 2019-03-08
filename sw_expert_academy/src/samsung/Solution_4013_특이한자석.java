package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4013_Ư�����ڼ� {
	static int[][] mg; // r : ��Ϲ���, c : �� ���

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		mg = new int[4][8]; // r : ��Ϲ���, c : �� ���1

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // N

			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					mg[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 1;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int mgNum = Integer.parseInt(st.nextToken()); // �ڼ� ��ȣ
				int turnDir = Integer.parseInt(st.nextToken()); // ȸ�� ����

				mgNum -= 1;
				// ���� �ε��� : 2, ������ �ȵ���, �ٸ��� ���� �ٸ� �������� ����.
				if (mgNum == 0) {
					if (mg[mgNum][2] != mg[mgNum+1][6]) {
						if (mg[mgNum+1][2] != mg[mgNum+2][6]) {
							if (mg[mgNum+2][2] != mg[mgNum+3][6]) {
								turn(mgNum+3, -turnDir);
							}
							turn(mgNum+2, turnDir); // ���� �ٸ� �������� ������.
						}
						turn(mgNum+1, -turnDir); // ���� �ٸ� �������� ������.
					}
					turn(mgNum, turnDir);
				}

				if (mgNum == 3) {
					if (mg[mgNum][6] != mg[mgNum-1][2]) {
						if (mg[mgNum-1][6] != mg[mgNum-2][2]) {
							if (mg[mgNum-2][6] != mg[mgNum-3][2]) {
								turn(mgNum-3, -turnDir);
							}
							turn(mgNum-2, turnDir); // ���� �ٸ� �������� ������.
						}
						turn(mgNum-1, -turnDir); // ���� �ٸ� �������� ������.
					}
					turn(mgNum, turnDir);
				}

				if (mgNum == 1) {
					if (mg[mgNum][6] != mg[mgNum - 1][2]) {
						turn(mgNum - 1, -turnDir);
					}
					if (mg[mgNum][2] != mg[mgNum + 1][6]) {
						if (mg[mgNum + 1][2] != mg[mgNum + 2][6]) {
							turn(mgNum + 2, turnDir);
						}
						turn(mgNum + 1, -turnDir);
					}
					turn(mgNum, turnDir);
				}

				if (mgNum == 2) {
					if (mg[mgNum][2] != mg[mgNum + 1][6]) {
						turn(mgNum + 1, -turnDir);
					}
					if (mg[mgNum][6] != mg[mgNum - 1][2]) {
						if (mg[mgNum - 1][6] != mg[mgNum - 2][2]) {
							turn(mgNum - 2, turnDir);
						}
						turn(mgNum - 1, -turnDir);
					}
					turn(mgNum, turnDir);
				}
//				System.out.println(mgNum+1 + ", "+ turnDir); 
//				System.out.println("============" + cnt +"==============");
//				for (int j = 0; j < 4; j++) {
//					System.out.println(Arrays.toString(mg[j]));
//				}
				
				cnt++;
			} // end of for of input
			
			int sum = 0;
			// N�� : 0
			for (int i = 0; i < 4; i++) {
				if(mg[i][0] == 1) {
					sum += (1 << i);
				} 
			}
			
			System.out.println("#"+ tc + " " + sum);
		}
	}

	/** mg : ���� ���� ����, d : ���� */
	private static void turn(int idx, int d) {
		switch (d) {
		case 1: // �ð����
			int[] temp = new int[8];
			temp[0] = mg[idx][7]; // ������ �� ����, �� ������ ������ ����.

			for (int i = 0; i < mg[0].length - 1; i++) {
				temp[i + 1] = mg[idx][i];
			}

			for (int i = 0; i < temp.length; i++) {
				mg[idx][i] = temp[i];
			}
			break;
		case -1: // �ݽð����
			int[] temp2 = new int[8];
			temp2[7] = mg[idx][0]; // ������ �� ����, �� ������ ������ ����.

			for (int i = 1; i < mg[0].length; i++) {
				temp2[i - 1] = mg[idx][i];
			}

			for (int i = 0; i < temp2.length; i++) {
				mg[idx][i] = temp2[i];
			}
			break;
		}
	}
}
