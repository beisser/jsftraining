package com.github.beisser.service;

import com.github.beisser.model.User;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Nico on 19.08.2016.
 */
@ApplicationScoped
public class DbServiceJPA {

    @Inject
    private EntityManager entityManager;

    public <T> List<T> findAll(Class<T> entityClass) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        return this.entityManager.createQuery(query.select(query.from(entityClass))).getResultList();
    }

    public <T> T findById(Class<T> entityClass, int id) {
        return this.entityManager.find(entityClass, id);
    }

    public <T> void add(T entity) {
        this.entityManager.persist(entity);
    }

    public <T> void update(T entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(entity);
    }

    public <T> void delete(T entity) {

    }

    public void flush() {
        this.entityManager.getTransaction().commit();
    }

    public void closeEm() {
        if(this.entityManager.getEntityManagerFactory().isOpen()) {
            this.entityManager.getEntityManagerFactory().close();
        }
        if(this.entityManager.isOpen()) {
            this.entityManager.close();
        }
    }
}
