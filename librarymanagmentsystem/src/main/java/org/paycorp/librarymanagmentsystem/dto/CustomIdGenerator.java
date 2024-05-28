package org.paycorp.librarymanagmentsystem.dto;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomIdGenerator implements IdentifierGenerator {

    private static int nextId = 100;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "PAY";
        String generatedId = prefix + nextId;
        nextId++;
        return generatedId;
    }
}
