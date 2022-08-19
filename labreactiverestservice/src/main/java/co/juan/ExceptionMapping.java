package co.juan;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;

@Path("cheeses/{cheese}")
public class ExceptionMapping {

    @GET
    public String findCheese(String cheese) {
        if(cheese == null)
            // send a 400
            throw new BadRequestException();
        if(!cheese.equals("camembert"))
            // send a 404
            throw new NotFoundException("Unknown cheese: " + cheese);
        return "Camembert is a very nice cheese";
    }
}