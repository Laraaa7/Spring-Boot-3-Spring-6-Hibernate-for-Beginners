package com.lara.aopdemo.dao;

import com.lara.aopdemo.Account;

import java.util.List;

public interface AccountDAO {

    //añadir un metodo findAccounts()
    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripWire);

    void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();


    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);


}
