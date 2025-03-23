public class Weather {
    public Weather (){
    }
    public String changeWeather(){
        double chance = Math.random();
        if (chance < 0.55){
            return "Snowy";
        }else if (chance <0.07){
            return "ThunderStorm";
        } else if (chance < 0.1){
            return "Rain";
        } else {
            return "Sunny";
        }
    }


}
