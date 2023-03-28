import java.util.ArrayList;

public class RoundRobin {
    private int timeQuantum;
    private ArrayList<Process> processes;
    private ArrayList<String> processGantt;

    ArrayList<Integer> processStopTime; //

    // Constructor
    public RoundRobin(int timeQuantum, ArrayList<Process> processes) {
        setQ(timeQuantum);
        setProcesses(processes);
        setProcessGantt(processGantt);
    }

    // Setter for int Q
    public void setQ(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    // Setter for ZZ Processes
    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public void setProcessGantt(ArrayList<String> processGantt) { //
        this.processGantt = processGantt;
    }

    // Getter for int Q
    public int getQ() {
        return timeQuantum;
    }

    // Getter for ZZ Processes
    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public ArrayList<String> getProcessGantt() {
        return processGantt;
    }

    public ArrayList<Integer> executeProcesses() {
        ArrayList<Process> readyQueue = new ArrayList<>();
        ArrayList<Process> waitingProcesses = new ArrayList<>(processes);

        processGantt = new ArrayList<String>();
        processStopTime = new ArrayList<>(); //

        Process currentProcess = new Process();
        int clock = 0;
        int terminatedProcesses = 0;
        boolean hang = false;

        do {
            // For Loop for inputting Processes into the ready queue when the clock == their arrival time
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
                for (int i = 0; i <= timeQuantum; i++) {
                    if (i != timeQuantum) {
                        currentProcess = readyQueue.get(0);

                        if(hang) { //
                            processGantt.add("_"); //
                            processStopTime.add(clock); //
                            hang = false;
                        }

                        int burstTime = currentProcess.getRemainingBurstTime();
                        burstTime--;
                        clock++;
                        for (int j = 0; j < waitingProcesses.size(); j++) {
                            Process process = waitingProcesses.get(j);
                            if (process.getArrivalTime() == clock) {
                                readyQueue.add(process);
                            }
                        }
                        if (burstTime == 0) {
                            currentProcess.setFinishTime(clock);
                            readyQueue.remove(currentProcess);
                            terminatedProcesses++;
                            break;
                        }
                        else {
                            currentProcess.setRemainingBurstTime(burstTime);
                        }
                    }
                    else {
                        readyQueue.add(currentProcess);
                        readyQueue.remove(currentProcess);
                    }
                }
                processGantt.add(currentProcess.getProcessID()); //
                processStopTime.add(clock); //
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
        return processStopTime; //
    }
}