package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.repository.InstructorRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;




import com.lambdaschool.school.SchoolApplicationTests;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplicationTests.class)
public class CourseServiceImplTest
{
    // mocks - fake data
    // stubs - code that forces a return value
    // Java/JUnit calls everything mocks

    // Wire up courseService
    @Autowired
    private CourseService courseService;

    // Wire up instructorService
    @Autowired
    private InstructorRepository instructorRepository;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findCourseById()
    {
        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
    }


    @org.junit.Test(expected = EntityNotFoundException.class)
    public void deleteNotFound()
    {
        courseService.delete(100);
        assertEquals(12, courseService.findAll().size());
    }

    @Test
    public void deleteFound()
    {
        courseService.delete(1);
        assertEquals(11, courseService.findAll().size());
    }

    @Test
    public void save()
    {
        // create mock course to save
        Instructor i1 = instructorRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException(Long.toString(2L)));
        Course c1 = new Course("Cyber Security", i1);
        Course newCourse =courseService.save(c1);

        assertNotNull(newCourse);

        Course findCourse = courseService.findCourseById(newCourse.getCourseid());
        assertEquals(newCourse.getCoursename(), findCourse.getCoursename());

    }

}