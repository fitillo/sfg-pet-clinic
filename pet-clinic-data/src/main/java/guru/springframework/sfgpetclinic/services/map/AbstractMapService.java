package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected final Map<Long, T> map;

    public AbstractMapService() {
        this.map = new HashMap<>();
    }

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(this.getNextId());
            }
            map.put(object.getId(), object);
        }
        return object;
    }

    void delete(T object) {
        map.values().removeIf(element -> element.equals(object));
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    private Long getNextId() {
        try {
            return Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            return 1L;
        }
    }
}
