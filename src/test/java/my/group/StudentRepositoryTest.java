package my.group;


import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    StudentRepository repository;
    @Test
    void saveFindStudentTest(){
        Student saveStudent =
                new Student("Artem","Petrov","my@mail.com","1234567890");
        repository.save(saveStudent);
        Iterable<Student> students = repository.findAll();

        for (Student student : students) {
            System.out.println(student.getFirstName());
            Assertions.assertEquals(student, saveStudent);
        }

    }
}