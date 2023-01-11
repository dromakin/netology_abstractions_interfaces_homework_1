package org.dromakin;


public abstract class Account {

    protected String name;

    protected Account(String name) {
        this.name = name;
    }

    public abstract boolean pay(int amount);

    public abstract boolean transfer(Account account, int amount);

    public abstract boolean addMoney(int amount);

}
