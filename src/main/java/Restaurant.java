import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final String name;
    private final String location;
    private final List<Item> menu = new ArrayList<Item>();
    public LocalTime openingTime;
    public LocalTime closingTime;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public int getCartValue(List<String> itemNames) throws itemNotFoundException {
        int total = 0;
        for (String itemName : itemNames) {
            Item foundItem = menu.stream()
                    .filter(item -> itemName.equals(item.getName()))
                    .findFirst()
                    .orElseThrow(() -> new itemNotFoundException(itemName));
            total += foundItem.getPrice();
        }
        return total;
    }

    public boolean isRestaurantOpen() {
        return getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime);
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName)) return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null) throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n" + "Location:" + location + "\n" + "Opening time:" + openingTime + "\n" + "Closing time:" + closingTime + "\n" + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }

}
