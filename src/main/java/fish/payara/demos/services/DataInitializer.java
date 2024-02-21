package fish.payara.demos.services;

import fish.payara.demos.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class DataInitializer {
    
    @Inject
    DataService dataService;
    
    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event){
        if(dataService.getAllUsers().isEmpty()){
            User zaq = dataService.createUser("Abderrazzaq", "laanaoui", "laanaoui", "admin");
            User noel = dataService.createUser("Noel", "noel", "noel", "user");
            
            dataService.createQuality("Wonderful", zaq);
            dataService.createQuality("Team Player", zaq);
            dataService.createQuality("Good Judgement", zaq);
            dataService.createQuality("Good Leader", zaq);
            
            dataService.createQuality("Dilligent", noel);
            dataService.createQuality("Responsible", noel);
            dataService.createQuality("Cares for his teammates", noel);
        }
    }
}
