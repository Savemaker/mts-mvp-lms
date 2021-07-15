package com.savushkin.demo.service;

import com.savushkin.demo.domain.Course;
import java.util.List;

public interface CourseCrudService {

	public Course createNewCourse();

	public void updateCourse(Course course);

	public void deleteCourse(String id);

	public Course getCourse(String id);

	public List<Course> getCourses(String query);

}
