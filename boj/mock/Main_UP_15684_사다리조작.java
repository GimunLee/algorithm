package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_UP_15684_��ٸ����� {
	private static int N, H, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // ���μ��� �� (Column)
		M = Integer.parseInt(st.nextToken()); // ���μ��� ����
		H = Integer.parseInt(st.nextToken()); // ���μ��� �� (Row)

		int[][] map = new int[H + 1][N + 1]; // ��ٸ��� ������ �� ���� (0�� ������� ����)

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); // ���μ��� ��ġ
			int b = Integer.parseInt(st.nextToken()); // ���μ� b�� b+1�� ���������
			map[a][b] = 1; // ��ٸ��� ����������� ǥ��
		} // end of for(Input)

		if (H == 0) { // ���μ��� ���ٸ�, �̹� ������ �����
			System.out.println(0); // 0�� ��� �� ����
			return;
		}

		ANSER = Integer.MAX_VALUE; // ���� ����
		solve(map, 1, 0); // solve(��, ���� ���μ��� �ε���, ���� ���μ��� ����)

		if (ANSER == Integer.MAX_VALUE || ANSER > 3) { // ������ ���� �� ���� ����̰ų�, ���μ��� ������ 3�� ������
			System.out.println("-1"); // �Ұ������� ���
		} else { // ������ �ִٸ�
			System.out.println(ANSER); // ���� ���
		}
	} // end of main

	private static int ANSER; // ���� ����

	/** map�� �޾Ƽ� ������ ������� üũ�ϴ� �Լ� */
	private static boolean check(int[][] map) {
		// for������ �ȵǴ� ��츦 üũ�Ѵ�.
		for (int c = 1; c < N; c++) { // �� ���μ��� �˻�
			int r = 1; // ���μ��� ��ġ -> �� ���μ����� �׻� �ֻ�ܺ��� ����
			int moveC = c; // ���μ��� ���μ��� ���¿� ���� �������� ����

			while (true) {
				if (map[r][moveC - 1] == 1) { // �������� �� �� �ִٸ�
					moveC--; // �������� ������
				} else if (map[r][moveC] == 1) { // ���������� �� �� �ִٸ�
					moveC++; // ���������� ������
				}
				// �� �ܿ��� 0�� ����̹Ƿ� �Ʒ��� �̵�
				if (++r == H + 1) { // ���� ���ϴܱ��� �����ߴٸ�, while Ż��
					break;
				}
			} // end of while

			if (moveC != c) { // ���ϴܱ��� ������, �������� moveC�� ���� ������ c�� ���� �ʴٸ�
				return false; // ������ �� �� ����
			}
		} // end of for

		return true; // for������ return�� �Ͼ�� �ʾҴٸ�, ������ ������ ����̹Ƿ� true ��ȯ
	} // end of fucn(check)

	/** chooseC�� chooseC+1 ���̿� ������ �ȴٸ� ��� ���� ��ٸ��� ��ġ�غ���. (chooseC���� chooseC+1�� ���� ��) */
	private static void solve(int[][] map, int chooseC, int cnt) {
		if (cnt > 3 || chooseC == N) { // ��ġ�� ���μ��� ���� 3�� �Ѱų�, ��� ���μ��� Ž���� ���
			return;
		}

		if (check(map)) { // �ϳ��� ���μ��� ��ġ�ϸ�, �������� Ȯ���غ�
			ANSER = ANSER > cnt ? cnt : ANSER; // ������ ����� ���� ����
			return;
		}

		// ���� ���μ� ���� ��� ���� ���μ��� ��ġ�غ�
		for (int r = 1; r <= H; r++) {
			// ���μ��� ���ӵ� �� �����Ƿ�, ���� ��ġ�� ���μ����� 3���(���� ���μ�, �� ���μ�, �� ���μ�)�� ��ٸ��� ��ġ���ִ��� Ȯ���Ѵ�.  
			if (map[r][chooseC - 1] != 1 && map[r][chooseC] != 1 && map[r][chooseC + 1] != 1) { 
				map[r][chooseC] = 1; // �����ϴٸ�, ���μ��� ��ġ�Ѵ�.
				solve(map, chooseC, cnt + 1); // ���� ���μ����� ������ �߰��ؼ� ������.
				map[r][chooseC] = 0; // ������ �ذ� ���� �� �����Ƿ�, ��� return��, ��ġ�� ���μ��� �ٽ� �����Ѵ�.
			}
		}
		// for���� ���Դٴ� ���� �� ���μ��� ���� ��� ���μ��� ���Ҵٴ� �ǹ��̴�. ���� ���� ���μ��� Ž���ϵ��� �Ѵ�.
		solve(map, chooseC + 1, cnt);
	}

	/** ������ print �Լ� */
	private static void print(int[][] map) {
		System.out.println();
		for (int r = 1; r < H + 1; r++) {
			for (int c = 1; c < N; c++) {
				System.out.print(" | ");
				System.out.print(map[r][c]);
				if (c == N - 1) {
					System.out.print(" | ");
				}
			}
			System.out.println();
		}
	} // end of func(print)
} // end of class
