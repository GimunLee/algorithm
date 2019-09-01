package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2019.09.01.(��) ���̸� ��� �Դ��� �� �����غ��� (�ð� ���� ����!)
 */
public class Main_16235_��������ũ_other2 {
	private static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // ���� ũ��
		int M = Integer.parseInt(st.nextToken()); // ������ ��
		int K = Integer.parseInt(st.nextToken()); // K�� ��

		int[][] map = new int[N + 1][N + 1];
		int[][] A = new int[N + 1][N + 1];

		int[][] treeMapIdx = new int[N + 1][N + 1];
		Tree[][][] treeMap = new Tree[N + 1][N + 1][1000];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				map[r][c] = 5; // ������ ����� 5
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int treeCnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			treeMap[r][c][treeMapIdx[r][c]++] = new Tree(r, c, age);
			treeCnt++;
		}
		// -- end of input

		int ANSER = solve(map, A, K, treeCnt, treeMap, treeMapIdx);

		System.out.println(ANSER);
	} // end of main

	private static int solve(int[][] map, int[][] A, int K, int treeCnt, Tree[][][] treeMap, int[][] treeMapIdx) {

		Queue<Tree> spreadQueue = new LinkedList<>();

		for (int k = 0; k < K; k++) {
			// �� (��� �Ա�)

			for (int r = 1; r < map.length; r++) {
				for (int c = 1; c < map[r].length; c++) {
					if (treeMapIdx[r][c] == 0) { // ������ �ִ�. � �������� �Ա�
						continue;
					}

					if (treeMapIdx[r][c] != 1) { // ���������� �ִ� ���
						Tree[] tmpTree = new Tree[treeMapIdx[r][c]];
						for (int i = 0; i < treeMapIdx[r][c]; i++) {
							tmpTree[i] = treeMap[r][c][i];
						}
						Arrays.sort(tmpTree);
						for (int i = 0; i < treeMapIdx[r][c]; i++) {
							treeMap[r][c][i] = tmpTree[i];
						}
					}

					int energy = map[r][c];

					int size = treeMapIdx[r][c];
					for (int i = 0; i < size; i++) {
						if (energy < treeMap[r][c][i].age) { // ���� ���̺��� ����� ���� ���
							treeCnt--;
							map[r][c] += (treeMap[r][c][i].age / 2);
							treeMapIdx[r][c]--;
							continue;
						} else {
							map[r][c] -= treeMap[r][c][i].age;
							energy -= treeMap[r][c][i].age;
							treeMap[r][c][i].age += 1;
							if (treeMap[r][c][i].age % 5 == 0) {
								spreadQueue.add(treeMap[r][c][i]);
							}
						}

					}

				}

			}

			if (treeCnt == 0) {
				return 0;
			}

			while (!spreadQueue.isEmpty()) {
				Tree tree = spreadQueue.poll();
				int r = tree.r;
				int c = tree.c;

				for (int d = 0; d < dr.length; d++) {
					int nR = r + dr[d];
					int nC = c + dc[d];

					if (nR <= 0 || nC <= 0 || nR >= map.length || nC >= map[0].length) {
						continue;
					}

					treeCnt++;
					treeMap[nR][nC][treeMapIdx[nR][nC]++] = new Tree(nR, nC, 1);
				}
			}

			for (int r = 1; r < map.length; r++) {
				for (int c = 1; c < map[r].length; c++) {
					map[r][c] += A[r][c]; // (�ܿ�) ��� �߰�
				}
			}

		}

		return treeCnt;
	} // end of func(solve)

	private static void print(int[][] treeMapIdx) {
		System.out.println();
		for (int r = 1; r < treeMapIdx.length; r++) {
			for (int c = 1; c < treeMapIdx.length; c++) {
				if (treeMapIdx[r][c] != 0) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}

	}

	private static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int age;

		public Tree(Tree tree) {
			this.r = tree.r;
			this.c = tree.c;
			this.age = tree.age;
		}

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	} // end of Tree
} // end of class
