public class FoodItem extends Item{

    private boolean poisoned;
    private int hungerValue;
    private int waterValue;
    private boolean isFood;
    private String descript;


    public FoodItem(String Food, int quantity) {
        super(Food, quantity);
        isFood =true;
        poisoned = true;
        hungerValue = 0;
        waterValue = 0;
        if (Food.equals("Rice")) {
            hungerValue = 1;
            waterValue = -1;
            descript = "Crunchy Rice!";
        } else if (Food.equals("Potato")) {
            hungerValue = 1;
            waterValue = -1;;
            descript = "Maybe Rotten?";
        } else if (Food.equals("Wheat")) {
            hungerValue = 1;
            waterValue = -2;
            descript = "Don't Eat";
        } else if (Food.equals("Water") ) {
            waterValue = -2;
            descript = "Dirty Water";
        } else {
            poisoned = false;
            if (Food.equals("Mandarin")) {
                hungerValue = 1;
                waterValue = 2;
                descript = "Yum yum Fruit";
            } else if (Food.equals("Cooked Rice")){
                hungerValue = 2;
                descript = "DELICIOUS RICE";
            } else if (Food.equals("Cooked Potato")){
                hungerValue = 3;
                descript = "potato...";
            } else if (Food.equals("Bread")){
                hungerValue = 3;
                descript = "eat me";
            } else if (Food.equals("Boiled Water")){
                waterValue = 3;
                descript = "99.9% bacteria free!";
            } else {
                isFood = false;
            }
        }
    }

    public int getHungerValue() {
        return hungerValue;
    }

    public int getWaterValue() {
        return waterValue;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public String getDescript() {
        return descript;
    }

    public boolean getIsFood() {
        return isFood;
    }

}
