package com.southwind.controller;

import com.southwind.entity.Student;
import com.southwind.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentHandler {
    @Autowired
    private StudentRepository studentRepository;
    @Value("${server.port}")
    private String port;
    @GetMapping("/findAll")
    public Collection<Student> findAll(){
        return   studentRepository.findAll();
    }
    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") long id){
        return studentRepository.findById(id);
    }
    @PostMapping("/save")
    public void save(@RequestBody Student student){
        studentRepository.saveOrUpdate(student);
    }
    @PutMapping ("/update")
    public void update(@RequestBody Student student){
        studentRepository.saveOrUpdate(student);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id){
        studentRepository.deleteById(id);
    }
    @GetMapping("/index")
    public String index1(){
        return "当前服务端口号："+port;
    }
}
