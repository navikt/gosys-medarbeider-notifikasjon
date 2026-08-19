package no.nav.oppgavehandtering;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class OppgavehendelseConsumer {
    private static final Logger log = LoggerFactory.getLogger(OppgavehendelseConsumer.class);

    @RestClient
    OppgavehendelseMedarbeiderClient oppgavehendelseMedarbeiderClient;

    @Incoming("oppgavehendelser")
    public void consume(Oppgavehendelse oppgavehendelse) {
        log.info("Sender videre mottatt hendelse for: {}", oppgavehendelse.oppgave().oppgaveId());
        oppgavehendelseMedarbeiderClient.sendNotifikasjon(oppgavehendelse);
    }
}

