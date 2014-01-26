package googleJam._2008_.qualification;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class TrainTimeTable_B {

    private void solve(Scanner sc, PrintWriter pw) {

        int T = sc.nextInt();
        sc.nextLine();

        int NA = sc.nextInt();
        int NB = sc.nextInt();

        sc.nextLine();

        class Trip {
            private int t0;
            private int t1;
            private int station;

            Trip(int t0, int t1, int station) {
                this.t0 = t0;
                this.t1 = t1;
                this.station = station;
                System.out.println("New trip created: Station = " + station + " Departure time = " + t0 + " Arrival time = " + t1);
            }
        }

        List<Trip> trips = new ArrayList<Trip>();

        for (int i = 0; i < NA; i++) {
            int t0 = toMinutes(sc.next());
            int t1 = toMinutes(sc.next());
            trips.add(new Trip(t0, t1, 0));
        }

        for (int i = 0; i < NB; i++) {
            int t0 = toMinutes(sc.next());
            int t1 = toMinutes(sc.next());
            trips.add(new Trip(t0, t1, 1));
        }

        Comparator comparator = new Comparator<Trip>(){
            public int compare(Trip trip1, Trip trip2){
                if(trip1.t0 == trip2.t0) {
                    return 0;
                }
                return trip1.t0 < trip2.t0 ? -1 : 1;
            }
        };

        Collections.sort(trips, comparator);

        Comparator pqComparator = new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return i1 <= i2 ? -1 : 1;
            }
        };

        PriorityQueue<Integer> trainsA = new PriorityQueue(1000, pqComparator);
        PriorityQueue<Integer> trainsB = new PriorityQueue(1000, pqComparator);

        int startA = 0;
        int startB = 0;

        for(Trip trip : trips) {
            int station = trip.station;

            if (station == 0) { // FROM A TO B
                if(!trainsA.isEmpty() && trainsA.peek() <= trip.t0) {
                    trainsA.poll();
                } else {
                    trainsB.offer((trip.t1 + T));
                    startA += 1;
                }
            } else {
                if(!trainsB.isEmpty() && trainsB.peek() <= trip.t0) {
                    trainsB.poll();
                } else {
                    trainsA.offer((trip.t1 + T));
                    startB += 1;
                }
            }
        }

        System.out.println("StartA = " + startA + " StartB = " + startB);
        pw.println(startA + " " + startB);
    }

    public static int toMinutes(String sTime) {
        String[] time = sTime.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new FileReader("B-small-practice.in.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));

        int testCnt = sc.nextInt();
        sc.nextLine();
        for (int testNum = 0; testNum < testCnt; testNum++) {
            System.out.println("Processing test case " + (testNum + 1));
            pw.print("Case #" + (testNum + 1) + ": ");
            new TrainTimeTable_B().solve(sc, pw);
        }

        pw.flush();
        pw.close();
        sc.close();
    }


}
