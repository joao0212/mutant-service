package br.com.mutants;

import br.com.config.ScyllaClusterConfig;
import com.datastax.driver.core.*;

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
        BoundStatement bs = ps.bind(message.getId(), message.getName(), message.getPower());
        session.execute(bs);
    }

    Mutant findById(Integer id) {
        PreparedStatement ps =
                session.prepare(
                        "SELECT * FROM mutant WHERE id = ?"
                );
        BoundStatement bs = ps.bind(id);
        ResultSet rs = session.execute(bs);

        Row row = rs.one();
        return MutantConverter.convert(row);
    }
}
