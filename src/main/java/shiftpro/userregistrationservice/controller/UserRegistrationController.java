package shiftpro.userregistrationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shiftpro.userregistrationservice.model.UserRegistration;
import shiftpro.userregistrationservice.service.UserRegistrationService;

@RestController
@RequestMapping("/api/user-registration")
public class UserRegistrationController {
    public final UserRegistrationService userRegistrationService;
    public record HomeMessage (String welcomeMessage, String description) {}

    public UserRegistrationController(UserRegistrationService userRegistrationService){
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping()
    public HomeMessage home() {
       HomeMessage homeMessage =
               new HomeMessage(
                       "Welcome to the User Registration app!",
                       "The purpose of the service is to process user input form.");
        return homeMessage;
    }

    @PostMapping("/register-user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegistration registerUser(@RequestBody UserRegistration userRegistration){
        this.userRegistrationService.registerUser(userRegistration);
        //get the body of the message then send it to the user-profile-service
        //get the password send it to the password-service
        return userRegistration;
    }

}
