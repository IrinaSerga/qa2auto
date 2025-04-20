package qa2auto.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao <K, E>{
     Optional <E> findById(K id);
     List<E> findAll();
     E save(E entity);
     E update(E entity);
     void delete(E entity);
     boolean deleteById(K id);


}
