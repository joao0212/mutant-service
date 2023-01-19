package br.com.mutants;

import br.com.config.ScyllaClusterConfig;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class MutantRepository {

    private final Session session;

    MutantRepository(ScyllaClusterConfig scyllaClusterConfig) {
        Cluster cluster = scyllaClusterConfig.buildCluster();
        this.session = cluster.connect("mutants");
    }

    void save(Mutant message){
        PreparedStatement ps =
                session.prepare
                        ("INSERT INTO mutant (id, name, power) VALUES (?, ?, ?)");
        BoundStatement bs = ps.bind(message.id(), message.name(), message.power());
        session.execute(bs);
    }
}
