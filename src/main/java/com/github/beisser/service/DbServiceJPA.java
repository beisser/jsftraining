package com.github.beisser.service;

import com.github.beisser.model.User;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Nico on 19.08.2016.
 */
@ApplicationScoped
public class DbServiceJPA {

    @Inject
    private EntityManager entityManager;

    @Resource
    UserTransaction utx;

    public <T> List<T> findAll(Class<T> entityClass) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        return this.entityManager.createQuery(query.select(query.from(entityClass))).getResultList();
    }

    public <T> T findById(Class<T> entityClass, int id) {
        return this.entityManager.find(entityClass, id);
    }

    public <T> void add(T entity) throws Exception{
        utx.begin();
        this.entityManager.persist(entity);
        utx.commit();
    }

    public <T> void update(T entity) throws Exception{
        utx.begin();
        this.entityManager.merge(entity);
        utx.commit();
    }

    public <T> void delete(T entity) throws Exception{
        utx.begin();
        this.entityManager.remove(
                this.entityManager.merge(entity)
        );
        utx.commit();

    }

}
