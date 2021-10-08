package org.knf.dev.demo.mobilewebsimulator.entity;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Singleton
public class MobileTempStorageService {

    @Inject
    EntityManager entityManager;

    public MobileTempStorage get(String id) {
        return entityManager.find(MobileTempStorage.class, id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public MobileTempStorage save(MobileTempStorage data) {
        entityManager.persist(data);
        return data;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(String id) {
        MobileTempStorage user = get(id);
        entityManager.remove(user);
    }
} 
