package org.dromakin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreditAccount extends Account {

    private static final Logger logger = LogManager.getLogger(CreditAccount.class);

    protected int balance;

    public CreditAccount(String name) {
        super(name);
        this.balance = 0;
    }

    @Override
    public boolean pay(int amount) {
        boolean result = false;

        if (amount <= 0) {
            logger.warn("Вы не можете оплатить с данного Кредитного счета, т.к. amount <= 0!");
        } else {
            this.balance -= amount;
            logger.info("С Вашего Кредитного счета была снята сумма: {}", amount);
            result = true;
        }

        printAttention();

        return result;
    }

    @Override
    public boolean transfer(Account account, int amount) {
        boolean result = false;

        // 0. transaction open
        // 1. minus amount from current account
        this.balance -= amount;
        // in some business cases it's better to use pay function here

        // 2. add amount to account
        boolean success = account.addMoney(amount);

        // 3. check transaction
        if (!success) {
            logger.warn("Перевод денег с Кредитного аккаунта {} в аккаунт {} отклонен!", this.name, account.name);
        } else {
            logger.info("Произведен перевод денег на сумму {} c Кредитного аккаунта {} на аккаунт {}", amount, this.name, account.name);
            result = true;
        }

        printAttention();

        return result;
    }

    @Override
    public boolean addMoney(int amount) {
        boolean result = false;

        if (amount <= 0) {
            logger.warn("На Кредитный счет {} не возможно добавить {}", this.name, amount);
        } else {
            this.balance += amount;
            result = true;
            logger.info("На аккаунт Кредитного счета {} было добавлено {}", this.name, amount);
        }

        printAttention();

        return result;
    }

    private void printAttention() {
        if (this.balance < 0) {
            logger.warn("Ваш Кредитный счет в минусе! Не забудьте пополнить счет.");
        }
    }

    public void getBalance() {
        logger.info("Текущий баланс на Кредитном аккаунте {} : {}", this.name, this.balance);
    }

}
