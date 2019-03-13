package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Tree implements Comparable<Tree> {
	int r;
	int c;
	int age;

	Tree(int r, int c, int age) {
		this.r = r;
		this.c = c;
		this.age = age;
	}

	@Override
	public int compareTo(Tree arg0) {
		return this.age - arg0.age;
	}
}

public class Main_16235_��������ũ {
	static int N; // N*N�� ũ��
	static int M; // �����ϴ� ����
	static int K; // ������ ���
	static int[][] dxy = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()) + 1; // N*N�� ũ��
		M = Integer.parseInt(st.nextToken()); // �����ϴ� ����
		K = Integer.parseInt(st.nextToken()); // ������ ���

		ArrayList<Tree>[][] map = new ArrayList[N][N]; // ������
		int[][] A = new int[N][N]; // ��ġ�� ��� ������ ������ �迭

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				A[i][j] = 5;
			}
		}

		int A_value = Integer.parseInt(br.readLine().trim()); // ����� ��

		boolean[][] visited = new boolean[N][N]; // �湮 check

		ArrayList<Tree> tp_list = new ArrayList<Tree>(); // ������ �ɾ��� ��
		ArrayList<Tree> dead_list = new ArrayList<Tree>(); // ���� �������� ����

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());

			if (map[r][c] == null) {
				map[r][c] = new ArrayList<Tree>();
			}

			map[r][c].add(new Tree(r, c, age));
			tp_list.add(new Tree(r, c, age));
		}

		for (int i = 0; i < K; i++) {
			// �� : �ڽ��� ���̸�ŭ ����� �԰�, ���̰� 1 �����Ѵ�. ���� ���� ������ ������, ���̰� � ������ �԰� ū��.
			for (int j = 0; j < tp_list.size(); j++) {
				Tree tp = tp_list.get(j);
				if (map[tp.r][tp.c].size() > 1) {
					Collections.sort(map[tp.r][tp.c]); // ���̰� � �켱
					for (int k = 0; k < map[tp.r][tp.c].size(); k++) {
						System.out.println("** " + k);
						System.out.println(tp.r + ", " + tp.c + " | " + map[tp.r][tp.c].get(k).age);
					}
				}
				Tree t = map[tp.r][tp.c].get(0);
				if (t.age > A[t.r][t.c]) { // ���
					dead_list.add(t);
					map[tp.r][tp.c].remove(t);
					tp_list.remove(t);

					System.out.println(tp_list.size());

				} else { // ����� �Ա�
					A[t.r][t.c] -= t.age++;
					visited[t.r][t.c] = true;
					tp_list.remove(t);
				}
			} // end of for of spring

			// ���� : ���� ������ ������ ������ 2
			for (int j = 0; j < dead_list.size(); j++) {
				Tree dt = dead_list.get(j);
				A[dt.r][dt.c] += (dt.age / 2);
			} // end of for of summer

			// ���� : ������ �����Ѵ�. ������ ���̰� 5�� ����̾�� �ϸ�, ������ 8���� ĭ�� ���̰� 1�� ������ ����
			for (int j = 0; j < tp_list.size(); j++) {
				Tree t = tp_list.get(j);
				if (t.age % 5 == 0) {
					for (int k = 0; k < dxy.length; k++) {
						int next_r = t.r + dxy[k][0];
						int next_c = t.c + dxy[k][0];
						if (inRange(next_r, next_c)) {
							if (map[next_r][next_c] == null) {
								map[next_r][next_c] = new ArrayList<Tree>();
							}
							map[next_r][next_c].add(new Tree(next_r, next_c, 1)); // �߰�
							tp_list.add(new Tree(next_r, next_c, 1));
						}
					}
				}
			} // end of for of fall

			// �ܿ� : S2D2�� ����� �߰��Ѵ�.
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					A[j][k] += A_value;
				}
			} // end of for of winter
		} // end of for of Year

		System.out.println(tp_list.size());
	} // end of main

	private static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N) {
			return false;
		}
		return true;
	}
} // end of class
