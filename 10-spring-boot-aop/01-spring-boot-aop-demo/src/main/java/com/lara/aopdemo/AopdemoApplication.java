package com.lara.aopdemo;

import com.lara.aopdemo.dao.AccountDAO;
import com.lara.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

        return runner ->
                demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);

    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO,  MembershipDAO theMembershipDAO) {

        // llamar al metodo de negocio
        Account myAccount = new Account();
        theAccountDAO.addAccount(myAccount, true);
        theAccountDAO.doWork();

        // llamar al metodo membership
        theMembershipDAO.addSillyMember();
        theMembershipDAO.goToSleep();


    }
}
