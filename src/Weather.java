public class Weather {
    public Weather (){
    }
    public String changeWeather(){
        double chance = Math.random();
        if (chance < 0.05){
            return "Rain";
        }else if (chance <0.07){
            return "ThunderStorm";
        } else if (chance < 0.1){
            return "Snowy";
        } else {
            return "Sunny";
        }
    }


}
