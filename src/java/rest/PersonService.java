
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
import javax.ws.rs.PathParam;

@Path("person")
public class PersonService {

    @Context
    private UriInfo context;
    private EntityFacade facade;
    
    public PersonService() {
        facade = new EntityFacade(Persistence.createEntityManagerFactory("CA2WebPU"));
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getJson(@PathParam("id") int id) {
       
       Person person =  facade.findPerson(id);
       PersonDTO dto = new PersonDTO(person.getId(), person.getEmail(), person.getFirstName(), person.getLastName());
       String personsAsJson = new Gson().toJson(dto);
       
       return personsAsJson;
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
