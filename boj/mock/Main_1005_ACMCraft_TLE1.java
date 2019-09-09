package boj.mock;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * �ð� �ʰ�
 */

public class Main_1005_ACMCraft_TLE1 {
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
			boolean[] visited = new boolean[N + 1];

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
			int[] tmp_D = new int[N + 1];

			here: while (true) {
				for (int i = 1; i <= N; i++) {
					if (indegree[i] == 0) { // 0�̸� ����
						if (i == W) { // ������ ���
							break here;
						}

						if (list[i] == null) { // ������ �ƴϰ� �������� ������ϴ� �ǹ��� ���� ���,
							continue;
						}

						for (int j = 0; j < list[i].size(); j++) {
							int next = list[i].get(j); // ���� ������ϴ� �ǹ�
							indegree[next] -= 1; // ������ ���� ���̱�
							// tmp_D : ������ ���� �� ���� ū ��

							if(visited[next]) {
								continue;
							}
							
							tmp_D[next] = tmp_D[next] < D[i] ? D[i] : tmp_D[next];
							if (indegree[next] == 0) { // ������ ������ ���� ó���� ��������,
								D[next] = tmp_D[next] + D[next];
								visited[next] = true;
								if (next == W) { // �ش� �ǹ��� ������ ���
									break here;
								}
							}
						}
					}
				}
			}
			sb.append(D[W]).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main()
} // end of class
