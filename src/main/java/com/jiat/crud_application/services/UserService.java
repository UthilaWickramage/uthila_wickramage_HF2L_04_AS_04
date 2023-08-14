package com.jiat.crud_application.services;

import com.jiat.crud_application.model.UserDetails;
import com.jiat.crud_application.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserService {
    public UserDetails getUserByName(String name){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        return new UserDetails("kamal","1234");
    }
}
