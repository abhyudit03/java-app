package Zotato;

public class order{
    private restraunt rest;
    private food order;
    private int quantity;

    public order(restraunt rest,food food,int quantity){
        this.order=food;
        this.rest=rest;
        this.quantity=quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public food getOrder() {
        return order;
    }

    public int getQuantity() {
        return quantity;
    }

    public restraunt getRest() {
        return rest;
    }

    public void setOrder(food order) {
        this.order = order;
    }
    private int customer;

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public void setRest(restraunt rest) {
        this.rest = rest;
    }
}
