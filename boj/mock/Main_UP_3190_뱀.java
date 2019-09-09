package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_UP_3190_�� {
	private static int[] dr = { -1, 0, 1, 0 }; // ������� (�ð����)
	private static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // ���� ũ�� ���� (N x N)
		int K = Integer.parseInt(br.readLine()); // ����� ���� 
		int[][] map = new int[N + 1][N + 1]; // �� ����
		
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 5; // �ʿ� ����� ����
		}

		int L = Integer.parseInt(br.readLine()); // ���� ���� ��ȯ ��ɾ� �� 
		int[][] commandArray = new int[L][2]; // ���� ���� ��ȯ ��ɾ ������ ����
		
		for (int l = 0; l < L; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int X = Integer.parseInt(st.nextToken()); // ��
			int C = st.nextToken().charAt(0) == 'L' ? -1 : 1; // ����
			commandArray[l][0] = X; // �ð� (X�ʸ� ��ġ�� ���� ��ȯ)
			commandArray[l][1] = C; // ���� (���� : -1, ������ : 1)
		}
		// -- end of input

		int r = 1; int c = 1; // �� �Ӹ��� ù ��ġ
		int curDir = 1; // ���� ���� (������)
		
		int[][] queue = new int[10001][2]; // ���� �Ӹ����� �������� ������ Queue ����
		int front = -1, rear = -1; // Queue�� ������ ����
		
		queue[++rear][0] = 1; queue[rear][1] = 1; // �� �Ӹ��� ���� ��ġ �ֱ�
		int commandIndex = 0; // ������� ������ ��ɾ� index
		map[1][1] = 9; // �ʿ� ���� �Ӹ� ǥ��
		
		int time = 1; // 1�ʺ��� ����
		while (true) {
			int nR = r + dr[curDir]; // ���� ���� �Ӹ��� �� ��ġ
			int nC = c + dc[curDir];

			if (nR > 0 && nC > 0 && nR <= N && nC <= N) { // ���� ����� ������
				if (map[nR][nC] == 9) { // ������ ���� �����ٸ�, ���� ����
					break;
				} else if (map[nR][nC] == 5) { // ����� ��, �� ��ġ�� ������ �ٲٱ�
					map[nR][nC] = 9;
				} else if (map[nR][nC] == 0) { // ��ĭ�̶��, ���� ����
					map[nR][nC] = 9;
					// Queue�� �� �տ��� �׻� ������ ������ֱ� ������ �ش� ��ġ�� 0���� �ٲ���
					int tR = queue[++front][0]; 
					int tC = queue[front][1];
					map[tR][tC] = 0;
				}
				
				queue[++rear][0] = nR; // ���� �Ӹ��κ��� Queue�� �־���
				queue[rear][1] = nC;

				if (commandIndex < L && commandArray[commandIndex][0] == time) { // ���� �ð��� ��ɾ ����� �ð��̶��
					int D = commandArray[commandIndex][1]; // ���� ��ȯ �̱�
					int turnDir = curDir + D;
					if (turnDir > 3) { // �¿��� �������̸� '��'���� �����ϹǷ� 0���� �ٲ���
						turnDir = 0;
					} else if (turnDir < 0) { // �󿡼� �����̸� '��'�� �����ϹǷ� 3���� �ٲ���
						turnDir = 3;
					}
					curDir = turnDir; // �ٲ� ���� ���� ������ ����
					commandIndex++; // ���� ��ɾ Ž���ϵ��� �ε��� ����
				}
				time++; // �ð� ���� 
				r = nR; // �̵��� ���� �Ӹ��� r�� c�� �ٲ���
				c = nC;
			} else { // ���� ����ٸ� 
				break; // ���� ����
			}
		} // end of while
		System.out.println(time); // ���� ���� �ð� ���
	} // end of main
} // end of class
