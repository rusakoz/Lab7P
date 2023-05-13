package org.client.Collection;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.client.InputOutput;

import java.util.Set;


/**
 * Клаас валидирования данных из файла
 */
public class Validators {

    /**
     *
     * @param flat объект класса Flat
     * @param msg параметр для вывода информации на экран
     */
    public final void validatorFlat(Flat flat, String msg) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Flat>> validate = validator.validate(flat);
        if (validate.size() > 0){
            new InputOutput().Output(msg);
            for(ConstraintViolation<Flat> violation : validate) {
                new InputOutput().Output(violation.getMessage());
            }
        }
        validatorFactory.close();
    }
    /**
     *
     * @param house объект класса House
     * @param msg параметр для вывода информации на экран
     */
    public final void validatorHouse(House house, String msg){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<House>> validate = validator.validate(house);
        if (validate.size() > 0){
            new InputOutput().Output(msg);
            for(ConstraintViolation<House> violation : validate) {
                new InputOutput().Output(violation.getMessage());
            }
        }
        validatorFactory.close();
    }
    /**
     *
     * @param coordinates объект класса Coordinates
     * @param msg параметр для вывода информации на экран
     */
    public final void validatorCoordinates(Coordinates coordinates, String msg){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Coordinates>> validate = validator.validate(coordinates);
        if (validate.size() > 0){
            new InputOutput().Output(msg);
            for(ConstraintViolation<Coordinates> violation : validate) {
                new InputOutput().Output(violation.getMessage());
            }
        }
        validatorFactory.close();
    }
}
