/*
Author: Elizabeth Andrews
Written for CSCI460 - Operating Systems at Montana State University
 */

public class Homework3 {

    public static void main(String [] args) {

        int currTime = 0; // current time in ms
        int[] buffer = new int[3];
        buffer[0] = 0;
        buffer[1] = 0;
        buffer[2] = 0;

        // create 3 random jobs just to use for now
        // assume that the jobs will be in the array in the order they will arrive
        Job1 job1 = new Job1(1);
        Job2 job2 = new Job2(3);
        Job3 job3 = new Job3(6);
        JobInterface[] jobsArray = new JobInterface[3];
        jobsArray[0] = job1;
        jobsArray[1] = job2;
        jobsArray[2] = job3;

        while(allJobsComplete(jobsArray) == false) {
            
            currTime++;
        }
    }

    public static boolean allJobsComplete(JobInterface[] jobs) {
        int numComplete = 0;
        for(int i = 0; i < jobs.length; i++) {
            if(jobs[i].isComplete()) {
                numComplete++;
            }
        }
        return (numComplete == jobs.length);
    }

}