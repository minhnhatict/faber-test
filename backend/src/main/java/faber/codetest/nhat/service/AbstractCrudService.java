package faber.codetest.nhat.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<T, S> {

    public T findById(S id){
        Optional<T>  optionalT = getRepository().findById(id);
        return optionalT.orElse(null);
    }

    public void save(T t){
        getRepository().save(t);
    }

    public void delete(T t){
        getRepository().delete(t);
    }

    public void deleteById(S id){
        getRepository().deleteById(id);
    }

    /**
     * parent method does not support to get all due to security, only implemented in concrete classes
     *
     * @return
     */
    public List<T> findAll(){
        return Collections.EMPTY_LIST;
    }

    public abstract JpaRepository<T,  S> getRepository();

}
