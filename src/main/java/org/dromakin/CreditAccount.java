package org.dromakin;


public class CreditAccount extends Account {
    protected int balance;

    public CreditAccount(String name) {
        super(name);
        this.balance = 0;
    }

    @Override
    public boolean pay(int amount) {
        boolean result = false;

        if (amount <= 0) {
            System.out.printf("Вы не можете оплатить с данного Кредитного счета, т.к. amount <= 0!\n");
        } else {
            this.balance -= amount;
            System.out.printf("С Вашего Кредитного счета была снята сумма: %s\n", amount);
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
            System.out.printf("Перевод денег с Кредитного аккаунта %s в аккаунт %s отклонен!\n", this.name, account.name);
        } else {
            System.out.printf("Произведен перевод денег на сумму %s c Кредитного аккаунта %s на аккаунт %s\n", amount, this.name, account.name);
            result = true;
        }

        printAttention();

        return result;
    }

    @Override
    public boolean addMoney(int amount) {
        boolean result = false;

        if (amount <= 0) {
            System.out.printf("На Кредитный счет %s не возможно добавить %s\n", this.name, amount);
        } else {
            this.balance += amount;
            result = true;
            System.out.printf("На аккаунт Кредитного счета %s было добавлено %s\n", this.name, amount);
        }

        printAttention();

        return result;
    }

    private void printAttention() {
        if (this.balance < 0) {
            System.out.printf("Ваш Кредитный счет в минусе! Не забудьте пополнить счет.\n");
        }
    }

    public void getBalance() {
        System.out.printf("Текущий баланс на Кредитном аккаунте %s : %s\n", this.name, this.balance);
    }

}
