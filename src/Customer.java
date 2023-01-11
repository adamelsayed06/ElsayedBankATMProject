public class Customer {
    private int PIN;
    private String name;

    public Customer(int PIN, String name){
        this.PIN = PIN;
        this.name = name;
    }

    public int getPIN(){
        return PIN;
    }

    public String getName(){
        return name;
    }

    public void setPIN(int newPIN){
        PIN = newPIN;
    }

    public void setName(String newName){
        name = newName;
    }
}
