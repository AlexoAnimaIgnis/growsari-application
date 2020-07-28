package com.growsari.application.server.util.jpa;

import java.io.Serializable;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@Transactional(propagation = Propagation.MANDATORY)
public interface GrowsariJpaRepository<T, ID extends Serializable> extends JpaRepositoryImplementation<T, ID>,
        EntityGraphJpaRepository<T, ID>, EntityGraphJpaSpecificationExecutor<T> {
    /**
     * Clear persistence context.
     */
    void clear();

    /**
     * It returns
     *
     * @param id the primary key
     * @return the object when exists or null otherwise
     */
    T get(ID id);

    /**
     * It persists or update the entity.
     *
     * @param entity the entity
     * @return the entity
     */
    T saveOrUpdate(T entity);

    /**
     * Re-read the state of the given instance from the underlying database.
     *
     * @param entity a persistent or detached instance
     */
    void refresh(T entity);

    /**
     * Remove the given entity from the persistence context, causing
     * a managed entity to become detached.
     *
     * @param entity entity instance
     * @throws IllegalArgumentException if the instance is not an entity
     */
    void detach(T entity);
}
