package com.lara.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public boolean addSillyMember() {

        System.out.println(getClass() + " :DOING MY DB WORK: ADDING A MEMERSHIP ACCOUNT");
    return true;
    }

    @Override
    public void goToSleep() {

        System.out.println(getClass() + " : I'm going to seep now...");
    }
}
