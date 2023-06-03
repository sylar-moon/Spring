package my.group.validator;

import my.group.Student;
import my.group.utilities.MyLogger;
import org.slf4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class MyValidator {
    private final Logger logger = new MyLogger().getLogger();
    public final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    public final Validator validator = factory.getValidator();

    public boolean validateStudent(Student student) {
        Set<ConstraintViolation<Student>> violation = validator.validate(student);

        if (!violation.isEmpty()) {
            for (ConstraintViolation<Student> goodConstraintViolation : violation) {
                logger.error(goodConstraintViolation.getMessage());
            }
            return false;
        } else {
            return true;
        }
    }
}
