//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/*
 *file name = Main
 * Lab 01
 * date 25/3/2025
 * @author RENUJAN J. 2022/E/065
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Student welcome = new Student(); // create a object for student class using welcome the student

        Scanner sc = new Scanner(System.in); // create a object for scanner

        // get the input from user
        System.out.print("Enter the Name: ");
        String name = sc.nextLine();
        System.out.print("Enter the Address: ");
        String address = sc.nextLine();
        System.out.print("Enter the Id number: ");
        int id = sc.nextInt();
        System.out.print("Enter the age: ");
        int age = sc.nextInt();
        System.out.print("Enter the Grade: ");
        int grade = sc.nextInt();
        System.out.print("Enter the Gender(M/F): ");
        char gender = sc.next().charAt(0);

        // create the obgect for student class and assign the attributes
        Student student1 = new Student(id, name, age, grade, address,gender );

        student1.displayStudentDetails(); // display the student details
        student1.categorizeStudent();

    }
}