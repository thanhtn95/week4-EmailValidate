package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class HomeController {
    private static Pattern pattern;

    private Matcher matcher;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    public HomeController() {

        pattern = Pattern.compile(EMAIL_REGEX);

    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST )
    public ModelAndView user(@RequestParam("email") String email){
        boolean isValid = this.validate(email);
        if(!isValid){
            ModelAndView modelAndView = new ModelAndView("home","messeage", "Invalid");
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("success","email",email);
            return modelAndView;
        }
    }

    private boolean validate(String regex) {

        matcher = pattern.matcher(regex);

        return matcher.matches();

    }
}
