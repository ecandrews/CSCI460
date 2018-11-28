/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */
import java.util.*;

public class Homework3 {

    public static void main(String [] args) {

        int currTime = 0; // current time in ms
        JobInterface currJob = null;
        ArrayList <JobInterface> jobsArray = new ArrayList <>();
        ArrayList <JobInterface> completedJobs = new ArrayList<>();

        // create 3 random jobs just to use for easy testing
        // Job1 job1 = new Job1(1);
        // Job2 job2 = new Job2(3);
        // Job3 job3 = new Job3(6);
        // jobsArray.add(job1);
        // jobsArray.add(job2);
        // jobsArray.add(job3);
        // int totalJobs = 3;

        // use the 10 jobs given in the assignment rubric
        Job3 job1 = new Job3(1);
        Job2 job2 = new Job2(3);
        Job3 job3 = new Job3(6);
        Job1 job4 = new Job1(8);
        Job2 job5 = new Job2(10);
        Job3 job6 = new Job3(12);
        Job1 job7 = new Job1(26);
        jobsArray.add(job1);
        jobsArray.add(job2);
        jobsArray.add(job3);
        jobsArray.add(job4);
        jobsArray.add(job5);
        jobsArray.add(job6);
        jobsArray.add(job7);
        int totalJobs = 7;

        while(allJobsComplete(completedJobs, totalJobs) == false) {

            if(currJob != null) { // if a job is currently running

                // check if a next job exists and if it has a higher priority
                if(jobsArray.size() > 1 && jobsArray.get(1).getPriority() > currJob.getPriority()) {
                    // if Job2 is being preempted, it needs to print out its result
                    if(currJob.getPriority() == 2) { currJob.printResult(); }
                    completedJobs.add(currJob);
                    currJob = jobsArray.get(1);
                    jobsArray.remove(0);
                }

                if(currJob.getTimeLeftToRun() == 0) { // if current job is complete
                    if(currJob.getPriority() == 2) {
                        currJob.printResult();
                    }
                    completedJobs.add(currJob);
                    jobsArray.remove(0);
                    currJob = null;

                } else { // if current job is not complete yet

                    currJob.setTimeLeftToRun(currJob.getTimeLeftToRun() - 1);
                    if(currJob.getPriority() == 2) { // if the current job is a Job2
                        currJob.setMSTaken(currJob.getMSTaken() + 1);
                    }

                    // if the next job to arrive has already passed its arrival time
                    if(jobsArray.size() > 1 && jobsArray.get(1).getArrivalTime() <= currTime) {
                        jobsArray.get(1).setArrivalTime(currTime + 1);
                    }
                }

            } else { // if a job is not currently running

                if(currTime == jobsArray.get(0).getArrivalTime()) { // if the current time is the next job's arrival time
                    currJob = jobsArray.get(0);
                    currJob.setTimeStarted(currTime);
                    currJob.setTimeLeftToRun(currJob.getTimeLeftToRun() - 1);
                    if(currJob.getPriority() == 1 || currJob.getPriority() == 3) {
                        currJob.printResult();
                    }
                }
            }
            currTime++;
        }
    }

    public static boolean allJobsComplete(ArrayList <JobInterface> jobs, int totalJobs) {
        return (jobs.size() == totalJobs);
    }

}