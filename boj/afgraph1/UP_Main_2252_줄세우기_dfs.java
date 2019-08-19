package boj.afgraph1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class UP_Main_2252_�ټ����_dfs {
	private static ArrayList<Integer>[] list; // ��� �� ������ִ���, �����ϴ� �迭 ����Ʈ ����
	private static boolean[] visited; // ���� �������� ���θ� �����ϴ� ���� 
	private static Stack<Integer> stack; // ���Լ���(FILO)�� ���� Stack ����

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // �л� ��
		int M = Integer.parseInt(st.nextToken()); // Ű ��Ƚ��

		list = new ArrayList[N + 1]; 
		visited = new boolean[N + 1];
		stack = new Stack<Integer>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if (list[A] == null) { // �ش� ����Ʈ �迭�� ������������ ���
				list[A] = new ArrayList<Integer>(); // ����Ʈ ����
			}
			list[A].add(B); // A�� Ű < B�� Ű
		} // end of for(input)

		for (int i = 1; i <= N; i++) {
			if (list[i] != null && !visited[i]) { // ������ ū ����� �ְ�, ���� ������ ���� ��� 
				dfs(i);
			}
		} // end of for(dfs)
		
		StringBuilder sb = new StringBuilder(); // �ð��� ȿ���� ���� StringBuilder�� �����ߴٰ� �ѹ��� ���
 		
		for (int i = 1; i <= N; i++) { // ���� ����� ���� �ο����� �� �����
			if (!visited[i]) { // ���� ������ ���� ���
				sb.append(i).append(" "); // �� �����
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(sb.toString()); // ���� ���
	} // end of main

	private static void dfs(int student) {
		visited[student] = true; // ���� ���������� ǥ��

		if (list[student] == null) { // �ڱ⺸�� ū ����� �����Ƿ� 
			stack.add(student); // �� �����
			return;
		}

		for (int i = 0; i < list[student].size(); i++) { // ������ ū ������� �湮
			int next = list[student].get(i); // ������ ū ���
			if (visited[next]) { // ������ ū ����� �� ������ ���
				continue;
			}
			dfs(next); // ������ ū ����� ���ȣ��
		}
		// ���� �ݺ����� ������, ������ ū ����� ���� ���
		stack.add(student); // ū ����� �����Ƿ� �� �����
	} // end of func(dfs)
} // end of class
