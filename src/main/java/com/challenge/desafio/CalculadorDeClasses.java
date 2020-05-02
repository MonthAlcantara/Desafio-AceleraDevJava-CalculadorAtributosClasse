package com.challenge.desafio;


import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;


import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;


public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object object) {
        Class objeto = Object.class; //Peguei a Classe
        Field[] fields = objeto.getFields(); // Peguei os atributos
        Object[] fieldSomar = new Object[fields.length];
        Object myObject = new Object();

        int i = 0;
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Subtrair.class) && !field.isAnnotationPresent(Subtrair.class)) {
                return BigDecimal.ZERO;
            }
            if (field.isAnnotationPresent(Somar.class)) {
                if (field.getType() != BigDecimal.class) {
                    return BigDecimal.ZERO;
                } else {
                    try {
                        fieldSomar[i] = field.get(myObject);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return (BigDecimal) Arrays.stream(fieldSomar).iterator();

    }

    @Override
    public BigDecimal subtrair(Object object) {
        Class objeto = Object.class; //Peguei a Classe
        Field[] fields = objeto.getFields(); // Peguei os atributos
        Object[] fieldSomar = new Object[fields.length];
        Object myObject = new Object();

        int i = 0;
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Subtrair.class) && !field.isAnnotationPresent(Subtrair.class)) {
                return BigDecimal.ZERO;
            }
            if (field.isAnnotationPresent(Subtrair.class)) {
                if (field.getType() != BigDecimal.class) {
                    return BigDecimal.ZERO;
                } else {
                    try {
                        fieldSomar[i] = field.get(myObject);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return (BigDecimal) Arrays.stream(fieldSomar).iterator();

    }

    @Override
    public BigDecimal totalizar(Object object) {
        return somar(object).subtract(subtrair(object));
    }


}
