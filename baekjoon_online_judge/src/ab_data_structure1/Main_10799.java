package ab_data_structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/** 
 * �踷��� �ڸ���
 */
public class Main_10799 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int sum = 0; // �߸� ����� ������ ��
        int open = 0; // ���� ��ȣ ������ ��
        
        char[] input = br.readLine().toCharArray(); // char�� ����
        
        for (int i = 0; i < input.length; i++) {
        	char tmp = input[i];
        
        	if(tmp=='(') {
        		open++;
        	}
        	// ���� ���� ������ ��,
        	else if(input[i-1] == '('){ // 1. �������� ���
        		open--;
        		sum += (open);
        	}else { // 2. �������� �ƴ� ���
        		open--;
        		sum += 1;
        	}
        }
        System.out.println(sum);
    }
}


