package com.henry.forum.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaDaoHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao{

    @Override
    public String select() {
        return "Hibernate";
    }

    @Override
    public int sum() {
        return 10;
    }
}
