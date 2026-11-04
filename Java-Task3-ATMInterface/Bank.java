import java.util.ArrayList;

public class Bank {
    private Account account;
    private Account receiverAccount;
    private ArrayList<Transaction> transactions;

    public Bank() {
        account = new Account("user123", 1234, 5000);
        receiverAccount = new Account("user456", 4321, 3000);

        transactions = new ArrayList<>();
    }

    public boolean login(String userId, int pin) {
        return account.getUserId().equals(userId)
                && account.checkPin(pin);
    }

    public double getBalance() {
        return account.getBalance();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            account.deposit(amount);
            transactions.add(new Transaction("Deposit", amount));
        } else {
            System.out.println("Invalid amount!");
        }
    }

    public void withdraw(double amount) {
        double oldBalance = account.getBalance();

        if (amount > 0 && amount <= oldBalance) {
            account.withdraw(amount);
            transactions.add(new Transaction("Withdrawal", amount));
        } else if (amount <= 0) {
            System.out.println("Invalid amount!");
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void transfer(String receiverId, double amount) {

        if (!receiverAccount.getUserId().equals(receiverId)) {
            System.out.println("Receiver account not found!");
            return;
        }

        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (amount > account.getBalance()) {
            System.out.println("Insufficient balance!");
            return;
        }

        account.withdraw(amount);
        receiverAccount.deposit(amount);

        transactions.add(
                new Transaction("Transfer to " + receiverId, amount));

        System.out.println("Money transferred successfully!");
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("\n--- Transaction History ---");

            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}