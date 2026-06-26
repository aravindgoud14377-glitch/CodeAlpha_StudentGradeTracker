import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentGradeTrackerUI {

    static ArrayList<Student> students = new ArrayList<>();
    static JTextArea reportArea = new JTextArea(20, 40);

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Grade Tracker");
        frame.setSize(550, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(new Color(30, 30, 30));

        JLabel nameLabel = new JLabel("Student Name");
        nameLabel.setForeground(Color.WHITE);
        JTextField nameField = new JTextField(20);

        JLabel marksLabel = new JLabel("Marks");
        marksLabel.setForeground(Color.WHITE);
        JTextField marksField = new JTextField(20);

        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("Generate Report");
        JButton clearButton = new JButton("Clear All");
        JButton saveButton = new JButton("Save Report");

        reportArea.setEditable(false);
        reportArea.setBackground(new Color(40, 40, 40));
        reportArea.setForeground(Color.GREEN);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int marks = Integer.parseInt(marksField.getText());

                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(frame, "Marks must be 0–100");
                    return;
                }

                students.add(new Student(name, marks));
                nameField.setText("");
                marksField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input");
            }
        });

        reportButton.addActionListener(e -> generateReport());
        clearButton.addActionListener(e -> {
            students.clear();
            reportArea.setText("");
        });
        saveButton.addActionListener(e -> saveReport());

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(marksLabel);
        frame.add(marksField);
        frame.add(addButton);
        frame.add(reportButton);
        frame.add(clearButton);
        frame.add(saveButton);
        frame.add(new JScrollPane(reportArea));

        frame.setVisible(true);
    }

    static String getGrade(int marks) {
        if (marks >= 90)
            return "A";
        if (marks >= 75)
            return "B";
        if (marks >= 60)
            return "C";
        if (marks >= 40)
            return "D";
        return "F";
    }

    static void generateReport() {
        if (students.isEmpty()) {
            reportArea.setText("No Data");
            return;
        }

        StringBuilder output = new StringBuilder();
        int total = 0;
        int highest = students.get(0).marks;
        int lowest = students.get(0).marks;

        output.append("===== STUDENT REPORT =====\n\n");

        for (Student s : students) {
            output.append("Name: ").append(s.name)
                    .append("\nMarks: ").append(s.marks)
                    .append("\nGrade: ").append(getGrade(s.marks))
                    .append("\n------------------\n");

            total += s.marks;
            highest = Math.max(highest, s.marks);
            lowest = Math.min(lowest, s.marks);
        }

        double average = (double) total / students.size();

        output.append("\nTotal Students: ").append(students.size());
        output.append("\nTotal Marks: ").append(total);
        output.append("\nAverage: ").append(String.format("%.2f", average));
        output.append("\nHighest: ").append(highest);
        output.append("\nLowest: ").append(lowest);

        reportArea.setText(output.toString());
    }

    static void saveReport() {
        try {
            FileWriter writer = new FileWriter("students_report.txt");
            writer.write(reportArea.getText());
            writer.close();
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Save Failed");
        }
    }
}
