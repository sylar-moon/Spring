package my.group.validator;

import my.group.utilities.MyLogger;
import org.slf4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CheckIpnValidator implements ConstraintValidator<CheckIpn, String> {
    private final Logger logger = new MyLogger().getLogger();

    @Override
    public boolean isValid(String ipn, ConstraintValidatorContext constraintValidatorContext) {
        if (ipn == null || ipn.length() != 10 || !isNumber(ipn)) {
            return false;
        }
        return checkFirstFiveNumb(ipn) && checkLastNumb(ipn);
    }

     boolean checkFirstFiveNumb(String ipn) {
        int firstFiveNumb = Integer.parseInt(ipn.substring(0, 5));
        LocalDate nowDate = LocalDate.now();
        LocalDate startDate = LocalDate.of(1899, 12, 31);
        LocalDate ageOldestPerson = nowDate.minusYears(118);

        long dayOldestPerson = ChronoUnit.DAYS.between(startDate, ageOldestPerson);
        long dayYoungestPerson = ChronoUnit.DAYS.between(startDate, nowDate);

        if (firstFiveNumb < dayOldestPerson) {
            logger.error("Such an IPN cannot exist because there are no such old people in Ukraine");
            logger.info("The minimum number of days from 12/31/1899 can be {}," +
                    " which is the IPN number of a 118 year old person", dayOldestPerson);
            return false;
        } else if (firstFiveNumb > dayYoungestPerson) {
            logger.error("Such an IPN number cannot exist because the first five numbers exceed the number of days from 12/31/1899");
            logger.info("The number of days from 31/12/1899 is {}", dayYoungestPerson);
            return false;
        }

        return true;

    }


     boolean checkLastNumb(String ipn) {
        int lastNumb = Integer.parseInt(ipn.substring(9));
        int[] str = Arrays.stream(ipn.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        final int first = (str[0] * (-1));
        final int second = (str[1] * 5);
        final int third = (str[2] * 7);
        final int forth = (str[3] * 9);
        final int fifth = (str[4] * 4);
        final int sixth = (str[5] * 6);
        final int seventh = (str[6] * 10);
        final int eighth = (str[7] * 5);
        final int ninth = (str[8] * 7);

        final int total = first + second + third + forth + fifth + sixth + seventh + eighth + ninth;
        final int controlDigit = (total % 11) % 10;
        if(lastNumb == controlDigit){
            return true;
        }else {
            logger.error("The control tenth number is calculated incorrectly");
            logger.info("Correct check number is {}",controlDigit);
            return false;
        }
    }


     boolean isNumber(String ipn) {
        return Pattern.matches("^[0-9]+$", ipn);
    }
}
