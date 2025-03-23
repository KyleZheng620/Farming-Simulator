public class Farmer {
    private Inventory inventory;
    private int money;
    private int hunger;
    private int thirst;
    private int day;
    private Weather weather;
    private String CurrentWeather;

    public Farmer(Inventory inventory){
        money = 0;
        hunger = 10;
        thirst = 10;
        day = 1;
        this.inventory = inventory;

        weather = new Weather();
        CurrentWeather = weather.changeWeather();
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

    public void sleep(){
        day++;
        CurrentWeather = weather.changeWeather();
    }
    public int getDay(){
        return day;
    }

    public String getCurrentWeather() {
        return CurrentWeather;
    }
}
