package Zotato;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static int ID=1;
    static double tech =0;
    static int delivery =0;
    static void restraunt (){
        System.out.println("1) "+shah.getName());
        System.out.println("2) "+ravi.getName());
        System.out.println("3) "+the_chinese.getName());
        System.out.println("4) "+wangs.getName());
        System.out.println("5) "+paradise.getName());
    }
    static void in_restraunt(){
        System.out.println("1) Add Item");
        System.out.println("2) Edit Item");
        System.out.println("3) Print Rewards");
        System.out.println("4) Discount on bill value");
        System.out.println("5) Exit");
    }
    static double price(ArrayList<order> cart,customer customer){
        int price=0;
        int flag=0;
        for(int i=0;i<cart.size();i++) {
            price += cart.get(i).getOrder().getPrice() * cart.get(i).getQuantity();
            price -= (double) cart.get(i).getOrder().getDiscount() / 100 * price;
            if (cart.get(i).getRest() instanceof authentic_restraunt) {
                flag = 1;
                authentic_restraunt aut = (authentic_restraunt) cart.get(i).getRest();
                price -= (double) aut.getDiscount() / 100 * price;
                if (price > 100) {
                    price -= 50;
                }
            }
            if (cart.get(i).getRest() instanceof fast_food) {
                flag = 2;
                fast_food ff = (fast_food) cart.get(i).getRest();
                price -= (double) ff.getDiscount() / 100 * price;
            }
            if (customer instanceof elite_customer) {
//                        System.out.println("Delivery Charge - 0/-");
                if (price > 200) {
                    price -= 50;
                }

//                        System.out.println("Total order value - "+price);


            } else if (customer instanceof special_customer) {
                if (price > 200) {
                    price -= 25;
                }
//                        delivery+=20;
//                        System.out.println("Delivery Charge - 20/-");
                price += 20;
//                        System.out.println("Total order value - "+price);

            } else {
//                        delivery+=40;
//                        System.out.println("Delivery Charge - 40/-");
                price += 40;
//                        System.out.println("Total order value - "+price);
            }
        }
        return price;
    }
    static void in_customer(ArrayList<restraunt> all_restrunt,customer customer){
        Scanner sc = new Scanner(System.in);
        int reward=0;

        boolean exit = true;
        ArrayList<order> cart = new ArrayList<order>();
        ArrayList<order> recent = new ArrayList<>();
//        HashMap<Integer,ArrayList<order>> recent10=new HashMap<>(10) ;
        while (exit){

            int item =0;
            System.out.println("1) Select Restraunt");
            System.out.println("2) Checkout cart");
            System.out.println("3) Reward Won");
            System.out.println("4) Print the recent orders");
            System.out.println("5) Exit");
            int query = sc.nextInt();
            if (query==1){
                System.out.println("Choose Restraunt");
                restraunt();
                int query1 = sc.nextInt();
                System.out.println("Choose item by code");
                int size = all_restrunt.get(query1-1).getFoods().size();
                Set<Integer> setkey = all_restrunt.get(query1-1).getFoods().keySet();
                for (Integer key : setkey){
                    System.out.println(all_restrunt.get(query1-1).getFoods().get(key).getId()+" "+all_restrunt.get(query1-1).getName()+" - "+all_restrunt.get(query1-1).getFoods().get(key).getName()+" "+all_restrunt.get(query1-1).getFoods().get(key).getPrice()+" "+ all_restrunt.get(query1-1).getFoods().get(key).getQuantity()+" "+all_restrunt.get(query1-1).getFoods().get(key).getDiscount()+"% off "+all_restrunt.get(query1-1).getFoods().get(key).getCategory());
                }
                int code = sc.nextInt();
                System.out.println("Enter item Quantity");
                int quantity = sc.nextInt();
                order od = new order(all_restrunt.get(query1-1),all_restrunt.get(query1-1).getFoods().get(code),quantity);
                cart.add(od);
                if(!(recent.size()>10)) {
                    if(customer instanceof elite_customer) od.setCustomer(0);
                    else if (customer instanceof special_customer) od.setCustomer(1);
                    else od.setCustomer(2);
                    recent.add(od);
                }
                else{
                    recent.remove(0);
                    recent.add(od);
                }
//                System.out.println(cart.size());
                System.out.println("Item added to cart");
                while (customer.getBalance()+ customer.getReward()<price(cart,customer)){
                    System.out.println("Insuffisient balance Plz edit your cart");
                    for(int i=0;i<cart.size();i++){
                        System.out.println(cart.get(i).getOrder().getId()+" "+cart.get(i).getRest().getName()+" - "+cart.get(i).getOrder().getName()+" "+cart.get(i).getOrder().getPrice()+" - "+cart.get(i).getOrder().getQuantity()+" - "+cart.get(i).getQuantity()+" - "+cart.get(i).getOrder().getDiscount()+"% off");

                    }
                    System.out.println("Which item u want to remove");
                    int IDD = sc.nextInt();
                    for(int i=0;i<cart.size();i++){
//                        System.out.println(cart.get(i).getOrder().getId()+" "+cart.get(i).getRest().getName()+" - "+cart.get(i).getOrder().getName()+" "+cart.get(i).getOrder().getPrice()+" - "+cart.get(i).getOrder().getQuantity()+" - "+cart.get(i).getQuantity()+" - "+cart.get(i).getOrder().getDiscount()+"% off");
                        if(IDD==cart.get(i).getOrder().getId()){
                            cart.remove(i);
                        }
                    }
                }



            }
            if (query==2){
                System.out.println("Items in cart -");
                double price = 0;
                int flag =0;
                for(int i=0;i<cart.size();i++) {
                    item+=cart.get(i).getQuantity();
                    price += cart.get(i).getOrder().getPrice() * cart.get(i).getQuantity();
                    price -= (double) cart.get(i).getOrder().getDiscount()/100*price;
                    if(cart.get(i).getRest() instanceof authentic_restraunt) {
                        flag=1;
                        authentic_restraunt aut = (authentic_restraunt) cart.get(i).getRest();
                        price -= (double) aut.getDiscount() / 100 * price;
                        if(price>100){
                            price-=50;
                        }
                    }
                    if (cart.get(i).getRest() instanceof fast_food){
                        flag=2;
                        fast_food ff = (fast_food) cart.get(i).getRest();
                        price-= (double)ff.getDiscount()/100*price;
                    }

                    System.out.println(cart.get(i).getOrder().getId()+" "+cart.get(i).getRest().getName()+" - "+cart.get(i).getOrder().getName()+" "+cart.get(i).getOrder().getPrice()+" - "+cart.get(i).getOrder().getQuantity()+" - "+cart.get(i).getQuantity()+" - "+cart.get(i).getOrder().getDiscount()+"% off");
                }

                if (customer instanceof elite_customer){
                    System.out.println("Delivery Charge - 0/-");
                    if(price>200) {
                        price -= 50;
                    }

                    System.out.println("Total order value - "+price);


                }
                else if(customer instanceof  special_customer){
                    if (price>200){
                        price-=25;
                    }
                    delivery+=20;
                    System.out.println("Delivery Charge - 20/-");
                    price+=20;
                    System.out.println("Total order value - "+price);

                }
                else{
                    delivery+=40;
                    System.out.println("Delivery Charge - 40/-");
                    price+=40;
                    System.out.println("Total order value - "+price);
                }


                System.out.println("1) Proceed to checkout");

                tech+= 0.01*price;
                double local=price;
                price-=customer.getReward();
                if (customer.getReward()>price){
                    customer.setReward(customer.getReward()-(int)local);
                }
                else {
                    customer.setReward(0);
                }
//                System.out.println(reward);
                if (flag==0){
                    reward+=((int)price/100)*5;
                    customer.setReward((customer.getReward()+((int)price/100)*5));
                    cart.get(0).getRest().setReward((customer.getReward()+(int)price/100)*5);
//                    System.out.println(reward);
                }
//                System.out.println();
                else if (flag==1){
                    reward+=((int)price/200)*25;
                    customer.setReward((customer.getReward()+(int)price/200)*25);
                    cart.get(0).getRest().setReward((customer.getReward()+(int)price/200)*25);
//                    System.out.println(reward);
                }
                else{
                    reward+=((int)price/150)*10;
                    customer.setReward((customer.getReward()+(int)price/150)*10);
                    cart.get(0).getRest().setReward((customer.getReward()+(int)price/150)*10);
//                    System.out.println(reward);
                }
                customer.setBalance(customer.getBalance()-price);

                for(int i=0;i<cart.size();i++){
                    cart.get(i).getOrder().setQuantity(cart.get(i).getOrder().getQuantity()-cart.get(i).getQuantity());
                }
                int asd = sc.nextInt();
                cart.get(0).getRest().setNo_of_orders(cart.get(0).getRest().getNo_of_orders()+1);
                System.out.println(item +"items successfully bought for INR "+price);
                cart.clear();

            }
            if (query==3){
                System.out.println("Total Reward -"+reward);
            }
            if (query==4){
                for(int i=0;i<recent.size();i++){
//                    restraunt rest1 = cart.get(i);
                    if(recent.get(i).getCustomer()==0){
                        System.out.println("Bought Item :"+recent.get(i).getOrder().getName()+" ,quantity "+recent.get(i).getQuantity()+" for price "+recent.get(i).getOrder().getPrice()+" from restraunt "+recent.get(i).getRest().getName() +" and delivery charge 0 /-");

                    }
                    else if(recent.get(i).getCustomer()==1){
                        System.out.println("Bought Item :"+recent.get(i).getOrder().getName()+" ,quantity "+recent.get(i).getQuantity()+" for price "+recent.get(i).getOrder().getPrice()+" from restraunt "+recent.get(i).getRest().getName()+" and delivery charge 20 /-");

                    }
                    else{
                        System.out.println("Bought Item :"+recent.get(i).getOrder().getName()+" ,quantity "+recent.get(i).getQuantity()+" for price "+recent.get(i).getOrder().getPrice()+" from restraunt "+recent.get(i).getRest().getName()+" and delivery charge 40 /-");

                    }
                }
            }
            if (query==5){
                exit=false;
            }
        }
    }
    static food food(int ID){
        System.out.println("Food name:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("item price:");
        double price = sc.nextDouble();
        System.out.println("Item Quantity");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.println("Item category");
        String category = sc.nextLine();
        System.out.println("offer: ");
        int discount = sc.nextInt();
        food ob = new food(name,price,quantity,discount,category,ID);
        return ob;
    }
    static authentic_restraunt shah = new authentic_restraunt("Shah","Delhi");
    static restraunt ravi = new restraunt("ravi","mumbai");
    static authentic_restraunt the_chinese = new authentic_restraunt("The Chinese","Kolkata");
    static fast_food wangs = new fast_food("Wang's","Chennai");
    static restraunt paradise = new restraunt("Paradise","Banglore");

    static elite_customer Ram = new elite_customer("Ram","Delhi");
    static elite_customer Sam = new elite_customer("Sam","Mumbai");
    static special_customer Tim = new special_customer("Tim","Kolkata");
    static customer kim = new customer("kim","chennai");
    static customer jim = new customer("jim","Pune");

    static void attribute(){
        System.out.println("choose an attribute to edit :");
        System.out.println("1) Name");
        System.out.println("2) Price");
        System.out.println("3) Quantity");
        System.out.println("4) Category");
        System.out.println("5) Offer");

    }

    public static void main (String[] args){
        HashMap<Integer,customer> customerr = new HashMap<Integer,customer>();
        customerr.put(1,Ram);
        customerr.put(2,Sam);
        customerr.put(3,Tim);
        customerr.put(4,kim);
        customerr.put(5,jim);

        ArrayList<restraunt> all_restraunts = new ArrayList<restraunt>(5);
        all_restraunts.add(0,shah);
        all_restraunts.add(1,ravi);
        all_restraunts.add(2,the_chinese);
        all_restraunts.add(3,wangs);
        all_restraunts.add(4,paradise);

        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        while(exit) {
            System.out.println("Welcome to Zotato");
            System.out.println("1) Enter as Restraunt Owner");
            System.out.println("2) Enter as Customer");
            System.out.println("3) Check User Detail");
            System.out.println("4) Company Account details");
            System.out.println("5) Exit");

            int query = sc.nextInt();
            if (query==1){
                System.out.println("Choose Restraunt: ");
                restraunt();
                int query1 = sc.nextInt();
                if(query1==1){

                    boolean exit1 = true;
                    while (exit1){
                        System.out.println("welcome "+all_restraunts.get(0).getName());
                        in_restraunt();
                        int query2= sc.nextInt();
                        if (query2==1){
                            food foood = food(ID);
                            int id = all_restraunts.get(0).getFoods().size()+1;

                            System.out.println(ID+" "+foood.getName()+" "+foood.getPrice()+" "+foood.getQuantity()+" "+foood.getDiscount()+"% off "+foood.getCategory());

                            all_restraunts.get(0).getFoods().put(ID,foood);
                            ID++;
                        }
                        if (query2==2) {
                            System.out.println("choose item by code");
                            int size = all_restraunts.get(0).getFoods().size();
                            Set<Integer> setkey = all_restraunts.get(0).getFoods().keySet();
                            for (Integer key : setkey) {
                                System.out.println(all_restraunts.get(0).getFoods().get(key).getId() + " " + all_restraunts.get(0).getName() + " - " + all_restraunts.get(0).getFoods().get(key).getName() + " " + all_restraunts.get(0).getFoods().get(key).getPrice() + " " +all_restraunts.get(0).getFoods().get(key).getQuantity()+" "+ all_restraunts.get(0).getFoods().get(key).getDiscount() + "% off " + all_restraunts.get(0).getFoods().get(key).getCategory());
                            }
                            int code = sc.nextInt()+1;

                            attribute();
                            int query3 = sc.nextInt();

                            if (query3 == 1) {
                                System.out.println("Enter the new name - ");
                                sc.nextLine();
                                String name = sc.nextLine();


                                all_restraunts.get(0).getFoods().get(code - 1).setName(name);
                                System.out.println(all_restraunts.get(0).getFoods().get(code - 1).getId() + " " + all_restraunts.get(0).getName() + " - " + all_restraunts.get(0).getFoods().get(code - 1).getName() + " " + all_restraunts.get(0).getFoods().get(code - 1).getPrice() + " " +all_restraunts.get(0).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(0).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(0).getFoods().get(code - 1).getCategory());
                            }
                            if (query3 == 2) {
                                System.out.println("Enter the new Price - ");
                                double pricee = sc.nextDouble();
                                System.out.println(code-1);
                                all_restraunts.get(0).getFoods().get(code - 1).setPrice(pricee);
                                System.out.println(all_restraunts.get(0).getFoods().get(code - 1).getId() + " " + all_restraunts.get(0).getName() + " - " + all_restraunts.get(0).getFoods().get(code - 1).getName() + " " + all_restraunts.get(0).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(0).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(0).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(0).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 3) {
                                System.out.println("Enter the new quatity");
                                int quant = sc.nextInt();
                                all_restraunts.get(0).getFoods().get(code - 1).setQuantity(quant);
                                System.out.println(all_restraunts.get(0).getFoods().get(code - 1).getId() + " " + all_restraunts.get(0).getName() + " - " + all_restraunts.get(0).getFoods().get(code - 1).getName() + " " + all_restraunts.get(0).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(0).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(0).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(0).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 4) {
                                System.out.println("Enter the new category");
                                sc.nextLine();
                                String cat = sc.nextLine();
                                all_restraunts.get(0).getFoods().get(code - 1).setCategory(cat);
                                System.out.println(all_restraunts.get(0).getFoods().get(code - 1).getId() + " " + all_restraunts.get(0).getName() + " - " + all_restraunts.get(0).getFoods().get(code - 1).getName() + " " + all_restraunts.get(0).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(0).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(0).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(0).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 5) {
                                System.out.println("Enter the new offer");
                                int dis = sc.nextInt();
                                all_restraunts.get(0).getFoods().get(code - 1).setDiscount(dis);
                                System.out.println(all_restraunts.get(0).getFoods().get(code - 1).getId() + " " + all_restraunts.get(0).getName() + " - " + all_restraunts.get(0).getFoods().get(code - 1).getName() + " " + all_restraunts.get(0).getFoods().get(code - 1).getPrice() + " " +all_restraunts.get(0).getFoods().get(code-1).getQuantity() +" "+ all_restraunts.get(0).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(0).getFoods().get(code - 1).getCategory());
                            }
                        }
                        if (query2==3){
                            System.out.println("Reward Points :"+all_restraunts.get(0).getReward());
                        }
                        if (query2==4){
                            System.out.println("Enter offer on total bill -");
                            int per = sc.nextInt();
                            if(all_restraunts.get(0) instanceof authentic_restraunt ){
                                authentic_restraunt sub_object = (authentic_restraunt) all_restraunts.get(0);
                                sub_object.setDiscount(per);
//                                System.out.println(((authentic_restraunt) all_restraunts.get(0)).getDiscount());
                            }

                        }
                        if (query2==5){
                            exit1=false;
                        }
                    }
                }
                if (query1==2){

                    boolean exit1 = true;
                    while (exit1){
                        System.out.println("welcome "+all_restraunts.get(1).getName());

                        in_restraunt();
                        int query2= sc.nextInt();
                        if (query2==1){
                            food foood = food(ID);
                            int id = all_restraunts.get(1).getFoods().size()+1;

                            System.out.println(ID+" "+foood.getName()+" "+foood.getPrice()+" "+foood.getQuantity()+" "+foood.getDiscount()+"% off"+foood.getCategory());

                            all_restraunts.get(1).getFoods().put(ID,foood);
                            ID++;
                        }
                        if (query2==2) {
                            System.out.println("choose item by code");
                            Set<Integer> setkey = all_restraunts.get(1).getFoods().keySet();
                            for (Integer key : setkey) {
                                System.out.println(all_restraunts.get(1).getFoods().get(key).getId() + " " + all_restraunts.get(1).getName() + " - " + all_restraunts.get(1).getFoods().get(key).getName() + " " + all_restraunts.get(1).getFoods().get(key).getPrice() + " " +all_restraunts.get(1).getFoods().get(key).getQuantity() +" "+all_restraunts.get(1).getFoods().get(key).getDiscount() + "% off " + all_restraunts.get(1).getFoods().get(key).getCategory());
                            }
                            int code = sc.nextInt()+1;

                            attribute();
                            int query3 = sc.nextInt();
                            if (query3 == 1) {
                                System.out.println("Enter the new name - ");
                                sc.nextLine();
                                String name = sc.nextLine();
                                all_restraunts.get(1).getFoods().get(code - 1).setName(name);
                                System.out.println(all_restraunts.get(1).getFoods().get(code - 1).getId() + " " + all_restraunts.get(1).getName() + " - " + all_restraunts.get(1).getFoods().get(code - 1).getName() + " " + all_restraunts.get(1).getFoods().get(code - 1).getPrice() + " " +all_restraunts.get(1).getFoods().get(code-1).getQuantity() +" "+ all_restraunts.get(1).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(1).getFoods().get(code - 1).getCategory());
                            }
                            if (query3 == 2) {
                                System.out.println("Enter the new Price - ");
                                double pricee = sc.nextDouble();
                                all_restraunts.get(1).getFoods().get(code - 1).setPrice(pricee);
                                System.out.println(all_restraunts.get(1).getFoods().get(code - 1).getId() + " " + all_restraunts.get(1).getName() + " - " + all_restraunts.get(1).getFoods().get(code - 1).getName() + " " + all_restraunts.get(1).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(1).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(1).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(1).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 3) {
                                System.out.println("Enter the new quatity");
                                int quant = sc.nextInt();
                                all_restraunts.get(1).getFoods().get(code - 1).setQuantity(quant);
                                System.out.println(all_restraunts.get(1).getFoods().get(code - 1).getId() + " " + all_restraunts.get(1).getName() + " - " + all_restraunts.get(1).getFoods().get(code - 1).getName() + " " + all_restraunts.get(1).getFoods().get(code - 1).getPrice() + " " +all_restraunts.get(1).getFoods().get(code-1).getQuantity() +" "+ all_restraunts.get(1).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(1).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 4) {
                                System.out.println("Enter the new category");
                                sc.nextLine();
                                String cat = sc.nextLine();
                                all_restraunts.get(1).getFoods().get(code - 1).setCategory(cat);
                                System.out.println(all_restraunts.get(1).getFoods().get(code - 1).getId() + " " + all_restraunts.get(1).getName() + " - " + all_restraunts.get(1).getFoods().get(code - 1).getName() + " " + all_restraunts.get(1).getFoods().get(code - 1).getPrice() + " " +all_restraunts.get(1).getFoods().get(code-1).getQuantity() +" "+ all_restraunts.get(1).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(1).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 5) {
                                System.out.println("Enter the new offer");
                                int dis = sc.nextInt();
                                all_restraunts.get(1).getFoods().get(code - 1).setDiscount(dis);
                                System.out.println(all_restraunts.get(1).getFoods().get(code - 1).getId() + " " + all_restraunts.get(1).getName() + " - " + all_restraunts.get(1).getFoods().get(code - 1).getName() + " " + all_restraunts.get(1).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(1).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(1).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(1).getFoods().get(code - 1).getCategory());
                            }
                        }
                        if (query2==3){
                            System.out.println("Reward Points :"+all_restraunts.get(1).getReward());
                        }
                        if (query2==4){
                            System.out.println("Enter offer on total bill -");
                            int per = sc.nextInt();

                        }
                        if (query2==5){
                            exit1=false;
                        }
                    }
                }
                if (query1==3){
                    boolean exit1 = true;
                    while (exit1){
                        System.out.println("welcome "+all_restraunts.get(2).getName());

                        in_restraunt();
                        int query2= sc.nextInt();
                        if (query2==1){
                            food foood = food(ID);
                            int id = all_restraunts.get(2).getFoods().size()+1;
//                            foood.setId(id);

                            System.out.println(ID+" "+foood.getName()+" "+foood.getPrice()+" "+foood.getQuantity()+" "+foood.getDiscount()+"% off"+foood.getCategory());

                            all_restraunts.get(2).getFoods().put(ID,foood);
                            ID++;
                        }
                        if (query2==2) {
                            System.out.println("choose item by code");
                            Set<Integer> setkey = all_restraunts.get(2).getFoods().keySet();
                            for (Integer key : setkey) {
                                System.out.println(all_restraunts.get(2).getFoods().get(key).getId() + " " + all_restraunts.get(2).getName() + " - " + all_restraunts.get(2).getFoods().get(key).getName() + " " + all_restraunts.get(2).getFoods().get(key).getPrice() + " "+all_restraunts.get(2).getFoods().get(key).getQuantity() +" "+ all_restraunts.get(2).getFoods().get(key).getDiscount() + "% off " + all_restraunts.get(2).getFoods().get(key).getCategory());
                            }
                            int code = sc.nextInt()+1;

                            attribute();
                            int query3 = sc.nextInt();
                            if (query3 == 1) {
                                System.out.println("Enter the new name - ");
                                sc.nextLine();
                                String name = sc.nextLine();
                                all_restraunts.get(2).getFoods().get(code - 1).setName(name);
                                System.out.println(all_restraunts.get(2).getFoods().get(code - 1).getId() + " " + all_restraunts.get(2).getName() + " - " + all_restraunts.get(2).getFoods().get(code - 1).getName() + " " + all_restraunts.get(2).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(2).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(2).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(2).getFoods().get(code - 1).getCategory());
                            }
                            if (query3 == 2) {
                                System.out.println("Enter the new Price - ");
                                double pricee = sc.nextDouble();
                                all_restraunts.get(2).getFoods().get(code - 1).setPrice(pricee);
                                System.out.println(all_restraunts.get(2).getFoods().get(code - 1).getId() + " " + all_restraunts.get(2).getName() + " - " + all_restraunts.get(2).getFoods().get(code - 1).getName() + " " + all_restraunts.get(2).getFoods().get(code - 1).getPrice() + " " +  all_restraunts.get(2).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(2).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(2).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 3) {
                                System.out.println("Enter the new quatity");
                                int quant = sc.nextInt();
                                all_restraunts.get(2).getFoods().get(code - 1).setQuantity(quant);
                                System.out.println(all_restraunts.get(2).getFoods().get(code - 1).getId() + " " + all_restraunts.get(2).getName() + " - " + all_restraunts.get(2).getFoods().get(code - 1).getName() + " " + all_restraunts.get(2).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(2).getFoods().get(code-1).getQuantity() +" "+ all_restraunts.get(2).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(2).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 4) {
                                System.out.println("Enter the new category");
                                sc.nextLine();
                                String cat = sc.nextLine();
                                all_restraunts.get(2).getFoods().get(code - 1).setCategory(cat);
                                System.out.println(all_restraunts.get(2).getFoods().get(code - 1).getId() + " " + all_restraunts.get(2).getName() + " - " + all_restraunts.get(2).getFoods().get(code - 1).getName() + " " + all_restraunts.get(2).getFoods().get(code - 1).getPrice() + " " +  all_restraunts.get(2).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(2).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(2).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 5) {
                                System.out.println("Enter the new offer");
                                int dis = sc.nextInt();
                                all_restraunts.get(2).getFoods().get(code - 1).setDiscount(dis);
                                System.out.println(all_restraunts.get(2).getFoods().get(code - 1).getId() + " " + all_restraunts.get(2).getName() + " - " + all_restraunts.get(2).getFoods().get(code - 1).getName() + " " + all_restraunts.get(2).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(2).getFoods().get(code-1).getQuantity() +" "+ all_restraunts.get(2).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(2).getFoods().get(code - 1).getCategory());
                            }
                        }
                        if (query2==3){
                            System.out.println("Reward Points :"+all_restraunts.get(2).getReward());
                        }
                        if (query2==4){
                            System.out.println("Enter offer on total bill -");
                            int per = sc.nextInt();
                            if(all_restraunts.get(2) instanceof authentic_restraunt){
                                authentic_restraunt sub_object = (authentic_restraunt) all_restraunts.get(0);
                                sub_object.setDiscount(per);
                            }
                        }
                        if (query2==5){
                            exit1=false;
                        }
                    }
                }
                if (query1==4){
                    boolean exit1 = true;
                    while (exit1){
                        System.out.println("welcome "+all_restraunts.get(3).getName());

                        in_restraunt();
                        int query2= sc.nextInt();
                        if (query2==1){
                            food foood = food(ID);

                            int id = all_restraunts.get(3).getFoods().size()+1;
//                            foood.setId(id);

                            System.out.println(ID+" "+foood.getName()+" "+foood.getPrice()+" "+foood.getQuantity()+" "+foood.getDiscount()+"% off"+foood.getCategory());
//                            ID++;
                            all_restraunts.get(3).getFoods().put(ID,foood);
                                ID++;
                        }
                        if (query2==2) {
                            System.out.println("choose item by code");
                            Set<Integer> setkey = all_restraunts.get(3).getFoods().keySet();
                            for (Integer key : setkey) {
                                System.out.println(all_restraunts.get(3).getFoods().get(key).getId() + " " + all_restraunts.get(3).getName() + " - " + all_restraunts.get(3).getFoods().get(key).getName() + " " + all_restraunts.get(3).getFoods().get(key).getPrice()+" "+all_restraunts.get(3).getFoods().get(key).getQuantity() + " " + all_restraunts.get(3).getFoods().get(key).getDiscount() + "% off " + all_restraunts.get(3).getFoods().get(key).getCategory());
                            }
                            int code = sc.nextInt()+1;

                            attribute();
                            int query3 = sc.nextInt();
                            if (query3 == 1) {
                                System.out.println("Enter the new name - ");
                                sc.nextLine();
                                String name = sc.nextLine();
                                all_restraunts.get(3).getFoods().get(code - 1).setName(name);
                                System.out.println(all_restraunts.get(3).getFoods().get(code - 1).getId() + " " + all_restraunts.get(3).getName() + " - " + all_restraunts.get(3).getFoods().get(code - 1).getName() + " " + all_restraunts.get(3).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(3).getFoods().get(code-1).getQuantity() +" "+ all_restraunts.get(3).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(3).getFoods().get(code - 1).getCategory());
                            }
                            if (query3 == 2) {
                                System.out.println("Enter the new Price - ");
                                double pricee = sc.nextDouble();
                                all_restraunts.get(3).getFoods().get(code - 1).setPrice(pricee);
                                System.out.println(all_restraunts.get(3).getFoods().get(code - 1).getId() + " " + all_restraunts.get(3).getName() + " - " + all_restraunts.get(3).getFoods().get(code - 1).getName() + " " + all_restraunts.get(3).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(3).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(3).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(3).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 3) {
                                System.out.println("Enter the new quatity");
                                int quant = sc.nextInt();
                                all_restraunts.get(3).getFoods().get(code - 1).setQuantity(quant);
                                System.out.println(all_restraunts.get(3).getFoods().get(code - 1).getId() + " " + all_restraunts.get(3).getName() + " - " + all_restraunts.get(3).getFoods().get(code - 1).getName() + " " + all_restraunts.get(3).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(3).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(3).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(3).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 4) {
                                System.out.println("Enter the new category");
                                sc.nextLine();
                                String cat = sc.nextLine();
                                all_restraunts.get(3).getFoods().get(code - 1).setCategory(cat);
                                System.out.println(all_restraunts.get(3).getFoods().get(code - 1).getId() + " " + all_restraunts.get(3).getName() + " - " + all_restraunts.get(3).getFoods().get(code - 1).getName() + " " + all_restraunts.get(3).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(3).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(3).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(3).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 5) {
                                System.out.println("Enter the new offer");
                                int dis = sc.nextInt();
                                all_restraunts.get(3).getFoods().get(code - 1).setDiscount(dis);
                                System.out.println(all_restraunts.get(3).getFoods().get(code - 1).getId() + " " + all_restraunts.get(3).getName() + " - " + all_restraunts.get(3).getFoods().get(code - 1).getName() + " " + all_restraunts.get(3).getFoods().get(code - 1).getPrice() + " " +all_restraunts.get(3).getFoods().get(code-1).getQuantity() +" "+ all_restraunts.get(3).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(3).getFoods().get(code - 1).getCategory());
                            }
                        }
                        if (query2==3){
                            System.out.println("Reward Points :"+all_restraunts.get(3).getReward());
                        }
                        if (query2==4){
                            System.out.println("Enter offer on total bill -");
                            int per = sc.nextInt();
                            if (all_restraunts.get(3) instanceof fast_food){
                                fast_food sub_object = (fast_food) all_restraunts.get(3);
                                sub_object.setDiscount(per);
                            }
                        }
                        if (query2==5){
                            exit1=false;
                        }
                    }
                }
                if (query1==5){
                    boolean exit1 = true;
                    while (exit1){
                        System.out.println("welcome "+all_restraunts.get(4).getName());

                        in_restraunt();
                        int query2= sc.nextInt();
                        if (query2==1){
                            food foood = food(ID);

                            int id = all_restraunts.get(4).getFoods().size()+1;
//                            foood.setId(id);
                            System.out.println(ID+" "+foood.getName()+" "+foood.getPrice()+" "+foood.getQuantity()+" "+foood.getDiscount()+"% off"+foood.getCategory());

                            all_restraunts.get(4).getFoods().put(ID,foood);
                            ID++;
                        }
                        if (query2==2) {
                            System.out.println("choose item by code");
                            Set<Integer> setkey = all_restraunts.get(4).getFoods().keySet();
                            for (Integer key : setkey) {
                                System.out.println(all_restraunts.get(4).getFoods().get(key).getId() + " " + all_restraunts.get(4).getName() + " - " + all_restraunts.get(4).getFoods().get(key).getName() + " " + all_restraunts.get(4).getFoods().get(key).getPrice() + " " +all_restraunts.get(4).getFoods().get(key).getQuantity()+" "+ all_restraunts.get(4).getFoods().get(key).getDiscount() + "% off " + all_restraunts.get(4).getFoods().get(key).getCategory());
                            }
                            int code = sc.nextInt()+1;

                            attribute();
                            int query3 = sc.nextInt();
                            if (query3 == 1) {
                                System.out.println("Enter the new name - ");
                                sc.nextLine();
                                String name = sc.nextLine();
                                all_restraunts.get(4).getFoods().get(code - 1).setName(name);
                                System.out.println(all_restraunts.get(4).getFoods().get(code - 1).getId() + " " + all_restraunts.get(4).getName() + " - " + all_restraunts.get(4).getFoods().get(code - 1).getName() + " " + all_restraunts.get(4).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(4).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(4).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(4).getFoods().get(code - 1).getCategory());
                            }
                            if (query3 == 2) {
                                System.out.println("Enter the new Price - ");
                                double pricee = sc.nextDouble();
                                all_restraunts.get(4).getFoods().get(code - 1).setPrice(pricee);
                                System.out.println(all_restraunts.get(4).getFoods().get(code - 1).getId() + " " + all_restraunts.get(4).getName() + " - " + all_restraunts.get(4).getFoods().get(code - 1).getName() + " " + all_restraunts.get(4).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(4).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(4).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(4).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 3) {
                                System.out.println("Enter the new quatity");
                                int quant = sc.nextInt();
                                all_restraunts.get(4).getFoods().get(code - 1).setQuantity(quant);
                                System.out.println(all_restraunts.get(4).getFoods().get(code - 1).getId() + " " + all_restraunts.get(4).getName() + " - " + all_restraunts.get(4).getFoods().get(code - 1).getName() + " " + all_restraunts.get(4).getFoods().get(code - 1).getPrice() + " " +all_restraunts.get(4).getFoods().get(code-1).getQuantity() +" "+ all_restraunts.get(4).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(4).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 4) {
                                System.out.println("Enter the new category");
                                sc.nextLine();
                                String cat = sc.nextLine();
                                all_restraunts.get(4).getFoods().get(code - 1).setCategory(cat);
                                System.out.println(all_restraunts.get(4).getFoods().get(code - 1).getId() + " " + all_restraunts.get(4).getName() + " - " + all_restraunts.get(4).getFoods().get(code - 1).getName() + " " + all_restraunts.get(4).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(4).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(4).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(4).getFoods().get(code - 1).getCategory());

                            }
                            if (query3 == 5) {
                                System.out.println("Enter the new offer");
                                int dis = sc.nextInt();
                                all_restraunts.get(4).getFoods().get(code - 1).setDiscount(dis);
                                System.out.println(all_restraunts.get(4).getFoods().get(code - 1).getId() + " " + all_restraunts.get(4).getName() + " - " + all_restraunts.get(4).getFoods().get(code - 1).getName() + " " + all_restraunts.get(4).getFoods().get(code - 1).getPrice() + " " + all_restraunts.get(4).getFoods().get(code-1).getQuantity() +" "+all_restraunts.get(4).getFoods().get(code - 1).getDiscount() + "% off " + all_restraunts.get(4).getFoods().get(code - 1).getCategory());
                            }
                        }
                        if (query2==3){
                            System.out.println("Reward Points :"+all_restraunts.get(2).getReward());
                        }
                        if (query2==4){
                            System.out.println("Enter offer on total bill -");
                            int per = sc.nextInt();

                        }
                        if (query2==5){
                            exit1=false;
                        }
                    }
                }
            }
            if (query==2){
                for(int i=1;i<6;i++) {
                    System.out.println(i + ") " + customerr.get(i).getName());
                }
                    int query2 = sc.nextInt();
                    if(query2==1){
                        System.out.println("Welcome "+ customerr.get(1).getName());
                        System.out.println("Customer Menu");
                        in_customer(all_restraunts,customerr.get(1));
                    }
                    if(query2==2){
                        System.out.println("Welcome "+ customerr.get(2).getName());
                        System.out.println("Customer Menu");
                        in_customer(all_restraunts,customerr.get(2));
                    }
                    if (query2==3){
                        System.out.println("Welcome "+ customerr.get(3).getName());
                        System.out.println("Customer Menu");
                        in_customer(all_restraunts,customerr.get(3));
                    }
                    if (query2==4){
                        System.out.println("Welcome "+ customerr.get(4).getName());
                        System.out.println("Customer Menu");
                        in_customer(all_restraunts,customerr.get(4));
                    }
                    if (query2==5){
                        System.out.println("Welcome "+ customerr.get(5).getName());
                        System.out.println("Customer Menu");
                        in_customer(all_restraunts,customerr.get(5));
                    }

            }
            if (query==3){
                System.out.println("1) Customer List");
                System.out.println("2) Restraunt List");
                int query1 = sc.nextInt();
                if(query1 == 1){
                    for(int i=1;i<6;i++) {
                        System.out.println(i + ") " + customerr.get(i).getName());
                    }
                    int query2 =sc.nextInt();
                    if (query2==1){
                        System.out.println(customerr.get(1).getName()+" "+customerr.get(1).getAddress()+" "+ customerr.get(1).getBalance());
                    }
                    if (query2==2){
                        System.out.println(customerr.get(2).getName()+" "+customerr.get(2).getAddress()+" "+ customerr.get(2).getBalance());

                    }
                    if (query2==3){
                        System.out.println(customerr.get(3).getName()+" "+customerr.get(3).getAddress()+" "+ customerr.get(3).getBalance());

                    }
                    if (query2==4){
                        System.out.println(customerr.get(4).getName()+" "+customerr.get(4).getAddress()+" "+ customerr.get(4).getBalance());

                    }
                    if (query2==5){
                        System.out.println(customerr.get(5).getName()+" "+customerr.get(5).getAddress()+" "+ customerr.get(5).getBalance());

                    }
                }
                if(query1==2){
                    restraunt();
                    int query2 = sc.nextInt();
                    if(query2==1){
                        System.out.println(all_restraunts.get(0).getName()+" "+all_restraunts.get(0).getAddress()+" "+all_restraunts.get(0).getNo_of_orders());
                    }
                    if(query2==2){
                        System.out.println(all_restraunts.get(1).getName()+" "+all_restraunts.get(1).getAddress()+" "+all_restraunts.get(1).getNo_of_orders());

                    }
                    if(query2==3){
                        System.out.println(all_restraunts.get(2).getName()+" "+all_restraunts.get(2).getAddress()+" "+all_restraunts.get(2).getNo_of_orders());

                    }
                    if(query2==4){
                        System.out.println(all_restraunts.get(3).getName()+" "+all_restraunts.get(3).getAddress()+" "+all_restraunts.get(3).getNo_of_orders());

                    }
                    if(query2==5){
                        System.out.println(all_restraunts.get(4).getName()+" "+all_restraunts.get(4).getAddress()+" "+all_restraunts.get(4).getNo_of_orders());

                    }
                }
            }
            if (query==4){
                System.out.println("Total Company Balance -" + tech);
                System.out.println("Total delivery Charges collected -" + delivery);
            }
            if (query==5){
                exit=false;
            }
        }
    }
}
