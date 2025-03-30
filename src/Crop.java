public class Crop{
    private String crop;

    private int waterLevel;
    private int growthTime;

    private String[] AllPlants = {"Rice","Potatoes","Wheat","Mandarin","Soil"};

    public Crop(String crop){
        this.crop = crop;
        if (crop.equals("Rice")) {
            growthTime = 6;
        } else if (crop.equals("Potatoes")) {
            growthTime = 6;
        } else if (crop.equals("Wheat")) {
            growthTime = 6;
        } else if (crop.equals("Mandarin")) {
            growthTime = 6;
        } else if (crop.equals("Soil")) {
            growthTime = 0;
        }

        waterLevel = 3;
    }

    public boolean waterCrop(Farmer player) {
        int waterAmount = player.getInventory().itemAmount("Water");
        if (crop.equals("Soil") || waterAmount <= 0){
            return false;
        }
        waterLevel++;
        player.getInventory().removeItem("Water", 1);
        if (waterLevel>=15){
            crop = "Soil";
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

    public int getWaterLevel(){
        return waterLevel;
    }
    public int getGrowthTime(){
        return growthTime/2;
    }
}
