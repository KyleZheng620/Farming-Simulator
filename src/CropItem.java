public class CropItem extends Item {
    private int cropSellPrice;

    public CropItem(String crop, int quantity) {
        super(crop, quantity);
        if (crop.equals("Rice")) {
            cropSellPrice = 15;
        } else if (crop.equals("Potatoes")) {
            cropSellPrice = 10;
        } else if (crop.equals("Wheat")) {
            cropSellPrice = 9;
        } else if (crop.equals("Mandarin")) {
            cropSellPrice = 5;
        }
    }

}
