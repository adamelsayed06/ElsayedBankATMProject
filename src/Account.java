public class Account {
    private String accountName;
    private int accountBalance;
    private Customer owner;

    public Account(String accountName, Customer owner){
        this.accountName = accountName;
        accountBalance = 0;
        this.owner = owner;
    }

    public Account(String accountName, int accountBalance, Customer owner){
        this.accountName = accountName;
        this.accountBalance = accountBalance;
        this.owner = owner;
    }
    public int getAccountBalance(){
        return accountBalance;
    }

    public String getAccountName(){
        return accountName;
    }
    public void addMoney(int deposit){
        accountBalance += deposit;
    }

    public void subtractMoney(int withdraw){
        accountBalance -= withdraw;
    }
}
