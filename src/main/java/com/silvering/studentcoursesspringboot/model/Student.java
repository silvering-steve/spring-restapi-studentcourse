package com.silvering.studentcoursesspringboot.model;

import java.util.List;

public record Student(
        String id,
        String name,
        String description,
        List<Course> courses) {

}
