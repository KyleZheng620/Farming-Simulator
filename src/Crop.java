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

    public Boolean waterCrop(boolean rain, boolean player) {
        if (crop.equals("soil")){
            return null;
        }
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

    public Boolean dayPass(){
        if (crop.equals("soil")){
            return null;
        }
        double chance = Math.random();
        if (chance>0.8){
            growthTimeLeft--;
        }
        waterLevel--;
        return waterLevel >= 0;
    }


    public void setCrop(String newCrop){
        crop = newCrop;
    }

    public String getCrop(){
        return crop;
    }
}
