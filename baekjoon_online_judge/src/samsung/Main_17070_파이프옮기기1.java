package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pipe {
	int tail;
	int head;
	int dir;

	Pipe(int tail, int head, int dir) {
		this.tail = tail;
		this.head = head;
		this.dir = dir;
	}
}

public class Main_17070_�������ű��1 {
	static int[] dr = { 1, 0, 1 }; // ��, ��, �밢��
	static int[] dc = { 0, 1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); // ���� ũ��
		int[][] map = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()); // �� ä���
			}
		}

		// ���� ��ġ 1,1 1,2 ����

		// �� �������� ��������.
		for (int i = 0; i < map.length; i++) {
			// ���� �� �ִ��� üũ�ؼ� �� �������� ������.
			
			
		}

	}
}
