import java.util.ArrayList;

public class FarmLand {
    private ArrayList<ArrayList<Crop>> plots;
    private Farmer player;
    private int plotPrice;

    public FarmLand(Farmer player){
        plotPrice = 20;
        this.player = player;
        plots = new ArrayList<>();
        Crop soil = new Crop ("Soil");
        Crop rice = new Crop ("Rice");
        Crop mandarin = new Crop ("Mandarin");
        Crop potatoes = new Crop ("Potatoes");
        Crop wheat = new Crop ("Wheat");
        for (int i = 0; i < 4; i++) {
            plots.add(new ArrayList<Crop>());
        }
        plots.get(0).add(soil);
        plots.get(1).add(soil);
        plots.get(2).add(soil);
        plots.get(3).add(soil);
    }

    public boolean buyPlot(){
        if (player.getMoney() < plotPrice){
            return false;
        } else {
            player.setMoney(player.getMoney()-plotPrice);
            plotPrice *= 2.2;
        }
        return true;
    }

    public ArrayList<ArrayList<Crop>> getPlots() {
        return plots;
    }
}
