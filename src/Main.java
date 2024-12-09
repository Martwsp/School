import menu.MenuLogic;
import school.School;
import school.SchoolClass;
import school.Student;
import school.Teacher;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuLogic menu = new MenuLogic();
        if (createNewSchoolQ())
            menu.logicLoop(testSchool());
        else menu.logicLoop(new School());
    }

    public static boolean createNewSchoolQ() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Load test school?  Y/N");

        return Optional.of(scanner.next())
                .map(String::toUpperCase)
                .filter(answer -> answer.matches("Y"))
                .isPresent();
    }

    public static School testSchool() {
        School school = new School();
        Teacher jones = new Teacher("Timothy Jones");
        jones.addSubject("Physics");
        jones.addSubject("Maths");
        school.addTeacher(jones);
        SchoolClass schoolClass1b8 = new SchoolClass("1b8");
        jones.setClass(schoolClass1b8);
        Student student1 = new Student("Barbara");
        student1.addSubject("Physics", 3);
        student1.addSubject("Maths", 1);
        student1.addSubject("Geography", 2);
        schoolClass1b8.addStudent(student1);
        Student student2 = new Student("Tom");
        student2.addSubject("English", 5);
        student2.addSubject("Maths", 4);
        student2.addSubject("Geography", 5);
        schoolClass1b8.addStudent(student2);
        Student student3 = new Student("Michael");
        student3.addSubject("Physics", 1);
        student3.addSubject("Maths", 2);
        student3.addSubject("Music", 1);
        schoolClass1b8.addStudent(student3);
        Teacher ramany = new Teacher("Shahid Ramany");
        ramany.addSubject("English");
        ramany.addSubject("Music");
        school.addTeacher(ramany);
        SchoolClass schoolClass1a8 = new SchoolClass("1a8");
        ramany.setClass(schoolClass1a8);
        Student student4 = new Student("Erica");
        student4.addSubject("Physics", 3);
        student4.addSubject("English", 1);
        student4.addSubject("Geography", 2);
        schoolClass1a8.addStudent(student4);
        Student student5 = new Student("Evelin");
        student5.addSubject("Physics", 5);
        student5.addSubject("Maths", 4);
        student5.addSubject("Geography", 5);
        schoolClass1a8.addStudent(student5);
        Student student6 = new Student("Jacob");
        student6.addSubject("Physics", 1);
        student6.addSubject("Maths", 2);
        student6.addSubject("Music", 1);
        schoolClass1a8.addStudent(student6);
        Teacher richard = new Teacher("Richard Morgan");
        richard.addSubject("Geography");
        school.addTeacher(richard);
        SchoolClass schoolClass1a6 = new SchoolClass("1a6");
        richard.setClass(schoolClass1a6);
        Student student7 = new Student("Gabriel");
        student7.addSubject("Physics", 2);
        student7.addSubject("English", 1);
        student7.addSubject("Geography", 2);
        student7.addSubject("Maths", 1);
        student7.addSubject("Music", 3);
        schoolClass1a6.addStudent(student7);
        Student student8 = new Student("Edward");
        student8.addSubject("Physics", 3);
        student8.addSubject("English", 4);
        student8.addSubject("Geography", 3);
        student8.addSubject("Maths", 2);
        student8.addSubject("Music", 1);
        schoolClass1a6.addStudent(student8);
        Student student9 = new Student("Bob");
        student9.addSubject("Physics", 1);
        student9.addSubject("Maths", 2);
        student9.addSubject("Music", 1);
        student9.addSubject("PE", 1);
        student9.addSubject("Geography", 2);
        student9.addSubject("English", 1);
        schoolClass1a6.addStudent(student9);
        Teacher barbara = new Teacher("Barbara Shepherd");
        barbara.addSubject("PE");
        Teacher tom = new Teacher("Tom Baker");
        tom.addSubject("Chemistry");
        return school;
    }
}