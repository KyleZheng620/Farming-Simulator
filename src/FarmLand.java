
public class FarmLand {
    private Crop[][] plots;
    private Farmer player;

    public FarmLand(Farmer player){
        this.player = player;
        plots = new Crop[4][21];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 21; j++){
                plots[i][j] = new Crop ("Soil");
            }
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


    public Crop[][] getPlots() {
        return plots;
    }
}
