package org.example;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public class Validators {

    public final void validatorFlat(Flat flat) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Flat>> validate = validator.validate(flat);
        if (validate.size() > 0){
            for(ConstraintViolation<Flat> violation : validate) {
                new InputOutput().Output(violation.getMessage());
            }
        }
        validatorFactory.close();
    }

    public final void validatorHouse(House house){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<House>> validate = validator.validate(house);
        if (validate.size() > 0){
            for(ConstraintViolation<House> violation : validate) {
                new InputOutput().Output(violation.getMessage());
            }
        }
        validatorFactory.close();
    }

    public final void validatorCoordinates(Coordinates coordinates){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Coordinates>> validate = validator.validate(coordinates);
        if (validate.size() > 0){
            for(ConstraintViolation<Coordinates> violation : validate) {
                new InputOutput().Output(violation.getMessage());
            }
        }
        validatorFactory.close();
    }
}
