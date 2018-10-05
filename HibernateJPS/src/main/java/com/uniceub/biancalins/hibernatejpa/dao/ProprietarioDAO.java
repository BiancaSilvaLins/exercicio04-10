/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniceub.biancalins.hibernatejpa.dao;

import com.uniceub.biancalins.hibernatejpa.Proprietario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.hibernate.HibernateException;

/**
 *
 * @author bianca.lins
 */
public class ProprietarioDAO {


    private PersistenceManager manager;

    public ProprietarioDAO() {
        manager = PersistenceManager.getInstance();
    }

    public void insert(Proprietario proprietario) {

        // recuperar o entity manager
        EntityManager entityManager = manager.getEntityManager();

        // recuperar o objeto que vai cuidar da transação
        final EntityTransaction transaction = entityManager.getTransaction();

        // iniciar a transação
        transaction.begin();

        entityManager.persist(proprietario);

        // commit da transação
        transaction.commit();

        // fechar o entity manager
        entityManager.close();

    }

    public List<String> findAll() {

        List<String> retorno = null;
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            // recuperar o entity manager
            entityManager = manager.getEntityManager();

            // recuperar o objeto que vai cuidar da transação
            transaction = entityManager.getTransaction();

            // iniciar a transação
            transaction.begin();


            // commit da transação
            transaction.commit();

        } catch (Exception e) {
            // rollback da transação
            if (transaction != null) {
                transaction.rollback();
            }

            throw new HibernateException("Erro do executar o método findAll de ProprietarioDAO", e);

        } finally {
            // fechar o entity manager
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return retorno;
    }

    public List<Proprietario> findByName(String nomeFiltro) {
        
        List<Proprietario> retorno = null;
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            // recuperar o entity manager
            entityManager = manager.getEntityManager();

            // recuperar o objeto que vai cuidar da transação
            transaction = entityManager.getTransaction();

            // iniciar a transação
            transaction.begin();

            Query query = entityManager.createQuery("from Proprietario where nome_proprietario = :nomeParametro");
            query.setParameter("nomeParametro", nomeFiltro);
            retorno = query.getResultList();

            // commit da transação
            transaction.commit();

        } catch (Exception e) {
            // rollback da transação
            if (transaction != null) {
                transaction.rollback();
            }

            throw new HibernateException("Erro do executar o método findAll de ProprietarioDAO", e);

        } finally {
            // fechar o entity manager
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return retorno;
    }

    public Proprietario findById(Integer id) {

        Proprietario retorno = null;

        // recuperar o entity manager
        EntityManager entityManager = manager.getEntityManager();

        // recuperar o objeto que vai cuidar da transação
        final EntityTransaction transaction = entityManager.getTransaction();

        // iniciar a transação
        transaction.begin();

        retorno = entityManager.find(Proprietario.class, id);

        // commit da transação
        transaction.commit();

        // fechar o entity manager
        entityManager.close();

        return retorno;
    }

    public Proprietario findByIdUsingReference(Integer id) {

        Proprietario retorno = null;

        // recuperar o entity manager
        EntityManager entityManager = manager.getEntityManager();

        // recuperar o objeto que vai cuidar da transação
        final EntityTransaction transaction = entityManager.getTransaction();

        // iniciar a transação
        transaction.begin();

        retorno = entityManager.getReference(Proprietario.class, id);
        System.out.println("Não executou a consulta ainda");

        retorno.getNome();

        System.out.println("Agora sim");

        //OBS: CUIDADO! Se for utilizar o método gerReference, sincronizar bem 
        // a execução dos métodos getters antes do commit da transação. Após
        // o commit da transação, a sessão com o BD é fechada.
        // commit da transação
        transaction.commit();

        // fechar o entity manager
        entityManager.close();

        return retorno;
    }

    public void update(Proprietario proprietario) {

        // recuperar o entity manager
        EntityManager entityManager = manager.getEntityManager();

        // recuperar o objeto que vai cuidar da transação
        EntityTransaction transaction = entityManager.getTransaction();

        // iniciar a transação
        transaction.begin();

        // recupera o objeto original no BD
        Proprietario proprietarioBD = entityManager.find(Proprietario.class, proprietario.getId());
        System.out.println("Valor de proprietario: " + proprietarioBD);

        // atualiza o objeto
        proprietarioBD.setNome(proprietario.getNome());
        System.out.println("Novo valor de proprietario: " + proprietarioBD);

        // commit da transação
        transaction.commit();
        
        // fechar o entity manager
        entityManager.close();

        
        entityManager = manager.getEntityManager();
        transaction = entityManager.getTransaction();

        transaction.begin();
        
        entityManager.merge(proprietarioBD);

        transaction.commit();
        entityManager.close();
    }

    public void remove(Proprietario proprietario) {

        // recuperar o entity manager
        EntityManager entityManager = manager.getEntityManager();

        // recuperar o objeto que vai cuidar da transação
        final EntityTransaction transaction = entityManager.getTransaction();

        // iniciar a transação
        transaction.begin();

        // recupera o objeto original no BD
        Proprietario proprietarioBD = entityManager.find(Proprietario.class, proprietario.getId());

        // remove uma Pessoa do BD
        entityManager.remove(proprietarioBD);

        // commit da transação
        transaction.commit();

        // fechar o entity manager
        entityManager.close();
    }

}
