package com.lambdaschool.school.controller;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
class CourseControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    //use ArrayList to mock a database

    private List<Course> courseList;

    @BeforeEach
    void setUp() throws Exception
    {
        courseList = new ArrayList<>();

        courseList.add(new Course("Data Science"));
        courseList.add(new Course("Web Development"));
        courseList.add(new Course("Mitchell's Army"));

    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void listAllCourses() throws Exception
    {
        String apiUrl = "/courses/courses";

        // Create mock call
        Mockito.when(courseService.findAll()).thenReturn(courseList);

        // Create real call

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void addNewCourse throws Exception
    {
        String apiUrl = "/courses/course/add";

        // create a course to test
        Instructor i1 = new Instructor("Skyelar");
        Course c1 = new Course("Cyber Security", i1);
        c1.setCourseid(42);

        ObjectMapper mapper = new ObjectMapper();
        String courseString = mapper.writeValueAsString(c1);

        Mockito.when(courseService.save(any(Course.class))).thenReturn(c1);

        // Build mock request
        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(courseString);
        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());




    }

}