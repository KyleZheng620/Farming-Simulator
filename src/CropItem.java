public class CropItem extends Item {
    private int cropBuyPrice;
    private int cropSellPrice;

    public CropItem(String crop, int item) {
        super(crop,item);
        if (crop.equals("Rice")) {
            cropBuyPrice = 8;
            cropSellPrice = 4;
        } else if (crop.equals("Potatoes")) {
            cropBuyPrice = 6;
            cropSellPrice = 3;
        } else if (crop.equals("Wheat")) {
            cropBuyPrice = 5;
            cropSellPrice = 2;
        } else if (crop.equals("Mandarin")) {
            cropBuyPrice = 3;
            cropSellPrice = 1;
        }
    }
}
