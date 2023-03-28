import java.util.ArrayList;

public class NonPreemptivePriority {
    private ArrayList<Process> processes;
    private ArrayList<String> processGantt; //

    ArrayList<Integer> processStopTime; //

    public NonPreemptivePriority(ArrayList<Process> processes) {
        setProcesses(processes);
        setProcessGantt(processGantt);
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public void setProcessGantt(ArrayList<String> processGantt) { //
        this.processGantt = processGantt;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public ArrayList<String> getProcessGantt() { //
        return processGantt;
    }

    public ArrayList<Integer> executeProcesses() {
        ArrayList<Process> readyQueue = new ArrayList<>();
        ArrayList<Process> waitingProcesses = new ArrayList<>(processes);
        int clock = 0;
        int terminatedProcesses = 0;
        boolean hang = false;

        processStopTime = new ArrayList<>(); //
        processGantt = new ArrayList<String>(); //

        do {
            for (int i = 0; i < waitingProcesses.size(); i++) {
                Process process = waitingProcesses.get(i);
                if (process.getArrivalTime() == clock) {
                    readyQueue.add(process);
                }
            }

            if (readyQueue.size() != 0) 
                break;

            clock++;
        } while (true);

        processStopTime.add(clock); //

        do {
            if(!(readyQueue.isEmpty())) {
                Process p = readyQueue.get(0);

                if(hang) { //
                    processGantt.add("_"); //
                    processStopTime.add(clock); //
                    hang = false;
                }

                for (int i = 1; i < readyQueue.size(); i++) {
                    if (readyQueue.get(i).getPriority() < p.getPriority())
                        p = readyQueue.get(i);
                }

                while (true) {
                    int burstTime = p.getRemainingBurstTime();
                    burstTime--;
                    clock++;
                    p.setRemainingBurstTime(burstTime);
                    for (int i = 0; i < waitingProcesses.size(); i++) {
                        Process process = waitingProcesses.get(i);
                        if (process.getArrivalTime() == clock) {
                            readyQueue.add(process);
                        }
                    }
                    if (burstTime == 0) {
                        p.setFinishTime(clock);
                        readyQueue.remove(p);
                        terminatedProcesses++;

                        processGantt.add(p.getProcessID()); //
                        processStopTime.add(clock); //
                        break;
                    }
                }
            }
            else { //
                clock++;
                hang = true;
                for (int i = 0; i < waitingProcesses.size(); i++) {
                    Process process = waitingProcesses.get(i);
                    if (process.getArrivalTime() == clock) {
                        readyQueue.add(process);
                    }
                }
            }
        } while (terminatedProcesses != processes.size());
        return processStopTime;
    }
}