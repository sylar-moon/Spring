package my.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository repo;

    @PostMapping("student")
    public void saveStudent(@RequestBody Student student) {
        repo.save(student);
    }

    @GetMapping("/student")
    public List<Student> listAll() {
        List<Student> list = new ArrayList<>();
        Iterable<Student> students = repo.findAll();
        for (Student student : students) {
            list.add(student);
        }
        return list;
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/student/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody Student student) {
        Student findStudent = repo.findById(id).orElse(null);

        if (findStudent!=null){
            findStudent.setLastName(student.getLastName());
            findStudent.setEmail(student.getEmail());
            findStudent.setFirstName(student.getFirstName());
            findStudent.setId(student.getId());
            repo.save(findStudent);
        }

    }

}
