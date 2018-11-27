/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public class Job2 implements JobInterface {

    private int priority = 2;
    private int msTaken = 0;
    private int timeLeftToRun = 10;
    private int arrivalTime = 0;
    private boolean isComplete = false;

    public Job2(int arrivalTime) { this.arrivalTime = arrivalTime; }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setMsTaken(int newMS) {
        this.msTaken = newMS;
    }

    public int getTimeLeftToRun() { return this.timeLeftToRun; }

    public boolean isComplete() { return isComplete; }

    public void printResult() {
        System.out.println("test print job2");
        System.out.print("T2 ");
        for(int i = 0; i < this.msTaken; i++) {
            System.out.print("N");
        }
        System.out.println(" T2");
    }
}