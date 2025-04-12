import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;

public class Main {
    private static StudentManagerInterface manager = new StudentManager();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        idLabel.setBounds(30, 20, 100, 25);
        idField.setBounds(150, 20, 200, 25);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        nameLabel.setBounds(30, 60, 100, 25);
        nameField.setBounds(150, 60, 200, 25);

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        ageLabel.setBounds(30, 100, 100, 25);
        ageField.setBounds(150, 100, 200, 25);

        JLabel courseLabel = new JLabel("Course:");
        JTextField courseField = new JTextField();
        courseLabel.setBounds(30, 140, 100, 25);
        courseField.setBounds(150, 140, 200, 25);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(30, 180, 150, 30);

        JButton viewButton = new JButton("View All");
        viewButton.setBounds(200, 180, 150, 30);

        JButton deleteButton = new JButton("Delete by ID");
        deleteButton.setBounds(30, 220, 150, 30);

        JButton searchButton = new JButton("Search by ID");
        searchButton.setBounds(200, 220, 150, 30);

        JTextArea displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(30, 270, 420, 250);

        frame.add(idLabel);
        frame.add(idField);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(ageLabel);
        frame.add(ageField);
        frame.add(courseLabel);
        frame.add(courseField);
        frame.add(addButton);
        frame.add(viewButton);
        frame.add(deleteButton);
        frame.add(searchButton);
        frame.add(scrollPane);

        frame.setVisible(true);

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String course = courseField.getText();

                Student s = new Student(id, name, age, course);
                manager.addStudent(s);
                displayArea.setText("Student added successfully.\n");

                idField.setText(""); nameField.setText(""); ageField.setText(""); courseField.setText("");
            } catch (Exception ex) {
                displayArea.setText("Error: Invalid input.\n");
            }
        });

        viewButton.addActionListener(e -> {
            displayArea.setText("");
            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            PrintStream ps = new PrintStream(out);
            PrintStream oldOut = System.out;
            System.setOut(ps);
            manager.viewStudents();
            System.out.flush();
            System.setOut(oldOut);
            displayArea.setText(out.toString());
        });

        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
                PrintStream ps = new PrintStream(out);
                PrintStream oldOut = System.out;
                System.setOut(ps);
                manager.deleteStudent(id);
                System.out.flush();
                System.setOut(oldOut);
                displayArea.setText(out.toString());
            } catch (Exception ex) {
                displayArea.setText("Error: Invalid ID.\n");
            }
        });

        searchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
                PrintStream ps = new PrintStream(out);
                PrintStream oldOut = System.out;
                System.setOut(ps);
                manager.searchStudent(id);
                System.out.flush();
                System.setOut(oldOut);
                displayArea.setText(out.toString());
            } catch (Exception ex) {
                displayArea.setText("Error: Invalid ID.\n");
            }
        });
    }
}
