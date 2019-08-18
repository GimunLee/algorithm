import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main_1828_�����_other{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //1~100
        int[][] ref = new int[N][2];
        for(int tc = 0; tc < N; tc++) {
            for(int i=0; i<N; i++) {
                ref[i][0] = sc.nextInt();
                ref[i][1] = sc.nextInt();
            }
            
            //���� �µ� �������� ����
            Arrays.sort(ref, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] <= o2[0] ? -1 : 1;
                }
            });
            
            ArrayList<int[]> list = new ArrayList<>();
            list.add(ref[0]); //ù ����� �µ����� �־���
            int idx = 0; //list�� �ε���
            
            for(int i=1; i<ref.length; i++) { 
                int[] before = list.get(idx);
                int min = before[0];
                int max = before[1];
                
                if(ref[i][0] <= max ) { // �µ��� ��ħ
                    min = ref[i][0];
                    max = max > ref[i][1] ? ref[i][1]:max ;
                    int[] tmp = {min, max};
                    list.set(idx, tmp);
                }else { // �µ��� �Ȱ�ħ
                    idx++;
                    list.add(ref[i]);
                }
            }
            System.out.println(list.size());
        }//end testCase
        
    }//end main
}//end class