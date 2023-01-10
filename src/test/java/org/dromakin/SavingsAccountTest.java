package org.dromakin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SavingsAccountTest {

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
        assertFalse(this.savingsAccount.pay(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, -3, 15, 1_000_000})
    void transferWithAddMoney(int number) {
        this.savingsAccount.addMoney(1_000);
        if (number <= 0 || number == 1_000_000) {
            assertFalse(this.savingsAccount.transfer(creditAccount, number));
        } else {
            assertTrue(this.savingsAccount.transfer(creditAccount, number));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, -3, 15, Integer.MAX_VALUE})
    void addMoney(int number) {
        if (number <= 0) {
            assertFalse(this.savingsAccount.addMoney(number));
        } else {
            assertTrue(this.savingsAccount.addMoney(number));
        }
    }
}