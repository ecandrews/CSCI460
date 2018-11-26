/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public class Job2 implements JobInterface {

    public int priority = 2;

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void printResult() {
        System.out.println("test print job2");
    }
}