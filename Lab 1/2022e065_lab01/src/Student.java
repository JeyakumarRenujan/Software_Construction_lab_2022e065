// 2022/E/065

class Student{ // Student class
    // create Instances variable
    int id;
    String name;
    int age;
    int grade;
    String address;
    char gender;

    // create static variable
    static int totalStudents;

    Student(){ // create a constructor
        System.out.println("Object is created");
    }
    protected void finalize(){ // Finalize method for garbage collection
        System.out.println("Object is removed");
    }

    Student(int id, String name, int age, int grade, String address, char gender){ // Constructor to assign the attribute
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.address = address;
        this.gender = gender;
    }

    void displayStudentDetails(){ // method to display the student details
        System.out.println(".......Student details..........");
        System.out.println("Student id: "+id +"\nStudent Name: "+name +"\nStudent Age: "+age +"\nStudent grade: "+grade
                +"\nStudent Address: "+address +"\nStudent Gender: "+ gender );
    }

    void categorizeStudent(){ // method for categorize Studente
        String categarize;
        if (grade>=75 && grade<=100)
            categarize = "Excellent";
        else if (grade>=65 && grade<75)
            categarize = "Good";
        else if (grade>=55 && grade<65)
            categarize = "Average";
        else
            categarize = "“Needs Improvement";

        switch (categarize){
            case "Excellent": System.out.println("\nExcellent Student"); break;
            case "Good": System.out.println("\nGood Student"); break;
            case "Average": System.out.println("\nAverage Student"); break;
            default: System.out.println("\nNeeds Improvement");
        }
    }

    void updateAge(int newAge){  // new method for update age
        int tempAge = newAge; // Local variable
        this.age = newAge;
    }

    void incrementTotalStudents(){
        totalStudents++;
    }
    void namespaceConflictExample(){
        System.out.println("Local ID: " + id);
        System.out.println("Instance ID: " + this.id);
    }

}