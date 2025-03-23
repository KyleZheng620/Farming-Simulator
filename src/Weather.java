public class Weather {
    public Weather (){
    }
    public String changeWeather(){
        double chance = Math.random();
        if (chance < 0.30){
            return "Rain";
        } else if (chance < 0.4){
            return "Snowy";
        } else {
            return "Sunny";
        }
    }


}
