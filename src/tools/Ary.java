package tools;

public class Ary {
    public static int srhInd(Object[] arr, Object obj){
        int i = 0;
        for (Object n : arr){
            if(n == obj){
                return i;
            }
            i++;
        }
        return -1;
    }
}
