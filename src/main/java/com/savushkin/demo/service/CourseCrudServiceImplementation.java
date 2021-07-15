package com.savushkin.demo.service;

import com.savushkin.demo.controller.NotFoundException;
import com.savushkin.demo.dao.CourseRepository;
import com.savushkin.demo.domain.Course;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseCrudServiceImplementation implements CourseCrudService {

	private final CourseRepository courseRepository;

	@Autowired
	public CourseCrudServiceImplementation(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public Course createNewCourse() {
		return new Course();
	}

	@Override
	public void updateCourse(Course course) {
		courseRepository.save(course);
	}

	@Override
	public void deleteCourse(String id) {
		try {
			long parsedId = Long.parseLong(id);
			courseRepository.delete(parsedId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Course getCourse(String id) {
		try {
			long parsedId = Long.parseLong(id);
			return courseRepository.findById(parsedId).orElseThrow(NotFoundException::new);
		} catch (NumberFormatException e) {
			return new Course();
		}
	}

	@Override
	public List<Course> getCourses(String query) {
		if (query == null) {
			return courseRepository.findAll();
		} else {
			return courseRepository.findByTitleWithPrefix(query);
		}
	}


}
