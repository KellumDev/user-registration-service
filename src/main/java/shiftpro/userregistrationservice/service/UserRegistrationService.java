package shiftpro.userregistrationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import shiftpro.userregistrationservice.model.UserRegistration;
import shiftpro.userregistrationservice.model.UserCredential;
import shiftpro.userregistrationservice.model.UserProfile;

@Service
public class UserRegistrationService {

    private static final String USER_PROFILE_ENDPOINT = "http://localhost:8040/api/user-profile/create-user-profile";
    private static final String PASSWORD_SERVICE_ENDPOINT = "http://password-service/api/passwords";

    private final RestTemplate restTemplate;

    @Autowired
    public UserRegistrationService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserProfile createUserProfile(UserRegistration userRegistration){
            UserProfile userProfile = new UserProfile(
                    userRegistration.getFirstName(),
                    userRegistration.getLastName(),
                    userRegistration.getEmail(),
                    userRegistration.getPhone(),
                    userRegistration.getAddress(),
                    userRegistration.getDateOfBirth(),
                    userRegistration.getGender(),
                    userRegistration.getBio(),
                    userRegistration.getSkills(),
                    userRegistration.getRole(),
                    userRegistration.getUsername()
            );
           return userProfile;
    }

    public UserCredential createPassword(UserRegistration userRegistration){
        UserCredential userCredential = new UserCredential(
                userRegistration.getRole(),
                userRegistration.getUsername(),
                userRegistration.getPassword(),
                userRegistration.getEmail()
        );
        return userCredential;
    }


    public void registerUser(UserRegistration registrationModel) {
        // Create User Profile
        UserProfile userProfile = createUserProfile(registrationModel);
        // Create Password
        UserCredential password = createPassword(registrationModel);

        restTemplate.postForLocation(USER_PROFILE_ENDPOINT, userProfile);
     //   restTemplate.postForLocation(PASSWORD_SERVICE_ENDPOINT, password);
    }






}
