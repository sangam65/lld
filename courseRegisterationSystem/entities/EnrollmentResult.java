package courseRegisterationSystem.entities;

public class EnrollmentResult {
    private final Course course;
    private final Student student;
    public Course getCourse() {
        return course;
    }
    public Student getStudent() {
        return student;
    }
    public EnrollmentResult(Course course, Student student) {
        this.course = course;
        this.student = student;
    }
}
