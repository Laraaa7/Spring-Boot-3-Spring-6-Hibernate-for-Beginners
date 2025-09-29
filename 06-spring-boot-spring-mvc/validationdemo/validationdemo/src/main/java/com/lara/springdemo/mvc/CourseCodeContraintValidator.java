package com.lara.springdemo.mvc;

import com.lara.springdemo.mvc.validation.CourseCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static org.attoparser.util.TextUtil.startsWith;

public class CourseCodeContraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode theCourseCode) {
    coursePrefix = theCourseCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {

       boolean result;
       if(theCode!=null){
           result=theCode.startsWith(coursePrefix);
       }else{
           result=true;
       }

       return result;
    }
}
