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

Scanner scan = new Scanner(System.in);

    public void welcomeUser(){
        System.out.println("Welcome to dsahdja's ATM. Please enter your name");
        name = scan.nextLine();
        System.out.println("Please choose your 4-digit PIN.");
        PIN = scan.nextInt();
        scan.nextLine();
    }
    public void start(){
        welcomeUser();
        System.out.println("Please enter your PIN");

        Customer customer = new Customer(PIN, name);

        Account savingsAccount = new Account("Savings", customer);
        Account checkingAccount = new Account("Checkings", customer);

        while(answer != 6){

            mainMenu();
            answer = scan.nextInt();
            scan.nextLine();


            if(answer == 1){
                System.out.println("How much would you like to withdraw?");
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
                        System.exit(0);
                    }
                    checkingAccount.subtractMoney(amountTransferred);
                    savingsAccount.addMoney(amountTransferred);

                    printRecieptHeader();

                    System.out.println("Deposited " + amountTransferred + " into savings account.");
                    System.out.println("Withdraw " + amountTransferred + " from checkings account");

                }

                if(moneyFrom.equals("S") || moneyFrom.equals("s")){
                    if(savingsAccount.getAccountBalance() < amountTransferred){
                        System.out.println("Error: Insufficient Funds");
                        System.exit(0);
                    }
                    checkingAccount.addMoney(amountTransferred);
                    savingsAccount.subtractMoney(amountTransferred);

                    printRecieptHeader();
                    System.out.println("Deposited " + amountTransferred + " into checking account.");
                    System.out.println("Withdraw " + amountTransferred  + " from savings account");
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

            System.out.println("Would you like to do anything else? (y/n)");
            response = scan.nextLine();
            if(response.equals("n") || response.equals("N")){
                System.out.println("Thank you for being a customer!");
                answer = 6;
            }
            if(response.equals("y") || response.equals("Y")){
                System.out.println("Please re-enter your password.");
                enteredPIN = scan.nextInt();
            }

        }
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
    }
}

//stuff to add: error system, pin checking system, and logic for withdraw.