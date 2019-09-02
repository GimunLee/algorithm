package swea.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * ����ȭ Memo
 */

public class Solution_UP_2383_���ɽĻ�ð�_memo {
	private static ArrayList<Pair> people; // ����� ��ġ ������ ���� ����
	private static Stair[] stairs; // ����� ��ġ�� ���̸� ���� ����
	private static int[][] memo; // [n��° ���][n��° ���] : n��° ����� n��° ��ܿ� ������ ���� �ð��� �޸�

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��
		StringBuilder sb = new StringBuilder(); // ��� �ð��� ���̱� ���� StringBuilder

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // ���� ũ��
			int[][] map = new int[N][N]; // ������ ����
			people = new ArrayList<Pair>(); // ����� ��ġ ������ ���� ����
			stairs = new Stair[2]; // ����� ��ġ�� ���̸� ���� ����
			int idx_stair = 0; // �迭�� ������ �� �ʿ��� index ����

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) { // ����� ��
						people.add(new Pair(r, c)); // ��� ���� List�� �߰�
					} else if (map[r][c] >= 2) { // ����� ��
						stairs[idx_stair++] = new Stair(r, c, map[r][c]); // ��� ���� �迭�� �߰�
					}
				}
			} // end of for(input)

			set = new int[people.size()]; // ������� �̿��� ����� ������ �ߺ������� ������
			memo = new int[2][people.size()]; // [n��° ���][n��° ���] : n��° ����� n��° ��ܿ� ������ ���� �ð��� �޸�
			ans = Integer.MAX_VALUE; // ���� ����
			setStairs(0); // ������� �̿��� ����� ����
			sb.append('#').append(tc).append(' ').append(ans).append('\n'); // �ѹ��� ����ϱ� ���� StringBuilder�� ����
		} // end of for TestCase
		System.out.print(sb.toString()); // StringBuilder�� �����س��� ������ �ѹ��� ���
	} // end of main

	private static int[] set; // �ߺ������� ������ �迭
	private static int ans; // ���� ����

	/** DFS�� �̿��� �ߺ������� ������� �̿��� ����� �̾��� */
	private static void setStairs(int len) {
		if (len == set.length) { // ��� �ο��� ��ŭ ����� ��������ٸ�
			int tmp = solve(); // �ش� ��ǿ��� �ɸ��� �� ��ȯ
			ans = (ans > tmp) ? tmp : ans; // �� �� �ּҰ� ����
			return;
		}
		for (int i = 0; i < 2; i++) {
			set[len] = i;
			setStairs(len + 1); // ��� ȣ��
		}
	} // end of func(setStairs)

	/** �ߺ������� �����ͷ� �ش� �ߺ����� ������ ��� ����� �������� �ð��� ��ȯ���� */
	private static int solve() {
		ArrayList<Integer> timeFirst = new ArrayList<>(); // ù��° ����� �̿��ϴ� ���
		ArrayList<Integer> timeSecond = new ArrayList<>(); // �ι�° ����� �̿��ϴ� ���

		// �� ������� ������ ��ܱ��� ���� �Ÿ��� ����Ͽ� List�� ����
		for (int idx_people = 0; idx_people < set.length; idx_people++) {
			int dTime = 0; // ��ܱ��� ���� �ð�
			int stairNum = set[idx_people]; // ���� ����� �̿��ϴ� ���

			if (memo[stairNum][idx_people] == 0) { // ������ �����س��� ���� ���� ���
				// ��ܱ��� ���� �ð� ���
				dTime = Math.abs(people.get(idx_people).r - stairs[stairNum].r)
						+ Math.abs(people.get(idx_people).c - stairs[stairNum].c);
				memo[stairNum][idx_people] = dTime; // ���� ���� �� �����Ƿ� �޸�
			} else { // ������ �޸� ���ִ� ���
				dTime = memo[stairNum][idx_people]; // �޸�� �ð��� �״�� ������
			}

			if (stairNum == 0) { // ù��° ����� �̿��� ��
				timeFirst.add(dTime); // ù��° ��ܱ��� �ɸ��� �ð��� List�� �߰�
			} else { // �ι�° ����� �̿��� ��
				timeSecond.add(dTime); // �ι�° ��ܱ��� �ɸ��� �ð��� List�� �߰�
			}
		}

		if (timeFirst.size() != 0) {
			Collections.sort(timeFirst); // �� �ð��� �������� �������� ����
		}
		if (timeSecond.size() != 0) {
			Collections.sort(timeSecond); // �� �ð��� �������� �������� ����
		}

		// ��� ����� ���������ϴ� ����̹Ƿ�, �ִ밪�� ����
		int max_first = Integer.MIN_VALUE; // ù��° ��� �̿��� �Ϸ��� ������ �ɸ��� �ð�
		int max_second = Integer.MIN_VALUE; // �ι�° ��� �̿��� �Ϸ��� ������ �ɸ��� �ð�

		if (timeFirst.size() > 0) { // ù��° ����� �̿��ϴ� ����� �ִ� ���
			for (int i = 0; i < timeFirst.size(); i++) { // ù��° ����� ����ϴ� ��� �� ��ŭ �ݺ�
				if (i < 3) { // 3������� ��ٸ��� �ʾƵ� �ǹǷ�, ��ܱ��� �ɸ��� �ð��� ����� �������� �ð��� ���ؼ� ����
					timeFirst.set(i, timeFirst.get(i) + stairs[0].len + 1);
					continue;
				}

				// 3�� �̻���ʹ� ����ؾ���
				if (i - 3 >= 0) {
					// ��ܱ��� ���� �ð����� �� �� ����� �� �������ٸ� ��ٸ��� �ʾƵ���
					if (timeFirst.get(i) >= timeFirst.get(i - 3)) {
						timeFirst.set(i, timeFirst.get(i) + stairs[0].len + 1);
					} else { // ��ٸ��� ���
						// ����� �������� �ִ� ����� �� �������� ���� �ش� ����� ����� �������� ���̶� ������
						timeFirst.set(i, timeFirst.get(i - 3) + stairs[0].len);
					}
				}
			}
		}

		// ù��° ��� ������ �Ȱ���
		if (timeSecond.size() > 0) {
			for (int i = 0; i < timeSecond.size(); i++) {
				if (i < 3) {
					timeSecond.set(i, timeSecond.get(i) + stairs[1].len + 1);
					continue;
				}
				if (i - 3 >= 0) {
					if (timeSecond.get(i) >= timeSecond.get(i - 3)) {
						timeSecond.set(i, timeSecond.get(i) + stairs[1].len + 1);
					} else {
						timeSecond.set(i, timeSecond.get(i - 3) + stairs[1].len);
					}
				}
			}
		}

		// ���� ���� �����߱� ������ ������ �ε��� ���� �ش� ����� ��� �̿����� ���� �ð���
		if (timeFirst.size() != 0) {
			max_first = timeFirst.get(timeFirst.size() - 1);
		}
		if (timeSecond.size() != 0) {
			max_second = timeSecond.get(timeSecond.size() - 1);
		}
		return Math.max(max_first, max_second); // ��� ����� �̿��� �Ϸ��ؾ��ϹǷ�, �� ��� �� ���� ū ���� ��ȯ��
	} // end of func(solve)

	private static class Pair { // ����� ����
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair

	private static class Stair { // ����� ����
		int r;
		int c;
		int len; // ����� ����

		Stair(int r, int c, int len) {
			this.r = r;
			this.c = c;
			this.len = len;
		}
	} // end of Stair
} // end of class
