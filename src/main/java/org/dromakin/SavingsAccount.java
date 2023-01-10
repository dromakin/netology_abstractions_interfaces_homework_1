package org.dromakin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SavingsAccount extends Account {

    private static final Logger logger = LogManager.getLogger(SavingsAccount.class);

    protected int balance;

    public SavingsAccount(String name) {
        super(name);
        this.balance = 0;
    }

    @Override
    public boolean pay(int amount) {
        logger.warn("Оплачивать со Сберегательного счета {} нельзя!", this.name);
        return false;
    }

    @Override
    public boolean transfer(Account account, int amount) {
        boolean result = false;

        String msgErr = "Перевод денег из Сберегательного аккаунта {} в аккаунт {} отклонен!";

        if (amount > this.balance) {
            logger.warn(msgErr, this.name, account.name);
        } else {

            // 0. transaction open
            // 1. minus amount from current account
            this.balance -= amount;
            // in some business cases it's better to use pay function here

            // 2. add amount to account
            boolean success = account.addMoney(amount);

            // 3. check transaction
            if (!success) {
                logger.warn(msgErr, this.name, account.name);
            } else {
                logger.info("Произведен перевод денег на сумму {} из Сберегательного аккаунта {} на аккаунт {}", amount, this.name, account.name);
                result = true;
            }

        }

        return result;
    }

    @Override
    public boolean addMoney(int amount) {
        boolean result = false;

        if (amount <= 0) {
            logger.warn("На Сберегательный счет {} не возможно добавить {}", this.name, amount);
        } else {
            this.balance += amount;
            result = true;
            logger.info("На Сберегательный аккаунт счета {} было добавлено {}", this.name, amount);
        }

        return result;
    }

    public void getBalance() {
        logger.info("Текущий баланс на Сберегательном аккаунте {} : {}", this.name, this.balance);
    }

}
