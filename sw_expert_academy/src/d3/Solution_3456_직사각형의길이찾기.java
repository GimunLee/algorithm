package d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ���簢�� ���� ã��
 */
public class Solution_3456_���簢���Ǳ���ã�� {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine().trim()); // TestCase

		for (int i = 1; i <= tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int result;
//			����
//			if( a == b) result = c;
//			else if(a == c) result = b;
//			else result = a;

//			���� �̿�
//			int data = 3;
//			int key = 5;
//			data = data ^ key;
//			data = data ^ key;
			result = a ^ b ^ c;

			System.out.println("#" + i + " " + result);
		}
	}
}
