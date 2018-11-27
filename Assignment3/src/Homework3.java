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
        int currJobIndex = 0;

        // create 3 random jobs just to use for now
        // assume that the jobs will be in the array in the order they will arrive
        Job1 job1 = new Job1(1);
        Job2 job2 = new Job2(5);
        Job3 job3 = new Job3(10);
        ArrayList <JobInterface> jobsArray = new ArrayList <JobInterface>();
        jobsArray.add(job1);
        jobsArray.add(job2);
        jobsArray.add(job3);
        int totalJobs = 3;
        // arraylist of completed jobs
        ArrayList <JobInterface> completedJobs = new ArrayList<JobInterface>();

        // TODO: see if you can just increase the time at the very end of the loop
        while(allJobsComplete(completedJobs, totalJobs) == false) {
            // if currJob is not null
            if(currJob != null) {
                // if current job is completed
                if(currJob.getTimeLeftToRun() == 0) {
                    // print out results
                    currJob.printResult();
                    // add currJob to completedJobs
                    completedJobs.add(currJob);
                    // remove currJob from jobsArray
                    jobsArray.remove(0);
                    // set currJob to null
                    currJob = null;
                } else {
                    // subtract from job's time left to run and increase currTime
                    currJob.setTimeLeftToRun(currJob.getTimeLeftToRun() - 1);
                    // if job has priority 2, increase msTaken by 1
                    if(currJob.getPriority() == 2) {
                        currJob.setMSTaken(currJob.getMSTaken() + 1);
                    }
                }

            } else {
                // if currJob is null, then the last job is complete
                // check if a new job has arrived
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