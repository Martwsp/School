package school;

import java.text.DecimalFormat;

public class Student extends Person {


    public Student(String name) {
        super(name);
    }

    public void addSubject(String subject, int grade){
        super.subjects.add(new Subject(subject, grade));
    }

    public double getAverageGrades(){
        return super.subjects.stream()
                .mapToDouble(Subject::getGrade)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
            return super.name + " - " + f.format(getAverageGrades());
    }


}
