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
    public void addThirst(int water) {
        if (thirst + water > 10) {
            thirst = 10;
        } else if (thirst + water < 0) {
            thirst = 0;
        } else {
            thirst+= water;
        }
    }

    public int getHunger(){
        return hunger;
    }
    public void addHunger(int hunger) {
        if (this.hunger + hunger > 10) {
            this.hunger = 10;
        } else if (this.hunger + hunger < 0) {
            this.hunger = 0;
        } else {
            this.hunger+= hunger;
        }
    }

    public void setFoodPoison (boolean p) {
        foodPoison = p;
    }

    public boolean isFoodPoison(){
        return foodPoison;
    }
    public void setWaterPoison (boolean p) {
        waterPoison = p;
    }

    public boolean isWaterPoison(){
        return waterPoison;
    }
    public FarmLand getFarmPlots(){
        return farmPlots;
    }

    public boolean sleep(){
        day++;
        CurrentWeather = weather.changeWeather();
        farmPlots.dayPass();
        if (isWaterPoison()) {
            thirst--;
        }
        if (isFoodPoison()) {
            hunger--;
        }
        thirst--;
        hunger--;
        return thirst != 0 && hunger != 0;
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
