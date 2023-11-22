import java.util.ArrayList;
import java.util.Scanner;

public class PersonalAccountUpdate {

    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    private int accountNumber;

    public String getAccountHolder() {
        return accountHolder;
    }
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
    private String accountHolder;

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    private double balance;
    private ArrayList<Amount> transactions;

    //methods
    public PersonalAccountUpdate(int accountNumber, String accountHolder){
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        balance=0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount){
        if (amount > 0){
            balance+=amount;
            transactions.add(new Amount(amount, "Deposit"));
        }
    }
//    public  void withdrawal(double amount){
//        if (amount > 0 && balance >= amount){
//            balance -= amount;
//            transactions.add(new Amount(amount, "Withdrawal"));
//        }else {
//            System.out.println("Insufficient funds for withdrawal.");
//        }
//    }
    public void withdrawal(double amount) throws InsufficientBalanceException{
        if (amount > balance){
            throw new InsufficientBalanceException("Insufficient funds for withdrawal.");
        } else {
            balance -= amount;
            transactions.add(new Amount(amount, "Withdrawal"));
        }



    }
    public void printTransactionHistory(){
        System.out.println("\nTransaction History for Account #" + accountNumber + " " + accountHolder + ":");
        for (Amount transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter account number: ");
        int accountNumber = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter account holder's name: ");
        String accountHolder = sc.nextLine();
        PersonalAccountUpdate account = new PersonalAccountUpdate(accountNumber,accountHolder);

        int choice;
        do {
            System.out.println("tap 1 to Deposit money || tap 2 to Withdraw money || tap 3 to deposit and withdraw || 0 to exit");
            choice = sc.nextInt();
            if (choice == 1){
                System.out.print("Enter the deposit amount: ");
                double dep = sc.nextDouble();
                account.deposit(dep);
                account.printTransactionHistory();
                System.out.println("Current Balance: $" + account.getBalance());
            }
            else if (choice == 2){
                try{
                    System.out.print("Enter the withdrawal amount: ");
                    double wth = sc.nextDouble();
                    account.withdrawal(wth);
                }catch (InsufficientBalanceException errorM){
                    System.out.println(errorM.getMessage());
                }
                System.out.println("Current Balance: $" + account.getBalance());
            }
            else if (choice == 3){
                System.out.print("Enter the deposit amount: ");
                double dep = sc.nextDouble();
                account.deposit(dep);
                try {
                    System.out.print("Enter the withdrawal amount: ");
                    double wth = sc.nextDouble();
                    account.withdrawal(wth);
                } catch (InsufficientBalanceException errorM) {
                    System.out.println(errorM.getMessage());
                }
                account.printTransactionHistory();
                System.out.println("Current Balance: $" + account.getBalance());
            }
            else if (choice == 0){
                System.out.println("Session is stopped!");
            }
            else{
                System.out.println("Invalid input!");
            }

        }while (choice != 0);

    }
}
