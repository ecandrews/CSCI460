/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Homework1 {

    private static Processor proc1;
    private static Processor proc2;
    private static Processor proc3;
    private static Processor[] procArray;
    private static List<Job> jobsWaiting = new ArrayList();
    private static List<Job> jobList1 = new ArrayList();
    private static List<Job> jobList2 = new ArrayList();
    private static List<Job> jobList3 = new ArrayList();
    private static List<Job> jobList4 = new ArrayList();

    public static void main(String [] args) {
        partB1();
        partB2();
        partC();
    }

    /*
    Run the jobs given in jobList using a circular procedure with three processors
    Returns the number of milliseconds since procedure began
     */
    public static int runJobs(List<Job> jobList) {
        // instantiate processors, processor array, and other variables
        proc1 = new Processor();
        proc2 = new Processor();
        proc3 = new Processor();
        procArray = new Processor[] { proc1, proc2, proc3 };
        int ms = 0;
        int nextProc = 0;
        Job lastJob = jobList.get(jobList.size() - 1);

        // continue to run while the last job in the jobList has not been completed
        while(lastJob.getCompleted() != true) {

            // check if any jobs assigned to a processor have completed
            for(Processor proc : procArray) {
                // if the processor has a job at the moment
                if(proc.getIsBusy()) {
                    // if the processor's current job's end time is the current time, end the job
                    if(proc.getCurrentJob().getEndTime() == ms) {
                        proc.endCurrentJob(ms);
                        // if current job has ended, add any job from the processor's queue to the processor
                        if(!proc.getJobQueue().isEmpty()) {
                            proc.setCurrentJob(proc.getJobQueue().get(0), ms);
                            proc.popFromQueue();
                        }
                    }
                }
            }

            /*
            if there is another job in the jobList and it's arrival time is the current time, add it to the next
            processor to be used
             */
            if(!jobList.isEmpty() && jobList.get(0).getArrivalTime() == ms) {
                // if next processor is not busy, set the current job, otherwise add the job to the processor's queue
                if(procArray[nextProc].getIsBusy() == false) {
                    procArray[nextProc].setCurrentJob(jobList.get(0), ms);
                } else {
                    procArray[nextProc].addJobToQueue(jobList.get(0));
                }
                jobList.remove(0);
            }

            // set next processor to be used and increase current millisecond
            nextProc = (nextProc + 1) % procArray.length;
            ms++;
        }
        return ms;
    }

    /*
    Run the jobs given in jobList using a faster procedure with three processors
    Returns the number of milliseconds since procedure began
     */
    public static int runJobsPartC(List<Job> jobList) {
        // instantiate processors, processor array, and other variables
        proc1 = new Processor();
        proc2 = new Processor();
        proc3 = new Processor();
        procArray = new Processor[] { proc1, proc2, proc3 };
        int ms = 0;
        int nextProc = 0;
        Job lastJob = jobList.get(jobList.size() - 1);

        // continue to run while the last job in the jobList has not been completed
        while(lastJob.getCompleted() != true) {

            // check if any jobs assigned to a processor have completed
            for(Processor proc : procArray) {
                // if the processor has a job at the moment
                if(proc.getIsBusy()) {
                    // if the processor's current job's end time is the current time, end the job
                    if(proc.getCurrentJob().getEndTime() == ms) {
                        proc.endCurrentJob(ms);
                        // if current job has ended, add any job from the queue of jobs waiting to the processor
                        if(!jobsWaiting.isEmpty()) {
                            proc.setCurrentJob(jobsWaiting.get(0), ms);
                            jobsWaiting.remove(0);
                        }
                    }
                }
            }

            /*
            if there is another job in the jobList and it's arrival time is the current time, add it to the next
            processor to be used
             */
            if(!jobList.isEmpty() && jobList.get(0).getArrivalTime() == ms) {
                // if next processor is not busy, set the current job, otherwise add the job to the jobs waiting queue
                if(procArray[nextProc].getIsBusy() == false) {
                    procArray[nextProc].setCurrentJob(jobList.get(0), ms);
                } else {
                    jobsWaiting.add(jobList.get(0));
                }
                jobList.remove(0);
            }

            // set next processor to be used and increase current millisecond
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
        int startTime;
        ArrayList<Integer> turnaroundTimes = new ArrayList<Integer>();

        // run 100 times to get statistics
        for(int i = 0; i < 100; i++) {

            // initialize jobs, add them to the jobList, and run
            for(int j = 0; j < 100; j++) {
                int procTime = rand.nextInt((500 - 1) + 1) + 1;
                jobList1.add(new Job(j, procTime));
            }
            startTime = jobList1.get(0).getArrivalTime();
            int finalRunTime = runJobs(jobList1) - startTime;
            turnaroundTimes.add(finalRunTime);

            if(finalRunTime < minRunTime) { minRunTime = finalRunTime; }
            if(finalRunTime > maxRunTime) { maxRunTime = finalRunTime; }
            sumRunTime = sumRunTime + finalRunTime;
        }

        // print results of part b1
        System.out.println("\n~~~~~~~~~~ PART B1 ~~~~~~~~~~\n");
        System.out.println("After running program 100 times these were the results (in ms):\n");
        System.out.println("Minimum turnaround time: " + minRunTime + "\n");
        System.out.println("Maximum turnaround time: " + maxRunTime + "\n");
        System.out.println("Average turnaround time: " + (sumRunTime / 100) + "\n");
        System.out.println("Standard deviation: " + (int)calcStdDev(turnaroundTimes, (sumRunTime / 100)) + "\n\n\n");
    }

    public static void partB2() {
        // initialize jobs, add them to the jobList, and run
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
        System.out.println("Overall turnaround time: " + (finalRunTime - 4) + "\n\n\n");
    }

    public static void partC() {
        Random rand = new Random();
        int minRunTime = 1000000;
        int maxRunTime = 0;
        int sumRunTime = 0;
        int startTime;
        ArrayList<Integer> turnaroundTimes = new ArrayList();

        // run 100 times to get statistics
        for(int i = 0; i < 100; i++) {

            // initialize jobs, add them to the jobList, and run
            for(int j = 0; j < 100; j++) {
                int procTime = rand.nextInt((500 - 1) + 1) + 1;
                jobList3.add(new Job(j, procTime));
            }
            startTime = jobList3.get(0).getArrivalTime();
            int part1finalRunTime = runJobsPartC(jobList3) - startTime;
            turnaroundTimes.add(part1finalRunTime);

            if(part1finalRunTime < minRunTime) { minRunTime = part1finalRunTime; }
            if(part1finalRunTime > maxRunTime) { maxRunTime = part1finalRunTime; }
            sumRunTime = sumRunTime + part1finalRunTime;
        }

        Job job1 = new Job(4,9);
        jobList4.add(job1);
        Job job2 = new Job(15, 2);
        jobList4.add(job2);
        Job job3 = new Job(18, 16);
        jobList4.add(job3);
        Job job4 = new Job(20, 3);
        jobList4.add(job4);
        Job job5 = new Job(26, 29);
        jobList4.add(job5);
        Job job6 = new Job(29, 198);
        jobList4.add(job6);
        Job job7 = new Job(35, 7);
        jobList4.add(job7);
        Job job8 = new Job(45, 170);
        jobList4.add(job8);
        Job job9 = new Job(57, 180);
        jobList4.add(job9);
        Job job10 = new Job(83, 178);
        jobList4.add(job10);
        Job job11 = new Job(88, 73);
        jobList4.add(job11);
        Job job12 = new Job(95, 8);
        jobList4.add(job12);
        int part2minRunTime = runJobsPartC(jobList4);

        // print results of partc
        System.out.println("~~~~~~~~~~ PART C ~~~~~~~~~~\n");
        System.out.println("From randomly generated test data for 100 jobs:\n");
        System.out.println("Minimum turnaround time: " + minRunTime + "\n");
        System.out.println("Maximum turnaround time: " + maxRunTime + "\n");
        System.out.println("Average turnaround time: " + (sumRunTime / 100) + "\n");
        System.out.println("Standard deviation: " + (int)calcStdDev(turnaroundTimes, (sumRunTime / 100)) + "\n\n\n");

        System.out.println("From test data of 12 jobs:\n");
        System.out.println("Arrival time of first job (in ms): 4\n");
        System.out.println("Finish time of last job (in ms): " + part2minRunTime + "\n");
        System.out.println("Overall turnaround time: " + (part2minRunTime - 4) + "\n\n\n");
    }

    /*
    Calculates the standard deviation given a list of numbers and their mean
     */
    public static double calcStdDev(ArrayList<Integer> times, int mean) {
        double sumTimes = 0;
        for(int i = 0; i < times.size(); i++) {
            times.set(i, (times.get(i) - mean) * (times.get(i) - mean));
            sumTimes = sumTimes + times.get(i);
        }
        return Math.sqrt(sumTimes/100);
    }
}
