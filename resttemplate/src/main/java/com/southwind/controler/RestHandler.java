package com.southwind.controler;

import com.southwind.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RestController
@RequestMapping("/rest")
public class RestHandler {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/findall")
    public Collection<Student> findAll(){
        return restTemplate.getForEntity("http://localhost:8010/student/findAll",Collection.class).getBody();
    }
    @GetMapping("/findall1")
    public Collection<Student> findAll1(){
        return restTemplate.getForObject("http://localhost:8010/student/findAll",Collection.class);
    }
    @GetMapping("/findbyid/{id}")
    public Student findById(@PathVariable("id") long id){
        return restTemplate.getForObject("http://localhost:8010/student/findById/{id}",Student.class,id);
    }
    @PostMapping("/save")
    public void save(@RequestBody Student student){
        restTemplate.postForObject("http://localhost:8010/student/save",student,Student.class);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") long id){
        restTemplate.delete("http://localhost:8010/student/delete/{id}",id);
    }
    @PutMapping("/update")
    public void update(@RequestBody Student student){
        restTemplate.put("http://localhost:8010/student/update/",student);
    }
}
