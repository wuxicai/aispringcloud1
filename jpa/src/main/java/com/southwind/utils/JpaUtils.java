package com.southwind.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
    private static EntityManagerFactory factory;
    static {
        factory= Persistence.createEntityManagerFactory("myJpa");
    }
    public static EntityManager getEntityManagerFactory(){
        return factory.createEntityManager();
    }
}
