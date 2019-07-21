package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * �޸������̼� ����
 * */

public class Main_1005_ACMCraft {
	private static int[] D;
	private static ArrayList<Integer>[] list;
	private static int[] indegree;
	private static int W;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= TC; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // �ǹ� ����
			int K = Integer.parseInt(st.nextToken()); // �ǹ� ���� ��Ģ

			D = new int[N + 1]; // �ǹ� ���� �ð�
			D[0] = Integer.MAX_VALUE; // ��� ����
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= N; i++) {
				D[i] = Integer.parseInt(st.nextToken()); // �ǹ� ���� �ð�
			}

			list = new ArrayList[N + 1];
			indegree = new int[N + 1]; // ������ ����

			// �ǹ� ���� ���� ��Ģ
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				if (list[s] == null) {
					list[s] = new ArrayList<Integer>();
				}
				list[s].add(e);
				indegree[e]++;
			}

			W = Integer.parseInt(br.readLine().trim()); // �ǹ� ��ȣ
			// -- end of input
			int time = 0;
			Queue<Integer> queue = new LinkedList<>();

			int tmp_time = Integer.MIN_VALUE;
			here: while (true) {
				for (int i = 1; i <= N; i++) {
					if (indegree[i] == 0) { // 0�̸� ����
						if (i == W) { // ������ ���
							sb.append(time).append("\n");
							break here;
						}

						if (list[i] == null) {
							D[i] = 0;
							continue;
						}
						
						// �ð� ���ϱ� ���� � ���� �ؾ���
						if(D[i] > tmp_time) {
							time += D[i]; // �ǹ� ����
						}

						for (int j = 0; j < list[i].size(); j++) {
							int next = list[i].get(j); // ���� ������ϴ� �ǹ�
							indegree[next] -= 1; // ������ ���� ���̱�

							// ���� ������ϴ� �ǹ��� ��, �ִ� �ð� (���� �Ǽ� ����)
							if (tmp_time < D[next]) {
								tmp_time = D[next];
							}
						}
					}
				}
			}
			System.out.println(sb.toString());
		} // end of for(TestCase)
	} // end of main()
} // end of class
