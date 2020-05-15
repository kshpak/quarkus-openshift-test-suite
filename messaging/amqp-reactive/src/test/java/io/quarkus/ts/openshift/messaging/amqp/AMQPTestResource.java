package io.quarkus.ts.openshift.messaging.amqp;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.apache.commons.io.FileUtils;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

public class AMQPTestResource implements QuarkusTestResourceLifecycleManager {
    private EmbeddedActiveMQ embedded;

    @Override
    public Map<String, String> start() {
        try {
            FileUtils.deleteDirectory(Paths.get("./target/artemis").toFile());
            embedded = new EmbeddedActiveMQ();
            embedded.start();
        } catch (Exception e) {
            throw new RuntimeException("Could not start embedded ActiveMQ server", e);
        }
        return Collections.emptyMap();
    }

    @Override
    public void stop() {
        try {
            embedded.stop();
        } catch (Exception e) {
            throw new RuntimeException("Could not stop embedded ActiveMQ server", e);
        }
    }
}
