import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    /**
     * Returns first Restaurant with matching name from the list
     * @param restaurantName name of restaurant
     * @return {@link Restaurant} object if specified name is found else throws {@link restaurantNotFoundException}
     */
    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        boolean isRestaurantFound = false;
        Restaurant restaurantFound = null;

        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(restaurantName))
            {
                isRestaurantFound = true;
                restaurantFound = restaurant;
                break;
            }
        }

        if (!isRestaurantFound)
            throw new restaurantNotFoundException(restaurantName);

        return restaurantFound;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }



    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

}
