package googleJam._2008_.qualification;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IncreasingSpeedLimits_C {

    private void solve(Scanner sc, PrintWriter pw) {

        int n = sc.nextInt();
        int m = sc.nextInt();
        int X = sc.nextInt();
        int Y = sc.nextInt();
        int Z = sc.nextInt();
        sc.nextLine();

        long[] A = new long[m];

        for (int i = 0; i < m; i++) {
            A[i] = sc.nextLong();
        }

        System.out.println();

        long[] B = new long[n];

        for (int i = 0; i < n; i++) {
            B[i] = A[i % m];
            System.out.println(A[i % m]);
            A[i % m] = (X * A[i % m] + Y * (i+1)) % Z;
        }

        System.out.println();

        int[] dp = new int[B.length];

        for (int i = 0; i < B.length; i++) {
            dp[i] = 1;

            for (int j = 0; j <= i - 1; j++) {
                if (B[j] < B[i]) {
                    dp[i] = dp[i] + dp[j];
                }
            }
        }

        long sum = 0;

        for(int i = 0; i < dp.length; i++) {
            //System.out.println(dp[i]);
            sum += dp[i];
        }

        pw.println(sum);
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new FileReader("B-small-practice.in.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

        int caseCnt = sc.nextInt();
        sc.nextLine();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum + 1) + ": ");
            new IncreasingSpeedLimits_C().solve(sc, pw);
        }

        pw.flush();
        pw.close();
        sc.close();
    }

}
