package net.kiranatos.b7observer.e1weather;

/**
 * Сьерра К., Бейтс Б., Фримен Э., Фримен Э. - Паттерны проектирования (2011-656-1).
 * стр. 71 глава 2
*/

public class WeatherStation {
    public static void main(String[] args) {
        System.out.println("\nExample from book: \n"
                + "Сьерра К., Бейтс Б., Фримен Э., Фримен Э. - Паттерны проектирования (2011-656-1)"
                + "стр. 71 глава 2\n");
        
        WeatherData wD = new WeatherData();
        CurrentConditionsDisplay ccD = new CurrentConditionsDisplay(wD);
        OtherDisplay oD = new OtherDisplay(wD);
        
        wD.setMeasurements(80, 60, 30.5f);
        wD.setMeasurements(70, 50, 31.5f);
        wD.setMeasurements(90, 60, 32.5f);
    }    
}
