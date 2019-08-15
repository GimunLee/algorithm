package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16236_�Ʊ��� {
	// �����¿�� �����̱� ���� ����
	static int[] dr = { -1, 1, 0, 0 }; // ��(����)
	static int[] dc = { 0, 0, -1, 1 }; // ��(�¿�)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 20

		int[][] map = new int[N][N]; // ������ ����
		int[][] visited = new int[N][N]; // �Ʊ� �� �湮�� ���� �̵��� �ð��� ������ ����
		int[] cntFish = new int[7];
		cntFish[0] = Integer.MAX_VALUE; // ũ�⺰ ���� ������� �� (Backtracking), 0�� ������� ����

		int[][] queue = new int[500][2]; // Queue ����
		int front = -1, rear = -1; // Queue�� ����ϱ� ���� front, rear ����

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) { // �Ʊ� ����� ���
					queue[++rear][0] = r;
					queue[rear][1] = c; // Queue�� ����
					map[r][c] = 0; // BFS Ž����, ���� �˻縦 ���� �ʱ� ���� '0'���� �ٲٱ�
				} else if (map[r][c] != 0) { // ������� ���
					cntFish[map[r][c]]++; // �ش� ũ���� ����� ���� ����������(Backtracking)
				}
			}
		} // end of for(input)

		int[][] availableFishArr = new int[N*N/2][2]; // �� Ÿ�ӿ� ���� �� �ִ� �����
		int availableFishArrIndex = 0; // ���� ������� ��

		int level = 2; // �Ʊ� ����� ũ��
		int cntEat = 0; // ���� ������� ��

		int ANS = 0; // ���� ����

		int time = 1; // �ð�
		total: while (front != rear) { // queue�� �� ������ (!isEmpty())
			int tmp = 0; 
			
			int size = rear; // �� Ÿ�Ӹ� ���� ���� ����
			while (front < size) {
				int r = queue[++front][0]; // queue���� r,c ��ǥ�� ���� (poll()) 
				int c = queue[front][1]; 

				for (int i = 0; i < dr.length; i++) { // �����¿� Ȯ��
					int nR = r + dr[i]; // �Ʊ� ����� ���� �� ��ġ
					int nC = c + dc[i]; // �Ʊ� ����� ���� �� ��ġ

					if (nR < 0 || nC < 0 || nR >= N || nC >= N) { // ������ ���� �ʰ�
						continue;
					}

					if (visited[nR][nC] != 0) { // �湮�� ���
						continue;
					}

					if (map[nR][nC] > level) { // �Ʊ� �� ���� ���� ������ ���� ���� ��� (����Ⱑ �� ū ���)
						continue;
					} else if (map[nR][nC] != 0 && map[nR][nC] < level) { // ���� ���� �� �ִ� ������ ���,
						// �� Ÿ���� Ž���ϰ� �˻��� �� �ֵ��� �迭�� ����
						availableFishArr[availableFishArrIndex][0] = nR; 
						availableFishArr[availableFishArrIndex++][1] = nC;
					} else { // ���� ���� ������ ������ �� �ִ� ���, (ũ�Ⱑ ���� ���)
						queue[++rear][0] = nR; // Queue�� ����
						queue[rear][1] = nC;
						visited[nR][nC] = time; // �湮�� �ð��� ǥ��
					}
				} // end of for(direction)
			} // end of while(1 time)
			
			if (availableFishArrIndex != 0) { // ���� �� �ִ� �������� �ִ� ���
				ANS = time; // ����⸦ ���� ���, ���� ����
				 // ���� ����� ������� ��ġ�� �����ϱ� ���� �ӽ� ����
				int tmpR = Integer.MAX_VALUE;
				int tmpC = Integer.MAX_VALUE; 
				
				for (int j = 0; j < availableFishArrIndex; j++) {
					int rr = availableFishArr[j][0];
					int cc = availableFishArr[j][1];
					
					if (tmpR > rr) { // �� ������ ���
						tmpR = rr; tmpC = cc; // �ӽ� ���� ����
					} else if (tmpR == rr) { // ���� ��(row)�� ���
						if (tmpC > cc) { // ��(column)�� �� ������ ��
							tmpR = rr; tmpC = cc; // �ӽ� ���� ����
						}
					}
				}
				
				cntFish[map[tmpR][tmpC]]--; // ���� ����� ũ���� �� ����
				map[tmpR][tmpC] = 0; // ���� ����� ���ֱ� 
				cntEat++; // ���� ����� ����   
				
				if (cntEat == level && level <= 6) { // level��ŭ �Ծ��ٸ�
 					cntEat = 0; // ������ �����Ƿ�, 0���� �ʱ�ȭ
					level++; // ������
				}

				// ���� �Ʊ� ����� ũ�⺸�� ���� ����Ⱑ �ִ��� �˻�
				for (int j = 1; j < level; j++) { 
					tmp += cntFish[j];
				}
				
				if (tmp == 0) { // ���� �� �ִ� ����Ⱑ ���ٸ�,
					break total; // Ž�� ���� (Backtracking)
				}
				// -- �ʱ�ȭ �۾�
				front = -1; rear = -1;
				visited = new int[N][N];
				visited[tmpR][tmpC] = time; // ���� �Ʊ� ��� ��ġ�� ���� �ð� ����
				queue[++rear][0] = tmpR; // Queue�� ���� �Ʊ� ��� ��ġ ����
				queue[rear][1] = tmpC;
				availableFishArrIndex = 0;
			}
			time++; // �ð� ����
		} // end of while(queue)
		System.out.println(ANS); // ���� ��� 
	} // end of main
} // end of class
