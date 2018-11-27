/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public class Job1 implements JobInterface {

    private int priority = 3;
    private int timeLeftToRun = 3;
    private int arrivalTime = 0;
    private boolean isComplete = false;
    private int timeStarted = 0;

    public Job1(int arrivalTime) { this.arrivalTime = arrivalTime; }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getArrivalTime() { return this.arrivalTime; }

    public int getTimeLeftToRun() { return this.timeLeftToRun; }

    public void setTimeLeftToRun(int left) { this.timeLeftToRun = left; }

    public void setTimeStarted(int finalTime) { this.timeStarted = finalTime; }

    public void setMSTaken(int newTime){}

    public int getMSTaken(){ return 0; }

    public void printResult() {
        System.out.print("time " + this.timeStarted + ", ");
        System.out.println("T1 111 T1");
    }
}