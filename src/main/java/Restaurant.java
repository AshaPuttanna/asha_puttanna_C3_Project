import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {

        if (this.openingTime.isBefore(this.closingTime)) {
            return this.openingTime.isBefore(LocalTime.now()) && this.closingTime.isAfter(LocalTime.now());
        } else {
            return LocalTime.now().isAfter(this.openingTime) || LocalTime.now().isBefore(this.closingTime);
        }

    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return this.menu;

    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }

    public double calculateOrderValue(){
        double orderValue=0.0;
        for (Item i:menu){
            orderValue+=i.getPrice();
        }
        return orderValue;
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("Amele's Cafe","Chennai",LocalTime.of(10,0,0),LocalTime.of(23,0,0));
        System.out.println(restaurant.isRestaurantOpen());
    }

}


