package com.growsari.application.server.util.jpa;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactory;
import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class GrowsariJpaRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends EntityGraphJpaRepositoryFactoryBean<R, T, I> {

    public GrowsariJpaRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    @SuppressWarnings({"unchecked", "NullableProblems"})
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new GrowsariJpaRepositoryFactory<T, I>(entityManager);
    }

    private static class GrowsariJpaRepositoryFactory<T, I extends Serializable>
            extends EntityGraphJpaRepositoryFactory {

        public GrowsariJpaRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }

        @Override
        @SuppressWarnings({"unchecked", "NullableProblems"})
        protected JpaRepositoryImplementation<T, I> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
            return new GrowsariJpaRepositoryImpl<>((Class<T>) information.getDomainType(), entityManager);
        }

        @Override
        @SuppressWarnings({"unchecked", "NullableProblems"})
        protected Class<GrowsariJpaRepositoryImpl> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return GrowsariJpaRepositoryImpl.class;
        }
    }
}
