public class CropItem extends FoodItem {
    private int cropSellPrice;
    private String itemDescription;

    public CropItem(String crop, int quantity) {
        super(crop, quantity);
        if (crop.equals("Rice")) {
            cropSellPrice = 22;
            itemDescription = "Fresh harvested rice.\nCan be sold or cooked.";
        } else if (crop.equals("Potato")) {
            cropSellPrice = 18;
            itemDescription = "Fresh harvested potato.\nCan be sold or cooked.";
        } else if (crop.equals("Wheat")) {
            cropSellPrice = 15;
            itemDescription = "Fresh harvested wheat.\nCan be sold or cooked.";
        } else if (crop.equals("Mandarin")) {
            cropSellPrice = 34;
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
