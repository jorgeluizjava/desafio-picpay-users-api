package com.picpay.users.shared;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class FindById {

    public static <T> T execute(Long id, EntityManager manager, Class<T> tClass) {
        Assert.notNull(id, "id is required.");
        T result = manager.find(tClass, id);
        if (result == null) {
            throw new EntityNotFoundException("id: " + id + tClass.getSimpleName() + " not found.");
        }
        return result;
    }

}
