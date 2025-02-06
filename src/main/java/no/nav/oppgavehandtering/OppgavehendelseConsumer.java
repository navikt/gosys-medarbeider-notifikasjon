package no.nav.oppgavehandtering;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class OppgavehendelseConsumer {
    private static final Logger log = LoggerFactory.getLogger(OppgavehendelseConsumer.class);
    @Inject
    OppgavehendelseMedarbeiderClient oppgavehendelseMedarbeiderClient;

    @Incoming("oppgavehendelser")
    public void consume(Oppgavehendelse oppgavehendelse) {
        if (oppgavehendelse.hendelse().hendelsestype() == Hendelsestype.OPPGAVE_ENDRET && oppgavehendelse.utfortAvMedarbeiderTildeltOppgaven()) {
            log.info("Oppgave: {}, Mottatt hendelse om endring utført av medarbeider som er tildelt oppgaven. Ikke relevant å varsle medarbeider", oppgavehendelse.oppgave().oppgaveId());
        } else if (oppgavehendelse.hendelse().hendelsestype() == Hendelsestype.OPPGAVE_OPPRETTET) {
            log.info("Oppgave: {}, Mottatt hendelse om opprettet oppgave. Skal varsle ved direktetildeling eller dersom man allerede arbeider " +
                    "med en oppgave tilknyttet aktuell bruker for fagområdet", oppgavehendelse.oppgave().oppgaveId());
        } else {
            log.info("Oppgave: {}, Mottatt hendelse om at oppgaven er avsluttet. Alltid videresende, og trigger varsel dersom utført av en annen", oppgavehendelse.oppgave().oppgaveId());
        }
    }
}

