package org.itstep;

/**
 * Java. Lesson040. Task01
 * Додати / зняти грошi з балансу
 *
 * @author Semenyuk Alexander
 * Date 7.12.2022
 * Створити екземпляр класу Account (див. демо з заняття)
 * Створити два потоки.
 * В першому в циклі 100_000 раз потрібно знімати гроші (метод withdraw) з рахунку по $1.
 * В другому в циклі 100_000 раз потрібно вкладати (метод deposit) $1 на рахунок.
 * Запустити потоки на виконання одночасно.
 * Дочекатися завершення роботи потоків і надрукувати результат балансу.
 */
public class Task01 {
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(0);
        int n = 100_000;
        int amount = 1;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                if (account.getBalance() > 0) {
                    account.withdraw(amount);
                }
            }
        });
        t1.setPriority(Thread.MAX_PRIORITY);

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                account.deposit(amount);
            }
        });
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(account);
    }
}

class Account {
    private long balance;

    Account(long balance) {
        this.balance = balance;
    }

    void deposit(int amount) {
        balance += amount;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    void withdraw(int amount) {
        balance -= amount;
    }

    @Override
    public String toString() {
        return "Balance: $" + balance;
    }
}