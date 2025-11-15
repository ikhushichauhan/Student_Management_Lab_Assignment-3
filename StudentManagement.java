import java.util.*;

// ---------------- CUSTOM EXCEPTION ----------------
class StudentNotFoundException extends Exception {
    StudentNotFoundException(String msg) {
        super(msg);
    }
}

// ---------------- LOADER THREAD CLASS ----------------
class Loader implements Runnable {
    public void run() {
        try {
            System.out.println("Loading.....");
            Thread.sleep(2000);        // 2 seconds delay for simulation
        } catch (Exception e) {
            System.out.println("Error while loading: " + e.getMessage());
        }
    }
}

// ---------------- INTERFACE ----------------
interface RecordActions {
    void addStudent();
    void showStudent();
}

// ---------------- STUDENT MANAGER CLASS ----------------
class StudentManager implements RecordActions {

    Integer rollNo;      // Wrapper class
    String name;
    String email;
    String course;
    Double marks;        // Wrapper class

    Scanner sc = new Scanner(System.in);

    // Add student details
    @Override
    public void addStudent() {
        try {
            System.out.print("Enter Roll No (Integer): ");
            rollNo = Integer.valueOf(sc.nextLine()); // Autoboxing

            System.out.print("Enter Name: ");
            name = sc.nextLine();
            if (name.isEmpty()) throw new Exception("Name cannot be empty");

            System.out.print("Enter Email: ");
            email = sc.nextLine();
            if (email.isEmpty()) throw new Exception("Email cannot be empty");

            System.out.print("Enter Course: ");
            course = sc.nextLine();
            if (course.isEmpty()) throw new Exception("Course cannot be empty");

            System.out.print("Enter Marks: ");
            marks = Double.valueOf(sc.nextLine()); // Autoboxing

            if (marks < 0 || marks > 100)
                throw new Exception("Marks must be between 0 and 100");

            // ----------- MULTITHREADING -----------
            Thread t = new Thread(new Loader());
            t.start();
            t.join(); // wait until loading completes

            System.out.println("Student added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric value! Please enter correct data.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // finally block example
            // System.out.println("addStudent() execution finished.");
        }
    }

    // Show student details
    @Override
    public void showStudent() {
        try {
            if (rollNo == null)
                throw new StudentNotFoundException("No student record found!");

            System.out.println("\n--- Student Details ---");
            System.out.println("Roll No: " + rollNo);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Course: " + course);
            System.out.println("Marks: " + marks);

            // Grade logic
            char grade;
            if (marks >= 90) grade = 'A';
            else if (marks >= 75) grade = 'B';
            else if (marks >= 50) grade = 'C';
            else grade = 'D';

            System.out.println("Grade: " + grade);

        } catch (StudentNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            System.out.println("\nProgram execution completed.");
        }
    }
}

// ---------------- MAIN CLASS ----------------
public class StudentManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManager sm = new StudentManager();

        while (true) {
            System.out.println("\n==== Student Management System ====");
            System.out.println("1. Add Student");
            System.out.println("2. Show Student Details");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> sm.addStudent();
                case "2" -> sm.showStudent();x
                case "3" -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
