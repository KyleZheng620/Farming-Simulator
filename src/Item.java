public class Item {
    private String name;
    private int quantity;

    public Item(String name) {
        this.name = name;
    }
    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void removeQuantity(int amount) {
        this.quantity = Math.max(this.quantity - amount, 0);
    }
}