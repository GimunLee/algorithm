package swea.d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 1 2 3 4 5 �ɰ���, �� �ɰ��� (�ɰ� ����Ʈ 4���� ��´�.)
 * 
 * ���̳ʸ� ī�������� ���ڸ� �ɰ� ����Ʈ�� ����� ���� ��������.
 *  �Ź� ���ȣ���ϸ� �ߺ��� ���� �߻��Ѵ�. 700ms
 *  �����ؼ� ���(�޸������̼�)              10ms
 */
public class Solution_7206_���ڰ��� {
	public static HashMap<Integer, Integer> hm;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		long time = System.currentTimeMillis(); // �ð� ���
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			hm = new HashMap<>();
			System.out.println("#" + tc + " " + f(N));
		}

		System.out.println((System.currentTimeMillis() - time) + "ms"); // �ð� ���

	} // end of main

	/** ���� ���� ���, ���ڸ� �ɰ��� ��� ��츦 ���ؼ�, �� �� �ִ밪 ã�Ƽ� ���� */
	private static int f(int N) {
		if (N < 10) { // �ɰ� �� ���� ���
			return 0;
		}
//		(int)Math.log10(N); ���ڸ����� ���ϱ�
		String s = "" + N;
		int len = s.length() - 1; // �ִ� �ɰ� �� �ִ� ������ ����
		int maxCnt = 0;
		for (int i = 1; i < 1 << len; i++) { // �ɰ� �� �ִ� ��� ��츦 ������, ���̳ʸ� ī������ �̿�
//			System.out.printf("%4s : ",Integer.toBinaryString(i));
			int mul = 1; // �������� ������ ����, ���������ڿ� ���� �׵�� 1�� �ʱ�ȭ
			int num = s.charAt(0) - '0'; // 0��° ���ڸ� ���ڷ� ����
			for (int j = 0; j < len; j++) { // ��Ʈ����ŷ, �ش� ��Ʈ�� 1�̸� �ɰ�, 0�̸� �̾����
				if ((i & 1 << j) == 0) { // ���ɰ�
					num = num * 10 + s.charAt(j + 1) - '0';
				} else { // �ɰ�
					mul *= num;
//					System.out.print(num + ",");
					num = s.charAt(j + 1) - '0'; // ���ο� ���ڸ� ����
				}
			}
			mul *= num;
//			System.out.println(num + " : " + mul);

//			int cnt = f(mul); // ���� �ߺ� �߻�
			int cnt;
			
			if(hm.containsKey(mul)) { // �̹� ȣ���� ����� ������, ��Ȱ��
				cnt = hm.get(mul);
			} else {
				cnt = f(mul);
				hm.put(mul, cnt);
			}

			if (maxCnt < cnt) {
				maxCnt = cnt;
			}
		}
		return maxCnt + 1;
	}
} // end of class
