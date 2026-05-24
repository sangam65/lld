package courseRegisterationSystem.entities;


import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Student {
    private final String studentId;
    private final String studentName;
    private final Map<String,Course>courses;
    private final Map<String,Course>waiting;
    public Map<String, Course> getWaiting() {
        return waiting;
    }
    public String getStudentId() {
        return studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public Map<String, Course> getCourses() {
        return courses;
    }
    public Student(String studentName) {
        this.studentName = studentName;
        this.courses = new ConcurrentHashMap<>();
        this.studentId=UUID.randomUUID().toString();
        this.waiting=new ConcurrentHashMap<>();
    }
    public boolean removeStudentFromCourses(){
        for(Course course:courses.values()){
            course.removeStudent(this);
        }
        courses.clear();
        for(Course course:waiting.values()){
            course.removeWaitingStudent(this);
        }
        waiting.clear();;

        return true;
    }
    public void informCourseEnrolled(Course course){
        if(!waiting.containsKey(course.getCourseName())){
            return;
        }
        System.out.println("Course is available now , you can take");
        waiting.remove(course.getCourseName());
    }
    public boolean removeFromCurrentCourse(Course course){
       courses.remove(course.getCourseName());
       waiting.remove(course.getCourseName());
       return true;
    }
    
    
}
