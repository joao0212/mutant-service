package br.com.mutants;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MutantService {

    private MutantRepository mutantRepository;

    MutantService(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    @Outgoing("mutants-out")
    public Multi<Record<Integer, Mutant>> persistOnTopic() {
        Mutant storm = new Mutant(1, "Storm", "Pow");
        Mutant xavier = new Mutant(2, "Xavier", "Mental");

        return Multi
                .createFrom()
                .items(
                        Record.of(storm.id(), storm),
                        Record.of(xavier.id(), xavier)
                );
    }

    @Incoming("mutants-in")
    public void save(Mutant message) {
        mutantRepository.save(message);
    }
}
