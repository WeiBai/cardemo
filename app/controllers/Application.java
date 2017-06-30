package controllers;

import play.*;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;

import model.Car;
import play.cache.Cache;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    /**
     * Returns all the registed cars, or the cars that match the specified
     * manufacturer or model.
     * @return
     */
    public static Result getCars(){//(String manufacturer, String model){
        /*List<Car> cars = (List<Car>) Cache.get("cars");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        if (manufacturer == null && model == null){
            node = mapper.convertValue(cars, JsonNode.class);
        } else {
            List<Car> result = new ArrayList<Car>();
            for (Car car : cars){
                if ((manufacturer != null && car.getManufacturer().equals(manufacturer))
                        || (model != null && car.getModel().equals(model))){
                    result.add(car);
                }
            }
            node = mapper.convertValue(result, JsonNode.class);
        }
        return ok(node);*/
        List<Car> cars = (List<Car>) Cache.get("cars");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(cars, JsonNode.class);
        return ok(node);
    }

    public static Result getCar(String manufacturer, String model) {
        List<Car> cars = (List<Car>) Cache.get("cars");
        Car result = null;
        for (Car car : cars) {
            if (car.getManufacturer().equals(manufacturer)
                    && car.getModel().equals(model)) {
                result = car;
                break;
            }
        }
        if (result == null) {
            return notFound();
        } else {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.convertValue(result, JsonNode.class);
            return ok(node);
        }
    }

}
