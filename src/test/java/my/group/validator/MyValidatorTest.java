package my.group.validator;

import my.group.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MyValidatorTest {

    MyValidator validator = new MyValidator();
    CheckIpnValidator ipnValidator = new CheckIpnValidator();

    @Test
    void testStudentValidator(){
        Assertions.assertTrue(validator.validateStudent(
                new Student("Gregor","Kaligan","my@mail.com","2801029831")));
        Assertions.assertFalse(validator.validateStudent(
                new Student("","Kaligan","my@mail.com","2801029831")));
        Assertions.assertFalse(validator.validateStudent(
                new Student("Gregor","","my@mail.com","2801029831")));
        Assertions.assertFalse(validator.validateStudent(
                new Student("Gregor","Kaligan","mymail.com","2801029831")));
    }

    @Test
    void TestCheckFirstFiveNumb(){
        Assertions.assertTrue(ipnValidator.checkFirstFiveNumb("03453"));
        Assertions.assertFalse(ipnValidator.checkFirstFiveNumb("01979"));
        Assertions.assertFalse(ipnValidator.checkFirstFiveNumb("46453"));
        Assertions.assertFalse(ipnValidator.checkFirstFiveNumb("00453"));

    }


    @Test
    void isNumberTest(){
       Assertions.assertTrue(ipnValidator.isNumber("1234567819"));
       Assertions.assertFalse(ipnValidator.isNumber("123456781a"));
       Assertions.assertFalse(ipnValidator.isNumber("a234567814"));
       Assertions.assertFalse(ipnValidator.isNumber("123!67811"));
    }

    @Test
    void checkLastNumberTest(){
        Assertions.assertTrue(ipnValidator.checkLastNumb("2801029831"));

        Assertions.assertFalse(ipnValidator.checkLastNumb("2801029830"));
        Assertions.assertFalse(ipnValidator.checkLastNumb("2801029832"));
        Assertions.assertFalse(ipnValidator.checkLastNumb("2801029833"));
        Assertions.assertFalse(ipnValidator.checkLastNumb("2801029834"));
        Assertions.assertFalse(ipnValidator.checkLastNumb("2801029835"));
        Assertions.assertFalse(ipnValidator.checkLastNumb("2801029836"));
        Assertions.assertFalse(ipnValidator.checkLastNumb("2801029837"));
        Assertions.assertFalse(ipnValidator.checkLastNumb("2801029838"));
        Assertions.assertFalse(ipnValidator.checkLastNumb("2801029839"));

        Assertions.assertTrue(ipnValidator.checkLastNumb("3198049272"));
        Assertions.assertTrue(ipnValidator.checkLastNumb("1225792550"));
    }
}