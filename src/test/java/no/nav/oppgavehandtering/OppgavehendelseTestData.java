package no.nav.oppgavehandtering;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OppgavehendelseTestData {
    private Hendelsestype hendelsestype;
    private Oppgavehendelse.Tilordning tilordning = new Oppgavehendelse.Tilordning("0101", null, "Z99998");
    private final Oppgavehendelse.UtfortAv utfortAv =  new Oppgavehendelse.UtfortAv("Z99999", "0101");

    public OppgavehendelseTestData medHendelsestype(Hendelsestype hendelsestype) {
        this.hendelsestype = hendelsestype;
        return this;
    }

    public OppgavehendelseTestData utfortAvTildelt() {
        this.tilordning = new Oppgavehendelse.Tilordning(utfortAv.enhetsnr(), null, utfortAv.navIdent());
        return this;
    }

    public Oppgavehendelse build() {
        return new Oppgavehendelse
                (new Oppgavehendelse.Hendelse(hendelsestype, LocalDateTime.now()),
                        utfortAv,
                        new Oppgavehendelse.Oppgave(1L, 1,
                                tilordning,
                                new Oppgavehendelse.Kategorisering("SYK", "JFR", null, null,
                                        Oppgavehendelse.Kategorisering.Prioritet.NORMAL),
                                new Oppgavehendelse.Behandlingsperiode(LocalDate.now(), LocalDate.now().plusDays(1)),
                                null));
    }
}
