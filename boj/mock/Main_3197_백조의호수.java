package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3197_������ȣ�� {
	static final int[] dr = { -1, 1, 0, 0 }; // ���Ͽ� ���� Ž���� �� ���� ���� �迭 (����)
	static final int[] dc = { 0, 0, -1, 1 }; // ���Ͽ� ���� Ž���� �� ���� ���� �迭 (�¿�)
	private static char[][] map; // ���� ���� ���¸� ������ �迭
	private static int R, C; // R : ���� ����, C : ���� ����
	private static int maxMeltTime; // ���ϰ� ��� ��µ� �ɸ��� �ִ� �ð�
	private static int[] anotherBird = { 0, 2, 1 }; // �������� �������� Ȯ���� �� ���� ����
	private static ArrayList<Pair> birdList; // ������ ���� ��ġ�� �����س��� ����

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // ���� ���� ����
		C = Integer.parseInt(st.nextToken()); // ���� ���� ����
		birdList = new ArrayList<Pair>(); // ������ ���� ��ġ�� �����ϱ� ���� ���� ����
		map = new char[R][C]; // �� ����
		Queue<Pair> waterQueue = new LinkedList<>(); // ���� �����س���, ���ϸ� ���� �� ����ϴ� ť
		int idx = 1; // ������ �ε���

		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c);
				if (map[r][c] == '.') { // ���� ��,
					waterQueue.add(new Pair(r, c));
				}
				if (map[r][c] == 'L') { // ������ ��,
					birdList.add(new Pair(r, c, idx++)); // ������ ���� ��ġ�� ����
					waterQueue.add(new Pair(r, c)); // ������ �ִ� ���� ���̹Ƿ�, waterQueue�� ����
				}
			}
		} // end of for(Input)

		maxMeltTime = Integer.MIN_VALUE; // ���ϰ� ��µ� �ɸ��� �ִ� �ð��� ���ϱ� ����, ���� ���� ���� �����س���
		int[][] iceMeltMap = checkIceMeltTime(waterQueue); // ���ϰ� ��� �ð��� BFS Ž���� ���� ���Ͽ� ����
		ANSER = Integer.MAX_VALUE; // ���� ���� �ʱ�ȭ
		binarySearch(iceMeltMap, 0, maxMeltTime); // iceMeltMap�� �̺� Ž���� ���� ������ ������ �ּ� �ð� ���ϱ�
		System.out.println(ANSER); // ���� ���
	} // end of main

	private static int ANSER; // ���� ����

	/** ���ϰ� ��� �ð��� ���Ͽ� iceMeltMap�� ������ �� �ֵ��� ��ȯ�ϴ� �Լ� */
	private static int[][] checkIceMeltTime(Queue<Pair> waterQueue) {
		int[][] iceMeltMap = new int[R][C]; // ��ȯ�� �� ����
		boolean[][] visited = new boolean[R][C]; // �湮�ߴ��� Ȯ���ϴ� ����
		int time = 1; // ���ϰ� ��� �ð��� ���ʿ� 1

		while (!waterQueue.isEmpty()) { // ��� ���� Ž���� ������
			
			int size = waterQueue.size(); // �켱 �� time���� Ž��

			for (int i = 0; i < size; i++) {
				Pair pair = waterQueue.poll(); // ���� ���� ������ ��
				int r = pair.r;
				int c = pair.c;
				visited[r][c] = true; // ���� ��ġ �湮 ǥ��
				
				for (int dir = 0; dir < dr.length; dir++) { // ���� �����¿츦 Ž��
					int nR = r + dr[dir];
					int nC = c + dc[dir];

					if (nR < 0 || nC < 0 || nR >= R || nC >= C) { // ���� ����ٸ�
						continue;
					}

					if (visited[nR][nC]) { // �̹� �湮�� ���̶��
						continue;
					}

					if (map[nR][nC] == 'X') { // ���� ��ġ�� ���϶��
						iceMeltMap[nR][nC] = time; // iceMeltMap�� �ð��� ��� �ð��� ����
						waterQueue.add(new Pair(nR, nC)); // �쿴���Ƿ�, waterQueue�� ����
						maxMeltTime = maxMeltTime < time ? time : maxMeltTime; // �ִ� ��� �ð��� �����ϱ� ���� 
						visited[nR][nC] = true; // ���� ��ġ�� Ž�������Ƿ� �湮 ǥ��
					}
				}
			} // end of for(1 Time)
			time++; // �ð� ����
		} // end of while(queue)
		return iceMeltMap; // ���ϰ� ��� �ð��� ���� ���� ��ȯ
	} // end of func(checkIceMeltTime)

	/** ������ N�ʿ� ������ ���Ѵٸ�, N�� ������ ���� ���� �� ������ �̿��� */
	private static void binarySearch(int[][] iceMeltMap, int start, int end) {
		if (start > end) { //��� Ž���ߴٸ�,
			return;
		}

		int mid = (start + end) / 2; // �߰����� ����

		Queue<Pair> birdQueue = new LinkedList<>(); // ������ �������� Queue�� ����
		for (int i = 0; i < 2; i++) { // ������ ���� ��ġ�� Queue�� ����
			Pair pair = birdList.get(i);
			birdQueue.add(new Pair(pair.r, pair.c, pair.birdIndex));
		}
		
		boolean flag = goBird(iceMeltMap, birdQueue, mid); // �ش� mid�ð��� ������ ���� �� �ִ��� ��������.
		
		if (flag) { // ���� �����ٸ�, 
			ANSER = ANSER > mid ? mid : ANSER; // ���� �� �ִ� �ð� �� �ּ� �ð��� ����
		}
		if (flag) { // ������ �ϴ���, �� ���� ���� �� �� �����Ƿ� �� �۰� ������
			binarySearch(iceMeltMap, start, mid - 1);
		} else { // �� �������Ƿ�, �ð��� �� �÷��� ������
			binarySearch(iceMeltMap, mid + 1, end);
		}
	}

	/** ������ �������� �����ٸ� true, �� �����ٸ� false ��ȯ */
	private static boolean goBird(int[][] iceMeltMap, Queue<Pair> birdQueue, int mid) {
		int[][] visited = new int[R][C]; // ���° ������ �湮�� ������ üũ�ϱ� ���� int ������ ����

		while (!birdQueue.isEmpty()) { // ������ �̵��� �� ������ ����
			Pair pair = birdQueue.poll(); // ���� ������ ������ �M
			int r = pair.r; 
			int c = pair.c;
			int birdIndex = pair.birdIndex; // ���° �������� 

			visited[r][c] = birdIndex; // �ش� ������ �湮������ ǥ��

			for (int dir = 0; dir < dr.length; dir++) { // �����¿�� Ž��
				int nR = r + dr[dir];
				int nC = c + dc[dir];

				if (nR < 0 || nC < 0 || nR >= R || nC >= C) { // ���� ��� ���
					continue;
				}

				if (visited[nR][nC] == birdIndex) { // �̹� �ش� ������ �湮�� ���̶��,
					continue;
				}

				if (visited[nR][nC] == anotherBird[birdIndex]) { // ���� ��ġ�� �ٸ� ������ �湮�� ���
					return true; // �� ������ �������Ƿ� true ��ȯ
				}

				if (mid >= iceMeltMap[nR][nC]) { // ���ϰ� ��� �ð����� ���� �ð��� ũ�ų� ���� ���
					birdQueue.add(new Pair(nR, nC, birdIndex)); // ������ �̵��� �� �����Ƿ� Queue�� ����
					visited[nR][nC] = birdIndex; // �� ��ġ�� �湮�����Ƿ�, �湮 ǥ��
				}
			}
		} // end of while(Queue)
		// Queue�� ��� Ž���� ���� true�� ��ȯ���� �ʾҴٸ�, �� ������ �ش� �ð��� ���� �� �����Ƿ� false ��ȯ
		return false; 
	}

	private static class Pair {
		int r, c, birdIndex;

		public Pair(int r, int c, int birdIndex) {
			this.r = r;
			this.c = c;
			this.birdIndex = birdIndex;
		}

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
