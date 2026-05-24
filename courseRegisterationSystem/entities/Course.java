package courseRegisterationSystem.entities;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Course {
    private final String courseId;
    private final String courseName;
    private final int capacity;
    private final Map<String, Student> enrolledStudnets;
    private final LinkedHashMap<String, Student> waitingStudents;

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public Map<String, Student> getEnrolledStudnets() {
        return enrolledStudnets;
    }

    public LinkedHashMap<String, Student> getWaitingStudents() {
        return waitingStudents;
    }

    public Course(String courseName, int capacity) {
        this.courseName = courseName;
        this.capacity = capacity;
        this.courseId = UUID.randomUUID().toString();
        this.enrolledStudnets = new ConcurrentHashMap<>();
        this.waitingStudents = new LinkedHashMap<>();

    }
    public void removeStudent(Student student){
        if(enrolledStudnets.containsKey(student.getStudentName())){
            enrolledStudnets.remove(student.getStudentName());
            
            informNextStudent();
        }
        waitingStudents.remove(student.getStudentName());
    }
    private void informNextStudent(){
        if(waitingStudents.size()>0){
          Student student= waitingStudents.firstEntry().getValue();
          waitingStudents.remove(student.getStudentName());
          student.informCourseEnrolled(this);
        }
    }
    public void removeWaitingStudent(Student student){
        if(!waitingStudents.containsKey(student.getStudentName())){
            return;
        }
        waitingStudents.remove(student.getStudentName());
    }
    public boolean removeAllStudentsFromCourse(){
        for(Student student:enrolledStudnets.values()){
            student.removeFromCurrentCourse(this);
        }
        for(Student student:waitingStudents.values()){
            student.removeFromCurrentCourse(this);
        }
        return true;
    }

}
