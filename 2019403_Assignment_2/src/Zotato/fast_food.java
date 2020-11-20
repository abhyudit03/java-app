package Zotato;

public class fast_food extends restraunt{
    public fast_food(String name, String Address) {
        super(name+" (Fast food)", Address);
    }
    private int discount;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}
