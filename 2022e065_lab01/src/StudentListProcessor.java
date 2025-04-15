// Part 2

import java.util.ArrayList;
import java.util.List;

class StudentListProcessor {
    List<Student> students;

    StudentListProcessor() {
        students = new ArrayList<>();
    }

    void addStudent(Student student) {
        students.add(student);
    }

    // For loop to print all students
    void printAllStudents() {
        for (Student student : students) {
            student.displayStudentDetails();
        }
    }

    //  while loop to count and print students with grades above a certain threshold
    void countHighAchievers(double threshold) {
        int count = 0;
        int i = 0;
        while (i < students.size()) {
            if (students.get(i).grade >= threshold) {
                count++;
            }
            i++;
        }
        System.out.println("Number of students with grades above " + threshold + ": " + count);
    }

    // do-while loop to print student details until a particular condition is met
    void printUntilConditionMet(int maxStudents) {
        int i = 0;
        do {
            students.get(i).displayStudentDetails();
            i++;
        } while (i < students.size() && i < maxStudents);
    }
}
