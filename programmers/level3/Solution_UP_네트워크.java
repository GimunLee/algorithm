package programmers.level3;

/**
 * 2019-10-05 blog upload
 * */
public class Solution_UP_��Ʈ��ũ {
	public static void main(String[] args) {
		int n = 3;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		int answer = solution(n, computers);
		System.out.println(answer);
	} // end of main

	static int[] p, rank; // p : ����� �θ� �ε���, rank : ���� �������� ���鶧, �� ����� ��ũ �� ū ������ ���� -> Ž�� �ð��� �ٿ���

	public static int solution(int n, int[][] computers) {
		int answer = n; // ���ʿ��� n����ŭ ��Ʈ��ũ�� ����
		make(n); // p�� rank �迭 ����

		// �� ����� ���� ������ ���� Ž���Ѵ�.
		for (int i = 0; i < computers.length; i++) {
			for (int j = i + 1; j < computers[i].length; j++) { // �밢������ ��Ī�̹Ƿ� i+1���� Ž���ص���
				if (computers[i][j] == 0) { // ���� ��������� �ʴٸ�,
					continue; // ��Ž��
				}

				// ���� ������ִٸ�

				int p1 = find(i); // i�� �θ� ã��
				int p2 = find(j); // j�� �θ� ã��

				if (p1 != p2) { // i�� j�� �θ� �ٸ��ٸ�, �� ��ǻ�͸� ���� �������� �������
					union(i, j); // ���� �������� ��ġ��
					answer--; // ���� ��Ʈ��ũ�� �����ϹǷ� answer�� ���ҽ�����
				}
			}
		}
		return answer; // ���� ��ȯ
	} // end of func(solution)

	private static void make(int n) {
		p = new int[n]; // ��ǻ�� ����ŭ �θ� �迭 ����
		rank = new int[n]; // ��ǻ�� ����ŭ rank �迭 ����
		for (int i = 0; i < p.length; i++) {
			p[i] = i; // ���ʿ��� �ڱ� �ڽ��� �θ�
		}
		return;
	} // end of func(make)

	private static int find(int node) {
		if (node == p[node]) { // ���� ����� �θ� �ڱ� �ڽ��̶��
			return p[node]; // �θ� ��ȯ
		} else { // ���� ����� �θ� ��尡 �ڽ��� �ƴ϶��, ��� ȣ��
			return p[node] = find(p[node]); // Path Compression : Ž�� �ð��� �ٿ���
		}
	} // end of func(find)

	private static void union(int node1, int node2) {
		int p1 = find(node1); // node1�� �θ� ã��
		int p2 = find(node2); // node2�� �θ� ã��
		
		if (p1 == p2) { // �θ� ���ٸ�,
			return;
		}
		if (rank[p1] < rank[p2]) { // node2 ������ rank�� �� ���ٸ�
			p[p1] = p2; // node1�� node2 �������� ����
		} else { // rank�� ���ų� �۴ٸ�, 
			if (rank[p1] == rank[p2]) { // �� ���� rank�� �������� rank�� �÷���
				rank[p1]++; 
			}
			p[p2] = p1; // node2�� node1�� �������� ����
		}
	} // end of func(union)
} // end of class
