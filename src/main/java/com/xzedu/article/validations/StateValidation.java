package com.xzedu.article.validations;

import com.xzedu.article.annotations.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName : StateValidation
 * @Description : StateValidation
 * @Author : Xxxxx
 * @Date: 2023-11-19 12:43
 */
public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return "已发布".equals(s) || "草稿".equals(s);
    }
}
