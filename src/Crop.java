import java.lang.reflect.Array;

public class Crop {

    private boolean watered;
    private int waterLevel;
    private int growthTimeLeft;
    private String crop;

    public Crop(String crop){
        watered = false;
        this.crop = crop;
        waterLevel = 0;
        growthTimeLeft = 10;

    }

    public boolean waterCrop(boolean rain, boolean player) {
        if (rain && player){
            waterLevel+=5;
        } else if (rain){
            waterLevel+=2;
        } else {
            waterLevel++;
        }
        if (waterLevel>15){
            return false;
        }
        return true;
    }

    public boolean dayPass(){
        double chance = Math.random();
        if (chance>0.8){
            growthTimeLeft--;
        }
        waterLevel--;
        if (waterLevel<0){
            return false;
        }
        return true;
    }


}
