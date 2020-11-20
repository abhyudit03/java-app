package Zotato;

import java.util.ArrayList;
import java.util.HashMap;

public class restraunt implements user{
    private final String Name;
    private final String Address;
    private int Reward;
    private int no_of_orders;

    private HashMap<Integer,food> foods = new HashMap<Integer,food>();

    public void setFoods(HashMap<Integer, food> foods) {
        this.foods = foods;
    }

    public HashMap<Integer, food> getFoods() {
        return foods;
    }

    public restraunt(String name, String Address) {
        Name = name;
        this.Address=Address;
    }

    public int getNo_of_orders() {
        return no_of_orders;
    }

    public void setNo_of_orders(int no_of_orders) {
        this.no_of_orders = no_of_orders;
    }

    public String getName() {
        return Name;
    }
    @Override
    public int getReward() {
        return Reward;
    }

    public String getAddress() {
        return Address;
    }
//    private int discount;

//    public void setDiscount(int discount) {
//        this.discount = discount;
//    }
//
//    public int getDiscount() {
//        return discount;
//    }
    @Override
    public void setReward(int reward) {
        Reward = reward;
    }
}
