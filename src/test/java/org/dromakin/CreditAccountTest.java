package org.dromakin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreditAccountTest {

    Account creditAccount;
    Account savingsAccount;

    @BeforeEach
    void setUp() {
        this.creditAccount = new CreditAccount("my1");
        this.savingsAccount = new SavingsAccount("my3");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, -3, 15, Integer.MAX_VALUE})
    void pay(int number) {
        if (number <= 0) {
            assertFalse(this.creditAccount.pay(number));
        } else {
            assertTrue(this.creditAccount.pay(number));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, -3, 15, 1_000_000})
    void transferWithAddMoney(int number) {
        if (number <= 0) {
            assertFalse(this.creditAccount.transfer(savingsAccount, number));
        } else {
            assertTrue(this.creditAccount.transfer(savingsAccount, number));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, -3, 15, Integer.MAX_VALUE})
    void addMoney(int number) {
        if (number <= 0) {
            assertFalse(this.creditAccount.addMoney(number));
        } else {
            assertTrue(this.creditAccount.addMoney(number));
        }
    }
}