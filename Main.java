import java.util.Scanner;

public class Main {

    public static String calculateGrade(int marks) {

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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentGradeTracker tracker = new StudentGradeTracker();

        int choice;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. Generate Report");
            System.out.println("3. Exit");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print(
                            "Enter Name: ");

                    String name = sc.nextLine();

                    int marks;

                    do {

                        System.out.print(
                                "Enter Marks (0-100): ");

                        marks = sc.nextInt();

                    } while (marks < 0 ||
                            marks > 100);

                    sc.nextLine();

                    tracker.addStudent(
                            name,
                            marks);

                    System.out.println(
                            "Grade: " +
                                    calculateGrade(marks));

                    break;

                case 2:

                    tracker.generateReport();

                    break;

                case 3:

                    System.out.println(
                            "Program Closed");

                    break;

                default:

                    System.out.println(
                            "Invalid Choice");
            }

        } while (choice != 3);

        sc.close();
    }
}