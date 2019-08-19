package boj.afgraph1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UP_Main_2252_�ټ����_queue {
	private static ArrayList<Integer>[] list; // ��� �� ������ִ���, �����ϴ� �迭 ����Ʈ ����
	private static boolean[] visited; // ���� �������� ���θ� �����ϴ� ���� 
	private static int[] indegree; // �ش� ������� Ű�� ���� ����� �� (������ ����)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // �л� ��
		int M = Integer.parseInt(st.nextToken()); // Ű ��Ƚ��

		list = new ArrayList[N + 1];  
		visited = new boolean[N + 1]; 
		indegree = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if (list[A] == null) { // �ش� ����Ʈ �迭�� ������������ ���
				list[A] = new ArrayList<Integer>(); // ����Ʈ ����
			}
			list[A].add(B); // A�� Ű < B�� Ű
			indegree[B]++; // B���� ������ ���� ����
		} // end of for(input)

		StringBuilder sb = new StringBuilder(); // �ð��� ȿ���� ���� StringBuilder�� �����ߴٰ� �ѹ��� ���
		int cnt = N; // ���� ������ ���� ���� ��� �� 

		while (cnt != 0) { // ���� ������ ���� ����� ������ �ݺ�
			for (int i = 1; i <= N; i++) { // ù��° ������� Ž���ϸ鼭
				if (indegree[i] == 0 && !visited[i]) { // ������ ���� ����� ����, ���� ������ �ʾҴٸ�
					visited[i] = true; // ���� �������� ǥ��
					sb.append(i).append(" "); // �� �����
					cnt--; // ���� �������Ƿ�, ���� ��� ���� ����

					if (list[i] == null) { // ����� ������ ���� ���
						continue;
					} else { // ����� ������ �ִ� ���
						for (int j = 0; j < list[i].size(); j++) {
							indegree[list[i].get(j)]--; // �ش� �ο��� ���� �������Ƿ�, ����� ������� ������ ������ �ٿ���
						}
					}
				}
			} 
		} // end of while(solve)
		System.out.println(sb.toString()); // ���� ���
	} // end of main
} // end of class
