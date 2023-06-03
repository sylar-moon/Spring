package my.group.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.regex.Pattern;

public class CheckIpnValidator implements ConstraintValidator<CheckIpn, String> {

    @Override
    public boolean isValid(String ipn, ConstraintValidatorContext constraintValidatorContext) {
    if(ipn == null||ipn.length() != 10|| isNumber(ipn)) {
        return false;
    }


        int firstFiveNumb = Integer.parseInt(ipn.substring(0,5));
        LocalDate nowDate = LocalDate.now();
        LocalDate startDate = LocalDate.of(1899,12,31);
        LocalDate ageOldestPerson = nowDate.minusYears(117);
        long dayOldestPerson = ChronoUnit.DAYS.between(startDate,ageOldestPerson);
        long dayYoungestPerson = ChronoUnit.DAYS.between(startDate,nowDate);
        if (firstFiveNumb<dayOldestPerson||firstFiveNumb>dayYoungestPerson||checkLastNumb(ipn)){
            return false;
        }


//        int lastNumb = ipn.





        return true;


    }

    public boolean checkLastNumb(String ipn) {
        int lastNumb =Integer.parseInt(ipn.substring(9)) ;
        int[]str = Arrays.stream(ipn.split(""))
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

        System.out.println(controlDigit);

        return lastNumb==controlDigit;

    }


    public boolean isNumber(String ipn) {
        return Pattern.matches("^[0-9]+$",ipn);
    }
}
