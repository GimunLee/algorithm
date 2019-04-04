package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS ����
 */

public class Solution_2383_���ɽĻ�ð�_BFS_F {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // ���� ũ��
			int[][] map = new int[N][N];

			Queue<Pair> q = new LinkedList<>();
			ArrayList<Stair> stairs = new ArrayList<>();
			ArrayList<Integer>[] wait = new ArrayList[2];

			wait[0] = new ArrayList<Integer>();
			wait[1] = new ArrayList<Integer>();

			int idx = 0;
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) {
						q.add(new Pair(r, c, idx++));
					} else if (map[r][c] >= 2) {
						stairs.add(new Stair(r, c, map[r][c]));
					}
				}
			}
			boolean[][][] visited = new boolean[idx][N][N];
			boolean[] isOut = new boolean[idx];
			boolean[] isWait = new boolean[idx];

			here: while (!q.isEmpty()) {

				int time = 0;
				for (int i = 0; i < q.size(); i++) {
					Pair p = q.poll();
//					System.out.println(p.idx);
					if (isOut[p.idx]) { // �̹� �������� ����� ���, Ž�� ����
						continue;
					}

					visited[p.idx][p.r][p.c] = true;

					time++;
					for (int j = 0; j < dr.length; j++) {
						int nR = p.r + dr[j];
						int nC = p.c + dc[j];

						if (nR >= N || nC >= N || nR < 0 || nC < 0) {
							continue;
						}

						if (visited[p.idx][nR][nC]) {
							continue;
						}

						if (map[nR][nC] >= 2) { // ��ܿ� ����
							for (int k = 0; k < 2; k++) {
								if (stairs.get(k).len == map[nR][nC]) { // �ش� ����� ���� ��������
									boolean canGo = false;
									int isP_idx = 0;
									int stair_idx = 0;

									for (int s = 0; s < 3; s++) {
										if (stairs.get(k).isP[0][s] == -1) { // �� �� �ִ� ���
											canGo = true;
											isP_idx = s; // ��⿭ �ε���
											break;
										}
									}
									if (canGo) { // �� �� �ִ� ���
										stairs.get(k).isP[0][isP_idx] = -1; // �ð� ����
										stairs.get(k).isP[1][isP_idx] = p.idx; // ��� ����

									} else { // �� �� ���� ���, ��⿭ �߰�
										wait[stair_idx].add(p.idx);
									}
									break;
								}

							}
						} else { // ����� �ƴ� ���, ��� ����
							visited[p.idx][nR][nC] = true;
							q.add(new Pair(nR, nC, p.idx));
						}

					} // end of for Move
				} // end of for Time

				for (int i = 0; i < isOut.length; i++) {
					if (!isOut[i]) { // �������� ����� ���� ���, ��� ���ƾ���
						break;
					} else if (i == isOut.length - 1) { // ��� �������� ���,
						System.out.println("#" + tc + " " + time);
						break here;
					}
				}

				// ��⿭ �ʱ�ȭ
				for (int i = 0; i < 2; i++) {
					Stair s = stairs.get(i);
					for (int k = 0; k < 3; k++) {
						if (s.isP[0][k] != -2) { // ����� ��������
							s.isP[0][k] += 1;
							if (s.isP[0][k] == s.len) { // �ش� ���̸�ŭ ������, ��⿭ ����
								s.isP[0][k] = -2;
								System.out.println(s.isP[1][k]);
								isOut[s.isP[1][k]] = true;
								s.isP[1][k] = 0;
							}
						} else { // ����� ����µ�, wait�� ������
							if (wait[i].size() != 0) {
								s.isP[0][k] = -1;
								s.isP[1][k] = wait[i].get(0);
								wait[i].remove(0);
							}
						}
					}
				}
			}
		} // end of for TestCase
	} // end of main

	private static class Pair {
		int r;
		int c;
		int idx;

		Pair(int r, int c, int idx) {
			this.r = r;
			this.c = c;
			this.idx = idx;
		}
	} // end of stair

	private static class Stair {
		int r;
		int c;
		int len; // ����� ����
		int[][] isP;

		Stair(int r, int c, int len) {
			isP = new int[2][3];
			this.r = r;
			this.c = c;
			this.len = len;
		}
	} // end of stair
} // end of class
