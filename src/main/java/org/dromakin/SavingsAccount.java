package org.dromakin;


public class SavingsAccount extends Account {
    protected int balance;

    public SavingsAccount(String name) {
        super(name);
        this.balance = 0;
    }

    @Override
    public boolean pay(int amount) {
        System.out.printf("Оплачивать со Сберегательного счета %s нельзя!", this.name);
        return false;
    }

    @Override
    public boolean transfer(Account account, int amount) {
        boolean result = false;

        String msgErr = "Перевод денег из Сберегательного аккаунта %s в аккаунт %s отклонен!\n";

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
                System.out.printf("Произведен перевод денег на сумму %s из Сберегательного аккаунта %s на аккаунт %s\n", amount, this.name, account.name);
                result = true;
            }

        }

        return result;
    }

    @Override
    public boolean addMoney(int amount) {
        boolean result = false;

        if (amount <= 0) {
            System.out.printf("На Сберегательный счет %s не возможно добавить %s\n", this.name, amount);
        } else {
            this.balance += amount;
            result = true;
            System.out.printf("На Сберегательный аккаунт счета %s было добавлено %s\n", this.name, amount);
        }

        return result;
    }

    public void getBalance() {
        System.out.printf("Текущий баланс на Сберегательном аккаунте %s : %s\n", this.name, this.balance);
    }

}
