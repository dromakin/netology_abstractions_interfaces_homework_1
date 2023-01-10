package org.dromakin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Account checkingAccount = new CheckingAccount("my1");
        Account creditAccount = new CreditAccount("my2");
        Account savingsAccount = new SavingsAccount("my3");

        // у нас есть 10_000_000 рублей, распределим их на 3 карты.
        boolean cha = checkingAccount.addMoney(4_000_000);
        boolean cra = creditAccount.addMoney(1_000_000);
        boolean sa = savingsAccount.addMoney(5_000_000);

        logger.info("result addMoney: checkingAccount {} | creditAccount {} | savingsAccount {}", cha, cra, sa);

        ((CheckingAccount) checkingAccount).getBalance();
        ((CreditAccount) creditAccount).getBalance();
        ((SavingsAccount) savingsAccount).getBalance();

        // покупаем в кредит машину за 2 млн. по кредитной карте
        cra = creditAccount.pay(2_000_000);

        ((CreditAccount) creditAccount).getBalance();

        // пытаемся купить квартиру за 7 млн. рублей
        cha = checkingAccount.pay(7_000_000);
        sa = savingsAccount.pay(7_000_000);

        logger.info("result pay: checkingAccount {} | creditAccount {} | savingsAccount {}", cha, cra, sa);

        ((CheckingAccount) checkingAccount).getBalance();
        ((CreditAccount) creditAccount).getBalance();
        ((SavingsAccount) savingsAccount).getBalance();

        // перебрасываем с кредитного счета деньги на расчетный и снова пытаемся оплатить
        cra = creditAccount.transfer(checkingAccount, 1_000_000);

        ((CheckingAccount) checkingAccount).getBalance();
        ((CreditAccount) creditAccount).getBalance();

        // попытка покупки
        cha = checkingAccount.pay(7_000_000);
        sa = savingsAccount.pay(7_000_000);

        // перебрасываем деньги со сберегательного на расчетный
        sa = savingsAccount.transfer(checkingAccount, 5_000_000);

        ((CheckingAccount) checkingAccount).getBalance();
        ((SavingsAccount) savingsAccount).getBalance();

        // покупаем квартиру
        cha = checkingAccount.pay(7_000_000);

        ((CheckingAccount) checkingAccount).getBalance();

        // распределяем оставшуюся часть суммы на кредитную карту и сберегательный счет
        // по ошибке указали перевод в 0 рублей
        cha = checkingAccount.transfer(creditAccount, 0);

        logger.info("result transfer: checkingAccount {} | creditAccount {} | savingsAccount {}", cha, cra, sa);

        cha = checkingAccount.transfer(creditAccount, 2_000_000);
        cha = checkingAccount.transfer(savingsAccount, 1_000_000);

        ((CheckingAccount) checkingAccount).getBalance();
        ((CreditAccount) creditAccount).getBalance();
        ((SavingsAccount) savingsAccount).getBalance();

    }
}