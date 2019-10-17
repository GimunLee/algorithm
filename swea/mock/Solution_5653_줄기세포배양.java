package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5653_�ٱ⼼����� {
	private static final int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 }; // �����¿�
	private static final int SIZE = 1200; // map�� �ִ�
	private static Cell[][] map; // �ٱ⼼�� �����
	private static int N, M, K; //
	private static int[][] isExist;
	private static boolean[][] notYet;
	private static Queue<Pair> queue;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // �ʱ� ������ ����ũ��
			M = Integer.parseInt(st.nextToken()); // �ʱ� ������ ����ũ��
			K = Integer.parseInt(st.nextToken()); // ��� �ð�
			map = new Cell[SIZE][SIZE];
			isExist = new int[SIZE][SIZE];
			notYet = new boolean[SIZE][SIZE];
			queue = new LinkedList<Pair>();
			int answer = 0;

			for (int r = SIZE / 2; r < SIZE / 2 + N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = SIZE / 2; c < SIZE / 2 + M; c++) {
					int x = Integer.parseInt(st.nextToken());
					if (x != 0) {
						map[r][c] = new Cell(x); // (750, 750) -> ������
						queue.add(new Pair(r, c));
						answer++;
						isExist[r][c] = 1; // �ʱ⿡�� �̹� ���� 1 -> 0�� �ǹ�
					}
				}
			}
			// 1. �ʱ⿡�� ��Ȱ�� ����, ����� ��ġ�� X�ð��϶�,
			// - X�ð����� ��Ȱ�� / X�ð��� ������ ���� Ȱ������
			// 2. Ȱ�� ��, X�ð����ȸ� ����ְ�, X�ð��� ������ �װԵ�
			// - ù 1�ð����� ��,��,��,�� �� ���� ���� ���� -> ���ĵ� �ٱ⼼���� ��Ȱ��
			// -> ���� �ð��� �����ϴ� ��� ������� �� ū �ٱ⼼���� ����
			// 3. �׾ ��������

			for (int k = 1; k <= K; k++) {
//				print(k);
				int queueSize = queue.size();
				for (int i = 0; i < queueSize; i++) {
					Pair p = queue.poll();
					if (notYet[p.r][p.c]) {
						notYet[p.r][p.c] = false;
						answer++;
					} else {
						map[p.r][p.c].z--;
					}
					if (-1 * map[p.r][p.c].z >= map[p.r][p.c].x) { // ���� ����
						answer--;
						continue;
					}
					queue.add(new Pair(p.r, p.c));
					if (map[p.r][p.c].z <= 0) { // Ȱ��ȭ ���� -> 4���� Ž��
						for (int d = 0; d < dr.length; d++) {
							int nR = p.r + dr[d];
							int nC = p.c + dc[d];

							if (isExist[nR][nC] != 0 && (isExist[nR][nC] - 1) < k) { // ���� ������ �̹� �� �ð��� �ɾ��� ���� �ִ� ���
								continue;
							}

							if (map[nR][nC] == null) { // ���� ���, �׳� �ɱ�
								map[nR][nC] = new Cell(map[p.r][p.c].x);
								queue.add(new Pair(nR, nC));
								isExist[nR][nC] = k + 1; // �ɾ��� �ð�
								notYet[nR][nC] = true;
							} else { // ���� �ð��� �̹� �ٸ� ������ �ִ� ���
								map[nR][nC].x = Math.max(map[nR][nC].x, map[p.r][p.c].x);
								map[nR][nC].z = Math.max(map[nR][nC].x, map[p.r][p.c].x);
							}
						}
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static void print(int k) {
		System.out.println(k + "�ð�");
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				if (map[r][c] == null) {
					System.out.print("0 ");
					continue;
				}
				System.out.print(map[r][c].x + " ");
			}
			System.out.println();
		}
	}

	private static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static class Cell {
		int x; // �����
		int z; // �ð��� ���� ��ƺ� ����� (Ȱ��ȭ�Ǵ��� �ȵǴ��� üũ), 0���� ũ�ٸ� ��Ȱ��ȭ, 0�̸� Ȱ��ȭ��, ����(z)�� x�� ����

		public Cell(int x) {
			this.x = x;
			this.z = x;
		}
	} // end of Cell
} // end of Class
