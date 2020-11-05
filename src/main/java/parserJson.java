

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class parserJson {
    public static void getFlight()  {
        String path = "d:\\flights.json";
        List<Flight> flights = new ArrayList<Flight>();
        int minPrice = 100000;
        int maxPrice = 0;
        int middlePrice = 0;
        int count = 0;
        try{
            JSONParser parser = new JSONParser();
            FileReader fileReader = new FileReader(path);
            Object object = parser.parse(fileReader);
            JSONObject jsonObject = (JSONObject) object;
            JSONArray flightsArrays = (JSONArray) jsonObject.get("flights");
            for (Object o: flightsArrays){
                JSONObject flightObject = (JSONObject) o;
                flights.add(new Flight(flightObject.get("fromCity").toString(), flightObject.get("toCity").toString(), Integer.parseInt(flightObject.get("price").toString())));
            }

            for (Flight flight : flights){
                if (flight.getFromCity().equals("Москва") && flight.getToCity().equals("Хабаровск")){
                    if (flight.getPrice() < minPrice){
                        minPrice = flight.getPrice();
                    }
                    if (flight.getPrice() > maxPrice){
                        maxPrice = flight.getPrice();
                    }

                    middlePrice +=flight.getPrice();
                    count++;
                }
            }
            middlePrice = middlePrice / count;
        }
        catch (Exception e){
            System.out.println("Что-то пошло не так ");
        }


        System.out.println("Минимальная стоимость: " + minPrice);
        System.out.println("Максимальная стоимость: " + maxPrice);
        System.out.println("Средняя стомость: " + middlePrice);
    }

}
