package com.prodyna.sb.library.common.service;


public interface PersonalService<T> {
    public T find(Long id);
    public T save(T t);
}
