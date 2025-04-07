public class CropItem extends FoodItem {
    private int cropSellPrice;
    private String itemDescription;

    public CropItem(String crop, int quantity) {
        super(crop, quantity);
        if (crop.equals("Rice")) {
            cropSellPrice = 30;
            itemDescription = "Fresh harvested rice.\nCan be sold or cooked.";
        } else if (crop.equals("Potato")) {
            cropSellPrice = 25;
            itemDescription = "Fresh harvested potato.\nCan be sold or cooked.";
        } else if (crop.equals("Wheat")) {
            cropSellPrice = 20;
            itemDescription = "Fresh harvested wheat.\nCan be sold or cooked.";
        } else if (crop.equals("Mandarin")) {
            cropSellPrice = 35;
            itemDescription = "Fresh harvested mandarin.\nCan be sold or eaten.";
        }
    }
    public int getCropSellPrice(){
        return cropSellPrice;
    }

    public String getItemDescription(){
        return itemDescription;
    }
}
