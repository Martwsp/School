package school;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class School {
    private final List<Teacher> teachers = new ArrayList<>();

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void printTeachers() {
        this.teachers.stream()
                .map(Person::getName)
                .sorted()
                .forEach(System.out::println);
    }

    public void printBestStudents() {
        this.teachers.stream()
                .map(Teacher::getSchoolClass)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(SchoolClass::getGradeAverage))
                .forEach(System.out::println);
    }

    public List<SchoolClass> getSchoolClasses(){
        return this.teachers.stream()
                .map(Teacher::getSchoolClass)
                .filter(Objects::nonNull)
                .toList();
    }

    public void printClasses() {
        this.teachers.stream()
                .map(Teacher::getSchoolClass)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(SchoolClass::getName))
                .forEach(System.out::println);
    }

    public void printSubjectAverage(){
        List<Subject> subjects = this.teachers.stream()
                .filter(teacher -> teacher.getSchoolClass() != null)
                .flatMap(teacher -> teacher.getSchoolClass().getStudents().stream())
                .flatMap(student -> student.getSubjects().stream())
                .toList();

        Set<String> subjectNames = subjects.stream()
                .map(Subject::getName)
                .collect(Collectors.toSet());

        List<Map.Entry<String, Double>> subjectAverages = subjectNames.stream()
                .map(name -> {
                    double gradeAverage = subjects.stream()
                            .filter(subject -> Objects.equals(subject.getName(), name))
                            .mapToDouble(Subject::getGrade)
                            .average()
                            .orElse(0.0);
                    return new AbstractMap.SimpleEntry<>(name, gradeAverage);
                })
                .sorted(Comparator.comparingDouble(AbstractMap.SimpleEntry::getValue))
                .collect(Collectors.toList());

        DecimalFormat f = new DecimalFormat("##.00");
        subjectAverages.forEach(entry -> System.out.println(entry.getKey() + " - " + f.format(entry.getValue())));
    }


}
