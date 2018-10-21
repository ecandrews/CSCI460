/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */
import java.util.ArrayList;

public class Processor {

    private boolean isBusy;
    private Job currentJob;
    private ArrayList<Job> jobQueue = new ArrayList<>();

    public Processor() {
        this.isBusy = false;
        this.currentJob = null;
    }

    public void setCurrentJob(Job currentJob, int startTime) {
        currentJob.setStartTime(startTime);
        currentJob.setEndTime(currentJob.getStartTime() + currentJob.getProcessingTime());
        this.currentJob = currentJob;
        this.isBusy = true;
    }

    public void endCurrentJob(int currMS) {
        currentJob.setEndTime(currMS - currentJob.getArrivalTime());
        currentJob.setCompleted(true);
        this.currentJob = null;
        this.isBusy = false;
    }

    public void addJobToQueue(Job jobToAdd) {
        jobQueue.add(jobToAdd);
    }

    public void popFromQueue() {
        jobQueue.remove(0);
    }

    public boolean getIsBusy() { return this.isBusy; }
    public Job getCurrentJob() { return this.currentJob; }
    public ArrayList<Job> getJobQueue() { return jobQueue; }
}
