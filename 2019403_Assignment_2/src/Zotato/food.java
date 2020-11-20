package Zotato;

public class food {
    private String Name;
    private String Category;
    private double price;
    private int quantity;
    private int discount;
    private final int id;

    public int getId() {
        return id;
    }


    public food(String Name, double price, int quantity, int discount , String Category, int ID){
        this.discount=discount;
        this.Name=Name;
        this.price = price;
        this.quantity = quantity;
        this.Category = Category;
        id=ID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
