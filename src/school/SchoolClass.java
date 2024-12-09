package school;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SchoolClass {
    String name;
    List<Student> students = new ArrayList<>();

    public SchoolClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void printStudents(){
        this.students.stream()
                .sorted(Comparator.comparing(Person::getName))
                .forEach(System.out::println);
    }

    public void printStudentsSortGrades(){
        this.students.stream()
                .sorted(Comparator.comparing(Student::getAverageGrades))
                .forEach(System.out::println);
    }

    public double getGradeAverage() {
        return this.students.stream()
                .mapToDouble(Student::getAverageGrades)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        return this.name + " - Average grade: " + f.format(getGradeAverage());
    }

//    public List<Student> getBestStudents(){
//        return this.students.stream()
//                .sorted(Comparator.comparing(Student::getAverageGrades))
//                .limit(3)
//                .toList();
//    }


}


