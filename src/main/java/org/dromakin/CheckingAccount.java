package org.dromakin;


public class CheckingAccount extends Account {
    protected int balance;

    public CheckingAccount(String name) {
        super(name);
        this.balance = 0;
    }

    @Override
    public boolean transfer(Account account, int amount) {
        boolean result = false;

        String msgErr = "Перевод денег с Расчетного аккаунта %s в аккаунт %s отклонен!\n";

        if (amount > this.balance) {
            System.out.printf(msgErr, this.name, account.name);
        } else {

            // 0. transaction open
            // 1. minus amount from current account
            this.balance -= amount;
            // in some business cases it's better to use pay function here

            // 2. add amount to account
            boolean success = account.addMoney(amount);

            // 3. check transaction
            if (!success) {
                System.out.printf(msgErr, this.name, account.name);
            } else {
                System.out.printf("Произведен перевод денег на сумму %s c Расчетного аккаунта %s на аккаунт %s\n", amount, this.name, account.name);
                result = true;
            }

        }

        return result;
    }

    @Override
    public boolean addMoney(int amount) {
        boolean result = false;

        if (amount <= 0) {
            System.out.printf("На Расчетный счет %s не возможно добавить %s\n", this.name, amount);
        } else {
            this.balance += amount;
            result = true;
            System.out.printf("На Расчетный аккаунт счета %s было добавлено %s\n", this.name, amount);
        }

        return result;
    }

    @Override
    public boolean pay(int amount) {
        boolean result = false;

        if (amount > this.balance || amount <= 0) {
            String errMsg = "Вы не можете оплатить с данного Расчетного счета, т.к. " + (amount <= 0 ? "amount <= 0!\n": "не хватает денег на счете!\n");
            System.out.printf(errMsg);
        } else {
            this.balance -= amount;
            System.out.printf("С Вашего Расчетного счета была снята сумма: %s\n", amount);
            result = true;
        }

        return result;
    }

    public void getBalance() {
        System.out.printf("Текущий баланс на Расчетном аккаунте %s : %s\n", this.name, this.balance);
    }

}
