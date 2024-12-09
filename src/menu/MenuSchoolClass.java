package menu;

import school.School;
import school.SchoolClass;
import school.Student;

import java.util.List;
import java.util.Optional;

public class MenuSchoolClass extends MenuLogic {

    public void logicLoopSchoolClasses(School school) {
        System.out.println("Which class would do you want to view?");
        school.printClasses();
        System.out.println("Enter class name:");
        String answer = scannerName(school.getSchoolClasses().stream().map(SchoolClass::getName).toList());
        if (answer != null) {
            Optional<SchoolClass> listSchoolClass = school.getSchoolClasses().stream()
                    .filter(a -> answer.equalsIgnoreCase(a.getName()))
                    .findFirst();

            SchoolClass schoolClass = listSchoolClass.orElseThrow(() -> new IllegalArgumentException("Error 7"));
            boolean loop2 = true;
            boolean orderByGrade = false;
            while (loop2) {
                System.out.println("--------------------------");
                System.out.println(schoolClass);
                System.out.println("--------------");
                if (orderByGrade) {
                    schoolClass.printStudentsSortGrades();
                } else {
                    schoolClass.printStudents();
                }
                System.out.println("--------------------------");
                printCommandsClasses(orderByGrade);
                switch (scannerNumber(4)) {
                    case 1:
                        MenuStudent menuStudent = new MenuStudent();
                        menuStudent.logicLoopStudent(schoolClass);
                        break;
                    case 2:
                        addStudent(schoolClass);
                        break;
                    case 3:
                        orderByGrade = !orderByGrade;
                        break;
                    case 4:
                        loop2 = false;
                        break;
                }
            }
        }
    }

    public void printCommandsClasses(Boolean orderByGrade) {
        System.out.println("1.: View a student's grades");
        System.out.println("2.: Add a new student");
        if (orderByGrade) {
            System.out.println("3.: Order by name");
        } else {
            System.out.println("3.: Order by grade");
        }
        System.out.println("4.: Back");
    }

    public void addStudent(SchoolClass schoolClass) {
        System.out.println("Input student's name:");
        try {
            String newName = scannerWord();
            List<String> studentNames = schoolClass.getStudents().stream().map(Student::getName).toList();
            if (newName != null && !studentNames.contains(newName)) {
                Student student = new Student(newName);
                schoolClass.addStudent(student);
            } else {
                System.out.println("Student already exists");
            }
        } catch (Exception e) {
            System.out.println("Error 3");
        }
        enterToContinue();
    }

}
