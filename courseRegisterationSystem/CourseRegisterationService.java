package courseRegisterationSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import courseRegisterationSystem.entities.Course;
import courseRegisterationSystem.entities.Student;
import courseRegisterationSystem.exception.CourseRegisterationException;

public class CourseRegisterationService {
    private static CourseRegisterationService courseRegisterationService;
    private final Map<String, Student> students = new ConcurrentHashMap<>();
    private final Map<String, Course> courses = new ConcurrentHashMap<>();

    private CourseRegisterationService() {
    }

    public synchronized CourseRegisterationService gCourseRegisterationServiceInstance() {
        if (courseRegisterationService == null) {
            courseRegisterationService = new CourseRegisterationService();
        }
        return courseRegisterationService;
    }
    public Course addCourse(String courseName,int capacity) throws CourseRegisterationException{
        if(courses.containsKey(courseName)){
            throw new CourseRegisterationException("Course with given name alread added in system");
        }
        Course course=new Course(courseName, capacity);
        courses.put(courseName,course);
        return course;
    }
    public Student addStudent(String studentName)throws CourseRegisterationException{
        if(students.containsKey(studentName)){
            throw new CourseRegisterationException("Student with given name alread added in system");
        }
       Student student=new Student(studentName);
       students.put(studentName,student);
       return student;
    }
    public synchronized boolean removeStudent(Student student)throws InterruptedException{
        if(!students.containsKey(student.getStudentName())){
            throw new CourseRegisterationException("Student not found alread added in system");
            
        }
        students.remove(student.getStudentName());
        student.removeStudentFromCourses();
        return true;
    }
    public synchronized boolean removeCourse(Course course) throws InterruptedException{
        if(!courses.containsKey(course.getCourseName())){
            throw new CourseRegisterationException("Course not found in system");

        }
        course.removeAllStudentsFromCourse();
        courses.remove(course.getCourseName());
        return true;
    }
    public synchronized void addStudentInCourse(Student student,Course course)throws InterruptedException{
        if(!students.containsKey(student.getStudentName())){
            throw new CourseRegisterationException("Student not found alread added in system");
            
        }
        if(!courses.containsKey(course.getCourseName())){
            throw new CourseRegisterationException("Course not found in system");

        }
        course.addStudentInCourse(student);
    }

}
