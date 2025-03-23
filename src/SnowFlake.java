public class SnowFlake {
    private int[] x;
    private int[] y;

    public SnowFlake() {
        x = new int[10];
        y = new int[10];
        int value = 0;
        for (int i = 0; i < x.length; i++) {
            value += 720/5;
            if (i%5 == 0) {
                value = 0;
            }
            x[i] = value;
        }
        value = 0;
        for (int i = 0; i < y.length; i++) {
            if (i == 5) {
                value = 390/2;
            }
            y[i] = value;
        }
    }

    public int[] getXandY(int num) {
        return new int[] {x[num], y[num], 720/5,390/2};
    }
}
