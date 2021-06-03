package org.mentor.dao;

import org.mentor.model.Credential;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class CredentialsDaoImpl implements CredentialsDao{

    private final EntityManager entityManager;

    public CredentialsDaoImpl(JPAEntityManager jpaEntityManager) {
        this.entityManager = jpaEntityManager.getEntityManager();
    }

    @Override
    public Integer findIdByLogin(String login) {
        Integer found_id;
        Query query = entityManager.createQuery("SELECT e.id FROM Credential e WHERE e.login = ?1");
        query.setParameter(1, login);
        try {
            found_id = (Integer) query.getSingleResult();
        }
        catch (RuntimeException e){
            found_id = null;
        }
        return found_id;
    }

    @Override
    public Credential findById(Integer id) {
        return entityManager.find(Credential.class, id);
    }

    @Override
    public void updateCredential(Credential credential) {
        entityManager.getTransaction().begin();
        entityManager.merge(credential);
        entityManager.getTransaction().commit();
    }
}
