public class Process {
    private int arrivalTime;
    private int burstTime;
    private String processID;
    private int finishTime;
    private int priority;
    private int waitingTime;
    private int turnaroundTime;
    private int remainingBurstTime;

    public Process() {
    
    }

    public Process(int arrivalTime, int burstTime, String processID) {
        setArrivalTime(arrivalTime);
        setBurstTime(burstTime);
        setProcessID(processID);
        setFinishTime(finishTime);
        setRemainingBurstTime(burstTime);
    }

    public Process(int arrivalTime, int burstTime, String processID, int priority) {
        setArrivalTime(arrivalTime);
        setBurstTime(burstTime);
        setProcessID(processID);
        setFinishTime(finishTime);
        setPriority(priority);
        setRemainingBurstTime(burstTime);
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setWaitingTime() {
        waitingTime =  turnaroundTime - burstTime;
    }

    public void setTurnaroundTime() {
        turnaroundTime = finishTime - arrivalTime;
    }

    public void setRemainingBurstTime(int remainingBurstTime) {
        this.remainingBurstTime = remainingBurstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public String getProcessID() {
        return processID;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public int getRemainingBurstTime() {
        return remainingBurstTime;
    }

    public void displayProcessInfo() {
        System.out.printf("%-9s%-14s%-12s%-13s%-17s%-14s\n", processID, arrivalTime, burstTime, finishTime, 
                          getTurnaroundTime(), getWaitingTime());
    }
}
