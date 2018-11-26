/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public class Job3 implements JobInterface {

    public int priority = 1;

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void printResult() {
        System.out.println("test print job3");
    }
}