// 2022/E/065

public class StudentMemoryDemo {
    public static void main(String[] arg){
        Student st1 = new Student();
        Student st2 = new Student();
        Student st3 = new Student();
        Student st4 = new Student();
        Student st5 = new Student();
        Student st6 = new Student();
        Student st7 = new Student();
        Student st8 = new Student();

        // By assigning a null
        st1 = null;
        st2 = null;
        st6 = null;

        System.gc(); // request garbage collection.
    }
}
