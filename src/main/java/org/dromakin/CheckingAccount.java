package org.dromakin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckingAccount extends Account {

    private static final Logger logger = LogManager.getLogger(CheckingAccount.class);

    protected int balance;

    public CheckingAccount(String name) {
        super(name);
        this.balance = 0;
    }

    @Override
    public boolean transfer(Account account, int amount) {
        boolean result = false;

        String msgErr = "Перевод денег с Расчетного аккаунта {} в аккаунт {} отклонен!";

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
                logger.info("Произведен перевод денег на сумму {} c Расчетного аккаунта {} на аккаунт {}", amount, this.name, account.name);
                result = true;
            }

        }

        return result;
    }

    @Override
    public boolean addMoney(int amount) {
        boolean result = false;

        if (amount <= 0) {
            logger.warn("На Расчетный счет {} не возможно добавить {}", this.name, amount);
        } else {
            this.balance += amount;
            result = true;
            logger.info("На Расчетный аккаунт счета {} было добавлено {}", this.name, amount);
        }

        return result;
    }

    @Override
    public boolean pay(int amount) {
        boolean result = false;

        if (amount > this.balance || amount <= 0) {
            String errMsg = "Вы не можете оплатить с данного Расчетного счета, т.к. " + (amount <= 0 ? "amount <= 0!": "не хватает денег на счете!");
            logger.warn(errMsg);
        } else {
            this.balance -= amount;
            logger.info("С Вашего Расчетного счета была снята сумма: {}", amount);
            result = true;
        }

        return result;
    }

    public void getBalance() {
        logger.info("Текущий баланс на Расчетном аккаунте {} : {}", this.name, this.balance);
    }

}
