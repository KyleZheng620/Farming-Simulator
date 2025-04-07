public class Crop{
    private String crop;

    private int waterLevel;
    private int growthTime;
    private CropItem cropItem;

    public Crop(String crop){
        this.crop = crop;
        if (crop.equals("Rice")) {
            growthTime = 6;
            cropItem = new CropItem("Rice", 1);
        } else if (crop.equals("Potato")) {
            growthTime = 6;
            cropItem = new CropItem("Potato", 1);
        } else if (crop.equals("Wheat")) {
            growthTime = 6;
            cropItem = new CropItem("Wheat", 1);
        } else if (crop.equals("Mandarin")) {
            growthTime = 6;
            cropItem = new CropItem("Mandarin", 1);
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
        if (waterLevel>=10){
            crop = "Soil";
        }
        return true;
    }

    public void dayPass(String weather){
        if (growthTime!=0) {
            if (crop.equals("Soil")) {
                return;
            }
            double chance = Math.random();
            if (weather.equals("Sunny")) {
                if (chance < 0.9) {
                    growthTime-=2;
                }
            }

            if (weather.equals("Rain")) {
                if (chance < 0.9) {
                    growthTime--;
                }
                waterLevel += 2;
            }
        }
        waterLevel--;
        if (waterLevel<0){
            crop = "Soil";
        }
    }

    public void plantCrop(String newCrop, Farmer player){
        if (crop.equals("Soil")) {
            player.getInventory().removeItem(newCrop,1);
            crop = newCrop.substring(0,newCrop.length()-6);
            if (crop.equals("Rice")) {
                growthTime = 6;
                waterLevel = 3;
                cropItem = new CropItem("Rice", 1);
            } else if (crop.equals("Potato")) {
                growthTime = 6;
                waterLevel = 3;
                cropItem = new CropItem("Potato", 1);
            } else if (crop.equals("Wheat")) {
                growthTime = 6;
                waterLevel = 3;
                cropItem = new CropItem("Wheat", 1);
            } else if (crop.equals("Mandarin")) {
                growthTime = 6;
                waterLevel = 3;
                cropItem = new CropItem("Mandarin", 1);
            }
        }
    }

    public boolean harvestCrop(Farmer player){
        if (growthTime <= 0 && !crop.equals("Soil")){
            crop = "Soil";
            player.getInventory().addItem(cropItem);
            return true;
        }
        return false;
    }

    public String getCrop(){
        return crop;
    }


    public int getGrowthTime(){
        if (growthTime%2 == 1){
            return growthTime/2 + 1;
        }
        return growthTime/2;
    }
}
