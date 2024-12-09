package school;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final String name;
    private final List<Grade> grades = new ArrayList<>();

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, int grade){
        this.name = name;
        addGrade(grade);
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return this.grades.stream()
                .mapToDouble(Grade::getValue)
                .average()
                .orElse(0.0);
    }

    public void addGrade (int grade){
        this.grades.add(new Grade(grade));
    }

    @Override
    public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        double grade = this.grades.stream()
                .mapToDouble(Grade::getValue)
                .average()
                .orElse(0.0);
        if (grade != 0) {
            return this.name + " - " + f.format(grade);
        } else {
            return this.name;
        }
    }


}
