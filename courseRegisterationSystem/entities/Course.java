package courseRegisterationSystem.entities;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import courseRegisterationSystem.exception.CourseRegisterationException;

public class Course {
    private final String courseId;
    private final String courseName;
    private final int capacity;
    private final Map<String, Student> enrolledStudnets;
    private final LinkedHashMap<String, Student> waitingStudents;
    private final ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock(true);

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

    public void removeStudent(Student student) throws InterruptedException, CourseRegisterationException {
        if (!reentrantLock.writeLock().tryLock(2000, TimeUnit.MILLISECONDS)) {
            throw new CourseRegisterationException("Try again later");
        }
        try {
            if (enrolledStudnets.containsKey(student.getStudentName())) {
                enrolledStudnets.remove(student.getStudentName());

                informNextStudent();
            }
            waitingStudents.remove(student.getStudentName());
        } finally {
            reentrantLock.writeLock().unlock();
        }

    }

    private void informNextStudent() throws InterruptedException {
       
            if (waitingStudents.size() > 0) {
                Student student = waitingStudents.firstEntry().getValue();
                waitingStudents.remove(student.getStudentName());
                student.informCourseEnrolled(this);
            }
        
    }

    public void removeWaitingStudent(Student student) throws InterruptedException {
        if (!reentrantLock.writeLock().tryLock(2000, TimeUnit.MILLISECONDS)) {
            throw new CourseRegisterationException("Try again later");
        }
        try {
            if (!waitingStudents.containsKey(student.getStudentName())) {
                return;
            }
            waitingStudents.remove(student.getStudentName());
        }

        finally {
            reentrantLock.writeLock().unlock();
        }
    }

    public boolean removeAllStudentsFromCourse() throws InterruptedException {
        if (!reentrantLock.writeLock().tryLock(2000, TimeUnit.MILLISECONDS)) {
            throw new CourseRegisterationException("Try again later");
        }
        try {
            for (Student student : enrolledStudnets.values()) {
                student.removeFromCurrentCourse(this);
            }
            for (Student student : waitingStudents.values()) {
                student.removeFromCurrentCourse(this);
            }

            return true;
        } finally {
            reentrantLock.writeLock().unlock();
        }

    }

    public boolean addStudentInCourse(Student student) throws InterruptedException {
        if (!reentrantLock.writeLock().tryLock(2000, TimeUnit.MILLISECONDS)) {
            throw new CourseRegisterationException("Try again later");
        }
        if (enrolledStudnets.containsKey(student.getStudentName())
                || waitingStudents.containsKey(student.getStudentName()))
            throw new CourseRegisterationException("Student has already taken this course ");

        
        try {
            if (enrolledStudnets.size() <this.capacity) {
                enrolledStudnets.put(student.getStudentName(), student);
                student.courseTaken(this);
                return true;
            } else {
                waitingStudents.put(student.getStudentName(), student);
                student.courseWaiting(this);
                return false;
            }
        } finally {
            reentrantLock.writeLock().unlock();
        }

    }

}
