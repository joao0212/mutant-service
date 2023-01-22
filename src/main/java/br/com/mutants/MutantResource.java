package br.com.mutants;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mutants")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class MutantResource {

    private final MutantRepository mutantRepository;

    MutantResource(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    @GET
    @Path("/{id}")
    public Mutant findById(@PathParam("id") Integer id) {
        return mutantRepository.findById(id);
    }
}
