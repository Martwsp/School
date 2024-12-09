package menu;

import school.*;

import java.util.*;

public class MenuLogic {

    public void logicLoop(School school) {

        boolean loop = true;
        while (loop) {
            printMenu(school);
            printCommandsSchool();
            switch (scannerNumber(8)) {
                case 1:
                    printTeachers(school);
                    break;
                case 2:
                    MenuSchoolClass menuSchoolClass = new MenuSchoolClass();
                    menuSchoolClass.logicLoopSchoolClasses(school);
                    break;
                case 3:
                    school.printBestStudents();
                    enterToContinue();
                    break;
                case 4:
                    school.printSubjectAverage();
                    enterToContinue();
                    break;
                case 5:
                    addTeacher(school);
                    break;
                case 6:
                    addSubjectToTeacher(school);
                    break;
                case 7:
                    addSchoolClass(school);
                    break;
                case 8:
                    loop = false;
                    break;
            }
        }

    }

    public void printMenu(School school) {
        System.out.println("--------------------------");
        System.out.println("Teachers: ");
        school.printTeachers();
        System.out.println("--------------------------");
        System.out.println("Classes:");
        school.printClasses();
        System.out.println("--------------------------");
    }

    public void printCommandsSchool() {
        System.out.println("1.: Teachers details");
        System.out.println("2.: Browse students in a class");
        System.out.println("3.: Print classes sorted by average grade");
        System.out.println("4.: Print average grade of each subject");
        System.out.println("5.: Add new teacher");
        System.out.println("6.: Add a subject taught to a teacher");
        System.out.println("7.: Add new class");
        System.out.println("8.: Exit");
    }

    public void printTeachers(School school) {
        for (Teacher teacher : school.getTeachers()) {
            System.out.println("----------");
            System.out.println(teacher.getName());
            if (teacher.getSchoolClass() != null) {
                System.out.print("Primary class: ");
                System.out.println(teacher.getSchoolClass());
            }
            System.out.println("Teaches: ");
            for (Subject subjects : teacher.getSubjects()) {
                System.out.println(subjects);
            }
        }
        enterToContinue();
    }

    public void addTeacher(School school) {
        System.out.println("Input name:");
        try {
            String newName = scannerWord();
            if (newName != null) {
                List<String> teachers;
                teachers = school.getTeachers().stream()
                        .map(Teacher::getName)
                        .toList();

                if (!teachers.contains(newName)) {
                    school.addTeacher(new Teacher(newName));
                    System.out.println("Teacher " + newName + " successfully added.");
                } else {
                    System.out.println("Name already exists");
                }
            }
        } catch (Exception e) {
            System.out.println("Error 1");
        }
        enterToContinue();
    }

    public void addSchoolClass(School school) {
        System.out.println("Input name:");
        try {
            String newName = scannerWord();
            List<String> schoolClassesNames = school.getSchoolClasses().stream().map(SchoolClass::getName).toList();
            if (newName != null && !schoolClassesNames.contains(newName)) {
                List<String> teachers;
                teachers = school.getTeachers().stream()
                        .filter(teacher -> teacher.getSchoolClass() == null)
                        .map(Teacher::getName)
                        .toList();

                if (!teachers.isEmpty()) {

                    System.out.println("Teachers without primary class:");
                    teachers.forEach(System.out::println);

                    String addToTeacher = scannerName(teachers);
                    if (addToTeacher != null) {
                        SchoolClass schoolClass = new SchoolClass(newName);

                        try {
                            school.getTeachers().stream()
                                    .filter(a -> addToTeacher.equalsIgnoreCase(a.getName()))
                                    .limit(1)
                                    .forEach(teacher -> teacher.setClass(schoolClass));
                            System.out.println("Class " + newName + " added.");
                        } catch (Exception e) {
                            System.out.println("Teacher does not exist or already has a primary class.");
                        }
                    }
                } else {
                    System.out.println("No available teachers without a primary class. Add a new teacher first");
                }
            } else {
                System.out.println("Class already exists");
            }
        } catch (Exception e) {
            System.out.println("Error 2");
        }
        enterToContinue();
    }

    public void addSubjectToTeacher(School school) {
        System.out.println("Which teacher add subject to?");
        String answer = scannerName(school.getTeachers().stream().map(Teacher::getName).toList());
        if (answer != null) {
            Optional<Teacher> teacherOptional = school.getTeachers().stream()
                    .filter(a -> answer.equalsIgnoreCase(a.getName()))
                    .findFirst();

            Teacher teacher = teacherOptional.orElseThrow(() -> new IllegalArgumentException("Error 8"));
            System.out.println("Input subject name:");
            try {
                String newName = scannerWord();
                if (newName != null) {
                    List<String> subjects;
                    subjects = teacher.getSubjects().stream()
                            .map(Subject::getName)
                            .toList();

                    if (!subjects.contains(newName)) {
                        teacher.addSubject(newName);
                        System.out.println("Subject successfully added.");
                    } else {
                        System.out.println("Subject already exists");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error 6");
            }
            enterToContinue();
        }
    }


    public void enterToContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("Enter to continue");
        scanner.nextLine();
    }

    public String scannerWord() {
        Scanner scanner = new Scanner(System.in);
        String userSelection;
        while (true) {
            try {
                userSelection = scanner.nextLine();
                if (userSelection.equalsIgnoreCase("exit")) {
                    return null;
                } else {
                    return userSelection;
                }
            } catch (Exception e) {
                System.out.println("Illegal characters");
            }
        }
    }

    public int scannerNumber(int numberOfOptions) {
        Scanner scanner = new Scanner(System.in);
        int userSelection;
        while (true) {
            try {
                userSelection = Integer.parseInt(scanner.nextLine());
                if (userSelection > numberOfOptions || userSelection <= 0) {
                    System.out.println("Must be a number from 1 to " + numberOfOptions);
                } else {
                    return userSelection;
                }
            } catch (Exception e) {
                System.out.println("Must be a number from 1 to " + numberOfOptions);
            }
        }
    }

    public String scannerName(List<String> options) {
        Scanner scanner = new Scanner(System.in);
        String userSelection;
        while (true) {
            try {
                userSelection = scanner.nextLine();
                if (options.contains(userSelection)) {
                    return userSelection;
                } else if (userSelection.equalsIgnoreCase("exit")) {
                    return null;
                } else {
                    System.out.println("Item not found");
                }
            } catch (Exception e) {
                System.out.println("Input name of item");
            }
        }
    }

}
