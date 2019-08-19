package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ������ ���� ¦����, �Ѿ� ¦�� ������ ¦�� �����ִ� ��� ����� ���� �����غ���, ������ ¦�� ����Ǵ� ���͸� ���ؼ�, ���͵��� ��ü
 * ���� ���ѵ�, ��� ��ü���� ũ�⸦ ���Ѵ�. �̷��� ���� ���� ��ü���� ũ���� �ּҰ��� ã�� ����
 * 
 * Tip : ���ͻ��� �����ġ�� �ִ� ������ ��ǥ�� �� - ���ͻ��� ������ġ�� �ִ� ������ ��ǥ�� �� = ���� ��ü�� �� �׷��Ƿ� �����ġ
 * ������ ������ ����Ǵ��� ���� ��ü�� ���� �����ϴ�. ������ġ ������ ������ ����Ǵ��� ���� ��ü�� ���� �����ϴ�. �ᱹ, ��� ��ġ��
 * �׷�, ���� ��ġ�� �׷����� �����ϴ� ����
 * 
 * 1�� ��� : ���� ���չ����� ������ �ϼ��Ǿ��� ��, ��ǥ���� ���� ���ؼ�, ����ũ���� �ּҰ��� ������Ʈ�Ѵ�. 196ms 2�� ��� :
 * ������ ���ȣ��� �������� ���ÿ��ο� ���� ��ǥ���� ���� �Ű������� �����Ѵ�. (ȿ����) 163ms
 */

public class Solution_1494_�����ī���_Prof {
	static int sumX = 0, sumY = 0; // ��ü X, Y�� ��
	static int[][] m; // �������� ��ġ
	static int[][] tr; // ������ �����̵��� ��ǥ
	static long min; // ��ü �������� ũ�� �� �ּҰ�, ��ǥ�� �����̶� int���� �Ѿ �� �ִ�.
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim()); // Ȥ�� �� �پȿ� ������ ������� ��� ���� �߻�, ���� ����

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // �������� ����, 2 <= N <= 20, N�� ¦��

			m = new int[N][2]; // 0 : x, 1 : y
			sumX = 0;
			sumY = 0;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken()); // -100,000 <= x, y <= 100,000
				int y = Integer.parseInt(st.nextToken()); // -100,000 <= x, y <= 100,000
				m[i][0] = x;
				m[i][1] = y;

				sumX += x;
				sumY += y;
			}
			
			tr = new int[N/2][2]; // ������ �����̵��� ��ǥ
			min = Long.MAX_VALUE; // ��ü �������� ũ��
			combi(N,N/2); // N�� �߿� N/2�� �̴� ����

			System.out.println("#" + tc + " " + min);
		} // end of for testCase

	} // end of main
	private static void combi(int n, int r) {
		if(n < r) {
			return;
		}
		else if(r==0) { // �ϳ��� ������ �ϼ���
			int sumSelectX = 0; // ������ �������� X��ǥ�� ��
			int sumSelectY = 0; // ������ �������� y��ǥ�� ��
			
			for (int i = 0; i < tr.length; i++) {
				sumSelectX += tr[i][0];
				sumSelectY += tr[i][1];
			}
			
			int sumUnselectX = sumX - sumSelectX;
			int sumUnselectY = sumY - sumSelectY;
			
			long vX = sumSelectX - sumUnselectX;
			long vY = sumSelectY - sumUnselectY;
			long v = vX *vX + vY * vY; // ���� ��ü ���� ũ��
			if(min > v) {
				min = v;
			}
		}else {
			tr[r-1][0] = m[n-1][0];
			tr[r-1][1] = m[n-1][1];
			combi(n-1,r-1);
			combi(n-1,r);
		}
		
	}
} // end of class
