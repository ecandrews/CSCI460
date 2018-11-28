/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public class Job2 implements JobInterface {

    private int priority = 2;
    private int msTaken = 0;
    private int timeLeftToRun = 10;
    private int arrivalTime = 0;
    private int timeStarted = 0;

    public Job2(int arrivalTime) { this.arrivalTime = arrivalTime; }

    public int getPriority() {
        return this.priority;
    }

    public int getArrivalTime() { return this.arrivalTime; }

    public void setArrivalTime(int newTime) { this.arrivalTime = newTime; }

    public void setMSTaken(int msTaken) {
        this.msTaken = msTaken;
    }

    public int getMSTaken() { return this.msTaken; }

    public int getTimeLeftToRun() { return this.timeLeftToRun; }

    public void setTimeLeftToRun(int left) { this.timeLeftToRun = left; }

    public void setTimeStarted(int finalTime) { this.timeStarted = finalTime; }

    public void printResult() {
        System.out.print("time " + this.timeStarted + ", ");
        System.out.print("T2 ");
        for(int i = 0; i < this.msTaken; i++) {
            System.out.print("N ");
        }
        System.out.println("T2");
    }
}