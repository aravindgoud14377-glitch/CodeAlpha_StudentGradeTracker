import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

class StudentGradeTracker {

    ArrayList<Student> students = new ArrayList<>();

    public void addStudent(
            String name,
            int marks) {

        students.add(
                new Student(
                        name,
                        marks));

        saveToFile(
                name,
                marks);
    }

    private void saveToFile(
            String name,
            int marks) {

        try {

            FileWriter writer = new FileWriter(
                    "students.txt",
                    true);

            writer.write(
                    name +
                            " - " +
                            marks +
                            "\n");

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "File Error");
        }
    }

    public void generateReport() {

        if (students.isEmpty()) {

            System.out.println(
                    "No data");

            return;
        }

        int total = 0;

        int highest = students.get(0).marks;

        int lowest = students.get(0).marks;

        System.out.println(
                "\n===== REPORT =====");

        for (Student s : students) {

            System.out.println(
                    s.name +
                            " → " +
                            s.marks);

            total += s.marks;

            highest = Math.max(
                    highest,
                    s.marks);

            lowest = Math.min(
                    lowest,
                    s.marks);
        }

        double avg = (double) total /
                students.size();

        System.out.println(
                "\nAverage: " +
                        avg);

        System.out.println(
                "Highest: " +
                        highest);

        System.out.println(
                "Lowest: " +
                        lowest);
    }
}