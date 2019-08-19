package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UP_Solution_4013_Ư�����ڼ� {
	static int[][] magnet; // r : ��Ϲ��� �ε���, c : �� ��� // ��ϸ� ���ڷ� �켭 ����

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		magnet = new int[4][8]; // r : ��Ϲ��� �ε���, c : �� ���

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // ȸ����Ű�� Ƚ��
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end of for(input)

			for (int i = 0; i < N; i++) { // ȸ����Ű�� Ƚ����ŭ �ݺ�
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int magnetNum = Integer.parseInt(st.nextToken()); // �ڼ� ��ȣ
				int rotateDir = Integer.parseInt(st.nextToken()); // ȸ�� ����
				magnetNum -= 1; // 0���� �����ϹǷ� 1�� ����

				// ȸ������ �� �� ���� �´��� �� �´��� üũ��
				if (magnetNum == 0) {
					if (magnet[magnetNum][2] != magnet[magnetNum + 1][6]) {
						if (magnet[magnetNum + 1][2] != magnet[magnetNum + 2][6]) {
							if (magnet[magnetNum + 2][2] != magnet[magnetNum + 3][6]) {
								rotate(magnetNum + 3, -rotateDir);
							}
							rotate(magnetNum + 2, rotateDir);
						}
						rotate(magnetNum + 1, -rotateDir);
					}
					rotate(magnetNum, rotateDir);
				}

				if (magnetNum == 3) {
					if (magnet[magnetNum][6] != magnet[magnetNum - 1][2]) {
						if (magnet[magnetNum - 1][6] != magnet[magnetNum - 2][2]) {
							if (magnet[magnetNum - 2][6] != magnet[magnetNum - 3][2]) {
								rotate(magnetNum - 3, -rotateDir);
							}
							rotate(magnetNum - 2, rotateDir);
						}
						rotate(magnetNum - 1, -rotateDir);
					}
					rotate(magnetNum, rotateDir);
				}

				if (magnetNum == 1) {
					if (magnet[magnetNum][6] != magnet[magnetNum - 1][2]) {
						rotate(magnetNum - 1, -rotateDir);
					}
					if (magnet[magnetNum][2] != magnet[magnetNum + 1][6]) {
						if (magnet[magnetNum + 1][2] != magnet[magnetNum + 2][6]) {
							rotate(magnetNum + 2, rotateDir);
						}
						rotate(magnetNum + 1, -rotateDir);
					}
					rotate(magnetNum, rotateDir);
				}

				if (magnetNum == 2) {
					if (magnet[magnetNum][2] != magnet[magnetNum + 1][6]) {
						rotate(magnetNum + 1, -rotateDir);
					}
					if (magnet[magnetNum][6] != magnet[magnetNum - 1][2]) {
						if (magnet[magnetNum - 1][6] != magnet[magnetNum - 2][2]) {
							rotate(magnetNum - 2, rotateDir);
						}
						rotate(magnetNum - 1, -rotateDir);
					}
					rotate(magnetNum, rotateDir);
				}
			} // end of for(EachRotate)

			int sum = 0; // ������ �����ϱ� ���� ����

			for (int i = 0; i < 4; i++) {
				if (magnet[i][0] == 1) {
					sum += (1 << i); // ��������� ���� Shift ����
				}
			}
			System.out.println("#" + tc + " " + sum); // ���� ���
		} // end of for(TestCase)
	} // end of main

	/** idx : ȸ����ų ���� �ε���, dir : ���� */
	private static void rotate(int idx, int dir) {
		int[] temp;
		switch (dir) {
		case 1: // �ð� ���� ȸ��
			temp = new int[8];
			temp[0] = magnet[idx][7]; // ȸ�� ��, ������ ���� �� ������ ������ ���� �ӽ� ����

			for (int i = 0; i < magnet[0].length - 1; i++) {
				temp[i + 1] = magnet[idx][i]; // ��ĭ�� ���������� �о��� ���� �ӽ� ������ ����
			}

			for (int i = 0; i < temp.length; i++) {
				magnet[idx][i] = temp[i]; // �ӽ� ������ ����� ���� �ٽ� ���� �����Ϳ� ����
			}
			break;
		case -1: // �ݽð� ���� ȸ��
			temp = new int[8];
			temp[7] = magnet[idx][0]; // ȸ�� ��, ù��° ���� �� �ڷ� ������ ���� �ӽ� ����

			for (int i = 1; i < magnet[0].length; i++) {
				temp[i - 1] = magnet[idx][i]; // ��ĭ�� �������� �о��� ���� �ӽ� ������ ����
			}

			for (int i = 0; i < temp.length; i++) {
				magnet[idx][i] = temp[i]; // �ӽ� ������ ����� ���� �ٽ� ���� �����Ϳ� ����
			}
			break;
		}
	} // end of func(rotate)
} // end of class
