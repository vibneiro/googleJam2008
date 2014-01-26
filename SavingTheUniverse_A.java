package googleJam._2008_.qualification;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class SavingTheUniverse_A {

    private void solve(Scanner sc, PrintWriter pw) {

        int sNum = sc.nextInt();
        List<String> sList = new ArrayList<String>();
        System.out.println("sNum = " + sNum);
        sc.nextLine();
        while(sNum-- > 0) {
            String sn = sc.nextLine();
            System.out.println(sn);
            sList.add(sn);
        }

        int qNum = sc.nextInt();
        List<String> qList = new ArrayList<String>();
        System.out.println("qNum = " + qNum);
        sc.nextLine();
        while(qNum-- > 0) {
            String qn = sc.nextLine();
            System.out.println(qn);
            qList.add(qn);

            if(!sList.contains(qn)) {
                pw.println(0);
                return;
            }
        }

        if(sList.isEmpty() | qList.isEmpty()) { pw.println(0); return; }

        int min = recursive(sList, qList, 0, Integer.MAX_VALUE, 0);

        System.out.println("Result min = " + min);

        pw.println(min);
    }

    public int recursive(List<String> sList, List<String> qList, int curMin, int min, int k) {

        if(qList.size() == k) {
            return curMin;
        } else {

            for(int j = 0; j < sList.size(); j++) {
                String s = sList.get(j);
                for(int i = k; i < qList.size(); i++) {
                    if(qList.get(i).equals(s)) {
                        curMin++;
//                        if (curMin < min) { // backtrack
//                            min = curMin;
                            curMin = recursive(sList, qList, curMin, min, k+1);
                       // }
                    }
                }
            }
        }

        return curMin;
    }

    public static void main(String[] args) throws Exception {


        Scanner sc = new Scanner(new FileReader("A-small-practice.in.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

        int caseCnt = sc.nextInt();
        sc.nextLine();
        for (int caseNum = 0; caseNum < caseCnt; caseNum++) {
            System.out.println("Processing test case " + (caseNum + 1));
            pw.print("Case #" + (caseNum + 1) + ": ");
            new SavingTheUniverse_A().solve(sc, pw);
        }

        pw.flush();
        pw.close();
        sc.close();
    }

}