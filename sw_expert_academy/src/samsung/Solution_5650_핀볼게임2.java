package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5650_�ɺ�����2 {
	static int[] dr = { -1, 0, 1, 0 }; // row : ��, ��, ��, ��
	static int[] dc = { 0, 1, 0, -1 }; // column : ��, ��, ��, ��
	private static int[][] map; // �Է� �޴� ���� �����ϱ� ���� 2���� �迭 ����
	private static int N; // map�� ũ�� (row, column)
	private static ArrayList<Pair>[] wHole; // ��Ȧ�� ��ġ�� ��� ���� list ����

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // map�� ũ�� 
			map = new int[N][N]; // map�� ũ�⸸ŭ 2���� �迭 ����
			Queue<Pair> q = new LinkedList<>(); // �ɺ��� ���� ��ġ�� ���Լ����� �ڷᱸ���� ť�� ����
			wHole = new ArrayList[5]; // ������ ������ ��Ȧ�� ��ġ�� ���� �޾ƿ��� ����, ArrayList �迭�� ���� 

			for (int i = 0; i < wHole.length; i++) {
				wHole[i] = new ArrayList<>(); // �� ��Ȧ index�� ArrayList ����
			}

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 0) { // 0�� ���, �ɺ��� ���� �� �����Ƿ� ť�� �����Ͽ� Ȯ��
						q.add(new Pair(r, c));
					}
					if (map[r][c] >= 6 && map[r][c] <= 10) { // ��Ȧ�� ���,
						wHole[map[r][c] - 6].add(new Pair(r, c)); // idx�� 0���� �����ϹǷ� -6�� �Ͽ� ����
					}
				}
			} // end of for Input

			int max = Integer.MIN_VALUE; // �������� ����� ����

			start: while (!q.isEmpty()) { // Ž������ ���� �������� ���������� �ݺ�
				Pair p = q.poll(); // Ž���� ������
				int sR = p.r; // �����ϱ� ���� ������ sR, sC
				int sC = p.c;

				dir: for (int i = 0; i < dr.length; i++) { // �ɺ��� 4����(�����¿�)�� Ž��
					int next_dir = i; // ����� ������ ���, ������ �������ֱ� ���� ����
					int r = p.r; // �ɺ��� ������ R
					int c = p.c; // �ɺ��� ������ C
					int cnt = 0; // ���̳� ��Ͽ� �ε��� ���, ������ �÷��ֱ� ���� ����
					
					here: while (true) { // �ɺ��� �������̳�, ��Ȧ�� ���������� �ݺ�
						int nR = r + dr[next_dir]; // ���� �������� �������� ���, ���� row ��ġ
						int nC = c + dc[next_dir]; // ���� �������� �������� ���, ���� column ��ġ

						boolean isWall = inRange(nR, nC); // ���� ��ġ�� ������ Ȯ��
						
						// �ɺ��� �������̰ų� ��Ȧ�̸�, ���� ������ �������ְ� �ɺ��� ���������� ���� ���� Ž��
						if (isWall && ((nR == sR && nC == sC) || map[nR][nC] == -1)) { 
							max = (max < cnt) ? cnt : max;
							continue dir;
						}

						// ���̸�, 5�� ��ϰ� ����
						if (!isWall) { 
							next_dir = block_dir[4][next_dir]; // ���� �ε������Ƿ�, ���� ���� 
							r = nR; // continue �ϱ� ��, ���� ��ġ�� ����
							c = nC; 
							cnt++; // ���� �ε������Ƿ�, ���� ����
							continue here; // �ٲ� �������� �ٽ� Ž���ϱ� ���� continue
						}

						// ����̶��, �� ��Ͽ� �°� ���� ����
						if (map[nR][nC] >= 1 && map[nR][nC] <= 5) {
							next_dir = block_dir[map[nR][nC] - 1][next_dir];
							r = nR;
							c = nC;
							cnt++;
							continue here;
						}

						// ��Ȧ�̶��, ��Ī�Ǵ� ��Ȧ�� ��ġ�� ����
						if (map[nR][nC] >= 6 && map[nR][nC] <= 10) { 
							Pair wp = get_wHole(nR, nC);
							r = wp.r;
							c = wp.c;
							continue here;
						}
						
						// ���� ���ǿ� �ش����� �ʴ´ٸ� �״�� ���� ��ġ�� �̵�
						r = nR;
						c = nC;

					} // end of here:while
				} // end of dir:while
			} // end of start:while
			System.out.println("#" + tc + " " + max); // ���� ���
		} // end of for TestCase
	} // end of main

	// 0:��, 1:��, 2:��, 3:��
	/** ���� ���⿡ N��° ����� �� ������¿� �¾��� ���, �ٲ�� ������ ���� */
	private static int[][] block_dir = { 
			{ 2, 3, 1, 0 }, // 1�� ���
			{ 1, 3, 0, 2 }, // 2�� ���
			{ 3, 2, 0, 1 }, // 3�� ���
			{ 2, 0, 3, 1 }, // 4�� ���
			{ 2, 3, 0, 1 } // 5�� ���
	};
	
	/** ��Ȧ�� ������, ���� ���� �ٸ� ��Ȧ ��ǥ ��ȯ */
	private static Pair get_wHole(int r, int c) {
		int idx = map[r][c] - 6;
		if (wHole[idx].get(0).r == r && wHole[idx].get(0).c == c) {
			return new Pair(wHole[idx].get(1).r, wHole[idx].get(1).c);
		} else {
			return new Pair(wHole[idx].get(0).r, wHole[idx].get(0).c);
		}
	} // end of get_wHole()

	/** �ܰ����� �������, �� ������ Ȯ���ϰ� ���̶�� false, ���� �ƴ϶�� true */
	private static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N) {
			return false;
		}
		return true;
	} // end of inRange()

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
