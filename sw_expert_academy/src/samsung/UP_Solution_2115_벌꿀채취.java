package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UP_Solution_2115_����ä�� {
	private static int N; // ä���� ���� ũ��
	private static int M; // ������ �� �ִ� ������ ����
	private static int C; // �ϲ۴� ���� ä���� �� �ִ� �ִ� ��
	private static int[][] map; // �Է����� �޴� ������ ������ 2���� ���� �迭
	private static boolean[][] isChoice; // �ϲ��� �̹� �����ߴ��� �Ǻ��ϴ� ����
	private static ArrayList<Pair> list; // map�� indexing�ؼ� �ϲۺ� ������ �̱� �����ϰ� �� 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // ä���� ���� ũ��
			M = Integer.parseInt(st.nextToken()); // ������ �� �ִ� ������ ����
			C = Integer.parseInt(st.nextToken()); // �ϲ۴� ���� ä���� �� �ִ� �ִ� ��

			map = new int[N][N];
			list = new ArrayList<>();
			comb_bucket = new int[2][M]; // �� �ϲ��� ������ ������ index�� �����ϴ� �����Դϴ�.

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					list.add(new Pair(r, c)); // ��� map�� list�� �����մϴ�.
				}
			} // end of for input

			isChoice = new boolean[N][N];
			ans = Integer.MIN_VALUE; // �ִ� ������ ������ �����Դϴ�.
			chooceBucket(0, 0); // idx : �ϲ��� ������ �� ����, len : �ϲ��� ������ ������� �Ǻ��ϴ� ����

			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		} // end of for TestCase
		System.out.println(sb.toString()); // ���� ���
	} // end of main

	private static int tmp; // ù��° �ϲ��� ���� ä������ �� �ִ� ������ �����ϴ� ����
	private static int ans; // �ι�° �ϲ۱��� ���� ä�� �Ϸ������� �ִ� ������ �����ϴ� ����
	private static int[][] comb_bucket; // �� �ϲ��� ���� ä���� index�� �����ϴ� ����

	/**
	 * �� �ϲ��� ���ǿ� �°� ���� ä���� ���� ���ϴ�.
	 * 
	 * @param idx : �ϲ��� ���� ���� �ε���
	 * @param len : ��� �ϲ��� ������ �� �̾Ҵ��� �Ǵ��� ���� 
	 * */
	private static void chooceBucket(int idx, int len) {
		if (len == 2) { // �ϲ� 2�� ��� ä���� ���� ����� ��,
			tmp = 0; // ���ո��� ù��° �ϲ��� �ִ� ������ �������ݴϴ�.
			
			// �� �ϲ� ��� ���� �Լ��� ����մϴ�. ��, �ϲ� �� ä���� ���� ���ڷ� �ѱ�ϴ�.
			dfs(comb_bucket[0], 0, 0, 0); // ù��° �ϲ��� ���� ä���� ��, ä���� ���� ����, ä���� �� �뷮, �ִ� ����
			// ù��° �ϲ��� �ִ� ������ ����, ������ ���� �� �����Ƿ� �ι�° �ϲ��� ù��° �ϲ��� ���� �ִ� ���Ϳ��� ����� �����մϴ�.
			dfs(comb_bucket[1], 0, 0, tmp); // �ι�° �ϲ��� ���� ä���� ��, ä���� ���� ����, ä���� �� �뷮, �ִ� ����
			ans = (ans<tmp)? tmp : ans; // �� �ϲ� ��� ä���� ��, ������ �������ݴϴ�.
			return; 
		}

		here: for (int i = idx; i < N * N; i++) {
			Pair p = list.get(i);
			int r = p.r;
			int c = p.c;
			
			// ���η� M��°���� �ϲ��� ������ ������ �� �ִ��� Ȯ���մϴ�.
			for (int j = 0; j < M; j++) {
				if (c + j >= N) { // ������ �Ѿ ���, ���� index���� Ȯ���մϴ�.
					continue here;
				}

				if (isChoice[r][c + j]) { // �̹� �ٸ� �ϲ��� ������ ������ ���, ���� index���� Ȯ���մϴ�.
					continue here;
				}
			}
			
			// ���ǿ� �����ϹǷ�, �ش� index�� isChoice �迭�� ä���ݴϴ�.
			for (int j = 0; j < M; j++) {
				comb_bucket[len][j] = i + j;
				isChoice[r][c + j] = true;
			}
			
			// ������ ���, M���� ���� idx�� Ž���ص� �ǹǷ� i+M�� �Ѱ��ְ�, �� �ϲ��� ���� ������ ������Ƿ� len+1 ���ݴϴ�. 
			chooceBucket(i + M, len + 1); 
			
			// ���󺹱� : ���� ������ �ذ� ���� �� �����Ƿ�, �ٽ� ���󺹱� ���ݴϴ�.
			for (int j = 0; j < M; j++) {
				isChoice[r][c + j] = false;
			}
		}
	} // end of chooceBucket()

	
	/**
	 * �� �ϲ��� ä���� ���뿡�� ���ǿ� �°� ���� ä���ϸ� �ִ� ������ ����մϴ�.
	 * 
	 *  @param each_bucket : �ϲ��� ä���� ���� �迭
	 *  @param idx : �ϲ��� ä���� ������ �ε���
	 *  @param honey : �ϲ��� ������� ä���� ��
	 *  @param benefit : �ϲ��� ���� ä���������� ����
	 * */
	private static void dfs(int[] each_bucket, int idx, int honey, int benefit) {
		if (tmp < benefit) { // ������ ����Ǿ��ִ� ���ͺ��� ���� ������ ũ�ٸ� �������ݴϴ�.
			tmp = benefit;
		}
		if (idx == M) { // �ϲ��� ������ ������ ä��������,
			return;
		}

		int r = list.get(each_bucket[idx]).r; // ���� ���� ������ r ��ǥ�� �����ɴϴ�.
		int c = list.get(each_bucket[idx]).c; // ���� ���� ������ c ��ǥ�� �����ɴϴ�.

		if (map[r][c] > C || honey + map[r][c] > C) { // �ϲ��� ���� �뷮�� ��������,
			return;
		}

		dfs(each_bucket, idx + 1, honey + map[r][c], benefit + (int) Math.pow(map[r][c], 2)); // ���� ä���� ���,
		dfs(each_bucket, idx + 1, honey, benefit); // ���� ä������ ���� ���,
	} // end of dfs()

	private static class Pair {
		int r;
		int c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	} // end of Pair
} // end of class
