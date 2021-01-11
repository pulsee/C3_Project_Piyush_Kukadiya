import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void setup()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        // Arrange

        LocalTime currentTime = LocalTime.parse("10:31:00");
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(currentTime);

        // Action
        boolean restaurantOpenActualValue = spyRestaurant.isRestaurantOpen();

        // Assert
        assertTrue(restaurantOpenActualValue);

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        // Arrange

        LocalTime currentTime = LocalTime.parse("10:29:00");
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(currentTime);

        // Action
        boolean restaurantOpenActualValue = spyRestaurant.isRestaurantOpen();

        // Assert
        assertFalse(restaurantOpenActualValue);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>Order Value<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void order_value_should_be_zero_when_list_is_empty(){

        ArrayList<String> items = new ArrayList<>();

        try {
            assertEquals(0,restaurant.getOrderValue(items));
        } catch (itemNotFoundException e) {
            fail("Must not throw Exception");
        }

    }

    @Test
    public void selected_item_that_does_not_exist_should_throw_exception() {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        ArrayList<String> selectedItems = new ArrayList<>();
        selectedItems.add("French fries");

        assertThrows(itemNotFoundException.class,
                ()->restaurant.getOrderValue(selectedItems));
    }

    @Test
    public void order_value_should_be_100_when_price_of_items_are_40_and_60(){

        restaurant.addToMenu("Sweet corn soup",40);
        restaurant.addToMenu("Vegetable lasagne", 60);
        restaurant.addToMenu("Omlette", 70);
        restaurant.addToMenu("Roti", 20);

        ArrayList<String> itemsSelected = new ArrayList<>();

        itemsSelected.add("Sweet corn soup");
        itemsSelected.add("Vegetable lasagne");

        try {
            assertEquals(100,restaurant.getOrderValue(itemsSelected));
        } catch (itemNotFoundException e) {
            fail("Must not throw Exception");
        }

    }

    //<<<<<<<<<<<<<<<<<<<<Order Value>>>>>>>>>>>>>>>>>>>>>>>>>>
}