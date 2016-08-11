package com.github.beisser.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Created by Nico on 10.08.2016.
 */
public class AppExceptionHandlerFactory extends ExceptionHandlerFactory{

    ExceptionHandlerFactory factory;

    public AppExceptionHandlerFactory(ExceptionHandlerFactory factory) {
        this.factory = factory;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new AppExceptionHandler(factory.getExceptionHandler());
    }
}
