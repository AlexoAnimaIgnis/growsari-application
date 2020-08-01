package com.growsari.application.server.util.jpa;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphSimpleJpaRepository;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.io.Serializable;

public class GrowsariJpaRepositoryImpl<T, ID extends Serializable> extends EntityGraphSimpleJpaRepository<T, ID>
        implements GrowsariJpaRepository<T, ID> {

    private EntityManager entityManager;
    private Class<T> domainClass;
    private JpaEntityInformation<T, ?> entityInformation;

    public GrowsariJpaRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
        this.entityManager = entityManager;
        this.domainClass = domainClass;
    }

    @Override
    public void clear() {
        this.entityManager.clear();
    }

    @Override
    public T get(ID id) {
        if(existsById(id)) {
            return getOne(id);
        } else {
            return null;
        }
    }

    @Override
    public T saveOrUpdate(T entity) {
        Session session = entityManager.unwrap(Session.class);

        if (isVersioned()) {
            session.saveOrUpdate(entity);
        } else if (entityInformation.isNew(entity)) {
            session.save(entity);
        } else {
            session.update(entity);
        }

        return entity;
    }

    private boolean isVersioned() {
        EntityType<T> entityType = entityManager.getMetamodel().entity(domainClass);

        return entityType.hasVersionAttribute();
    }

    @Override
    public void refresh(T entity) {
        Session session = entityManager.unwrap(Session.class);
        session.refresh(entity);
    }

    @Override
    public void detach(T entity) {
        entityManager.detach(entity);
    }
}
