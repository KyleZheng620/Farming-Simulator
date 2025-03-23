class SnowFlake {
    private int[] x;
    private int[] y;

    public SnowFlake() {
        x = new int[10];
        y = new int[10];
        int value = 0;
        for (int i = 0; i < x.length; i++) {
            value += 790/5;
            if (i%5 == 0) {
                value = 0;
            }
            x[i] = value;
        }
        value = 0;
        for (int i = 0; i < y.length; i++) {
            if (i == 5) {
                value = 316/2;
            }
            y[i] = value;
        }
    }

    public int[] getXandY(int num) {
        return new int[] {x[num], y[num], 790/5,316/2, (int)(Math.random() * 1920), 0};
    }

}
