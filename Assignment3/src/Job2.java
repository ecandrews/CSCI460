/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public class Job2 implements JobInterface {

    public int priority = 2;
    public int msTaken = 0;

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setMsTaken(int newMS) {
        this.msTaken = newMS;
    }

    public void printResult() {
        System.out.println("test print job2");
        System.out.print("T2 ");
        for(int i = 0; i < this.msTaken; i++) {
            System.out.print("N");
        }
        System.out.println(" T2");
    }
}