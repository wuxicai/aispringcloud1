package com.southwind.repository;

import com.southwind.entity.Student;

import java.util.List;

public interface StudentRepository {
    public List<Student> findAll();
    public Student findById();
    public void save(Student student);
    public void updateOrUpdate(Student student);
}
