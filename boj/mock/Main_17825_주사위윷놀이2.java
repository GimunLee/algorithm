package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_17825_�ֻ���������2 {
	private static final int[][] map = {
			{ 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0 }, { 10, 13, 16, 19 },
			{ 20, 22, 24 }, { 25, 30, 35 }, { 30, 28, 27, 26 } };

	private static int[] input;
	private static Horse[] horse = new Horse[4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		input = new int[10];
		for (int i = 0; i < 10; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		} // -- end of for(Input)

		for (int i = 0; i < horse.length; i++) {
			horse[i] = new Horse(i, 0, -1);
		}

		answer = Integer.MIN_VALUE;
		dfs(0, 0);
		System.out.println(answer);
	} // end of main

	private static int answer;

	private static void dfs(int len, int score) {
		if (len == 10) {
			answer = Math.max(answer, score);
			return;
		}
		for (int i = 0; i < horse.length; i++) {
			if (horse[i].position == map[0].length - 1) { // �̹� ������ ���
				continue;
			}
			Horse origin = new Horse(horse[i].index, horse[i].mapIndex, horse[i].position);
			boolean canMove = move(i, input[len]);
			if (!canMove) {
				continue;
			}
			dfs(len + 1, score + getScore(i));
			horse[i] = origin;
		}
	}

	private static int getScore(int idx) {
		return map[horse[idx].mapIndex][horse[idx].position];
	}

	private static boolean move(int idx, int diceNum) {
		boolean ret = false;
		// 1. ���� ��ġ �����س���, �װ� ����������
		Horse moveHorse = new Horse(horse[idx].index, horse[idx].mapIndex, horse[idx].position);

		moveHorse.position += diceNum;

		switch (moveHorse.mapIndex) {
		case 0: // �� �̵����� Ȯ��
			if (moveHorse.position == 4) {
				moveHorse.mapIndex = 1;
				moveHorse.position = 0;
				break;
			} else if (moveHorse.position == 9) {
				moveHorse.mapIndex = 2;
				moveHorse.position = 0;
				break;
			} else if (moveHorse.position == 14) {
				moveHorse.mapIndex = 4;
				moveHorse.position = 0;
				break;
			} else if (moveHorse.position >= map[0].length) {
				moveHorse.position = map[0].length - 1; // ����
				break;
			}
			break;
		case 1: // 10�� ��
			if (moveHorse.position >= map[1].length) {
				moveHorse.mapIndex = 3; // 25��
				moveHorse.position = moveHorse.position - map[1].length;
				if (moveHorse.position >= map[3].length) {
					moveHorse.mapIndex = 0; // 25��
					moveHorse.position = map[0].length - 2 + moveHorse.position - map[3].length;
				}
			}
			break;
		case 2: // 20�� ��
			if (moveHorse.position >= map[2].length) {
				moveHorse.mapIndex = 3; // 25��
				moveHorse.position = moveHorse.position - map[2].length;
				if (moveHorse.position >= map[3].length) {
					moveHorse.mapIndex = 0; // 25��
					moveHorse.position = map[0].length - 2 + moveHorse.position - map[3].length;
				}
			}
			break;
		case 3: // 25�� ��
			if (moveHorse.position >= map[3].length) {
				moveHorse.mapIndex = 0; // 25��
				moveHorse.position = map[0].length - 2 + moveHorse.position - map[3].length;
				if (moveHorse.position >= map[0].length) {
					moveHorse.position = map[0].length - 1;
				}
			}
			break;
		case 4: // 30�� ��
			if (moveHorse.position >= map[4].length) {
				moveHorse.mapIndex = 3; // 25��
				moveHorse.position = moveHorse.position - map[4].length;
				if (moveHorse.position >= map[3].length) {
					moveHorse.mapIndex = 0; // 25��
					moveHorse.position = map[0].length - 2 + moveHorse.position - map[3].length;
				}
			}
			break;
		}

		// 3. �ڸ��� �ߺ��Ǵ��� Ȯ��
		if (!isDuplicated(moveHorse)) {
			// 4. �ߺ� �ȵǸ� �ڸ� ����
			horse[idx] = moveHorse;
			ret = true;
		}
		return ret;
	} // end of func(move)

	private static boolean isDuplicated(Horse compareHorse) {
		// ���������� �˻� ���ص���
		if (compareHorse.mapIndex == 0 && compareHorse.position == map[0].length - 1) {
			return false;
		}
		for (int i = 0; i < horse.length; i++) {
			if (i == compareHorse.index) {
				continue;
			}
			if (horse[i].mapIndex == compareHorse.mapIndex && horse[i].position == compareHorse.position) {
				return true;
			}
		}
		return false;
	} // end of func(isDuplicated)

	private static class Horse {
		int index, mapIndex, position;

		public Horse(int index, int mapIndex, int position) {
			this.index = index;
			this.mapIndex = mapIndex;
			this.position = position;
		}

//		@Override
//		public String toString() {
//			return "Horse [index=" + index + ", mapIndex=" + mapIndex + ", position=" + position + "]";
//		}
	} // end of Horse
} // end of class
