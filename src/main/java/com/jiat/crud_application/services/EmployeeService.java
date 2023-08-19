package com.jiat.crud_application.services;

import com.jiat.crud_application.entity.Employee;
import com.jiat.crud_application.util.HibernateUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class EmployeeService {
    public List<Employee> getEmployeeList() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            List<Employee> employees = session.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
            return employees;
        }catch (Exception e){
            return null;
        }
    }

    public List<Employee> getEmployeeListBySearch(String search){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        try{
            CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = query.from(Employee.class);
            query.select(root).where(criteriaBuilder.or(
                    criteriaBuilder.equal(root.get("name"), search),
                    criteriaBuilder.equal(root.get("position"), search),
                    criteriaBuilder.equal(root.get("department"), search)
            ));
            return session.createQuery(query).getResultList();
        }catch (Exception e){
            return null;
        }
}

    public Employee getEmployee(String search) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
           return session.createQuery("SELECT e FROM Employee e WHERE e.id=:id", Employee.class)
                    .setParameter("id",search).getSingleResult();
        }catch(NoResultException e){
            return  null;
        }
    }

    public String updateEmployee(String id, String name, String position, String department, String salary) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, id);
        if (employee != null) {
            employee.setName(name);
            employee.setPosition(position);
            employee.setDepartment(department);
            employee.setSalary(Double.valueOf(salary));
        } else {
            return "fail";
        }
        transaction.commit();
        return "success";
    }

    public String insertEmployee(String name, String position, String department, Double salary) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Employee employee = new Employee();
            employee.setName(name);
            employee.setPosition(position);
            employee.setDepartment(department);
            LocalDate date = LocalDate.now();
            employee.setHire_date(date);
            employee.setSalary(salary);
            session.merge(employee);
        }catch(Exception e){
            return "fail";
        }
        transaction.commit();

        return "success";
    }


    public String deleteEmployee(Long id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        if(employee!=null){
            session.remove(employee);
        }else {
            return "fail";
        }
        transaction.commit();
        return "success";
    }
}
