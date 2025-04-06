import java.nio.file.attribute.PosixFileAttributes;

public class FoodItem extends CropItem{

    private boolean Poisoned;
    private int hungerValue;
    private int waterValue;
    private boolean IsFood;
    private String Descript;


    public FoodItem(String Food, int quantity) {
        super(Food, quantity);
        IsFood =true;
        Poisoned = true;
        hungerValue = 0;
        waterValue = 0;
        if (Food.equals("Rice")) {
            hungerValue = 1;
            waterValue = -1;
            Descript = "Crunchy Rice!";
        } else if (Food.equals("Potato")) {
            hungerValue = -1;
            waterValue = -1;;
            Descript = "Maybe Rotten?";
        } else if (Food.equals("Wheat")) {
            hungerValue = -1;
            waterValue = -2;
            Descript = "Don't Eat";
        } else if (Food.equals("Water") ) {
            waterValue = -2;
            Descript = "Dirty Water";
        } else {
            Poisoned = false;
            if (Food.equals("Mandarin")) {
                hungerValue = 1;
                waterValue = 2;
                Descript = "Yum yum Fruit";
            } else if (Food.equals("Cooked Rice")){
                hungerValue = 2;
                Descript = "DELICIOUS RICE";
            } else if (Food.equals("Cooked Potato")){
                hungerValue = 3;
                Descript = "potato...";
            } else if (Food.equals("Bread")){
                hungerValue = 3;
                Descript = "eat me";
            } else if (Food.equals("Boiled Water")){
                waterValue = 3;
                Descript = "99.9% bateria free!";
            } else {
                IsFood = false;
            }
        }
    }

    public int getHungerValue() {
        return hungerValue;
    }

    public int getWaterValue() {
        return waterValue;
    }

    public boolean IsPoisoned() {
        return Poisoned;
    }

    public String getDescript() {
        return Descript;
    }

    public boolean Isfood() {
        return IsFood;
    }

}
