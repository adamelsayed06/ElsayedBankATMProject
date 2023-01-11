public class Account {
    private String accountName;
    private int accountBalance;
    private Customer owner;

    public Account(String accountName, Customer owner){
        this.accountName = accountName;
        accountBalance = 100;
        this.owner = owner;
    }

    public Account(String accountName, int accountBalance, Customer owner){
        this.accountName = accountName;
        this.accountBalance = accountBalance;
        this.owner = owner;
    }

    public void addMoney(int deposit){
        accountBalance += deposit;
    }

    public void subtractMoney(int withdraw){
        accountBalance -= withdraw;
    }

    public String getAccountName(){
        return accountName;
    }

    public int getAccountBalance(){
        return accountBalance;
    }


}
