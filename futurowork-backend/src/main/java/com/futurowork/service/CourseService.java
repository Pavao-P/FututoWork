package com.futurowork.service;

import com.futurowork.entity.Course;
import com.futurowork.repository.CourseRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository repo;

    public CourseService(CourseRepository repo) { this.repo = repo; }

    @Cacheable("courses")
    public Page<Course> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public Course create(Course c) { return repo.save(c); }
}
