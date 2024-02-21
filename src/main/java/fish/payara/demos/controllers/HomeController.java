package fish.payara.demos.controllers;

import fish.payara.demos.entities.Quality;
import fish.payara.demos.entities.User;
import fish.payara.demos.services.DataService;
import java.util.List;
import java.util.Optional;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@RequestScoped
@Named
public class HomeController {
    
    @Inject
    DataService dataService;
    
    @Inject
    SecurityContext securityContext;
    
    @Inject
    FacesContext facesContext;
    
    private Optional<User> currentUser;
    private List<Quality> currentQualities;
    
    @PostConstruct
    public void initialize(){
        String username = securityContext.getCallerPrincipal().getName();
        this.currentUser = dataService.getUser(username);
        this.currentUser.ifPresent(user -> {
            this.currentQualities  = dataService.getQualities(user);
        });
    }
    
    public User getCurrentUser(){
        return currentUser.orElse(null);
    }

    public List<Quality> getCurrentQualities() {
        return currentQualities;
    }
    
    public boolean isAllowedToSeeUsers(){
        return securityContext.isCallerInRole("admin");
    }
    
    public String logout() throws ServletException{
        ExternalContext ec = facesContext.getExternalContext();
        ((HttpServletRequest)ec.getRequest()).logout();
        return "/login.xhtml?faces-redirect=true";
    }
}
