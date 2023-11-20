package com.xzedu.article.annotations;

import com.xzedu.article.validations.StateValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class})
public @interface State {
    String message() default "文章状态只能是 已发布或者草稿";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
