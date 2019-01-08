package com.zhitar.part_manager.dao;

import com.zhitar.part_manager.entity.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class PartDAOImpl implements PartDAO{

    private static final Logger loger = LoggerFactory.getLogger(PartDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addPart(Part part) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(part);
        loger.info("Part successfully saved. Part details: " + part);
    }

    @Override
    public void updatePart(Part part) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(part);
        loger.info("Part successfully update. Part details: " + part);
    }

    @Override
    public void removePart(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Part part = session.load(Part.class, id);
        if (part != null) {
            session.delete(part);
        }
        loger.info("Part successfully delete. Part details: " + part);
    }

    @Override
    public Part getPartById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println(id + "*********************************************\n\n\n");
        Part part = session.load(Part.class, id);
        System.out.println(part + "+++++++++++++++++++++++++++++++++++");
        loger.info("Part successfully loaded. Part details: " + part);
        return part;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> getAllParts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Part> parts = session.createQuery("from Part").list();

        for (Part part : parts) {
            loger.info("Part list: " + part);
        }
        return parts;
    }
}
