package programmers.level3;

/** �ּ� ���� Ʈ�� (ũ�罺Į) */
public class Solution_UP_�������ϱ� {
	public static void main(String[] args) {
		int n = 4;
		int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
		System.out.println(solution(n, costs));
	}

	private static int[] p, rank; // p : �� ����� �θ� �迭, rank : �� ����� rank�� ����
	private static Edge[] edgeArray; // �� ���ἱ�� ������ (ũ�罺Į)

	public static int solution(int n, int[][] costs) {
		int answer = 0; // ���� ����
		makeSet(n); // �� p�� rank�� �ʱ�ȭ

		// costs = {start, end, cost}
		edgeArray = new Edge[costs.length]; // �������� ������ �迭 ����

		for (int r = 0; r < costs.length; r++) {
			int start = costs[r][0];
			int end = costs[r][1];
			int cost = costs[r][2];
			edgeArray[r] = new Edge(start, end, cost);
		} // end of for(connection)

		quickSort(0, edgeArray.length - 1); // ����Ʈ �Լ� ����
		// Arrays.sort(edgeArray); // ���̺귯�� ���

		int count = n - 1; // �����ؾߵ� ������ ����
		for (int i = 0; i < costs.length; i++) {
			Edge edge = edgeArray[i]; // ���� cost�� ���� ������ ����

			int p1 = find(edge.start); // �� ������ ����� ù ����� �θ� ã��
			int p2 = find(edge.end); // �� ������ ����� �ι�° ����� �θ� ã��

			if (p1 == p2) { // �θ� ���ٸ�, ���� �̹� ���� �����̴�. -> �������� ����������� �ǹ���
				continue;
			} else if (p1 != p2) { // �θ� �ٸ��ٸ�,
				union(edge.start, edge.end); // ���� ���� �������� ����� -> �� �����ϱ�
				count--; // ���� ���������Ƿ� count ����
				answer += edge.cost; // ����� answer�� ����
				if (count == 0) { // ��� ���� ����ƴٸ�, Ž�� ����
					break;
				}
			}
		}
		return answer; // ���� ��ȯ
	} // end of func(solution)

	/** �� �θ� �迭�� rank �迭�� ������ */
	private static void makeSet(int n) {
		p = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = i; // ���ʿ��� �ڽ��� �θ�� �ڽ����� ����
		}
		return;
	} // end of func(makeSet)

	// node�� �θ� ã��
	private static int find(int node) {
		if (p[node] == node) {
			return p[node];
		} else {
			return p[node] = find(p[node]); // Path Compression
		}
	} // end of func(find)

	// �� node�� �� �������� ��ġ��
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
		return;
	} // end of func(union)

	/** ���� ���̺귯���� ����� �� ���� ���� Comparable ���� */
	private static class Edge implements Comparable<Edge> {
		int start, end, cost;

		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	} // end of class(Edge)

	// ������ ����
	private static void quickSort(int start, int end) {
		if (start >= end) {
			return;
		}
		int i = start - 1;
		int j = end + 1;
		Edge pivot = edgeArray[(start + end) / 2];
		while (true) {
			while (edgeArray[++i].cost < pivot.cost) {
			}
			while (edgeArray[--j].cost > pivot.cost) {
			}
			if (i >= j) {
				break;
			}
			// swap
			Edge tmp = edgeArray[i];
			edgeArray[i] = edgeArray[j];
			edgeArray[j] = tmp;
		}
		quickSort(start, i - 1);
		quickSort(j + 1, end);
		return;
	} // end of func(quickSort)
} // end of class
