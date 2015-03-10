
package rest;

import com.google.gson.Gson;
import entity.Person;
import facade.EntityFacade;
import java.util.List;
import javax.persistence.Parameter;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

@Path("/person")
public class PersonService {

    @Context
    private UriInfo context;
    private EntityFacade facade;
    
    public PersonService() {
        EntityFacade facade = new EntityFacade(Persistence.createEntityManagerFactory("CA2WebPU"));
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getJson(int id) {
        List<Person> projects = facade.getPersons();
       String projectsAsJson = new Gson().toJson(projects);
       
       return projectsAsJson;
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
