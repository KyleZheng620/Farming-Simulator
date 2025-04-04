public class Weather {
    public Weather (){
    }
    public String changeWeather(){
        double chance = Math.random();
        if (chance < 0.25){
            return "Rain";
        } else if (chance < 0.35){
            return "Snowy";
        } else {
            return "Sunny";
        }
    }


}
