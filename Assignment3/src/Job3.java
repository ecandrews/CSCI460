/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public class Job3 implements JobInterface {

    private int priority = 1;
    private int timeLeftToRun = 3;
    private int arrivalTime = 0;
    private boolean isComplete = false;
    private int timeStarted = 0;

    public Job3(int arrivalTime) { this.arrivalTime = arrivalTime; }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) { this.priority = priority; }

    public int getArrivalTime() { return this.arrivalTime; }

    public void setTimeLeftToRun(int left) { this.timeLeftToRun = left; }

    public int getTimeLeftToRun() { return this.timeLeftToRun; }

    public void setTimeStarted(int finalTime) { this.timeStarted = finalTime; }

    public void setMSTaken(int newTime){}

    public int getMSTaken() { return 0; }

    public void printResult() {
        System.out.print("time " + this.timeStarted + ", ");
        System.out.println("T3 333 T3");
    }
}