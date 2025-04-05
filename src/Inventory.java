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
    public void addItem(Item i) {
        for (Item item : items) {
            if (item.getName().equals(i.getName())) {
                item.addQuantity(i.getQuantity());
                return;
            }
        }
        items.add(i);
    }

    public void removeItem(String name, int amount) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                item.removeQuantity(amount);
                System.out.println(item.getQuantity());
                if (item.getQuantity() == 0){
                    items.remove(item);
                    break;
                }
                System.out.println(item.getName());
            }
        }

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

    public int getQuanitiyOfItem(String name) {
        // note if there is no item then it would return 0
        for (int i = 0 ; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                return items.get(i).getQuantity();
            }
        }
        return 0;
    }

}