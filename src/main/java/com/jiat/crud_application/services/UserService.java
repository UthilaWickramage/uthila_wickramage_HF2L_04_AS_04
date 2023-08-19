package com.jiat.crud_application.services;

import com.jiat.crud_application.entity.User;
import com.jiat.crud_application.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class UserService {
    public User getUserByNameAndPassword(String name,String password){

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query<User> query = session.createNamedQuery("User.getUserByEmailAndPassword",User.class);
        query.setParameter("name",name);
        query.setParameter("password",password);
        User user = query.getSingleResult();
        return user;
    }
}
