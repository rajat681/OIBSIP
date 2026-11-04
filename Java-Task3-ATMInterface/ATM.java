import java.util.Scanner;

public class ATM {
    private Bank bank;
    private Scanner sc;

    public ATM() {
        bank = new Bank();
        sc = new Scanner(System.in);
    }

    public void start() {
        System.out.println("===== ATM SYSTEM =====");

        int attempts = 0;
        boolean loggedIn = false;

        // Login system
        while (attempts < 3) {
            System.out.print("Enter User ID: ");
            String userId = sc.nextLine();

            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();
            sc.nextLine();

            if (bank.login(userId, pin)) {
                loggedIn = true;
                System.out.println("Login Successful!");
                break;
            } else {
                attempts++;
                System.out.println("Invalid User ID or PIN!");
                System.out.println("Attempts left: " + (3 - attempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Too many attempts. Exiting ATM.");
            return;
        }

        // ATM Menu
        int choice = 0;

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit");
            System.out.println("6. Transfer Money");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println(
                            "Available Balance: Rs. " + bank.getBalance());
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    sc.nextLine();

                    bank.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();
                    sc.nextLine();

                    bank.withdraw(withdrawAmount);
                    break;

                case 4:
                    bank.showTransactions();
                    break;

                case 5:
                    System.out.println("Thank you for using ATM!");
                    break;

                case 6:
                    System.out.print("Enter receiver User ID: ");
                    String receiverId = sc.nextLine();

                    System.out.print("Enter transfer amount: ");
                    double transferAmount = sc.nextDouble();
                    sc.nextLine();

                    bank.transfer(receiverId, transferAmount);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }
}