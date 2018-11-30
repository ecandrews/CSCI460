/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */
import java.util.*;

public class Homework3 {

    public static void main(String [] args) {

        int currTime = 0; // current time in ms
        JobInterface currJob = null; // job that is currently running
        ArrayList <JobInterface> jobsArray = new ArrayList <>(); // arraylist of all jobs
        ArrayList <JobInterface> completedJobs = new ArrayList<>(); // arraylist of jobs that are complete or preempted

        // uncomment for part 1: job4 preempts job3
        // Job1 job1 = new Job1(1);
        // Job2 job2 = new Job2(3);
        // Job3 job3 = new Job3(6);
        // Job2 job4 = new Job2(7);
        // jobsArray.add(job1);
        // jobsArray.add(job2);
        // jobsArray.add(job3);
        // jobsArray.add(job4);
        // int totalJobs = 4;

        // uncomment for part 2: the 10 jobs given in the assignment rubric
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

        // while the list of completed or preempted jobs is less than the total number of jobs created
        while(allJobsComplete(completedJobs, totalJobs) == false) {

            if (currJob != null) { // if any job is currently running

                // start check for possible preemption
                // if there is another job arriving after the current job, and if currTime is its arrival time
                if (jobsArray.size() > 1 && jobsArray.get(1).getArrivalTime() == currTime) {

                    // check if next job has a higher priority
                    // but T1 cannot preempt T3, so if T1 is the next job, then continue
                    if (jobsArray.get(1).getPriority() > currJob.getPriority() && !(jobsArray.get(1).getPriority() == 3 && currJob.getPriority() == 1)) { // if true, preempt

                        // if Job2 is being preempted, print out its result
                        if (currJob.getPriority() == 2) { currJob.printResult(); }

                        // add current job to the list of completed jobs and remove it from the array
                        completedJobs.add(currJob);
                        jobsArray.remove(0);
                        // job with higher priority becomes new current job
                        currJob = jobsArray.get(0);
                        currJob.setArrivalTime(currTime);
                        currJob.setTimeStarted(currTime);

                    } else if (jobsArray.get(1).getPriority() < currJob.getPriority() && currJob.getTimeLeftToRun() > 0) {
                        // if next job does not have a higher priority and the current job is not finished yet,
                        // remove the next arriving job from the list, as it will not be able to run
                        jobsArray.remove(1);
                    }
                }

                // if currently running job is complete
                if (currJob.getTimeLeftToRun() == 0) {
                    // if Job2 was the current job, print out its results
                    if (currJob.getPriority() == 2) { currJob.printResult(); }
                    // add current job to the list of completed jobs and remove it from jobsArray. currJob becomes null.
                    completedJobs.add(currJob);
                    jobsArray.remove(0);
                    currJob = null;

                } else { // if current job is not complete yet
                    // decrease current job's time left to run by 1
                    currJob.setTimeLeftToRun(currJob.getTimeLeftToRun() - 1);
                    // if the current job is a Job2, increase msTaken variable
                    if (currJob.getPriority() == 2) { currJob.setMSTaken(currJob.getMSTaken() + 1); }

                    // if the next job to arrive has already passed its arrival time
                    if (jobsArray.size() > 1 && jobsArray.get(1).getArrivalTime() <= currTime) {
                        jobsArray.get(1).setArrivalTime(currTime + 1);
                    }
                }

            } else if(jobsArray.size() == 0) {
                // if the jobs array list contains no more jobs, exit the loop
                break;

            } else { // if a job is not currently running

                // if the current time is the next arriving job's arrival time
                if(currTime == jobsArray.get(0).getArrivalTime()) {
                    // set current job to the job that just arrived
                    currJob = jobsArray.get(0);
                    currJob.setTimeStarted(currTime);
                    currJob.setTimeLeftToRun(currJob.getTimeLeftToRun() - 1);
                    // if the job is T3 or T1, print out the new buffer
                    if(currJob.getPriority() == 1 || currJob.getPriority() == 3) {
                        currJob.printResult();
                    }
                }
            }
            currTime++; // increment current time by one millisecond
        }
    }

    // checks to see if the list of completed jobs is equal to the total number of jobs arriving, that is, if all
    // jobs that are scheduled to arrive have either completed or been preempted
    public static boolean allJobsComplete(ArrayList <JobInterface> completedJobs, int totalJobs) {
        return (completedJobs.size() == totalJobs);
    }

}