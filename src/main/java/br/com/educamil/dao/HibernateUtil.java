package br.com.educamil.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import br.com.educamil.entity.*;


public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration cfg = new Configuration();       
			 cfg.addAnnotatedClass(Endereco.class);
			 cfg.addAnnotatedClass(Pessoa.class);
			 cfg.addAnnotatedClass(Aluno.class);
			 cfg.addAnnotatedClass(Professor.class);
			 cfg.addAnnotatedClass(Disciplina.class);
			 cfg.addAnnotatedClass(Turma.class);
			 cfg.addAnnotatedClass(Nota.class);
			 cfg.addAnnotatedClass(Direcao.class);
			 cfg.addAnnotatedClass(Perfil.class);
                    
            cfg.configure("/br/com/educamil/dao/hibernate.cfg.xml");
            StandardServiceRegistryBuilder build = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(build.build());
        } catch (HibernateException ex) {
            System.err.println("Erro ao criar Hibernate util." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session abrirSessao() {
        return sessionFactory.openSession();
    }
}
