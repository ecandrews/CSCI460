/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

// interface describing the three different jobs
public interface JobInterface {
    int getPriority();
    void setMSTaken(int msTaken);
    int getMSTaken();
    int getArrivalTime();
    void setArrivalTime(int newTime);
    void printResult();
    void setTimeStarted(int finalTime);
    int getTimeLeftToRun();
    void setTimeLeftToRun(int left);
}