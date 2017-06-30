package global;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import model.Car;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.cache.Cache;

/**
 * Created by baiwe on 2017/6/30.
 */
public class Global extends GlobalSettings {
    private static final String CARS_FILE = "cars.json";

    public void onStart(Application app){
        Logger.info("Application has started");
        try{
            loadCars();
        } catch (Exception e){
            Logger.error("Could not load the " + CARS_FILE + " file. Error: "
                            + e.getLocalizedMessage());
        }
    }

    private void loadCars() throws Exception {
        Logger.info("Loading " + CARS_FILE);
        File input = new File("C:\\Users\\baiwe\\IdeaProjects\\CarDemo\\app\\global\\cars.json");
        InputStream is = new FileInputStream(input);
      //  InputStream is = Global.class.getResourceAsStream("C:\\Users\\baiwe\\IdeaProjects\\CarDemo\\app\\global\\cars.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Car> cars = mapper.readValue(is, new TypeReference<List<Car>>(){});
        Cache.set("cars", cars,0);
        Logger.info(cars.size() + " cars loaded.");
    }

    public void onStop(Application app){
        Logger.info("Application shutdown...");
    }
}
