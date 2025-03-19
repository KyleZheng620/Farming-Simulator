import java.util.ArrayList;

public class FarmLand {
    private ArrayList<ArrayList<Crop>> plots;
    int rows;
    int columns;

    public FarmLand(){
        plots = new ArrayList<>();
        int rows = 2;
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
        if (rows>9 || columns>9){
            return null;
        }
        return false;
    }
}
