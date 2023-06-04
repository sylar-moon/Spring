package my.group;

import my.group.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentRepository repo;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @PostMapping("/students/save")
    public ResponseEntity<String> saveStudent(@RequestBody @Valid Student student) {
        repo.save(student);
        return ResponseEntity.ok("Student added to database");
    }

    @GetMapping("/students/getAll")
    public List<Student> getListAllStudents() {
        List<Student> list = new ArrayList<>();
        Iterable<Student> students = repo.findAll();
        for (Student student : students) {
            list.add(student);
        }
        return list;
    }

    @GetMapping("/students/get{id}")
    public Student getStudentByID(@PathVariable("id") Long id){
        if(repo.existsById(id)) {
            return repo.findById(id).orElse(null);
        }else{
           throw new NotFoundException
                   ("ID of this student is not in the database, so it is not possible to get him");}


    }


    @DeleteMapping("/students/delete{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long id) {
       if(repo.existsById(id)) {
           repo.deleteById(id);

       }else throw new NotFoundException("ID of this student is not in the database, so it is not possible to delete him");
        return ResponseEntity.ok("Student is delete");

    }

    @PutMapping("/students/update{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody @Valid Student student) {
        Student findStudent = repo.findById(id).orElse(null);

        if (findStudent!=null){
            findStudent.setLastName(student.getLastName());
            findStudent.setEmail(student.getEmail());
            findStudent.setFirstName(student.getFirstName());
            repo.deleteById(id);
            repo.save(findStudent);
        }else {
            throw new NotFoundException("ID of this student is not in the database, so his information cannot be updated");
        }
        return ResponseEntity.ok("Student is update");

    }
}
