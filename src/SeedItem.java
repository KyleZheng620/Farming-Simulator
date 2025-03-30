public class SeedItem extends Item{
    private int seedBuyPrice;
    private int seedSellPrice;

    public SeedItem(String seed, int amount) {
        super(seed, amount);
        if (seed.equals("Rice seeds")) {
            seedBuyPrice = 8;
            seedSellPrice = 4;
        } else if (seed.equals("Potato seeds")) {
            seedBuyPrice = 6;
            seedSellPrice = 3;
        } else if (seed.equals("Wheat seeds")) {
            seedBuyPrice = 5;
            seedSellPrice = 2;
        } else if (seed.equals("Mandarin seeds")) {
            seedBuyPrice = 3;
            seedSellPrice = 1;
        }
    }
}
