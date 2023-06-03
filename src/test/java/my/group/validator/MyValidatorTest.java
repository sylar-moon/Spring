package my.group.validator;

import my.group.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyValidatorTest {

    MyValidator validator = new MyValidator();
    CheckIpnValidator ipnValidator = new CheckIpnValidator();

    @Test
    void testStudentValidator(){
        Student student = new Student("Gregor","Kaligan","my@mail.com","02344 6789 a");
        Assertions.assertTrue(validator.validateStudent(student));
    }
    @Test
    void isNumberTest(){
        String str= "1234567819";
       Assertions.assertTrue(ipnValidator.isNumber(str));
    }

    @Test
    void checkLastNumberTest(){
        String str= "33383506998";
        Assertions.assertTrue(ipnValidator.checkLastNumb(str));
    }
}