package mock;
/* -- �ݷ�
10
1 1 1 0 0 0 0 0 0 0
1 1 1 1 0 0 0 0 0 0
1 0 1 1 0 0 0 0 0 0
0 0 1 1 1 0 0 0 0 0
0 0 0 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0


11
1 1 1 1 1 0 0 0 0 0 0 
1 0 0 0 1 0 0 0 0 0 1
1 1 1 0 1 0 0 0 0 0 0
1 0 0 0 1 0 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0

5
1 0 0 0 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 0 0 1
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UP_Main_2146_�ٸ������ {
	static int[] dr = { -1, 1, 0, 0 }; // �ٸ��� ��ġ�� �� �� ���� (����)
	static int[] dc = { 0, 0, -1, 1 }; // �ٸ��� ��ġ�� �� �� ���� (�¿�)
	private static int[][] map; // input�� ������ ����
	private static int N; 
	private static boolean[][] visited; // �ٸ��� ��ġ�� ������ Ȯ���ϴ� ����
	private static Queue<Pair> island_queue; // ���� �ܰ��� ������ ����

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // map�� ũ�� (N*N)
		map = new int[N][N]; // map ����
		visited = new boolean[N][N]; // visit ����

		for (int r = 0; r < N; r++) { 
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // end of for(input)

		island_queue = new LinkedList<>(); // queue ����

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1 && !visited[r][c]) {
					checkSide(r, c);
					idx++;
				}
			}
		} // end of for(���� �ܰ� ���� ����)

		int ans = Integer.MAX_VALUE; // ���� ª�� �ٸ� ���� (����)
		int[][] length = new int[N][N]; // ��ġ�� �ٸ� ���̸� ������ ����
		boolean flag = false; // �ٸ����� ���� ���, �� Ÿ���� �ٸ��� ��� ��ġ�ϰ� �����ϱ� ���� ����

		while (!island_queue.isEmpty()) { // queue�� �� ������
			int size = island_queue.size(); // �� Ÿ�Ӿ� �ٸ��� ��ġ�ϱ� ���� ����
			for (int i = 0; i < size; i++) { // �� Ÿ�ӵ���
				Pair p = island_queue.poll();
				
				for (int j = 0; j < dr.length; j++) { // ���� �ܰ����� �����¿� �ٸ� ��ġ (���ǿ� �´� ���)
					int nR = p.r + dr[j]; 
					int nC = p.c + dc[j];

					if (nR < 0 || nC < 0 || nR >= N || nC >= N) { // ������ ��� ���
						continue;
					}

					if (visited[nR][nC]) { // ���� ���
						continue;
					} else if (map[nR][nC] != 0) { // �ٴ�����, �ٸ��� �̹� ��ġ�� ���
						if (map[nR][nC] != p.num) { // ���� �ٸ��� ��ġ�ϰ� �ִ� ���� �ƴ� ���
							// ���� ��ġ�� �ٸ� ���̰� ���� ����� �亸�� ª�� ��� �� ����
							ans = ans > length[nR][nC] + length[p.r][p.c] ? length[nR][nC] + length[p.r][p.c] : ans;
							flag = true; // �ٸ��� ��ġ�߱� ������ �� Ÿ���� ������ Ž�� ����
						}
					} else {
						island_queue.add(new Pair(nR, nC, p.num)); // ������ Ȯ���� �ٸ�
						map[nR][nC] = p.num; // �ٸ� ��ġ
						length[nR][nC] = length[p.r][p.c] + 1; // �ٸ� ���� ������Ʈ
					}
				}
			}
			if (flag) {
				break;
			}
		} // end of while(setBridge)
		System.out.println(ans); // ���� ���
	} // end of main

	private static int idx = 1; // ���° ������ ������ ����

	/** ���� �ܰ� üũ */
	private static void checkSide(int r, int c) {
		visited[r][c] = true; 
		map[r][c] = idx; // map�� ���° ������ üũ

		for (int i = 0; i < dr.length; i++) {
			int nR = r + dr[i]; 
			int nC = c + dc[i];

			if (nR < 0 || nC < 0 || nR >= N || nC >= N) { // �ܰ��� ���
				continue;
			}

			if (visited[nR][nC]) { // �湮�� ���
				continue;
			}

			if (map[nR][nC] == 0) { // �����¿� �� �� ���̶� ���̶�� ���� �ܰ��Դϴ�.
				island_queue.add(new Pair(r, c, idx)); // ���� �ܰ��� queue�� �����Ͽ�, �ٸ� ��ġ�� ���
			} else {
				checkSide(nR, nC); // �̾����ִ� ���, ��� Ž��
			}
		}
		return;
	} // end of func(checkSide)

	private static class Pair {
		int r;
		int c;
		int num; // ���° ������ ����

		Pair(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	} // end of Pair
} // end of class
