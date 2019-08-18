package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * �޸������̼� ����
 */

public class Main_1005_ACMCraft_loop {
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

			Queue<Integer> queue = new LinkedList<>();

			for (int i = 0; i < indegree.length; i++) {
				if (indegree[i] == 0) {
					queue.add(i);
				}
			}

			int[] tmp_D = new int[N + 1];

			while (!queue.isEmpty()) {
				int cur = queue.poll();

				if (list[cur] == null) { // ������ �ƴϰ� �������� ������ϴ� �ǹ��� ���� ���,
					continue;
				}

				for (int j = 0; j < list[cur].size(); j++) {
					int next = list[cur].get(j); // ���� ������ϴ� �ǹ�

					if (--indegree[next] == 0) {
						queue.add(next);
					}

					// tmp_D : ������ ���� �� ���� ū ��

					tmp_D[next] = tmp_D[next] < D[cur] ? D[cur] : tmp_D[next];
					
					if (indegree[next] == 0) { // ������ ������ ���� ó���� ��������,
						D[next] = tmp_D[next] + D[next];
					}
				}
			}
			sb.append(D[W]).append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main()
} // end of class
