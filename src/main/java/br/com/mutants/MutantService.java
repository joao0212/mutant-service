package br.com.mutants;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;

@ApplicationScoped
public class MutantService {

    private final MutantRepository mutantRepository;

    MutantService(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }
    @Outgoing("mutants-out")
    //Reactive message sent messages to the channel
    public Multi<Record<Integer, Mutant>> persistOnTopic() {
        return Multi
                .createFrom()
                .ticks().every(Duration.ofMillis(1000))
                .onOverflow().drop()
                .map(tick -> {
                    int id = new Random().nextInt(300);
                    String name = "Mutant" + id;
                    int power = new Random().nextInt();

                    return Record.of(id, new Mutant(id, name, power));
                });
    }

    //For every message, Reactive Message calls save
    @Incoming("mutants-in")
    public void save(Mutant message) {
        //Jackson serializer
        mutantRepository.save(message);
    }
}
