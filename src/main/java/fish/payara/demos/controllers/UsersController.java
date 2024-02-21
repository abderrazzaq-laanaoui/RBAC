package fish.payara.demos.controllers;

import fish.payara.demos.entities.User;
import fish.payara.demos.services.DataService;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestScoped
@Named
public class UsersController {
    
    @Inject
    DataService dataService;
    
    private List<User> allUsers;
    
    @PostConstruct
    public void initialize(){
        this.allUsers = dataService.getAllUsers();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
}
