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
        if(oppgavehendelse.hendelse().hendelsestype() == Hendelsestype.OPPGAVE_OPPRETTET) {
            log.info("Mottatt hendelse for opprettet oppgave");
            if(!oppgavehendelse.utfortAvMedarbeiderTildeltOppgaven() && oppgavehendelse.oppgave().tilordning().navIdent() != null) {
                log.info("Opprettet oppgave som er direkte tildelt en annnen medarbeider. Utført av: {}, tildelt: {}", oppgavehendelse.utfortAv().navIdent(), oppgavehendelse.oppgave().tilordning());
            }
        }  else if(!oppgavehendelse.utfortAvMedarbeiderTildeltOppgaven()) {
            log.info("Mottatt hendelse utfort av {} {}. Oppgaven er tildelt: {}", oppgavehendelse.hendelse().hendelsestype(), oppgavehendelse.utfortAv().navIdent(), oppgavehendelse.oppgave().tilordning());
        } else if(oppgavehendelse.oppgaveAvsluttet()) {
            log.info("Mottatt hendelse for avsluttet oppgave");
            oppgavehendelseMedarbeiderClient.sendNotifikasjon(new Notifikasjon(oppgavehendelse.hendelse().hendelsestype()));
        } else {
            log.debug("Mottatt hendelse som ikke er relevant for notifikasjon");
        }
    }
}

