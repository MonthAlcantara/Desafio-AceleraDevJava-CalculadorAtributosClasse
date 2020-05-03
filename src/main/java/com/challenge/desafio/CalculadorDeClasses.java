package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;


public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object object) {

        return realizarOperacao(object, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object object) {

        return realizarOperacao(object, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object object) {

        return somar(object).subtract(subtrair(object));
    }

    private BigDecimal realizarOperacao(Object object, Class annotation) {
        Class classe = object.getClass();
        Field[] fields = classe.getDeclaredFields();
        BigDecimal total = BigDecimal.ZERO;

        for (Field field : fields) {
            if (field.getAnnotatedType().equals(annotation)) {
                field.setAccessible(true);
                try {
                    Object value = classe.getField(field.getName());
                    total = total.add((BigDecimal) value);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }

            }
        }
        return total;
    }
}