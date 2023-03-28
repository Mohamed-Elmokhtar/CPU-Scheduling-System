import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LaunchPage extends JFrame implements ActionListener{
    
    JComboBox<String> scheduleTypeBox;
    JTextField arrivalTimeField;
    JTextField burstTimeField;
    JTextField priorityField;
    JTextField timeQuantumField;
    JTextField dummyField;
    JButton submitButton;

    String[] arrivalTimeArray;
    String[] burstTimeArray;
    String[] priorityArray;

    int processNumber;

    LaunchPage() {
        this.setTitle("CPU Scheduling Launch Page");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        String[] scheduleTypeArray = {"Non Preemptive SJF", "Non Preemptive Priority", "Round Robin"};

        createDesignLabel();

        scheduleTypeBox = new JComboBox<>(scheduleTypeArray);
        scheduleTypeBox.setBounds(450, 305, 200, 30);
        scheduleTypeBox.addActionListener(this);

        arrivalTimeField = new JTextField();
        arrivalTimeField.setBounds(150, 230, 200, 30);
        arrivalTimeField.setText("e.g. ~> 0 1 2 3");
        
        arrivalTimeField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isDigit(c) || c== KeyEvent.VK_SPACE)) {
                    e.consume();
                }
            }

            // Below two methods are not needed but had to be kept in due to KeyListener
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        arrivalTimeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(arrivalTimeField.getText().equals("e.g. ~> 0 1 2 3")) {
                    arrivalTimeField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(arrivalTimeField.getText().equals("")) {
                    arrivalTimeField.setText("e.g. ~> 0 1 2 3");
                }
            }
        });

        burstTimeField = new JTextField();
        burstTimeField.setBounds(150, 380, 200, 30);
        burstTimeField.setText("e.g. ~> 1 2 3 4");

        burstTimeField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isDigit(c) || c== KeyEvent.VK_SPACE)) {
                    e.consume();
                }
            }

            // Below two methods are not needed but had to be kept in due to KeyListener
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        burstTimeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(burstTimeField.getText().equals("e.g. ~> 1 2 3 4")) {
                    burstTimeField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(burstTimeField.getText().equals("")) {
                    burstTimeField.setText("e.g. ~> 1 2 3 4");
                }
            }
        });

        priorityField = new JTextField();
        priorityField.setBounds(150, 530, 200, 30);
        priorityField.setText("e.g. ~> 1 2 3 4");

        priorityField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isDigit(c) || c== KeyEvent.VK_SPACE)) {
                    e.consume();
                }
            }

            // Below two methods are not needed but had to be kept in due to KeyListener
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        priorityField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(priorityField.getText().equals("e.g. ~> 1 2 3 4")) {
                    priorityField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(priorityField.getText().equals("")) {
                    priorityField.setText("e.g. ~> 1 2 3 4");
                }
            }
        });
        priorityField.setVisible(false);

        timeQuantumField = new JTextField();
        timeQuantumField.setBounds(150, 530, 200, 30);
        timeQuantumField.setText("e.g. ~> 3");

        timeQuantumField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!(Character.isDigit(c))) {
                    e.consume();
                }
            }

            // Below two methods are not needed but had to be kept in due to KeyListener
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        timeQuantumField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(timeQuantumField.getText().equals("e.g. ~> 3")) {
                    timeQuantumField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(timeQuantumField.getText().equals("")) {
                    timeQuantumField.setText("e.g. ~> 3");
                }
            }
        });
        timeQuantumField.setVisible(false);

        dummyField = new JTextField();
        dummyField.setBounds(150, 530, 200, 30);
        dummyField.setEnabled(false);
        dummyField.setText("Not Applicable for Schedule Type");
        dummyField.setForeground(Color.RED);
        dummyField.setBackground(Color.BLACK);

        submitButton = new JButton("Calculate");
        submitButton.setBounds(500, 455, 100, 30);
        submitButton.addActionListener(this);
        submitButton.setHorizontalTextPosition(JButton.CENTER);
        
        this.add(scheduleTypeBox);
        this.add(arrivalTimeField);
        this.add(burstTimeField);
        this.add(priorityField);
        this.add(timeQuantumField);
        this.add(dummyField);
        this.add(submitButton);

        this.setSize(800,800);  
        this.setLayout(null);  
        this.setVisible(true);  
    }

    public void createDesignLabel() {
        JLabel cpuScheduling = new JLabel();
        cpuScheduling.setText("CPU Scheduling Algorithm");
        cpuScheduling.setFont(new Font("Calibri", Font.PLAIN, 30));
        cpuScheduling.setSize(1000,1000);
        cpuScheduling.setLocation(240, -450);
        cpuScheduling.setForeground(Color.BLACK);

        JLabel simulation = new JLabel();
        simulation.setText("SIMULATION");
        simulation.setFont(new Font("Calibri", Font.PLAIN, 62));
        simulation.setSize(1000,1000);
        simulation.setLocation(240, -405);
        simulation.setForeground(Color.BLACK);

        JLabel arrivalTimeLabel = new JLabel();
        arrivalTimeLabel.setText("Arrival Time");
        arrivalTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        arrivalTimeLabel.setSize(1000,1000);
        arrivalTimeLabel.setLocation(173, -290);
        arrivalTimeLabel.setForeground(Color.BLACK);

        JLabel burstTimeLabel = new JLabel();
        burstTimeLabel.setText("Burst Time");
        burstTimeLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        burstTimeLabel.setSize(1000,1000);
        burstTimeLabel.setLocation(180, -140);
        burstTimeLabel.setForeground(Color.BLACK);

        JLabel priorityOrQuantumLabel = new JLabel();
        priorityOrQuantumLabel.setText("Priority / Quantum");
        priorityOrQuantumLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        priorityOrQuantumLabel.setSize(1000,1000);
        priorityOrQuantumLabel.setLocation(135, 13);
        priorityOrQuantumLabel.setForeground(Color.BLACK);

        JLabel mokhtarName = new JLabel();
        mokhtarName.setText("Mokhtar");
        mokhtarName.setFont(new Font("Calibri", Font.PLAIN, 30));
        mokhtarName.setSize(1000,1000);
        mokhtarName.setLocation(100, 180);
        mokhtarName.setForeground(Color.BLACK);

        JLabel mokhtarID = new JLabel();
        mokhtarID.setText("1191102491");
        mokhtarID.setFont(new Font("Calibri", Font.PLAIN, 30));
        mokhtarID.setSize(1000,1000);
        mokhtarID.setLocation(80, 215);
        mokhtarID.setForeground(Color.BLACK);

        JLabel haqeemName = new JLabel();
        haqeemName.setText("Haqeem");
        haqeemName.setFont(new Font("Calibri", Font.PLAIN, 30));
        haqeemName.setSize(1000,1000);
        haqeemName.setLocation(335, 180);
        haqeemName.setForeground(Color.BLACK);

        JLabel haqeemID = new JLabel();
        haqeemID.setText("1191102618");
        haqeemID.setFont(new Font("Calibri", Font.PLAIN, 30));
        haqeemID.setSize(1000,1000);
        haqeemID.setLocation(310, 215);
        haqeemID.setForeground(Color.BLACK);

        JLabel wathiqName = new JLabel();
        wathiqName.setText("Wathiq");
        wathiqName.setFont(new Font("Calibri", Font.PLAIN, 30));
        wathiqName.setSize(1000,1000);
        wathiqName.setLocation(565, 180);
        wathiqName.setForeground(Color.BLACK);

        JLabel wathiqID = new JLabel();
        wathiqID.setText("1191102425");
        wathiqID.setFont(new Font("Calibri", Font.PLAIN, 30));
        wathiqID.setSize(1000,1000);
        wathiqID.setLocation(540, 215);
        wathiqID.setForeground(Color.BLACK);
    
        this.add(cpuScheduling);
        this.add(simulation);

        this.add(arrivalTimeLabel);
        this.add(burstTimeLabel);
        this.add(priorityOrQuantumLabel);

        this.add(mokhtarName);
        this.add(mokhtarID);
        this.add(haqeemName);
        this.add(haqeemID);
        this.add(wathiqName);
        this.add(wathiqID);
    }

    public void createTextField(String scheduleType) {
        if(scheduleType.equals("Non Preemptive Priority")) {          
            priorityField.setVisible(true);
            timeQuantumField.setVisible(false);

            this.invalidate();
            this.validate();
            this.repaint();
        }
        else if (scheduleType.equals("Round Robin")) {
            priorityField.setVisible(false);
            timeQuantumField.setVisible(true);

            this.invalidate();
            this.validate();
            this.repaint();
        }
        else {
            timeQuantumField.setVisible(false);
            priorityField.setVisible(false);

            this.invalidate();
            this.validate();
            this.repaint();
        }
    }

    public ArrayList<Process> createList(boolean priorityFlag, String arrivalInput) {
        ArrayList<Process> wholeList = new ArrayList<>();

        Process testProcess;

        String checkBurstInput = burstTimeField.getText();
        checkBurstInput = checkBurstInput.replaceFirst("^\\s*", "");
        String checkPriority;
        int arrivalTime, burstTime, priority;
        String processID;

        String[] arrivalTimeArray = arrivalInput.split(" ");
        String[] burstTimeArray = checkBurstInput.split(" ");
        String[] priorityArray;

        if(priorityFlag) {
            checkPriority = priorityField.getText();
            checkPriority = checkPriority.replaceFirst("^\\s*", "");
            priorityArray = checkPriority.split(" ");
        }
        else {
            priorityArray = new String[processNumber];
        }

        for(int i = 0; i < processNumber; i++) {
            arrivalTime = Integer.parseInt(arrivalTimeArray[i]);
            burstTime = Integer.parseInt(burstTimeArray[i]);
            processID = "P" + i;

            if (priorityFlag) {
                priority = Integer.parseInt(priorityArray[i]);
                testProcess = new Process(arrivalTime, burstTime, processID, priority);
            }

            else {
                testProcess = new Process(arrivalTime, burstTime, processID);
            }
            wholeList.add(testProcess);
        }

        Arrays.fill(arrivalTimeArray, null);
        Arrays.fill(burstTimeArray, null);
        Arrays.fill(priorityArray, null);

        return wholeList;
    }

    public boolean checkInput(String scheduleType) {
        boolean validInput = false;

        if(arrivalTimeField.getText().equals("e.g. ~> 0 1 2 3") || burstTimeField.getText().equals("e.g. ~> 1 2 3 4")) {
            JOptionPane.showMessageDialog(this, "Missing Input(s))", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        arrivalTimeArray = arrivalTimeField.getText().split(" ");
        burstTimeArray = burstTimeField.getText().split(" ");

        if(arrivalTimeArray.length == burstTimeArray.length) {
            validInput = true;
        }
        else {
            JOptionPane.showMessageDialog(this, "Arrival Time do not match Burst Time given\nOR\nThere is a Whitespace at the front of your Input", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(scheduleType.equals("Non Preemptive Priority")) {
            priorityArray = priorityField.getText().split(" ");

            if(arrivalTimeArray.length == priorityArray.length) {
                validInput = true;
            }
            else {
                JOptionPane.showMessageDialog(this, "List of Priority's given do not match number of Processes given", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        else if (scheduleType.equals("Round Robin")) {
            if(!(timeQuantumField.getText().equals("e.g. ~> 3"))) {
                validInput = true;
            }
            else {
                JOptionPane.showMessageDialog(this, "No Time Quantum given", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if(!(arrivalTimeArray.length < 3 || arrivalTimeArray.length > 10)) {
            validInput = true;
        }
        else {
            JOptionPane.showMessageDialog(this, "You may only input 3 to 10 Processes\nas per assignment guidelines", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
        }

        return validInput;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == scheduleTypeBox){
            createTextField((String) scheduleTypeBox.getSelectedItem());
        }

        if(e.getSource() == submitButton) {
            ArrayList<Process> wholeList;
            ArrayList<Integer> processStopTime;
            ArrayList<String> ganttProcess;

            String checkArrivalInput = arrivalTimeField.getText();
            checkArrivalInput = checkArrivalInput.replaceFirst("^\\s*", "");
            processNumber = checkArrivalInput.split(" ").length;

            boolean validInput = checkInput((String) scheduleTypeBox.getSelectedItem());

            if (validInput) {
                if (scheduleTypeBox.getSelectedItem().equals("Non Preemptive Priority")) {
                    wholeList = createList(true, checkArrivalInput);
                    NonPreemptivePriority scheduleNPP =  new NonPreemptivePriority(wholeList);
                    processStopTime = scheduleNPP.executeProcesses();
                    ganttProcess = scheduleNPP.getProcessGantt();
                }

                else if (scheduleTypeBox.getSelectedItem().equals("Round Robin")) {
                    wholeList = createList(false, checkArrivalInput);
                    int timeQuantum = Integer.parseInt(timeQuantumField.getText());
                    RoundRobin scheduleRR = new RoundRobin(timeQuantum, wholeList);
                    processStopTime = scheduleRR.executeProcesses();
                    ganttProcess = scheduleRR.getProcessGantt();
                }

                else {
                    wholeList = createList(false, checkArrivalInput);
                    NonPreemptiveSJF scheduleSJF = new NonPreemptiveSJF(wholeList);
                    processStopTime = scheduleSJF.executeProcess();
                    ganttProcess = scheduleSJF.getProcessGantt();
                }
                new TableAndGanttPage(wholeList, processStopTime, ganttProcess, processNumber);
            }
        }
    }
}