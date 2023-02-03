public class test {
    public static class t {
        int a;
        String str;
        public t(int i, String s) {
            a = i;
            str = s;
        }
    }
    public static void main(String[] args) {
        t t1 = new t(2, "haha");
        t t2 = t1;
        t1 = null;
        System.out.println(t2.str);
    }
}
