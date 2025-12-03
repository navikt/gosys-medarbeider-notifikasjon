package no.nav.oppgavehandtering;

import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.spi.Connector;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import static no.nav.oppgavehandtering.Hendelsestype.OPPGAVE_ENDRET;
import static no.nav.oppgavehandtering.Hendelsestype.OPPGAVE_FERDIGSTILT;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@QuarkusTest
@QuarkusTestResource(KafkaTestResource.class)
class OppgavehendelseConsumerTest {
    @Inject
    @Connector("smallrye-in-memory")
    InMemoryConnector connector;

    @InjectMock
    @RestClient
    OppgavehendelseMedarbeiderClient oppgavehendelseMedarbeiderClient;

    @Test
    void sender_notifikasjon_naar_utfort_av_annen_enn_medarbeider_tildelt_oppgaven() {
        Oppgavehendelse oppgavehendelse = new OppgavehendelseTestData()
                .medHendelsestype(OPPGAVE_FERDIGSTILT)
                .build();

        this.connector.source("oppgavehendelser").send(oppgavehendelse);
        verify(oppgavehendelseMedarbeiderClient).sendNotifikasjon(oppgavehendelse);
    }

    @Test
    void sender_ikke_notifikasjon_naar_endring_utfort_av_medarbeider_tildelt_oppgaven() {
        Oppgavehendelse oppgavehendelse = new OppgavehendelseTestData()
                .medHendelsestype(OPPGAVE_ENDRET)
                .utfortAvTildelt()
                .build();

        this.connector.source("oppgavehendelser").send(oppgavehendelse);
        verifyNoInteractions(oppgavehendelseMedarbeiderClient);
    }

}