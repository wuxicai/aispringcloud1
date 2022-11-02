package com.southwind.repository.impl;

import com.southwind.entity.Student;
import com.southwind.repository.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {

    private static Map<Long,Student> studentMap;

    static {
        studentMap=new HashMap<>();
        studentMap.put(1L,new Student(1L,"33",33));
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findById() {
        return null;
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public void updateOrUpdate(Student student) {

    }
}
