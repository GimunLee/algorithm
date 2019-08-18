package d3;

import java.util.Arrays;

/**
 * �����佺�׳׽��� ü 3131. 100�� ������ ��� �Ҽ�
 */
public class Solution_3131_100�������Ǹ��Ҽ� {
	public static void main(String[] args) {
// �����佺�׳׽��� ü
		StringBuilder sb = new StringBuilder(); // ���� �����忡���� �� �� �ְ�, ���� ������.
		boolean[] isHapSungSu = new boolean[1000001];
		for (int i = 2; i < isHapSungSu.length; i++) {
			if (!isHapSungSu[i]) { // ó�� �������� false
				sb.append(i).append(' '); // append(i+ " ") ���ڿ��� ������ �ٿ���.
				// i�� ������� �Ҽ� �ƴϹǷ� true�� ����
				for (int j = i + i; j < isHapSungSu.length; j += i) { // ������ �ٿ���.
					isHapSungSu[j] = true;
				}
			}

		}
		System.out.print(sb);

// �����佺�׳׽��� ü Time Out
//		int N = 1000;
//		boolean[] result = new boolean[1000000];
//		int[] answer = new int[1000000];
//		for (int i = 2; i < N; i++) {
//			boolean chk = false;
//			for (int j = 2; j < i; j++) {
//				if(i % j == 0) {
//					chk = true;
//					break;
//				}
//			}
//			if(!chk) {
//				for (int j = 2; j < N; j++) {
//					if((i * j) < N) {
//						result[i * j] = true;	
//					}
//				}
//			}
//		}
//		int index = 0;
//		for (int i = 2; i < 1000000; i++) {
//			if(!result[i]) {
//				answer[index++] = i;
//			}
//		}
//		System.out.println(Arrays.toString(answer));

// Time Out		
//		int N = 1000;
//		int[] Answer = new int[N];
//		int index = 0;
//		// 2 3 5 7 11 13 17 19 23
//		for (int i = 2; i < N; i++) {
//			boolean chk = false;
//			for (int j = 2; j < i; j++) {
//				if(i % j == 0) {
//					chk = true;
//					break;
//				}
//			}
//			if(!chk) {
//				Answer[index++] = i;	
//			}
//		}
//		for (int i = 0; i < index; i++) {
//			System.out.print(Answer[i] + " ");	
//		}
	}
}
