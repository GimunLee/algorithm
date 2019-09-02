package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2019.09.01.(��)
 * 2019.09.02.(��) Upload
 */
public class Will_Main_16235_��������ũ_Deque {
	// ������ 8ĭ�� ������ �� ����� �迭
	private static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 }; // �� (row)
	private static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 }; // �� (column)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // ���� ũ��
		int M = Integer.parseInt(st.nextToken()); // ������ ��
		int K = Integer.parseInt(st.nextToken()); // K�� ��

		int[][] map = new int[N + 1][N + 1]; // map ���� (��ǥ�� +1 �� ������ ������ N+1��ŭ ����)
		int[][] A = new int[N + 1][N + 1]; // A ���� (��ǥ�� +1 �� ������ ������ N+1��ŭ ����)

		// �տ� �ְ�, �ڿ� �ֱ� ���� �ڷᱸ�� Deque ���
		Deque<Tree>[][] treeMap = new LinkedList[N + 1][N + 1]; // ��ǥ���� ������ Deque�� ����

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 1; c <= N; c++) {
				map[r][c] = 5; // ������ ����� 5
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int treeCnt = 0; // ������ ����
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			treeMap[r][c] = new LinkedList<Tree>();
			treeMap[r][c].add(new Tree(r, c, age));
			treeCnt++;
		}
		// -- end of input

		int ANSER = solve(map, A, K, treeCnt, treeMap);

		System.out.println(ANSER); // ���� ���
	} // end of main

	private static int solve(int[][] map, int[][] A, int K, int treeCnt, Deque<Tree>[][] treeMap) {

		Queue<Tree> spreadQueue = new LinkedList<>(); // ������ �����ϴ� ������ ����
		for (int k = 0; k < K; k++) { // K�⸸ŭ �ݺ�

			// �� (��� �԰� ���� �Ա�), ���� (���� ���� ������� �����) �ѹ��� ó��
			for (int r = 1; r < map.length; r++) {
				for (int c = 1; c < map[r].length; c++) {
					if (treeMap[r][c] == null) { // ������ ���� ��ǥ�̹Ƿ�, continue
						continue;
					}

					int energy = map[r][c]; // ���� �ִ� ����� �ӽ� ���� (������ �ѹ��� ó���ϱ� ������)
					int size = treeMap[r][c].size(); // ������ ������ �̸� ����

					for (int i = 0; i < size; i++) { // ������ ������ŭ Ž��
						Tree tree = treeMap[r][c].pollFirst(); // ���̼��̹Ƿ� �տ������� ����
						if (energy < tree.age) { // ���� ���̺��� ����� ���� ���
							treeCnt--; // ������ ����
							if (treeCnt == 0) { // ������ Ž���ؾ��� ������ ���ٸ� 0 ��ȯ
								return 0;
							}
							map[r][c] += (tree.age / 2); // ����� �÷���
							if (treeMap[r][c].size() == 0) { // �ش� ��ġ�� ������ �Ѱ��� ���ٸ� null�� �������
								treeMap[r][c] = null;
								break;
							}
						} else {
							map[r][c] -= tree.age; // ��� �Ա�
							energy -= tree.age; // ��� �Ա�
							tree.age += 1; // ���� ���� �Ա�
							treeMap[r][c].addLast(tree); // ������ �ں��� ���� (���� �������� �ʾƵ� ��)
							if (tree.age % 5 == 0) { // 5�� ������
								spreadQueue.add(tree); // ������ �����ؾ��ϱ� ���� Queue�� ����
							}
						}
					}
				}
			} // end of (Spring & Summer)

			// ���� (���� �����ϱ�)
			while (!spreadQueue.isEmpty()) {
				Tree tree = spreadQueue.poll();
				int r = tree.r;
				int c = tree.c;

				for (int d = 0; d < dr.length; d++) { // ������ 8ĭ ����
					int nR = r + dr[d];
					int nC = c + dc[d];

					if (nR <= 0 || nC <= 0 || nR >= map.length || nC >= map[0].length) {
						continue;
					}

					if (treeMap[nR][nC] == null) { // �ش� �ڸ��� ������ �ѱ׷絵 �����ٸ�
						treeMap[nR][nC] = new LinkedList<>();
					}
					treeCnt++; // ���� ���� �ø���
					treeMap[nR][nC].addFirst(new Tree(nR, nC, 1)); // 1�����̹Ƿ� �տ� �߰� (���� �������� �ʾƵ���)
				}
			} // end of (Fall)

			// �ܿ�
			for (int r = 1; r < map.length; r++) {
				for (int c = 1; c < map[r].length; c++) {
					map[r][c] += A[r][c]; // ��� �߰�
				}
			} // end of (Winter)

		}
		return treeCnt; // K�� �� ������ ���� ��ȯ
	} // end of func(solve)

	/** ������ print �Լ� */
	private static void print(ArrayList<Tree>[][] treeMap) {
		System.out.println();
		for (int r = 1; r < treeMap.length; r++) {
			for (int c = 1; c < treeMap.length; c++) {
				if (treeMap[r][c] != null) {
					System.out.print(treeMap[r][c].size() + " ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	} // end of func(print)

	private static class Tree {
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
	} // end of Tree
} // end of class
