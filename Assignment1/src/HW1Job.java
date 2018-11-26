/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */
public class HW1Job {

    private int arrivalTime;
    private int processingTime;
    private int startTime;
    private int endTime;
    private boolean completed;

    public HW1Job(int arrivalTime, int processingTime) {
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
        this.completed = false;
    }

    public int getArrivalTime() { return this.arrivalTime; }
    public int getProcessingTime() { return this.processingTime; }
    public int getStartTime() { return this.startTime; }
    public int getEndTime() { return this.endTime; }
    public boolean getCompleted() { return this.completed; }
    public void setStartTime(int startTime) { this.startTime = startTime; }
    public void setEndTime(int endTime) { this.endTime = endTime; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
