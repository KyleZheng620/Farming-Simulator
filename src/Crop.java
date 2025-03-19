public class Crop {
    private String crop;

    private int waterLevel;
    private int CropPrice = 0;
    private int growthTimeLeft;
    private int growthTime;

    private boolean watered;

    private String[] AllPlants = {"Rice","Potatoes","Wheat","Mandarin","Soil"};

    public Crop(String crop){
        this.crop = crop;
        this.CropPrice = CropPrice;
        this.growthTime = growthTime;

        if (crop.equals("Rice")) {
            CropPrice = 10;
            growthTime = 5;
        } else if (crop.equals("Potatoes")) {
            CropPrice = 10;
            growthTime = 5;
        } else if (crop.equals("Wheat")) {
            CropPrice = 10;
            growthTime = 5;
        } else if (crop.equals("Mandarin")) {
            CropPrice = 10;
            growthTime = 5;
        } else if (crop.equals("Soil")) {
            CropPrice = 0;
            growthTime = 0;
        }

        watered = false;
        waterLevel = 0;
        growthTimeLeft = growthTime;
    }

    public Boolean waterCrop(boolean rain, boolean player) {
        if (crop.equals("Soil")){
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

    public int getCropPrice() { return CropPrice;}
}
