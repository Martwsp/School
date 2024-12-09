package school;

public class Teacher extends Person {
    private SchoolClass schoolClass;

    public Teacher(String name) {
        super(name);
    }

    public SchoolClass getSchoolClass() {
        return this.schoolClass;
    }

    public void setClass(SchoolClass schoolClass){
        this.schoolClass = schoolClass;
    }

    public void addSubject(String name){
        super.subjects.add(new Subject(name));
    }

    @Override
    public String toString() {
        if (this.schoolClass != null){
            return name + " - " + schoolClass;
        }
        return name;
    }
}
