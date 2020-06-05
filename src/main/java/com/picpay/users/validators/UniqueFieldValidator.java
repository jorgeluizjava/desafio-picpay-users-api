package com.picpay.users.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.domainAttribute = constraintAnnotation.domainAttribute();
        this.klass = constraintAnnotation.klass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Query query = manager.createQuery("select 1 from "+klass.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value", value);
        return query.getResultList().isEmpty();
    }
}
