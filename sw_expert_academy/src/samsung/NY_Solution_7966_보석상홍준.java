package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NY_Solution_7966_������ȫ�� {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // ������ ����

			Jewelry[] jewelryArray = new Jewelry[N];

			int[] price = new int[N + 1]; // ������ ����
			int[] beauty = new int[N + 1]; // ������ ���� ��ġ
			double[] beautyOfOnePrice = new double[N + 1];

			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

			int beautySum = 0;

			for (int i = 1; i <= N; i++) {
				price[i] = Integer.parseInt(st1.nextToken());
				beauty[i] = Integer.parseInt(st2.nextToken());
				beautySum += beauty[i];
				// ��ġ/�� : ���� ��� ��ġ (������ Ȯ�� �ʿ�)
				beautyOfOnePrice[i] = beauty[i] / (double) price[i];
				jewelryArray[i - 1] = new Jewelry(i, price[i], beauty[i], beautyOfOnePrice[i]);
			} // end of for(���������ޱ�)

			int X = Integer.parseInt(br.readLine().trim()); // �ʿ��� ������ġ
			int K = Integer.parseInt(br.readLine().trim()); // ������ �ִ� ������ ��

			boolean[] isGotten = new boolean[K + 1];
			if (K != 0) {
				StringTokenizer st3 = new StringTokenizer(br.readLine(), " ");
				for (int i = 1; i <= K; i++) {
					int idx = Integer.parseInt(st3.nextToken());
					isGotten[idx] = true;
				}
			}
			// -- end of input

			if (beautySum < X) { // �Ұ����� ���
				sb.append("#").append(tc).append(" ").append("-1").append("\n");
				continue;
			}

			Arrays.sort(jewelryArray); // ������ ���� �� | ���� ���� ��

			for (int i = 0; i < jewelryArray.length; i++) {
				System.out.println(jewelryArray[i].toString());
			}
			System.out.println();
			
			
			

			sb.append("#").append(tc).append(" ").append("��").append("\n");
		} // end of for(TestCase)
		System.out.println(sb.toString());
	} // end of main

	private static class Jewelry implements Comparable<Jewelry> {
		int idx; // ���� ��ȣ
		int price; // ���� ����
		int beauty; // ���� ���� ��ġ
		double beautyOfOnePrice; // ���� ������ (��ġ/����)

		public Jewelry(int idx, int price, int beauty, double beautyOfOnePrice) {
			this.idx = idx;
			this.price = price;
			this.beauty = beauty;
			this.beautyOfOnePrice = beautyOfOnePrice;
		}

		@Override
		public String toString() {
			return "Jewelry [idx=" + idx + ", price=" + price + ", beauty=" + beauty + ", beautyOfOnePrice="
					+ beautyOfOnePrice + "]";
		}

		// ������ ���ٸ� ������ ���� ������ ����
		@Override
		public int compareTo(Jewelry o) {
			double tmp = o.beautyOfOnePrice - this.beautyOfOnePrice;
			if (tmp == 0.0) {
				int tmp_price = this.price - o.price;
				return tmp_price;
			} else if (tmp > 0) {
				return 1;
			} else {
				return -1;
			}
		}
	} // end of Jewelry
} // end of class
