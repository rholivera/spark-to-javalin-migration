package com.example.utils;


import static org.apache.commons.lang3.exception.ExceptionUtils.getThrowables;
public abstract class ExceptionUtils {

    public static boolean existsInChain(Throwable throwable, Class<? extends Throwable> clazz) {
        return org.apache.commons.lang3.exception.ExceptionUtils.indexOfType(throwable, clazz) != -1;
    }

    public static Throwable getFromChain(Throwable throwable, Class<? extends Throwable> clazz) {
        return ExceptionUtils.existsInChain(throwable, clazz)
                ? getThrowables(throwable)[org.apache.commons.lang3.exception.ExceptionUtils.indexOfType(throwable, clazz)]
                : throwable;
    }

}