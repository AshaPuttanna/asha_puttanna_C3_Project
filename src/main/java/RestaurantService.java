import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws RestaurantNotFoundException {
        if(this.getRestaurants().stream().filter(it -> it.getName().contains(restaurantName)).collect(Collectors.toList()).size() >0){
            return this.getRestaurants().stream().filter(it -> it.getName().contains(restaurantName)).collect(Collectors.toList()).get(0) ;
        }else{
            try {
                throw new RestaurantNotFoundException(restaurantName);
            } catch (RestaurantNotFoundException e) {
                throw new RestaurantNotFoundException(e.getMessage());
            }
        }


    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws RestaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
