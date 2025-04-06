
public class FarmLand {
    private Crop[][] plots;
    private Crop soil;
    private Farmer player;
    private int plotPrice;
    private int lastBoughtRow;
    private int lastBoughtCol;

    public FarmLand(Farmer player){
        plotPrice = 20;
        this.player = player;
        plots = new Crop[4][21];
        lastBoughtRow = 3;
        lastBoughtCol = 0;
        soil = new Crop ("Soil");
        Crop rice = new Crop ("Rice");
        Crop mandarin = new Crop ("Mandarin");
        Crop potato = new Crop ("Potato");
        Crop wheat = new Crop ("Wheat");
        for (int i = 0; i < 4; i++) {
            plots[i][0] = new Crop ("Soil");
        }
    }

    public void dayPass() {
        for (Crop[] row: plots) {
            for (Crop e : row) {
                if (e!=null){
                    e.dayPass(player.getCurrentWeather());
                }
            }
        }
    }

    public Boolean buyPlot(){
        if (lastBoughtCol==20 && lastBoughtRow == 3){
            return null;
        }
        if (player.getMoney() < plotPrice){
            return false;
        } else {
            player.setMoney(player.getMoney()-plotPrice);
            plotPrice *= 1.5;
        }
        if (lastBoughtRow == 3){
            lastBoughtRow = 0;
        } else {
            lastBoughtRow++;
        }
        lastBoughtCol++;
        plots[lastBoughtRow][lastBoughtCol] = soil;

        return true;
    }


    public Crop[][] getPlots() {
        return plots;
    }
}
