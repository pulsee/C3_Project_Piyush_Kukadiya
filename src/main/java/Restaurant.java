import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
        LocalTime currentTime = getCurrentTime();
        if (currentTime.isAfter(openingTime) && currentTime.isBefore(closingTime)) {
            return true;
        } else {
            return false;
        }

    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public String getLocation() {
        return location;
    }

    public double getOrderValue(ArrayList<String> items) throws itemNotFoundException {
        double totalOrderValue = 0;

        for (String item : items)
        {
            Item itemToBeCount = findItemByName(item);

            if (itemToBeCount == null)
                throw new itemNotFoundException(item);

            totalOrderValue+=itemToBeCount.getPrice();
        }

        return totalOrderValue;
    }
}
