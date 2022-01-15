package se.haris.booklender.service.intefaces;

import java.util.List;

public interface GenericCRUD<R,T,String> {
    R create(T dto);
    R findById(String id);
    List<R> findAll();
    R update(String id, T dto);
    boolean delete(String id);
}
