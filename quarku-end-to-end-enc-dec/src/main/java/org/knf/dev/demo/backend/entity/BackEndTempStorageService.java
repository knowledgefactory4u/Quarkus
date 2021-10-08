package org.knf.dev.demo.backend.entity;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
public class BackEndTempStorageService {

    @Inject
    EntityManager entityManager;

    public BackEndTempStorage get(String id) {
        return entityManager.find(BackEndTempStorage.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public BackEndTempStorage save(BackEndTempStorage data) {
        entityManager.persist(data);
        return data;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(String id) {
        BackEndTempStorage user = get(id);
        entityManager.remove(user);
    }
} 
