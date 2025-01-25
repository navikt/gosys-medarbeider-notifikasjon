package no.nav.oppgavehandtering;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;

import java.util.HashMap;
import java.util.Map;

public class KafkaTestResource implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        Map<String, String> props1 = InMemoryConnector.switchIncomingChannelsToInMemory("oppgavehendelser");
        return new HashMap<>(props1);
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();
    }
}