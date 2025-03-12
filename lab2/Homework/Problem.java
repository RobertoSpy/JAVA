public class Problem {
    private Student[] students;
    private Teacher[] teachers;

    public Problem(Student[] students, Teacher[] teachers) {
        this.students = students;
        this.teachers = teachers;
    }

    public Student[] getStudents() {
        return students;
    }

    public Teacher[] getTeachers() {
        return teachers;
    }

    public Person[] getPersons() {
        Person[] persons = new Person[students.length + teachers.length];
        int index = 0;
        for (Student student : students) {
            persons[index++] = student;
        }
        for (Teacher teacher : teachers) {
            persons[index++] = teacher;
        }
        return persons;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Students and Teachers in the Problem:\n");
        for (Person person : getPersons()) {
            sb.append(person).append("\n");
        }
        return sb.toString();
    }
}
