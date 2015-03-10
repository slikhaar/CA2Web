/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Hobby;
import entity.Person;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Afrooz
 */
public class EntityFacade implements Serializable {

    public EntityFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Person createPerson(Person person) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return person;
    }

    public List<Person> getPersons() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            TypedQuery<Person> q = em.createQuery("select persons from Person persons", Person.class); //JPQL hedder sproget 
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Person addHobby(Person person, Hobby h) {
        person.addHobby(h);
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return person;
    }
    
    public Person addHobbyFromId(int personId, Hobby h) {

        EntityManager em = null;

        try {
            em = getEntityManager();
            Person p = em.find(Person.class, personId); //nullpointerException
            p.addHobby(h);
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return p;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }
    
       public Person findPerson(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }
    
}
