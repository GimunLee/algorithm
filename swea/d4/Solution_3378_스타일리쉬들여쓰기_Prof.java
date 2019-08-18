package d4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N�� Ŀ���� �� ǰ
public class Solution_3378_��Ÿ�ϸ����鿩����_Prof {

	private static String[] prr;
	private static String[] qrr;
	private static int[] result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim()); // TestCase ��

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken()); // 1 <= p, q <= 10
			int q = Integer.parseInt(st.nextToken()); // 1 <= p, q <= 10

			prr = new String[p]; // ������ �ڵ�
			qrr = new String[q]; // ���� �ڵ�
			result = new int[q]; // ����� ��� ������ �迭

			Arrays.fill(result, -2); // �迭�� �ʱⰪ�� ������� �ʴ� ���� -2�� �ʱ�ȭ

			for (int i = 0; i < p; i++) {
				prr[i] = br.readLine(); // �� ���� �ִ� 80��
			}

			for (int i = 0; i < q; i++) {
				qrr[i] = br.readLine();
			}

			for (int r = 1; r <= 20; r++) {
				for (int c = 1; c <= 20; c++) {
					for (int s = 1; s <= 20; s++) {
						if (pOK(r, c, s)) { // �������� ��� �ڵ��࿡ ������ ������ üũ
							qCheck(r, c, s);// ������ ��� �ڵ� �࿡�� �������� üũ �� �迭�� ����
						}
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc);
			for (int i = 0; i < result.length; i++) {
				sb.append(' ').append(result[i]);
			}
			System.out.println(sb.toString());
		} // end of for testCase
	} // end of main

	/** ������ �ڵ��� ��� �࿡ ������ ������ üũ */
	private static boolean pOK(int r, int c, int s) {
		int rr = 0; // ()�� ����
		int cc = 0; // {}�� ����
		int ss = 0; // []�� ����

		for (int i = 0; i < prr.length; i++) {
			int dot = 0; // .�� ����
			String str = prr[i];
			int j =0;
			for (j = 0; j < str.length(); j++) {
				if(str.charAt(j) == '.') {
					dot++;
				}else {
					break; // �ٸ� ���ڰ� ������ ����
				}
			}
			
			if(r*rr + c*cc + s*ss != dot) return false;
			
			for (; j < str.length(); j++) { // . ���� ���ڵ� �������� �о��ֱ� 
				if	   (str.charAt(j) == '(') rr++;
				else if(str.charAt(j) == ')') rr--;
				else if(str.charAt(j) == '{') cc++;
				else if(str.charAt(j) == '}') cc--;
				else if(str.charAt(j) == '[') ss++;
				else if(str.charAt(j) == ']') ss--;
			}
		}
		return true;
	} // end of pOK()

	/** ������ ��� �ڵ� �࿡�� �������� üũ �� �迭�� ���� */
	private static void qCheck(int r, int c, int s) {
		int rr = 0;
		int cc = 0;
		int ss = 0;
		
		for (int i = 0; i < qrr.length; i++) {
			int dap = r*rr + c*cc + s*ss; // ����� ��
			
			if(result[i] == -2) { // ó�� �����ϴ� ��Ȳ
				result[i] = dap;
			}else if(result[i] >= 0 && result[i] != dap){ // �����߾��µ�, ���� �޶��� ��Ȳ
				result[i] = -1; // ���� �ϳ��� �ƴϴ�.
			}
			
			String str = qrr[i];
			for (int j=0; j < str.length(); j++) { // . ���� ���ڵ� �������� �о��ֱ� 
				if	   (str.charAt(j) == '(') rr++;
				else if(str.charAt(j) == ')') rr--;
				else if(str.charAt(j) == '{') cc++;
				else if(str.charAt(j) == '}') cc--;
				else if(str.charAt(j) == '[') ss++;
				else if(str.charAt(j) == ']') ss--;
			}
		}
	} // end of qCheck()
} // end of class

