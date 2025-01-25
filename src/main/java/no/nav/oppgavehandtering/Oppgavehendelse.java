package no.nav.oppgavehandtering;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public record Oppgavehendelse(Hendelse hendelse, UtfortAv utfortAv, Oppgave oppgave) {
    public record Hendelse(Hendelsestype hendelsestype, LocalDateTime tidspunkt) {
    }

    public record UtfortAv(String navIdent, String enhetsnr) { }

    public record Oppgave(Long oppgaveId, Integer versjon, Tilordning tilordning, Kategorisering kategorisering,
                          Behandlingsperiode behandlingsperiode, Bruker bruker) {
    }

    public record Tilordning(String enhetsnr, Long enhetsmappeId, String navIdent) {
    }

    public record Kategorisering(String tema, String oppgavetype, String behandlingstema, String behandlingstype,
                                 Prioritet prioritet) {
        public enum Prioritet {HOY, NORMAL, LAV}
    }

    public record Behandlingsperiode(LocalDate aktiv, LocalDate frist) {
    }

    public record Bruker(String ident, IdentType identType) {
        public enum IdentType {FOLKEREGISTERIDENT, NPID, ORGNR, SAMHANDLERNR}
    }

    public boolean utfortAvMedarbeiderTildeltOppgaven() {
        return Objects.equals(utfortAv.navIdent, oppgave.tilordning.navIdent);
    }

    public boolean oppgaveAvsluttet() {
        return hendelse.hendelsestype == Hendelsestype.OPPGAVE_FERDIGSTILT || hendelse.hendelsestype == Hendelsestype.OPPGAVE_FEILREGISTRERT;
    }

}