package no.nav.oppgavehandtering;


import io.quarkus.oidc.client.filter.OidcClientFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "gosys-oppgavebehandling-api")
@OidcClientFilter
@ApplicationScoped
public interface OppgavehendelseMedarbeiderClient {
    @POST
    @Path("/hendelser")
    void sendNotifikasjon(Oppgavehendelse oppgavehendelse);
}