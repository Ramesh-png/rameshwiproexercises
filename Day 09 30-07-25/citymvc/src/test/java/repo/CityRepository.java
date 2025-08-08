package repo;


import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepository {
    public List<String> getCities() {
        return Arrays.asList("Andhra","Bangalore", "Chennai", "Hyderabad");
    }
}
