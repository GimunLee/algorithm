import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2634_��ɲ�_BST {
	public static long close_m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
		int M = Integer.parseInt(st.nextToken()); // ����� ��
		int N = Integer.parseInt(st.nextToken()); // ������ ��
		long L = Long.parseLong(st.nextToken()); // �����Ÿ�

		st = new StringTokenizer(br.readLine().trim(), " ");
		long[] m = new long[M]; // ��� �迭

		for (int i = 0; i < M; i++) {
			m[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(m);
		int ans = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());

			close_m = 0;
			if (y <= L) { // y�� �����Ÿ� ���̰�,
				search(m, x, 0, M - 1); // ����� ��븦 ã�´�.
				if (Math.abs(x - close_m) + y <= L) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	public static void search(long[] m, long x, int start, int end) {
		if (Math.abs(start - end) == 1 || start == end) {
			long a = x - m[start];
			long b = m[end] - x;
			if (a > b) {
				close_m = m[end];
			} else {
				close_m = m[start];
			}
			return;
		}

		int mid = (start + end) / 2;

		if (x > m[mid]) {
			search(m, x, mid, end);
		} else if (x == m[mid]) {
			close_m = x;
			return;
		} else {
			search(m, x, start, mid);
		}
	}
}
