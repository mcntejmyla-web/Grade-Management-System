import java.util.Scanner;

public class GradeManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] studentNames = new String[5];
        int[][] marks = new int[5][5];

        int studentCount = 0;
        int choice;

        do {

            System.out.println("\n===== GRADE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Report");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    if (studentCount >= 5) {
                        System.out.println("Maximum students reached!");
                        break;
                    }

                    System.out.print("Enter Student Name: ");
                    studentNames[studentCount] = sc.nextLine();

                    System.out.println("Enter 5 Subject Marks:");

                    for (int i = 0; i < 5; i++) {

                        while (true) {
                            try {
                                System.out.print("Subject " + (i + 1) + ": ");
                                int mark = sc.nextInt();

                                if (mark >= 0 && mark <= 100) {
                                    marks[studentCount][i] = mark;
                                    break;
                                } else {
                                    System.out.println("Marks must be between 0 and 100.");
                                }

                            } catch (Exception e) {
                                System.out.println("Invalid Input! Enter numbers only.");
                                sc.nextLine();
                            }
                        }
                    }

                    studentCount++;
                    System.out.println("Student Added Successfully!");
                    break;

                case 2:

                    if (studentCount == 0) {
                        System.out.println("No Student Records Found!");
                        break;
                    }

                    System.out.println("\n===== STUDENT REPORT =====");

                    for (int i = 0; i < studentCount; i++) {

                        int total = 0;
                        int highest = marks[i][0];
                        int lowest = marks[i][0];

                        for (int j = 0; j < 5; j++) {

                            total += marks[i][j];

                            if (marks[i][j] > highest) {
                                highest = marks[i][j];
                            }

                            if (marks[i][j] < lowest) {
                                lowest = marks[i][j];
                            }
                        }

                        double average = (double) total / 5;

                        String grade;

                        if (average >= 90) {
                            grade = "A";
                        } else if (average >= 80) {
                            grade = "B";
                        } else if (average >= 70) {
                            grade = "C";
                        } else if (average >= 60) {
                            grade = "D";
                        } else {
                            grade = "F";
                        }

                        System.out.println("\nStudent Name : " + studentNames[i]);
                        System.out.println("Total Marks  : " + total);
                        System.out.println("Average      : " + average);
                        System.out.println("Highest Mark : " + highest);
                        System.out.println("Lowest Mark  : " + lowest);
                        System.out.println("Grade        : " + grade);
                    }

                    break;

                case 3:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 3);

        sc.close();
    }
}
