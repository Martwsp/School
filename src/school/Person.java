package school;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    protected final String name;
    protected final List<Subject> subjects = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Subject> getSubjects() {
        return this.subjects;
    }


}
