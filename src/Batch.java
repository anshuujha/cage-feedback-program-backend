import java.util.ArrayList;
import java.util.List;

public class Batch {
    private String name;
    private List<String> questions;
    private List<User> students;

    public Batch(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addQuestion(String question) {
        questions.add(question);
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void addStudent(User student) {
        students.add(student);
    }

    public List<User> getStudents() {
        return students;
    }
}