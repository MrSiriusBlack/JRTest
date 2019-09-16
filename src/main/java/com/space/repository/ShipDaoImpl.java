package com.space.repository;

import com.space.model.Ship;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShipDaoImpl  implements ShipRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Ship> getShipList() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ship").list();
    }

}
