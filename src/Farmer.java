public class Farmer {
    private int money;
    private int hunger;
    private int thirst;
    private int day;

    public Farmer(){
        money = 0;
        hunger = 10;
        thirst = 10;
        day = 1;
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
    }
    public int getDay(){
        return day;
    }
}
