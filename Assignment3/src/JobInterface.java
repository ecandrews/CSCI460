/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public interface JobInterface {

    public int getPriority();
    public void setMSTaken(int msTaken);
    public int getMSTaken();
    public int getArrivalTime();
    public void setArrivalTime(int newTime);
    public void printResult();
    public void setTimeStarted(int finalTime);
    public int getTimeLeftToRun();
    public void setTimeLeftToRun(int left);
}