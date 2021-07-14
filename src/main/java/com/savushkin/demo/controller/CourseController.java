package com.savushkin.demo.controller;

import com.savushkin.demo.dao.CourseRepository;
import com.savushkin.demo.domain.Course;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/course")
public class CourseController {

	private final CourseRepository courseRepository;

	@Autowired
	public CourseController(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@GetMapping
	public String getCourses(Model model, @RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
		model.addAttribute("courses", courseRepository.findByTitleWithPrefix(titlePrefix == null ? "" : titlePrefix));
		model.addAttribute("activePage", "courses");
		return "course-list";
	}

	@PostMapping
	public String saveCourse(@ModelAttribute @Valid Course course, BindingResult bindingResult, Model model) {
		model.addAttribute("course", course);
		if (bindingResult.hasErrors())
			return "create-course";
		courseRepository.save(course);
		return "redirect:/course";
	}

	@GetMapping("/{id}")
	public String getCourseById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("course", courseRepository.findById(id).orElseThrow(NotFoundException::new));
		return "create-course";
	}

	@GetMapping("/new")
	public String createNewCourse(Model model) {
		model.addAttribute("course", new Course());
		return "create-course";
	}

	@DeleteMapping("/{id}")
	public String deleteCourse(@PathVariable("id") Long id) {
		courseRepository.delete(id);
		return "redirect:/course";
	}

	@ExceptionHandler
	public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
		ModelAndView modelAndView = new ModelAndView("error-no-course");
		modelAndView.setStatus(HttpStatus.NOT_FOUND);
		return modelAndView;
	}
}
