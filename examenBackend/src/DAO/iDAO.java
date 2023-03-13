package DAO;

import java.util.List;

public interface iDAO <T>{
    public T guardar(T t);

    public List<T> listarTodos();

}
