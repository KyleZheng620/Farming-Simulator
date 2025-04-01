public class Farmer {
    private Inventory inventory;
    private int money;
    private int hunger;
    private int thirst;
    private int day;
    private boolean foodPoison;
    private boolean waterPoison;
    private Weather weather;
    private String CurrentWeather;
    private FarmLand farmPlots;

    public Farmer(Inventory inventory){
        farmPlots = new FarmLand(this);
        foodPoison = false;
        waterPoison = false;
        money = 0;
        hunger = 10;
        thirst = 10;
        day = 1;
        this.inventory = inventory;
        weather = new Weather();
        CurrentWeather = "Sunny";
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int newMoney){
        money = newMoney;
    }

    public int getThirst(){
        return thirst;
    }

    public int getHunger(){
        return hunger;
    }

    public boolean isFoodPoison(){
        return foodPoison;
    }

    public boolean isWaterPoison(){
        return waterPoison;
    }
    public FarmLand getFarmPlots(){
        return farmPlots;
    }

    public void sleep(){
        day++;
        CurrentWeather = weather.changeWeather();
        farmPlots.dayPass();
        thirst--;
        hunger--;
    }
    public int getDay(){
        return day;
    }

    public String getCurrentWeather() {
        return CurrentWeather;
    }

    public Inventory getInventory(){
        return inventory;
    }
}
