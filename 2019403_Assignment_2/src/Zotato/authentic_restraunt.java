package Zotato;
public class authentic_restraunt extends restraunt {
    public authentic_restraunt(String name, String Address) {

        super(name+" (Authentic)", Address);
    }
    private int discount;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
