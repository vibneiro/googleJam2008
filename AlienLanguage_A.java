package googleJam._2008_.qualification;

public class AlienLanguage_A {

    public static void permute2(char ch[], int k) {

        if (ch.length - 1 == k) {
            System.out.println(new String(ch));
        } else {
            for(int i = k; i < ch.length; i++) {
                swap2(ch, i, k);
                permute2(ch, k+1);
                swap2(ch, i, k);
            }
        }
    }

    public static void swap2(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a  = new int[] { 1,2,3 };
        permute2("ABC".toCharArray(), 0);
    }

}
