import java.util.Scanner;

public class GradeManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static String[] studentNames = new String[100];
    static int[][] marks = new int[100][5];
    static int studentCount = 0;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== GRADE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Find Top Performer");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            choice = getValidChoice();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewAllStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    findTopPerformer();
                    break;

                case 5:
                    generateReport();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 6);
    }

    public static void addStudent() {

        System.out.print("Enter Student Name: ");
        studentNames[studentCount] = sc.nextLine();

        for (int i = 0; i < 5; i++) {

            while (true) {
                try {
                    System.out.print("Enter Subject " + (i + 1) + " Marks: ");
                    int mark = sc.nextInt();

                    if (mark >= 0 && mark <= 100) {
                        marks[studentCount][i] = mark;
                        break;
                    } else {
                        System.out.println("Marks must be between 0 and 100.");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid Input!");
                    sc.nextLine();
                }
            }
        }

        sc.nextLine();

        studentCount++;

        System.out.println("Student Added Successfully!");
    }

    public static void viewAllStudents() {

        if (studentCount == 0) {
            System.out.println("No Students Found!");
            return;
        }

        System.out.println("\n===== ALL STUDENTS =====");

        for (int i = 0; i < studentCount; i++) {

            int total = calculateTotal(i);
            double average = calculateAverage(i);

            System.out.println("\nStudent Name : " + studentNames[i]);
            System.out.println("Total Marks  : " + total);
            System.out.println("Average      : " + average);
            System.out.println("Highest Mark : " + findHighest(i));
            System.out.println("Lowest Mark  : " + findLowest(i));
            System.out.println("Grade        : " + getGrade(average));
        }
    }

    public static void searchStudent() {

        System.out.print("Enter Student Name: ");
        String search = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < studentCount; i++) {

            if (studentNames[i].equalsIgnoreCase(search)) {

                found = true;

                System.out.println("\nStudent Found!");
                System.out.println("Name         : " + studentNames[i]);
                System.out.println("Total Marks  : " + calculateTotal(i));
                System.out.println("Average      : " + calculateAverage(i));
                System.out.println("Grade        : " + getGrade(calculateAverage(i)));
            }
        }

        if (!found) {
            System.out.println("Student Not Found!");
        }
    }

    public static void findTopPerformer() {

        if (studentCount == 0) {
            System.out.println("No Students Found!");
            return;
        }

        int topIndex = 0;

        for (int i = 1; i < studentCount; i++) {

            if (calculateAverage(i) > calculateAverage(topIndex)) {
                topIndex = i;
            }
        }

        System.out.println("\n===== TOP PERFORMER =====");
        System.out.println("Name    : " + studentNames[topIndex]);
        System.out.println("Average : " + calculateAverage(topIndex));
    }

    public static void generateReport() {

        if (studentCount == 0) {
            System.out.println("No Students Found!");
            return;
        }

        System.out.println("\n===== PERFORMANCE REPORT =====");

        System.out.println("Total Students : " + studentCount);

        double classAverage = 0;

        for (int i = 0; i < studentCount; i++) {
            classAverage += calculateAverage(i);
        }

        classAverage = classAverage / studentCount;

        System.out.println("Class Average  : " + classAverage);

        int a = 0, b = 0, c = 0, d = 0, f = 0;

        for (int i = 0; i < studentCount; i++) {

            double avg = calculateAverage(i);

            if (avg >= 90)
                a++;
            else if (avg >= 80)
                b++;
            else if (avg >= 70)
                c++;
            else if (avg >= 60)
                d++;
            else
                f++;
        }

        System.out.println("\nGrade Distribution");
        System.out.println("A Grade : " + a);
        System.out.println("B Grade : " + b);
        System.out.println("C Grade : " + c);
        System.out.println("D Grade : " + d);
        System.out.println("F Grade : " + f);
    }

    public static int calculateTotal(int index) {

        int total = 0;

        for (int i = 0; i < 5; i++) {
            total += marks[index][i];
        }

        return total;
    }

    public static double calculateAverage(int index) {
        return (double) calculateTotal(index) / 5;
    }

    public static int findHighest(int index) {

        int highest = marks[index][0];

        for (int i = 1; i < 5; i++) {

            if (marks[index][i] > highest) {
                highest = marks[index][i];
            }
        }

        return highest;
    }

    public static int findLowest(int index) {

        int lowest = marks[index][0];

        for (int i = 1; i < 5; i++) {

            if (marks[index][i] < lowest) {
                lowest = marks[index][i];
            }
        }

        return lowest;
    }

    public static String getGrade(double average) {

        if (average >= 90)
            return "A";
        else if (average >= 80)
            return "B";
        else if (average >= 70)
            return "C";
        else if (average >= 60)
            return "D";
        else
            return "F";
    }

    public static int getValidChoice() {

        while (true) {

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice >= 1 && choice <= 6) {
                    return choice;
                }

                System.out.print("Enter 1-6 only: ");

            } catch (Exception e) {

                System.out.print("Invalid Input! Enter number: ");
                sc.nextLine();
            }
        }
    }
}
