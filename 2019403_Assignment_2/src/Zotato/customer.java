package Zotato;

public class customer implements user {
    private final String Name;
    private final String Address;
    private int Reward;
    private double balance;



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public customer(String Name, String Address) {
        this.Name = Name;
        this.Address = Address;
        balance = 1000;
    }

    public String getName() {
        return Name;
    }

    @Override
        public void setReward ( int reward){
            Reward = reward;
        }


    public String getAddress() {
        return Address;
    }

    @Override
    public int getReward() {
        return Reward;
    }

}
