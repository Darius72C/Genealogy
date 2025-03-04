/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.utilitiesDB;

/**
 *
 * @author FACULTY
 */
//import mainEntities.Employee;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Repository;
//import mainEntities.Account;

@Repository
public class DataManager {
    private static DataManager dataManager = null;
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("genealogyPU");
        private final EntityManager em;

    private DataManager() {
        em = emf.createEntityManager();
    }

    public static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public EntityManagerFactory getEntityManagerFactory(){
        return emf;
    } 
    /**
     * All changes that have been made to the managed entities in the
     * persistence context are applied to the database and committed.
     */
    private EntityTransaction getTransaction() {
        return em.getTransaction();
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> void persistEntity(T entity) {
        EntityTransaction entityTransaction = getTransaction();
        if (!em.getTransaction().isActive()) {
            entityTransaction.begin();
        }
        em.persist(entity);
        entityTransaction.commit();
    }

    public <T> T mergeEntity(T entity) {
        EntityTransaction entityTransaction = getTransaction();
        if (!em.getTransaction().isActive()) {
            entityTransaction.begin();
        }
        entity = em.merge(entity);
        entityTransaction.commit();
        return entity;
    }

    public <T> void removeEntity(T entity) {
        EntityTransaction entityTransaction = getTransaction();
        if (!em.getTransaction().isActive()) {
            entityTransaction.begin();
        }
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
        entityTransaction.commit();
    }

    public EntityManager getEntityManager() {
        return getInstance().em;
    }

    public void clearPersistedObjects() {
        getInstance().em.clear();
    }

    @PreDestroy
    public void destruct() {
        emf.close();
    }

    public <T> boolean constraintValidationsDetected(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());
            }
            return true;
        } else { 
            return false;
        }
    }

    public static void main(String[] args) {
        try {
           // dataManager = getInstance();
           // Query query = dataManager.getEntityManager().createQuery("select e from Employee e");
            //List<Employee> employeeList = query.getResultList();
          /*  Account employee =MainDAO.findAccount("ACCT_1");
            System.out.println(employee.getUserName());*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
