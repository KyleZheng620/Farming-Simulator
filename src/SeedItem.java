public class SeedItem extends Item{
    private int seedBuyPrice;
    private int seedSellPrice;

    public SeedItem(String seed, int amount) {
        super(seed, amount);
        if (seed.equals("Rice seeds")) {
            seedBuyPrice = 15;
            seedSellPrice = 4;
        } else if (seed.equals("Potato seeds")) {
            seedBuyPrice = 12;
            seedSellPrice = 3;
        } else if (seed.equals("Wheat seeds")) {
            seedBuyPrice = 10;
            seedSellPrice = 2;
        } else if (seed.equals("Mandarin seeds")) {
            seedBuyPrice = 20;
            seedSellPrice = 1;
        }
    }
}
