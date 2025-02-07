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
        if (oppgavehendelse.hendelse().hendelsestype() == Hendelsestype.OPPGAVE_ENDRET && oppgavehendelse.utfortAvMedarbeiderTildeltOppgaven()) {
            log.info("Oppgave: {}, Mottatt hendelse om endring utført av medarbeider som er tildelt oppgaven. Ikke relevant å varsle medarbeider", oppgavehendelse.oppgave().oppgaveId());
        }  else {
            oppgavehendelseMedarbeiderClient.sendNotifikasjon(oppgavehendelse);
        }
    }
}

