package boj.newmind;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_6603_�ζ� {
	private static int[] S; // ���� �迭
	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken()); // ������ ���� ������ ����

			if (k == 0) { // k���� 0�� ������ ����
				return;
			}

			S = new int[k]; // ���� �迭
			int idx = 0;

			while (st.hasMoreTokens()) {
				S[idx++] = Integer.parseInt(st.nextToken());
			}

			sb = new StringBuilder();
			comb(0, 0);

		}

	} // end of main()

	private static void comb(int idx, int cnt) {
		if(idx == S.length-1) {
			return;
		}
		
		sb.append(S[idx]).append(' ');
		
		if (cnt == 5) {
			System.out.println(sb.toString());
			sb.deleteCharAt(sb.length()-1);
			System.out.println();
			return;
		}

		comb(idx+1, cnt+1);
		comb(idx+1, cnt);
		
	} // end of comb()
} // end of class
