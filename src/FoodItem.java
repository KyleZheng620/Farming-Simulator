import java.nio.file.attribute.PosixFileAttributes;

public class FoodItem extends CropItem{

    private boolean Poisoned;
    private int hungerValue;
    private int waterValue;
    private boolean IsFood;


    public FoodItem(String Food, int quantity) {
        super(Food, quantity);
        IsFood =true;
        Poisoned = true;
        hungerValue = 0;
        waterValue = 0;
        if (Food.equals("Rice")) {
            hungerValue = 2;
        } else if (Food.equals("Potato")) {
            hungerValue = 1;
            waterValue = 1;;
        } else if (Food.equals("Wheat")) {
            hungerValue = 1;
        } else if (Food.equals("Water") ) {
            waterValue = 1;
        } else {
            Poisoned = false;
            if (Food.equals("Mandarin")) {
                hungerValue = 2;
                waterValue = 2;
            } else if (Food.equals("Cooked Rice")){
                hungerValue = 3;
            } else if (Food.equals("Cooked Potato")){
                hungerValue = 4;
            } else if (Food.equals("Bread")){
                hungerValue = 4;
            } else if (Food.equals("Boiled Water")){
                waterValue = 3;
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

    public boolean Isfood() {
        return IsFood;
    }

}
