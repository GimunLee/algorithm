package boj.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11650_��ǥ�����ϱ� {
	private static int listSize = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim()); // 1 <= N <= 100,000

		ListNode head = null;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			head = ListNode.appendListNode(head, new Pair(x, y));
			listSize++;
		}

		ListNode node = head;
		do {
			if (head == null) {
				break;
			}
			System.out.print(node.data + " ");
			node = node.next;
		} while (node != head);

	} // end of main

	private static class ListNode {
		Pair data;
		ListNode prev;
		ListNode next;

		public ListNode(Pair data) {
			this.data = data;
			prev = this;
			next = this;
		}

		public static ListNode appendListNode(ListNode head, Pair data) {
			ListNode node = new ListNode(data);
			if (head == null) { // List�� ù ����� ��,
				head = node;
			} else {
				ListNode last = head.prev; // ������ ��尡 head�� ���� ����Ű����, ��¼�� head�� prev�� ���� ������ ����̹Ƿ�
				last.next = node; // �� ������ ����� next�� ���� ���� ��带 ����
				head.prev = node; // �ٽ� ������ ��带 ���� ���� ���� ��ü��
				node.prev = last; // ���� ����� �տ��� �� ��尡 �������� ���� ������ ��带 ����Ű����
				node.next = head; // ���� ����� �������� head�� ����Ű���� ��
			}
			return head;
		} // end of func(appendListNode)

		public static ListNode removeList(ListNode head, ListNode node) {
			if (head == head.next) { // ����Ʈ�� ��尡 �ϳ��ۿ� ���� ��
				return null;
			} else { // �������� ���� ��,
				ListNode prevNode = node.prev; // �����ϰ����ϴ� ����� ���� ����
				ListNode nextNode = node.next; // �����ϰ��� �ϴ� ����� ������ ����
				prevNode.next = nextNode;
				nextNode.prev = prevNode;
				return (head == node) ? nextNode : head; // ������ ���� ���� ù��° �����
			}
		} // end of func(ListNode)
	} // end of ListNode

	private static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	} // end of Pair
} // end of class
