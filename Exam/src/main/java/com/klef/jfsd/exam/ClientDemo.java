


package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure and build SessionFactory
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Open a session
        Session session = sessionFactory.openSession();

        // Insert records into the database
        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setEmail("john.doe@example.com");
        customer1.setAge(28);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Jane Smith");
        customer2.setEmail("jane.smith@example.com");
        customer2.setAge(32);
        customer2.setLocation("Los Angeles");

        session.persist(customer1);
        session.persist(customer2);


        // Query using Criteria API
        System.out.println("Customers aged greater than 30:");
        Criteria criteria1 = session.createCriteria(Customer.class);
        criteria1.add(Restrictions.gt("age", 30));
        List<Customer> result1 = criteria1.list();
        result1.forEach(c -> System.out.println(c.getName()));

        System.out.println("Customers located in New York:");
        Criteria criteria2 = session.createCriteria(Customer.class);
        criteria2.add(Restrictions.eq("location", "New York"));
        List<Customer> result2 = criteria2.list();
        result2.forEach(c -> System.out.println(c.getName()));

        session.close();
        sessionFactory.close();
    }
}