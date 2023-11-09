package com.silvering.studentcoursesspringboot.service;

import com.silvering.studentcoursesspringboot.model.Course;
import com.silvering.studentcoursesspringboot.model.Student;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private static final List<Student> students = new ArrayList<>();

    private final SecureRandom secureRandom = new SecureRandom();

    static {
        Course courseOne = new Course("Course1", "Spring", "10 Steps",
                List.of("Learn Maven", "Import Project", "First Example", "Second Example"));

        Course courseTwo = new Course("Course2", "Spring MVC", "10 Examples",
                List.of("Learn Maven", "Import Project", "First Example", "Second Example"));

        Course courseThree = new Course("Course3", "Spring Boot", "6K Students",
                List.of("Learn Maven", "Learn Spring", "Learn Spring MVC", "First Example", "Second Example"));

        Course courseFour = new Course("Course4", "Maven", "Most popular maven course on internet!",
                List.of("Pom.xml", "Build Life Cycle", "Parent POM", "Importing into Eclipse"));

        Student vincent = new Student("Student1", "Vincent", "Hiker, Programmer and Architect",
                new ArrayList<>(List.of(courseOne, courseTwo, courseThree, courseFour)));

        Student jovan = new Student("Student2", "Jovan", "Hiker, Programmer and Architect",
                new ArrayList<>(List.of(courseOne, courseTwo, courseThree, courseFour)));

        students.add(vincent);
        students.add(jovan);
    }

    public List<Student> retrieveAllStudents() {
        return students;
    }

    public Student retrieveStudent(String studentId) {
        return students.stream()
                .filter(student -> student.id().equals(studentId))
                .findAny()
                .orElse(null);
    }

    public List<Course> retrieveCourses(String studentId) {
        Student student = retrieveStudent(studentId);

        return student == null ? null : student.courses();
    }

    public Course retrieveCourse(String studentId, String coursesId) {
        Student student = retrieveStudent(studentId);

        if (student == null)
            return null;

        return student.courses().stream()
                .filter(course -> course.id().equals(coursesId))
                .findAny()
                .orElse(null);
    }

    public Course addCourse(String studentID, Course course) {
        Student student = retrieveStudent(studentID);

        if (student == null)
            return null;

        String randomId = new BigInteger(130, secureRandom).toString(32);
        course.setId(randomId);

        student.courses()
                .add(course);

        return course;
    }
}
