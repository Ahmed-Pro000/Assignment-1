import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfStudents = readPositiveInt(
                scanner,
                "Enter the number of students to register: "
        );
        Student[] students = new Student[numberOfStudents];

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("\nRegister student " + (i + 1) + ":");

            int rollNumber = readPositiveInt(scanner, "Roll number: ");
            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            char gender = readGender(scanner);

            students[i] = new Student(rollNumber, name, gender);
        }

        try (FileWriter writer = new FileWriter("students.txt")) {
            for (Student student : students) {
                writer.write(
                        "Roll number: " + student.getRollNumber()
                        + ", Name: " + student.getName()
                        + ", Gender: " + student.getGender()
                        + System.lineSeparator()
                );
            }
        } catch (IOException exception) {
            System.out.println("Could not save students.txt: " + exception.getMessage());
        }

        System.out.println("\nRegistered students:");
        for (Student student : students) {
            System.out.println("--------------------");
            System.out.println("Roll number: " + student.getRollNumber());
            System.out.println("Name: " + student.getName());
            System.out.println("Gender: " + student.getGender());
        }

        scanner.close();
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                }
                System.out.println("Please enter a number greater than zero.");
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static char readGender(Scanner scanner) {
        while (true) {
            System.out.print("Gender (M/F): ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                char gender = Character.toUpperCase(input.charAt(0));
                if (gender == 'M' || gender == 'F') {
                    return gender;
                }
            }
            System.out.println("Please enter M or F.");
        }
    }
}

class Student {
    private final int rollNumber;
    private final String name;
    private final char gender;

    public Student(int rollNumber, String name, char gender) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.gender = gender;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }
}
