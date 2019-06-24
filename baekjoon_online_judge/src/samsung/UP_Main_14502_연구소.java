package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UP_Main_14502_������ {
	static int[][] move = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // ���̷��� ������ ����(�����¿�)
	static int[][] map; // ������ map
	static int[][] map_temp; // �׽�Ʈ�� map�� �ӽ� ����

	static boolean[][] visited; // ���̷����� ��Ʈ�ȴ��� �˱� ���� ����
	static int[][] q = new int[1000][2]; // queue ���� / [0] : row, [1] : column
	static int front = -1; // queue���� ����� front ����
	static int rear = -1; // queue���� ����� rear ����

	static int ans = 0; // ���� ����

	static int size_virus;
	static int[] r_combi;
	static int[] c_combi;

	static int N; // map�� �� ũ��
	static int M; // map�� �� ũ��

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // map�� �� ũ��
		M = Integer.parseInt(st.nextToken()); // map�� �� ũ��
		map = new int[N][M]; // map ����

		// ��ĭ�� ���� �迭 (���� ����� ���� ����)
		r_combi = new int[N * M]; // ����ִ� ���� ������ ����
		c_combi = new int[N * M]; // ����ִ� ���� ������ ����
		int size_combi = 0; // nCr���� n�� �ش��ϴ� ���� (���� ���� �� �ִ� �� ����)

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) { // ���̷������
					// queue�� �����ϰ� �۵�
					++rear;
					q[rear][0] = r;
					q[rear][1] = c;
					size_virus++; // ���̷��� ���� ����
				} else if (map[r][c] == 0) { // ��ĭ�̶��
					r_combi[size_combi] = r;
					c_combi[size_combi] = c;
					size_combi++;
				}
			}
		} // end of for(input)
		setWall(size_combi, 0, 0); // �� ����� + Ȯ��
		System.out.println(ans); // �� ���
	} // end of main

	/** r�� c�� �޾� map�� ũ�⸦ ����� �ʴ��� Ȯ���մϴ�. */
	public static boolean inRange(int r, int c) {
		if (r < 0 || c < 0 || r > (map.length - 1) || c > (map[0].length - 1)) {
			return false;
		}
		return true;
	} // end of func(inRange)

	static ArrayList<Integer> rList = new ArrayList<Integer>(); // ���� ���� �� ���� ������ ����
	static ArrayList<Integer> cList = new ArrayList<Integer>(); // ���� ���� �� ���� ������ ����

	/**
	 * ���� ���� �� �ִ� ����(size_combi)���� ���� ����(cnt)�� �̾Ƽ� ���� �����. t : combination���� index
	 * ����
	 */
	static void setWall(int size_combi, int cnt, int t) {
		if (cnt == 3) { // 3���� �̾Ҵٸ�
			map_temp = new int[N][M];
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[0].length; c++) {
					map_temp[r][c] = map[r][c];
				}
			} // end of for(copy map)

			for (int i = 0; i < rList.size(); i++) {
				map_temp[rList.get(i)][cList.get(i)] = 1;
			} // �������

			visited = new boolean[N][M]; // ���̷����� ���� ������ �˱� ���� ���� ����
			front = -1;
			rear = size_virus - 1;

			// ���̷��� ��Ʈ����
			for (int i = 0; i < size_virus; i++) {
				int r_v = q[i][0]; // virus�� �� ��ǥ
				int c_v = q[i][1]; // virus�� �� ��ǥ

				if (!visited[r_v][c_v]) { // ���̷����� ������ ���� ���̶��
					spreadVirus(r_v, c_v); // ���̷��� ��Ʈ����
				}
			} // end of for(spread virus)

			int temp = 0; // ���������� ���̸� ������ �ӽ� ����

			for (int i = 0; i < map_temp.length; i++) {
				for (int j = 0; j < map_temp[0].length; j++) {
					if (map_temp[i][j] == 0) {
						temp++;
					}
				}
			} // end of for(count safety)

			ans = Math.max(temp, ans); // �� ����
			return;
		}

		// ���� ���� �� ���ϱ� (���� ���)
		for (int i = t; i < size_combi; i++) {
			rList.add(r_combi[i]); // ���� �� ��ǥ
			cList.add(c_combi[i]); // ���� �� ��ǥ
			setWall(size_combi, cnt + 1, i + 1); // 3���� ���� ������ ��� ȣ��
			// �ش� ��ǥ���� ����߱� ������ �ٽ� ����
			rList.remove(rList.size() - 1);
			cList.remove(cList.size() - 1);
		}
	} // end of func(setWall)

	/** ���̷��� ��Ʈ���� (BFS) */
	private static void spreadVirus(int r, int c) {
		visited[r][c] = true;

		while (front != rear) { // queue�� �� ������
			++front;
			r = q[front][0]; // �� ��ġ
			c = q[front][1];

			for (int i = 0; i < 4; i++) { // ����
				int r_n = r + move[i][0];
				int c_n = c + move[i][1];

				if (inRange(r_n, c_n)) {
					if (!visited[r_n][c_n] && map_temp[r_n][c_n] == 0) { // ��ĭ�̸�,
						visited[r_n][c_n] = true;
						map_temp[r_n][c_n] = 2;
						++rear;
						q[rear][0] = r_n;
						q[rear][1] = c_n;
					}
				}
			}
		}
	} // end of func(spreadVirus)
} // end of class
