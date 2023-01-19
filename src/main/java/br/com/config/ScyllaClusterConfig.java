package br.com.config;

import com.datastax.driver.core.Cluster;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScyllaClusterConfig {

    @ConfigProperty(name = "scylla.db.contract-points")
    String contractPoints;

    @ConfigProperty(name = "scylla.db.cluster-name")
    String clusterName;

    public Cluster buildCluster() {
        return Cluster.builder()
                .withoutJMXReporting()
                .withClusterName(clusterName)
                .addContactPoint(contractPoints)
                .build();
    }
}
