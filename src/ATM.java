import java.util.Scanner;
public class ATM {
    private int PIN;
    private int newPIN;
    private int enteredPIN;
    private String name;
    private int answer = 0;
    private String response;
    private int depositAmount;
    private String depositAccountType;
    private String moneyFrom;
    private int amountTransferred;
    private int withdrawAmount;
    private boolean validAmount = false;
    private boolean error;
    private int securityPIN = -1;
    private int transactionID = 1000;
    Scanner scan = new Scanner(System.in);

    public void welcomeUser(){
        System.out.println("Welcome to Adam's ATM. Please enter your name");
        name = scan.nextLine();
        System.out.println("Please choose your PIN.");
        PIN = scan.nextInt();
        scan.nextLine();
    }
    public void start(){
        welcomeUser();
        while(securityPIN != PIN) {
            System.out.println("Please enter your PIN");
            securityPIN = scan.nextInt();
            if (securityPIN != PIN) {
                System.out.println("Your PIN is incorrect, please try again.");
            }
        }

        Customer customer = new Customer(PIN, name);

        Account savingsAccount = new Account("Savings", customer);
        Account checkingAccount = new Account("Checkings", customer);

        while(answer != 6 && error == false){

            mainMenu();
            answer = scan.nextInt();
            scan.nextLine();

            validAmount = false;
            while (answer == 1 && !(validAmount)){
                System.out.println("Which account would you like to withdraw from? (C)hecking or (S)avings");
                String str = scan.nextLine();

                if(str.equals("C") || str.equals("c")) {
                    while (!(validAmount)) {
                        System.out.println("How much would you like to withdraw?");
                        withdrawAmount = scan.nextInt();
                        if (withdrawAmount % 5 != 0) {
                            System.out.println("Invalid Amount: Please try again!");
                            printRecieptHeader();
                            System.out.println("Failed to withdraw $" + withdrawAmount + " from checkings account due to invalid amount.");
                        }
                        else if (withdrawAmount > checkingAccount.getAccountBalance()) {
                            System.out.println("Error: Insufficient Funds");
                            printRecieptHeader();
                            System.out.println("Failed to withdraw $" + withdrawAmount + " from checkings account due to insufficient funds");
                        } else if (!validAmount) {

                            optionsToWithdraw(withdrawAmount);
                            System.out.println("Please select how you would like to recieve this money.");
                            str = scan.nextLine();
                            checkingAccount.subtractMoney(withdrawAmount);
                            printRecieptHeader();
                            System.out.println("Successfully Withdrew $" + withdrawAmount + " From Checkings Account");
                            validAmount = true;
                        }
                    }
                }

                if(str.equals("S") || str.equals("s")){
                    while (!(validAmount)) {
                        System.out.println("How much would you like to withdraw?");
                        withdrawAmount = scan.nextInt();
                        if (withdrawAmount % 5 != 0) {
                            System.out.println("Invalid Amount: Please try again!");
                            printRecieptHeader();
                            System.out.println("Failed to withdraw $" + withdrawAmount + " from savings account due to invalid amount.");
                        }
                        else if (withdrawAmount > savingsAccount.getAccountBalance()) {
                            System.out.println("Error: Insufficient Funds");
                            printRecieptHeader();
                            System.out.println("Failed to withdraw $" + withdrawAmount + " from checkings account due to insufficient funds");
                        } else if (!validAmount) {
                            optionsToWithdraw(withdrawAmount);
                            System.out.println("Please select how you would like to recieve this money.");
                            str = scan.nextLine();
                            savingsAccount.subtractMoney(withdrawAmount);
                            printRecieptHeader();
                            System.out.println("Sucessfully Withdrew $" + withdrawAmount + " From Savings Account");
                            validAmount = true;
                        }
                    }
                }
            }
            if(answer == 2){
                System.out.println("In which account will you be depositing money? (C)heckings or (S)avings?");
                depositAccountType = scan.nextLine();
                System.out.println("How much would you like to deposit?");
                depositAmount = scan.nextInt();
                scan.nextLine();

                if(depositAccountType.equals("C") || depositAccountType.equals("c")){
                    checkingAccount.addMoney(depositAmount);
                    printRecieptHeader();
                    System.out.println("Deposited " + depositAmount + " into checking account.");
                }

                if(depositAccountType.equals("S") || depositAccountType.equals("s")){
                    savingsAccount.addMoney(depositAmount);
                    printRecieptHeader();
                    System.out.println("Deposited " + depositAmount + " into savings account.");
                }
            }

            if(answer == 3){
                System.out.println("Which account would you like to take money from, (S)avings or (C)heckings?");
                moneyFrom = scan.nextLine();
                System.out.println("How much would you like to transfer?");
                amountTransferred = scan.nextInt();
                scan.nextLine();
                if(moneyFrom.equals("C") || moneyFrom.equals("c")){
                    if(checkingAccount.getAccountBalance() < amountTransferred){
                        System.out.println("Error: Insufficient Funds");
                        printRecieptHeader();
                        System.out.println("Failed to transfer $" + amountTransferred + " from checkings account due to insufficient funds.");
                        error = true;
                    } else {
                        checkingAccount.subtractMoney(amountTransferred);
                        savingsAccount.addMoney(amountTransferred);

                        printRecieptHeader();

                        System.out.println("Successfully deposited " + amountTransferred + " into savings account.");
                        System.out.println("Successfully withdrew " + amountTransferred + " from checkings account");
                    }
                }

                if(moneyFrom.equals("S") || moneyFrom.equals("s")){
                    if(savingsAccount.getAccountBalance() < amountTransferred){
                        System.out.println("Error: Insufficient Funds");
                        printRecieptHeader();
                        System.out.println("Failed to transfer $" + amountTransferred + " from savings account due to insufficient funds.");
                        error = true;
                    } else {
                        checkingAccount.addMoney(amountTransferred);
                        savingsAccount.subtractMoney(amountTransferred);

                        printRecieptHeader();
                        System.out.println("Successfully deposited " + amountTransferred + " into checking account.");
                        System.out.println("Successfully withdrew " + amountTransferred + " from savings account");
                    }
                }
            }
            if(answer == 4){
                System.out.println("Savings Account: $" + savingsAccount.getAccountBalance());
                System.out.println("Checkings Account: $" + checkingAccount.getAccountBalance());
            }
            if(answer == 5){
                System.out.println("What would you like your new PIN to be?");
                newPIN = scan.nextInt();
                scan.nextLine();
                customer.setPIN(newPIN);
                printRecieptHeader();
                System.out.println("Changed PIN to " + newPIN);
            }


            if(!(error)) {
                System.out.println("Would you like to do anything else? (y/n)");
                response = scan.nextLine();
                if (response.equals("n") || response.equals("N")) {
                    System.out.println("Thank you for being a customer!");
                    answer = 6;
                }
                if (response.equals("y") || response.equals("Y")) {
                    System.out.println("Please re-enter your password.");
                    enteredPIN = scan.nextInt();
                    if (enteredPIN != customer.getPIN()) {
                        System.out.println("Error: Incorrect PIN");
                        error = true;
                    }
                }
            }

            if(error){
                System.out.println("Because of your error, we will log you out for security purpose.");
                System.out.println("We are sorry for any inconvenience");
            }
        }
    }

