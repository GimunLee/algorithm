package boj.samsung;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UP_Main_17135_ĳ�����潺 {
	private static int N; // ������ �� ����
	private static int M; // ������ �� ����
	private static int D; // �ü��� ���� �Ÿ� ����
	private static int[][] map; // �������� ������ ����
	private static ArrayList<Enermy> list; // ������ ������ ������ ����
	private static int enermy_cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // ���� ��
		M = Integer.parseInt(st.nextToken()); // ���� ��
		D = Integer.parseInt(st.nextToken()); // �ü��� ���� �Ÿ� ����

		map = new int[N][M]; // ������ ����
		list = new ArrayList<>();
		enermy_cnt = 0; // ������ �� �ο��� ������ ����

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) { // ������ ���
					list.add(new Enermy(r, c)); // list�� ������ ������ ����
					enermy_cnt++; // ���� �� ����
				}
			}
		} // end of for(input)
		setDiffenser(0, 0); // �ü� ��ġ
		System.out.println(ans); // ���� ���
	} // end of main

	private static int[] set = new int[3]; // �ü��� �� ���� ������ ���� (���� N+1�� ����)

	/** DFS�� �ü��� �� ���� �������� ���� */
	private static void setDiffenser(int idx, int len) {
		if (len == 3) { // �ü� 3���� �ڸ��� ���� ���
			playGame(set); // ������ ������
			return;
		}

		for (int i = idx; i < M; i++) { // ���� ���� �ε������� for�� ����
			set[len] = i;
			setDiffenser(i + 1, len + 1);
		}
	} // end of setDiffenser()

	private static int ans = Integer.MIN_VALUE; // ������ ������ ����

	// (N+1,set)
	private static void playGame(int[] set) {
		int temp_enermy_cnt = enermy_cnt; // �� ���մ� ���̰� ���� ������ ���� ������ �ӽ� ����
		int temp_ans = 0; // �� ���մ� ���� ������ ���� ������ ����
		ArrayList<Enermy> tlist = new ArrayList<>(); // �� ���մ� ������ ������ ������ �ӽ� list ����

		for (int i = 0; i < list.size(); i++) {
			tlist.add(new Enermy(list.get(i))); // �ʱ� ����Ʈ���� �ӽ� ����Ʈ ������ ����
		}

		while (temp_enermy_cnt > 0) { // ���� ������ ���� ������ �ݺ�
			for (int i = 0; i < set.length; i++) { // �ü����� ����
				for (int j = 0; j < tlist.size(); j++) { // �� ������ ������ ���� for��
					Enermy e = tlist.get(j);
					if (!e.isDie) { // ���� ���� ������ ���
						e.dis[i] = Math.abs(e.r - N) + Math.abs(e.c - set[i]); // �ش� �ü��� �� ������ �Ÿ��� ���
					}
					e.turn = i; // �ش� �ü��� turn���� ����
				} // end of for(Calculate Distance)

				Collections.sort(tlist); // �Ÿ� ������ ����

				here: for (int j = 0; j < tlist.size(); j++) {
					Enermy e = tlist.get(j);
					if (!e.isDie) { // ���� ���� ���� �ʾ�����
						if (e.dis[i] <= D) { // �ü��� �����Ÿ� ���̸�
							e.shooted++;
							break here;
						}
					}
				} // end of for(Kill Enermy)
			} // end of for(Calculate and Kill)

			// ȭ�쿡 �¾Ҵ��� �Ǻ�
			for (int j = 0; j < tlist.size(); j++) {
				Enermy e = tlist.get(j);
				if (!e.isDie) { // ���� ���� ���
					if (e.shooted > 0) { // ȭ�쿡 �¾��� ��
						e.isDie = true; // �ش� enermy�� ����
						temp_ans++; // ���� ������ �� ����
						temp_enermy_cnt--; // ���� ������ �� ����
					}
				}
				e.r++; // ������ �� ĭ ������ ����
				if (!e.isDie) {
					if (e.r >= N) { // N ������ ��� ���
						e.isDie = true; // ���� ������ ó��
						temp_enermy_cnt--; // ���� ���� �� ����
					}
				}
			}
		} // end of while(one time)
		ans = (ans < temp_ans) ? temp_ans : ans; // ���� ����
		return;
	} // end of playGame()

	/** Comparable�� �ü��� ������ �Ÿ����� ���� */
	private static class Enermy implements Comparable<Enermy> {
		int r; // ������ �� ��ǥ
		int c; // ������ �� ��ǥ
		int turn; // ���� ���� ��, � �ü��� ����� �Ǻ��� ���� (�ü��� ������ �Ÿ��� ������ �� ����)
		int[] dis; // �� index�� �� �ü�, ���� �ü��� ������ �Ÿ�
		int shooted; // �� ������ ȭ�쿡 �¾Ҵ��� ������ ����
		boolean isDie; // ������ �׾����� �Ǻ��� ����

		public Enermy(int r, int c) {
			this.r = r;
			this.c = c;
			dis = new int[3]; // �ü��� 3������ �����̹Ƿ� ����
		}

		public Enermy(Enermy e) {
			this.r = e.r;
			this.c = e.c;
			this.turn = e.turn;
			this.dis = e.dis;
			this.shooted = e.shooted;
			this.isDie = e.isDie;
		}

		@Override
		public int compareTo(Enermy o) {
			int temp = this.dis[this.turn] - o.dis[this.turn];
			if (temp == 0) { // �Ÿ��� ������
				// column �켱 ����
				temp = this.c - o.c;
			}
			return temp;
		}
	} // end of Enermy
} // end of class
