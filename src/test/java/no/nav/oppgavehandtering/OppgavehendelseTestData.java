package no.nav.oppgavehandtering;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OppgavehendelseTestData {
    private Hendelsestype hendelsestype;
    public OppgavehendelseTestData medHendelsestype(Hendelsestype hendelsestype) {
        this.hendelsestype = hendelsestype;
        return this;
    }

    public Oppgavehendelse build() {
        return new Oppgavehendelse
                (new Oppgavehendelse.Hendelse(hendelsestype, LocalDateTime.now()),
                        new Oppgavehendelse.UtfortAv("Z99999", "0101"),
                        new Oppgavehendelse.Oppgave(1L, 1,
                                new Oppgavehendelse.Tilordning("0101", null, "Z99999"),
                                new Oppgavehendelse.Kategorisering("SYK", "JFR", null, null,
                                        Oppgavehendelse.Kategorisering.Prioritet.NORMAL),
                                new Oppgavehendelse.Behandlingsperiode(LocalDate.now(), LocalDate.now().plusDays(1)),
                                null));
    }
}
