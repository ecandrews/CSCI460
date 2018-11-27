/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public class Job1 implements JobInterface {

    private int priority = 3;
    private int timeLeftToRun = 3;
    private int arrivalTime = 0;
    private boolean isComplete = false;

    public Job1(int arrivalTime) { this.arrivalTime = arrivalTime; }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTimeLeftToRun() { return this.timeLeftToRun; }

    public boolean isComplete() { return isComplete; }

    public void printResult() {
        System.out.println("test print job1");
        System.out.println("T1 111 T1");
    }
}