package nl.cim.training.springboot.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nl.cim.training.springboot.dto.EmployeeRequest;
import nl.cim.training.springboot.validators.EmployeeValidation;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;


public class EmployeeRequestConstraint implements ConstraintValidator<EmployeeValidation, EmployeeRequest> {

    @Override
    public boolean isValid(EmployeeRequest employeeRequest,
                           ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        boolean hasViolated = false;

        if (employeeRequest == null) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Empty Request")
                    .addConstraintViolation();
            hasViolated = true;

        }

        if (isTooYoung(employeeRequest)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Too Young")
                    .addConstraintViolation();
            hasViolated = true;


        }

        if (isFuncGroupNotOk(employeeRequest)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Function Group Must Be Rounded")
                    .addConstraintViolation();
            hasViolated = true;


        }

        if (isNotValidEmail(employeeRequest)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Email Address not Valid")
                    .addConstraintViolation();
            hasViolated = true;


        }

        return !hasViolated;


    }

    private static boolean isTooYoung(EmployeeRequest empReq) {
        return Period.between(empReq.getDateOfBirth(), LocalDate.now()).getYears() < 18;
    }

    private static boolean isFuncGroupNotOk(EmployeeRequest empReq) {
        double d = empReq.getFuncGroup().doubleValue();
        return (d % 1.0 > 0) || (d >= 100.0);
    }

    private static boolean isNotValidEmail(EmployeeRequest empReq) {
        String regex
                = "^[a-z|0-9|A-Z]+([_][a-z|0-9|A-Z]+)*([.][a-z|0-9|A-Z]+)*([.][a-z|0-9|A-Z]+)*(([_][a-z|0-9|A-Z]+)*)"
                + "?@[a-z][a-z|0-9|A-Z]*\\.([a-z][a-z|0-9|A-Z]*(\\.[a-z][a-z|0-9|A-Z]*)?)$";
        return !Pattern
                .compile(regex)
                .matcher(empReq.getEmail())
                .matches();
    }
}


