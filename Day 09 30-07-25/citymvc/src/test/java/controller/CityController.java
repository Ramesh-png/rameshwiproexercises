package controller;



import java.util.List;

import service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/city")
    public String showCities(Model model) {
        List<String> cityList = cityService.getAllCities();
        model.addAttribute("cities", cityList);
        return "cities"; // corresponds to templates/cities.html
    }
}
