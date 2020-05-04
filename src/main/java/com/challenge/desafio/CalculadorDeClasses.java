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
        Field[] fields = object.getClass().getDeclaredFields();
        Method[] methods = object.getClass().getMethods();
        BigDecimal total = BigDecimal.ZERO;

        for (Field field : fields) {
            if (field.isAnnotationPresent(annotation) && field.getType().equals(BigDecimal.class)) {
                if (field.isAccessible()) {
                    try {
                        total = total.add((BigDecimal) field.get(object));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    for (Method method : methods) {
                        if (method.getName().toUpperCase().equals("GET".concat(field.getName().toUpperCase()))) {
                            try {
                                total = total.add((BigDecimal) method.invoke(object));
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return total;
    }
}