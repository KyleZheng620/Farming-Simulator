public class Crop {
    private String crop;

    private int waterLevel;
    private int CropPrice;
    private int growthTime;

    private String[] AllPlants = {"Rice","Potatoes","Wheat","Mandarin","Soil"};

    public Crop(String crop){
        this.crop = crop;
        if (crop.equals("Rice")) {
            CropPrice = 10;
            growthTime = 6;
        } else if (crop.equals("Potatoes")) {
            CropPrice = 10;
            growthTime = 6;
        } else if (crop.equals("Wheat")) {
            CropPrice = 10;
            growthTime = 6;
        } else if (crop.equals("Mandarin")) {
            CropPrice = 10;
            growthTime = 6;
        } else if (crop.equals("Soil")) {
            CropPrice = 0;
            growthTime = 6;
        }

        waterLevel = 3;
    }

    public Boolean waterCrop(boolean player) {
        if (crop.equals("Soil")){
            return null;
        }
        waterLevel++;
        if (waterLevel>15){
            return false;
        }
        return true;
    }

    public Boolean dayPass(String weather){
        if (crop.equals("Soil")){
            return null;
        }
        double chance = Math.random();
        if (weather.equals("Sunny")){
            if (chance<0.9){
                growthTime--;
            }
        }

        if (weather.equals("Snowy")) {
            if (chance < 0.2) {
                growthTime--;
            }
        }

        if (weather.equals("Rain")) {
            if (chance<0.9){
                growthTime--;
            }
            waterLevel+=3;
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

    public int getWaterLevel(){
        return waterLevel;
    }
    public int getGrowthTime(){
        return growthTime/2;
    }
}
