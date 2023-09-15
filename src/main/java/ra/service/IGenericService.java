package ra.service;

import java.util.List;


public interface IGenericService<K,V, E> {
    List<K> findAll();

    K findById(E id);

    K save(V v);
    K update(V v,E id);

    K delete(E id);
}
