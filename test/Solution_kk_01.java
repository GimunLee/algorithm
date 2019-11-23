package etc;

import java.util.Stack;

public class Solution_kk_01 {
	private int height, width; // ����, �ʺ�

	public int solution(int[][] board, int[] moves) {
		height = board.length; // ����
		width = board[0].length; // �ʺ�
		int[] each_top_index = getTopIndex(board); // map�� �ֻ�� ���� ���� ����
		int answer = getAnswer(board, moves, each_top_index); // �����̱� ����
		return answer;
	} // end of func(solution)

	private int[] getTopIndex(int[][] board) {
		int[] ret = new int[height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (board[y][x] != 0) {
					ret[x] = y; // �ֻ�� ���� ���� ����
					break;
				}
			}
		}
		return ret;
	} // end of func(getTopIndex)

	private int getAnswer(int[][] board, int[] moves, int[] each_top_index) {
		Stack<Integer> basket = new Stack<Integer>();
		int ret = 0;
		for (int i = 0; i < moves.length; i++) {
			int idx = moves[i] - 1;
			if (each_top_index[idx] == height) {
				continue;
			}
			int pick = board[each_top_index[idx]][idx];
			each_top_index[idx]++;
			if (basket.isEmpty() || basket.peek() != pick) { // �ٱ��ϰ� ����ְų�, ������ ���� �ʴٸ�
				basket.add(pick); // �ٱ��Ͽ� �ֱ�
			} else {
				basket.pop(); // �ٱ��Ͽ��� ����
				ret += 2;
			}
		}
		return ret;
	} // end of func(getAnswer)
} // end of Solution