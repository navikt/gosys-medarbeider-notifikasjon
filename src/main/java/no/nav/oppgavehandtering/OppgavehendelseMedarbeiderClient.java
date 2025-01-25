package no.nav.oppgavehandtering;


import jakarta.enterprise.context.ApplicationScoped;

//@RegisterRestClient(configKey = "gosys-oppgavebehandling-api")
//@OidcClientFilter("gosys-oppgavebehandling-api-ccf")
@ApplicationScoped
public class OppgavehendelseMedarbeiderClient {

//    @POST
//    @Path("/api/medarbeider")
    void sendNotifikasjon(Notifikasjon notifikasjon) {
        //Dummy
    }
}