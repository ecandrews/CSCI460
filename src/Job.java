public class Job {

    private int arrivalTime;
    private int processingTime;

    public Job(int arrivalTime, int processingTime) {
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public int getArrivalTime() { return this.arrivalTime; }
    public int getProcessingTime() { return this.processingTime; }
}
