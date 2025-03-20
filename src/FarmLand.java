import java.util.ArrayList;

public class FarmLand {
    private ArrayList<ArrayList<Crop>> plots;
    private int rows;
    private int columns;
    private Farmer player;
    private int plotPrice;

    public FarmLand(Farmer player){
        plotPrice = 20;
        this.player = player;
        plots = new ArrayList<>();
        Crop soil = new Crop ("Soil");
        for (int i = 0; i < 2; i++) {
            plots.add(new ArrayList<Crop>());
        }
        plots.get(0).add(soil);
        plots.get(1).add(soil);
        plots.get(0).add(soil);
        plots.get(1).add(soil);
    }

    public Boolean buyPlot(){
        if (rows>4 && columns>9){
            return null;
        } if (player.getMoney() < plotPrice){
            return false;
        } else {
            player.setMoney(player.getMoney()-plotPrice);
            plotPrice *= 4;//james is such a monkey ong
        }
        return true;
    }

    public ArrayList<ArrayList<Crop>> getPlots() {
        return plots;
    }
}
