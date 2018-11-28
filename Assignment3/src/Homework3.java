/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */
import java.util.*;

public class Homework3 {

    public static void main(String [] args) {

        int currTime = 0; // current time in ms
        JobInterface currJob = null;
        int[] buffer = new int[3];
        buffer[0] = 0;
        buffer[1] = 0;
        buffer[2] = 0;

        // create 3 random jobs just to use for now
        Job1 job1 = new Job1(1);
        Job2 job2 = new Job2(3);
        Job3 job3 = new Job3(6);
        ArrayList <JobInterface> jobsArray = new ArrayList <JobInterface>();
        jobsArray.add(job1);
        jobsArray.add(job2);
        jobsArray.add(job3);
        int totalJobs = 3;
        ArrayList <JobInterface> completedJobs = new ArrayList<JobInterface>();

        while(allJobsComplete(completedJobs, totalJobs) == false) {
            if(currJob != null) {
                if(currJob.getTimeLeftToRun() == 0) {
                    currJob.printResult();
                    completedJobs.add(currJob);
                    jobsArray.remove(0);
                    currJob = null;
                } else {
                    currJob.setTimeLeftToRun(currJob.getTimeLeftToRun() - 1);
                    if(currJob.getPriority() == 2) {
                        currJob.setMSTaken(currJob.getMSTaken() + 1);
                    }
                    if(jobsArray.size() > 1 && jobsArray.get(1).getArrivalTime() <= currTime) {
                        jobsArray.get(1).setArrivalTime(currTime + 1);
                    }
                }

            } else {
                if(currTime == jobsArray.get(0).getArrivalTime()) {
                    currJob = jobsArray.get(0);
                    currJob.setTimeStarted(currTime);
                    currJob.setTimeLeftToRun(currJob.getTimeLeftToRun() - 1);
                }
            }
            currTime++;
        }
    }

    public static boolean allJobsComplete(ArrayList <JobInterface> jobs, int totalJobs) {
        return (jobs.size() == totalJobs);
    }

}