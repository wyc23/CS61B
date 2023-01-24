public class ex_2 {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int index = 0;
        int max = m[index];
        while (index < m.length) {
            if (max < m[index]) {
                max = m[index];
            }
            index++;
        }
        return max;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
       System.out.println(max(numbers));      
    }
}