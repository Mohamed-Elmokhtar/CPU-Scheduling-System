import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TableAndGanttPage extends JFrame implements ActionListener{
    private JTable dataTable;
    private ArrayList<Process> wholeList;
    private ArrayList<Integer> processStopTime;
    private ArrayList<String> ganttProcess;
    private int processNumber;
    JPanel tablePanel;
    JPanel ganttPanel;
    JButton backButton;

    TableAndGanttPage(ArrayList<Process> wholeList, ArrayList<Integer> processStopTime, ArrayList<String> ganttProcess, int processNumber) {
        this.setTitle("Non Preemptive Priority - Table and Gantt Chart Page");
        this.setResizable(false);

        setWholeList(wholeList);
        setProcessNumber(processNumber);
        setGanttProcess(ganttProcess);
        setProcessStopTime(processStopTime);

        createTable();
        createTableLabel();
        createGanttChart();
        createDesignLabel();

        this.add(tablePanel);
        this.setSize(1000,650);  
        this.setLayout(null);  
        this.setVisible(true);  
    }

    public void setWholeList(ArrayList<Process> wholeList) {
        this.wholeList = wholeList;
    }

    public void setProcessStopTime(ArrayList<Integer> processStopTime) {
        this.processStopTime = processStopTime;
    }

    public void setGanttProcess(ArrayList<String> ganttProcess) {
        this.ganttProcess = ganttProcess;
    }
    
    public void setProcessNumber(int processNumber) {
        this.processNumber = processNumber;
    }
    
    public ArrayList<Process> getWholeList() {
        return wholeList;
    }

    public ArrayList<Integer> getProcessStopTime() {
        return processStopTime;
    }

    public ArrayList<String> getGanttProcess() {
        return ganttProcess;
    }

    public int getProcessNumber() {
        return processNumber;
    }

    public Object[][] arrangeData() {
        Object[][] arrangedData = new Object[processNumber][6];

        for (int i = 0; i < processNumber; i++) {
            wholeList.get(i).setTurnaroundTime();
            wholeList.get(i).setWaitingTime();
            arrangedData[i][0] = wholeList.get(i).getProcessID();
            arrangedData[i][1] = wholeList.get(i).getArrivalTime();
            arrangedData[i][2] = wholeList.get(i).getBurstTime();
            arrangedData[i][3] = wholeList.get(i).getFinishTime();
            arrangedData[i][4] = wholeList.get(i).getTurnaroundTime();
            arrangedData[i][5] = wholeList.get(i).getWaitingTime();
        }

        return arrangedData;
    }

    public void createTable() {
        tablePanel = new JPanel();
        tablePanel.setBounds(90, 55, 800, 300);
        tablePanel.setLayout(new FlowLayout());

        String[] columnNames = {
            "Process", "Arrival Time", "Burst Time", "Finish Time", "Turn Around Time", "Waiting Time"
        };

        Object[][] arrangedData = arrangeData();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        dataTable = new JTable(arrangedData, columnNames) {
            public boolean isCellEditable (int row, int column) {
                return false;
            }
        };
        dataTable.setRowHeight(30);
        dataTable.setFillsViewportHeight(true);
        dataTable.setDefaultRenderer(Object.class, centerRenderer);

        if(processNumber < 6) {
            int rowHeight = 30 * processNumber;
            dataTable.setPreferredScrollableViewportSize(new Dimension(800, rowHeight));
        }
        else {
            dataTable.setPreferredScrollableViewportSize(new Dimension(800, 150));
        }

        JScrollPane scrollPane = new JScrollPane(dataTable);
        tablePanel.add(scrollPane);
    }

    public void createTableLabel() {
        JLabel totalTurnAroundTimeLabel = new JLabel();
        JLabel totalWaitingTimeLabel = new JLabel();
        JLabel avgTATimeFluffLabel = new JLabel();
        JLabel avgTATimeLabel = new JLabel();
        JLabel avgWaitTimeFluffLabel = new JLabel();
        JLabel avgWaitTimeLabel = new JLabel();
        int totalTurnAroundTime = 0;
        int totalWaitingTime = 0;
        double avgTurnAroundTime = 0;
        double avgWaitingTime = 0;

        DecimalFormat df = new DecimalFormat("######.###");
        
        for (int i = 0; i < processNumber; i++) {
            totalTurnAroundTime += wholeList.get(i).getTurnaroundTime();
            totalWaitingTime += wholeList.get(i).getWaitingTime();
        }

        avgTurnAroundTime = (double) totalTurnAroundTime / (double) processNumber;
        avgWaitingTime = (double) totalWaitingTime / (double) processNumber;

        totalTurnAroundTimeLabel.setText("Total Turn Around Time  =  " + String.valueOf(totalTurnAroundTime) + " units");

        totalWaitingTimeLabel.setText("Total Waiting Time  =  " + String.valueOf(totalWaitingTime)  + " units");

        avgTATimeFluffLabel.setText("Average Turn Around Time  =  " + String.valueOf(totalTurnAroundTime) + " / " + String.valueOf(processNumber));
        avgTATimeLabel.setText("=  " + String.valueOf(df.format(avgTurnAroundTime) + " units"));

        avgWaitTimeFluffLabel.setText("Average Waiting Time  =  " + String.valueOf(totalWaitingTime) + " / " + String.valueOf(processNumber));
        avgWaitTimeLabel.setText("=  " + String.valueOf(df.format(avgWaitingTime) + " units"));
        
        totalTurnAroundTimeLabel.setLocation(113, -170 + 35);
        totalTurnAroundTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        totalTurnAroundTimeLabel.setSize(800, 800);

        avgTATimeFluffLabel.setLocation(88, -130 + 35);
        avgTATimeFluffLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        avgTATimeFluffLabel.setSize(800, 800);

        avgTATimeLabel.setLocation(320, -90 + 35);
        avgTATimeLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        avgTATimeLabel.setSize(800, 800);

        totalWaitingTimeLabel.setLocation(585, -170 + 35);
        totalWaitingTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        totalWaitingTimeLabel.setSize(800, 800);

        avgWaitTimeFluffLabel.setLocation(560, -130 + 35);
        avgWaitTimeFluffLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        avgWaitTimeFluffLabel.setSize(800, 800);

        avgWaitTimeLabel.setLocation(750, -90 + 35);
        avgWaitTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        avgWaitTimeLabel.setSize(800, 800);

        this.add(totalTurnAroundTimeLabel);
        this.add(totalWaitingTimeLabel);
        this.add(avgTATimeFluffLabel);
        this.add(avgTATimeLabel);
        this.add(avgWaitTimeFluffLabel);
        this.add(avgWaitTimeLabel);
    }

    public void createGanttChart() {
        int startCoordinate = 18;
        int clockDifference = 0;
        int trueTimeLength = 0;
        int listSize = processStopTime.size();
        double boxLength = 0.0;
        int roundOffBoxLength = 0;
        int colorSelect = 0;
        JLabel processLabel;
        JLabel clockLabel;
        JLabel finalIndexLabel;

        for(int i = 0; i < listSize - 1; i++) {
            ganttPanel = new JPanel();
            ganttPanel.setLayout(new GridBagLayout());
            processLabel = new JLabel();
            clockLabel = new JLabel();
            finalIndexLabel = new JLabel();

            clockDifference = processStopTime.get(i+1) - processStopTime.get(i);
            processLabel.setText(ganttProcess.get(i));

            clockLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
            clockLabel.setSize(1000,800);

            if (i == (listSize - 2)) {
                finalIndexLabel.setFont(new Font("Calibri", Font.PLAIN, 13));
                finalIndexLabel.setSize(1000,800);
                finalIndexLabel.setText(Integer.toString(processStopTime.get(ganttProcess.size())));
                finalIndexLabel.setLocation(960, 120);

                this.add(finalIndexLabel);
            }

            clockLabel.setText(Integer.toString(processStopTime.get(i)));
            clockLabel.setLocation(startCoordinate, 120);

            trueTimeLength = processStopTime.get(listSize-1) - processStopTime.get(0);
            boxLength = ((double) clockDifference / (double) trueTimeLength) * 950;
            roundOffBoxLength = (int) boxLength;

            processLabel.setVerticalAlignment(JLabel.CENTER);
            processLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
            processLabel.setSize(1000,1000);
            processLabel.setForeground(Color.WHITE);

            ganttPanel.setBounds(startCoordinate, 405, roundOffBoxLength, 100);
            startCoordinate = roundOffBoxLength + startCoordinate;
            ganttPanel.add(processLabel);
            
            if (colorSelect == 0) {
                ganttPanel.setBackground(Color.decode("#6e6e6e"));
                colorSelect++;
            }
            else if (colorSelect == 1) {
                ganttPanel.setBackground(Color.decode("#4d4d4d"));
                colorSelect++;
            }
            else if (colorSelect == 2) {
                ganttPanel.setBackground(Color.decode("#383838"));
                colorSelect = 0;
            }

            this.add(clockLabel);
            this.add(ganttPanel);
        }
    }

    public void createDesignLabel() {
        JLabel ganttChartLabel = new JLabel();
        ganttChartLabel.setText("Gantt Chart");
        ganttChartLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        ganttChartLabel.setSize(400,400);
        ganttChartLabel.setLocation(420, 180);
        ganttChartLabel.setForeground(Color.BLACK);

        JLabel tableLabel = new JLabel();
        tableLabel.setText("CPU Schedule Table");
        tableLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        tableLabel.setSize(400,400);
        tableLabel.setLocation(370, -160);
        tableLabel.setForeground(Color.BLACK);

        backButton = new JButton("Close");
        backButton.setBounds(440, 555, 100, 30);
        backButton.addActionListener(this);
        backButton.setHorizontalTextPosition(JButton.CENTER);

        this.add(ganttChartLabel);
        this.add(tableLabel);
        this.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            this.dispose();
        }
        
    }
}