    public void optionsToWithdraw(int amount){
        int numberOfFives;
        int numberOfTwenties = 0;
        int numberOfOptions;

        if(amount % 5 == 0 && amount - 20 < 0){
            numberOfFives = amount / 5;
            System.out.println(numberOfFives + " 5 Dollar Bills");
        }
        if(amount % 5 == 0 && amount - 20 >= 0){
            numberOfOptions = (amount / 20) + 1;
            numberOfFives = amount / 5;

            for(int i = 1; i <= numberOfOptions; i++){
                System.out.println("Option " + i + ": " + numberOfFives + " Five Dollar Bills and " + numberOfTwenties + " Twenty Dollar Bills");

                if(numberOfFives >= 4){
                    numberOfFives -= 4;
                    numberOfTwenties++;

                }
            }
        }

       String str = scan.nextLine();
    }

    public void mainMenu(){
        System.out.println("(1) Withdraw Money");
        System.out.println("(2) Deposit Money");
        System.out.println("(3) Transfer Money Between Accounts");
        System.out.println("(4) Get Account Balances");
        System.out.println("(5) Change PIN");
        System.out.println("(6) Exit");
    }
   public void printRecieptHeader(){
       System.out.println();
       System.out.println("Reciept");
       System.out.println("--------");
       System.out.println("Transaction ID: " + transactionID);
       transactionID++;
    }
}