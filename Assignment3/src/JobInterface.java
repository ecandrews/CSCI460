/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public interface Job {

    private int priority;
    public int getPriority();
    public void setPriority(int priority);
    public void printResult();

}