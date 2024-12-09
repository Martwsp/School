package menu;

import school.SchoolClass;
import school.Student;
import school.Subject;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MenuStudent extends MenuLogic {

    public void logicLoopStudent(SchoolClass schoolClass) {
        System.out.println("Which student would do you want to view?");
        schoolClass.getStudents().forEach(System.out::println);
        System.out.println("Enter student's name:");
        String answer = scannerName(schoolClass.getStudents().stream().map(Student::getName).toList());
        if (answer != null) {
            Optional<Student> listStudents = schoolClass.getStudents().stream()
                    .filter(a -> answer.equalsIgnoreCase(a.getName()))
                    .findFirst();

            Student student = listStudents.orElseThrow(() -> new IllegalArgumentException("Error 4"));
            boolean loop2 = true;
            while (loop2) {
                System.out.println("--------------------------");
                System.out.println(student);
                System.out.println("--------------");
                student.getSubjects().stream()
                        .sorted(Comparator.comparing(Subject::getName))
                        .forEach(System.out::println);
                System.out.println("--------------------------");
                printCommandsStudent();
                switch (scannerNumber(3)) {
                    case 1:
                        addGrade(student);
                        break;
                    case 2:
                        addSubject(student);
                        break;
                    case 3:
                        loop2 = false;
                        break;
                }
            }
        }
    }

    public void printCommandsStudent() {
        System.out.println("1.: Add new grade to a subject");
        System.out.println("2.: Add new subject");
        System.out.println("3.: Back");
    }

    public void addSubject(Student student) {
        System.out.println("Input subject name:");
        try {
            String newName = scannerWord();
            if (newName != null) {
                List<String> subjects;
                subjects = student.getSubjects().stream()
                        .map(Subject::getName)
                        .toList();

                if (!subjects.contains(newName)) {
                    System.out.println("What is the first grade?:");
                    student.addSubject(newName, scannerNumber(5));
                    System.out.println("Subject successfully added.");
                } else {
                    System.out.println("Subject already studied,use edit grade instead.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error 5");
        }
        enterToContinue();
    }

    public void addGrade (Student student) {
        System.out.println("Add grade to which subject?");
        String answer = scannerName(student.getSubjects().stream().map(Subject::getName).toList());
        if (answer != null) {
            Optional<Subject> listSubjects = student.getSubjects().stream()
                    .filter(a -> answer.equalsIgnoreCase(a.getName()))
                    .findFirst();

            Subject subject = listSubjects.orElseThrow(() -> new IllegalArgumentException("Error 8"));
            System.out.println("And what grade? 1-5");
            subject.addGrade(scannerNumber(5));
            System.out.println("Grade added to " + subject.getName());

        }
    }

}
