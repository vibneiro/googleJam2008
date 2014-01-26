package googleJam._2008_.qualification;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class UglyNumbers_B {

    private int a[];
    private long counter = 0;

    public void enumerate(String number, int k, PrintWriter pw) {

        if(k == a.length) {
            processNumber(number, pw);
            return;
        } else {
            for(int n = 0; n < 3; n++) { //3^(d-1)
                a[k] = n;
                enumerate(number, k + 1, pw);
            }
        }
        a[k] = 0;
    }

    public void processNumber(String number, PrintWriter pw) {

        long result = 0;
        String lastNumber = "";

        for(int i = 0; i < a.length; i++) {
            switch(a[i]) {
                // -
                case 0:
                    if(!lastNumber.isEmpty()) {
                        result += Long.valueOf(lastNumber);
                    }
                    lastNumber = "-" + number.charAt(i) + "";

                    break;
                // +
                case 1:
                    if(!lastNumber.isEmpty()) {
                        result += Long.valueOf(lastNumber);
                    }
                    lastNumber = number.charAt(i) + "";
                    break;
                // nothing
                case 2:
                    lastNumber += number.charAt(i);
                    break;
            }
        }

        if (!lastNumber.isEmpty()) {
            result += Long.valueOf(lastNumber);
        }

        if(result % 2 == 0 || result % 3 == 0 || result % 5 == 0 || result % 7 == 0) {
            ++counter;
        }
    }

    private void solve(Scanner sc, PrintWriter pw) {

        String number = sc.next();

        if(Long.valueOf(number) == 0) {
            pw.println(1);
            return;
        }

        a = new int[number.length()];
        enumerate(number, 0, pw);
        System.out.println(counter/3);
        pw.println(counter/3);

    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new FileReader("B-small-practice.in.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

        int caseCnt = sc.nextInt();
        sc.nextLine();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum + 1) + ": ");
            new UglyNumbers_B().solve(sc, pw);
        }

        pw.flush();
        pw.close();
        sc.close();
    }

}
