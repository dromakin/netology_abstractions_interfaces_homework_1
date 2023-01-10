package org.dromakin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {

    Account checkingAccount;
    Account savingsAccount;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.checkingAccount = new CheckingAccount("my1");
        this.savingsAccount = new SavingsAccount("my3");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, -3, 15, Integer.MAX_VALUE})
    void transfer(int number) {
        assertFalse(this.checkingAccount.transfer(savingsAccount, number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, -3, 15, 1_000_000})
    void transferWithAddMoney(int number) {
        this.checkingAccount.addMoney(1_000);
        if (number <= 0 || number == 1_000_000) {
            assertFalse(this.checkingAccount.transfer(savingsAccount, number));
        } else {
            assertTrue(this.checkingAccount.transfer(savingsAccount, number));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, -3, 15, Integer.MAX_VALUE})
    void addMoney(int number) {
        if (number <= 0) {
            assertFalse(this.checkingAccount.addMoney(number));
        } else {
            assertTrue(this.checkingAccount.addMoney(number));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, -3, 15, Integer.MAX_VALUE})
    void pay(int number) {
        assertFalse(this.checkingAccount.pay(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, -3, 15, 1_000_000})
    void payWithAddMoney(int number) {
        this.checkingAccount.addMoney(1_000);
        if (number <= 0 || number == 1_000_000) {
            assertFalse(this.checkingAccount.pay(number));
        } else {
            assertTrue(this.checkingAccount.pay(number));
        }
    }

}