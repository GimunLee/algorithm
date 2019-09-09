package boj.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1005_ACMCraft {
	private static int[] D; // �� �ǹ��� �Ǽ� �ð��� ������ �迭
	private static ArrayList<Integer>[] list; // ���� ��� ����Ʈ ( list[1]�� ����� ����Ʈ�� '1'�� ���� ���� �� �ִ� �ǹ��� �����ϰ� ����) 
	private static int[] indegree; // �� �ǹ��� ������ ������ ������ ������ �迭
	private static int W; // �Ǽ��ؾ��ϴ� �ǹ��� ��ȣ

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder(); // ������ �ѹ��� ����ϱ� ���� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine().trim()); // Test Case ��

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // �ǹ� ����
			int K = Integer.parseInt(st.nextToken()); // �ǹ� ���� ��Ģ

			D = new int[N + 1]; // �ش� �ǹ��� ���µ� �ɸ��� �ð��� ������ ���� ����
			D[0] = Integer.MAX_VALUE; // 0�� ������� ����
			
			st = new StringTokenizer(br.readLine(), " "); 
			for (int i = 1; i <= N; i++) {
				D[i] = Integer.parseInt(st.nextToken()); 
			}

			list = new ArrayList[N + 1]; // ���� ��� ����Ʈ ����
			indegree = new int[N + 1]; // ������ ������ ���� ������ ���� ����

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken()); 
				int e = Integer.parseInt(st.nextToken()); // s�� ����� e�� ���� �� ����
				if (list[s] == null) {
					list[s] = new ArrayList<Integer>();
				}
				list[s].add(e); // s�� ���� ���� ������ϴ� e�� ����Ʈ�� ����
				indegree[e]++; // e�� ������ ���� ����������
			}
			W = Integer.parseInt(br.readLine().trim()); // ���������� ������ϴ� �ǹ� ��ȣ
			// -- end of input

			Queue<Integer> queue = new LinkedList<>(); // BFS�� �ǹ��� Ž���ϱ� ���� Queue

			for (int i = 0; i < indegree.length; i++) {
				if (indegree[i] == 0) { // ������ ������ ���� ���� ������
					queue.add(i); 
				}
			}

			int[] tmp_D = new int[N + 1]; // ������ ���� �� ���� ū ���� �ӽ÷� ������ ����

			while (!queue.isEmpty()) { // ������ ���� �ǹ����� ���� ����
				int cur = queue.poll(); // ���� Ž���� �ǹ��� ��ȣ

				if (list[cur] == null) { // �ش� �ǹ� �������� ������ϴ� �ǹ��� ���� ���,
					continue;
				}

				for (int j = 0; j < list[cur].size(); j++) {
					int next = list[cur].get(j); // ���� ������ϴ� �ǹ�

					 // �ش� �ǹ��� �������Ƿ�, �������� ������ϴ� �ǹ��� ������ ������ �ٿ���
					if (--indegree[next] == 0) { // �ٿ�����, 0�̶�� ���� �� �ִ� �ǹ��̹Ƿ� Queue�� ����
						queue.add(next); 
					}

					// ���� �ǹ��� ���� ���� �ɸ��� �ִ� �ð��� ����
					tmp_D[next] = tmp_D[next] < D[cur] ? D[cur] : tmp_D[next]; 

					if (indegree[next] == 0) { // ���� �ǹ��� ���� �� �ִ� ���¶��, (������ ������ ���ٸ�)
						// ���� �ǹ��� ���µ� �ɸ��� �ð��� ���� �ǹ��� ���� ���� ����� �ߴ� �ǹ����� �� �ð��� ���ؼ� ����
						D[next] = tmp_D[next] + D[next]; // ���� D[next]���� next�� �ǹ� ���⸦ �Ϸ��� �������� �ð��� �����
					}
				}
			} // end of while(Queue)
			sb.append(D[W]).append("\n"); // D[W]���� W �ǹ��� ���� ���� �ð��� ����������Ƿ�, �迭�� �����Ͽ� ���
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main()
} // end of class
