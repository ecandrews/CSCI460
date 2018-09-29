import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Homework1 {

    private int numProcessors = 3;
    private static List<Job> jobList1 = new ArrayList();
    private static List<Job> jobList2 = new ArrayList();

    public static void main(String [] args) {
        partB1();
        partB2();

    }

    public static void partB1() {
        Random rand = new Random();
        int arrTime = 1;
        for(int i = 0; i < 100; i++) {
            arrTime = rand.nextInt((arrTime + 12            ) - arrTime + 1) + (arrTime + 1);
            int procTime = rand.nextInt((200 - 3) + 1) + 3;
            jobList1.add(new Job(arrTime, procTime));
            System.out.println("arrTime: " + arrTime + " -- procTime: " + procTime);
        }

        // loop to take in jobs, each loop is 1 ms
        int finalTime = jobList1.get(jobList1.size() - 1).getArrivalTime() + jobList1.get(jobList1.size() - 1).getProcessingTime();
        int ms = 0;
        while(ms <= finalTime ) {
            // do processing stuff
            ms++;
        }

    }

    public static void partB2() {
        Job job1 = new Job(4,9);
        jobList2.add(job1);
        Job job2 = new Job(15, 2);
        jobList2.add(job2);
        Job job3 = new Job(18, 16);
        jobList2.add(job3);
        Job job4 = new Job(20, 3);
        jobList2.add(job4);
        Job job5 = new Job(26, 29);
        jobList2.add(job5);
        Job job6 = new Job(29, 198);
        jobList2.add(job6);
        Job job7 = new Job(35, 7);
        jobList2.add(job7);
        Job job8 = new Job(45, 170);
        jobList2.add(job8);
        Job job9 = new Job(57, 180);
        jobList2.add(job9);
        Job job10 = new Job(83, 178);
        jobList2.add(job10);
        Job job11 = new Job(88, 73);
        jobList2.add(job11);
        Job job12 = new Job(95, 8);
        jobList2.add(job12);
        System.out.println("job list from b2: " + jobList2.size());
    }

    public void partC() {

    }
}
