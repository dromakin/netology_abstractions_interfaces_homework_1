package org.dromakin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Account {

    private static final Logger logger = LogManager.getLogger(Account.class);

    protected String name;

    protected Account(String name) {
        this.name = name;
    }

    public abstract boolean pay(int amount);

    public abstract boolean transfer(Account account, int amount);

    public abstract boolean addMoney(int amount);

}
