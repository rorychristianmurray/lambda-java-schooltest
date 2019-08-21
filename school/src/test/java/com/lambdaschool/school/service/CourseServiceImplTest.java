package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import org.junit.After;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
class CourseServiceImplTest
{
    // mocks - fake data
    // stubs - code that forces a return value
    // Java/JUnit calls everything mocks

    // Wire up courseService
    @Autowired
    private CourseService courseService;

    @Before
    void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    void tearDown()
    {
    }

    @Test
    void findCourseById()
    {
        assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
    }

    @Test
    public void deleteFound()
    {
        courseService.delete(1);
        assertEquals(11, courseService.findAll().size());
    }

}