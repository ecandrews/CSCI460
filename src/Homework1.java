import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Homework1 {

    private static Processor proc1;
    private static Processor proc2;
    private static Processor proc3;
    private static Processor[] procArray;
    private static List<Job> jobList1 = new ArrayList();
    private static List<Job> jobList2 = new ArrayList();

    public static void main(String [] args) {
        partB1();
        partB2();
    }

    // run the given jobs on three processors
    // returns the time once all the jobs are finished
    public static int runJobs(List<Job> jobList) {

        proc1 = new Processor();
        proc2 = new Processor();
        proc3 = new Processor();
        procArray = new Processor[] { proc1, proc2, proc3 };

        // loop to take in jobs, each loop is 1 ms
        int finalTime = jobList.get(jobList.size() - 1).getArrivalTime() + jobList.get(jobList.size() - 1).getProcessingTime();
        int ms = 0;
        int nextProc = 0;
        Job lastJob = jobList.get(jobList.size() - 1);

        // while the last job is not complete
        while(lastJob.getCompleted() != true) {

            // update processors
            for(Processor proc : procArray) {
                // if the processor has a job at the moment
                if(proc.getIsBusy()) {
                    if(proc.getCurrentJob().getEndTime() == ms) {
                        proc.endCurrentJob(ms);
                        if(!proc.getJobQueue().isEmpty()) {
                            proc.setCurrentJob(proc.getJobQueue().get(0), ms);
                            proc.popFromQueue();
                        }
                    }
                }
            }

            // check next arriving job
            if(!jobList.isEmpty() && jobList.get(0).getArrivalTime() == ms) {
                // if next processor is not busy
                if(procArray[nextProc].getIsBusy() == false) {
                    procArray[nextProc].setCurrentJob(jobList.get(0), ms);
                } else {
                    procArray[nextProc].addJobToQueue(jobList.get(0));
                }
                jobList.remove(0);
            }

            // set next processor
            nextProc = (nextProc + 1) % procArray.length;
            ms++;
        }
        return ms;
    }

    public static void partB1() {
        Random rand = new Random();
        int minRunTime = 1000000;
        int maxRunTime = 0;
        int sumRunTime = 0;

        // run 100 times to get statistics
        for(int i = 0; i < 100; i++) {

            // initialize jobs and run
            for(int j = 0; j < 100; j++) {
                int procTime = rand.nextInt((500 - 1) + 1) + 1;
                jobList1.add(new Job(j, procTime));
            }
            int finalRunTime = runJobs(jobList1);

            if(finalRunTime < minRunTime) { minRunTime = finalRunTime; }
            if(finalRunTime > maxRunTime) { maxRunTime = finalRunTime; }
            sumRunTime = sumRunTime + finalRunTime;
        }

        // print results of part b1
        System.out.println("\n~~~~~~~~~~ PART B1 ~~~~~~~~~~\n");
        System.out.println("After running program 100 times these were the results (in ms):\n");
        System.out.println("Minimum turnaround time: " + minRunTime + "\n");
        System.out.println("Maximum turnaround time: " + maxRunTime + "\n");
        System.out.println("Average turnaround time: " + (sumRunTime / 100) + "\n\n\n");
    }

    public static void partB2() {
        // create jobs and add them to the jobList
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
        int finalRunTime = runJobs(jobList2);

        // print results of part b2
        System.out.println("~~~~~~~~~~ PART B2 ~~~~~~~~~~\n");
        System.out.println("Arrival time of first job (in ms): 4\n");
        System.out.println("Finish time of last job (in ms): " + finalRunTime + "\n");
        System.out.println("Overall turnaround time: " + (finalRunTime - 4) + "\n");
    }

    public void partC() {

    }
}
