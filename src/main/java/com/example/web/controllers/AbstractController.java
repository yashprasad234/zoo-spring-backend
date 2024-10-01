package com.example.web.controllers;

import java.lang.reflect.ParameterizedType;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.RequestMapping;

public abstract class AbstractController<T>
{
	@SuppressWarnings("unchecked")
	private final Class<T> objectClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	public String[] getMapping()
	{
		return getClass().getAnnotation(RequestMapping.class).value();
	}
	
	public void log()
	{
		Stream.of(getMapping()).forEach(t -> {
			System.out.println("this is " + getClass().getSimpleName() + " which is used to handle " + objectClass + " object with mapping " + t);
		});
		
	}
}
