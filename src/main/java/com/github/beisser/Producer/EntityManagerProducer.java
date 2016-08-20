package com.github.beisser.Producer;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.logging.Logger;

/**
 * Created by Nico on 19.08.2016.
 */
@Dependent
public class EntityManagerProducer {

    final String UNITNAME = "beisser";

    @PersistenceContext(unitName = UNITNAME, type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        if (this.entityManager == null) {
            // tomcat workaround
            this.entityManager = Persistence.createEntityManagerFactory(UNITNAME).createEntityManager();
        }
        return this.entityManager;
    }

    public void dispose(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
}
