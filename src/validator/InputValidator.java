package validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputValidator {

    public static void validateNotBlank(String value, String fieldName){
        if(value == null || value.isEmpty()){
            throw new IllegalArgumentException(fieldName + " should not be null or empty");
        }
    }

    public static void validatePositive(int value, String fieldName){
        if(value < 0){
            throw new IllegalArgumentException(fieldName + " should be positive");
        }
    }

    public static void validateDate(String value, String fieldName){
        try {
            LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(fieldName + " is not a valid date (yyyy-MM-dd)");
        }
    }
}
