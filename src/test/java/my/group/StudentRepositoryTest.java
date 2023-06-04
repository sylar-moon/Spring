package my.group;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    StudentRepository repository;

    @Test
    void testSaveDeleteStudent() {
        Student saveStudent =
                new Student("Artem", "Petrov", "my@mail.com", "3198049272");
        repository.save(saveStudent);
        Student saveStudent2 =
                new Student("Vasiliy", "Horoshko", "my@mail.com", "1225792550");
        repository.save(saveStudent2);
        repository.deleteById(1L);
        Student student =  repository.findById(2L).get();
        Assertions.assertThrows(NoSuchElementException.class, () -> repository.findById(1L).get());
        Assertions.assertEquals(saveStudent2, student);
    }


}