import java.awt.*;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> items;

    public Inventory() {
        items = new ArrayList<>();

    }
    public void addItem(String name, int amount) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                item.addQuantity(amount);
                return;
            }
        }
        items.add(new Item(name, amount));
    }

    public void removeItem(String name, int amount) {
        items.removeIf(item -> {
            item.removeQuantity(amount);
            return item.getQuantity() <= 0;
        });
    }
    public int itemAmount(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {;
                return item.getQuantity();
            }
        }
        return 0;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

}