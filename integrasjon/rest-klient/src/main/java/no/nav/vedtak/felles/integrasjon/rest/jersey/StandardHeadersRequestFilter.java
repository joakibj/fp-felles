package no.nav.vedtak.felles.integrasjon.rest.jersey;

import static javax.ws.rs.Priorities.HEADER_DECORATOR;
import static no.nav.vedtak.felles.integrasjon.rest.jersey.AbstractJerseyRestClient.ALT_NAV_CALL_ID;
import static no.nav.vedtak.felles.integrasjon.rest.jersey.AbstractJerseyRestClient.DEFAULT_NAV_CALLID;
import static no.nav.vedtak.felles.integrasjon.rest.jersey.AbstractJerseyRestClient.DEFAULT_NAV_CONSUMERID;
import static no.nav.vedtak.felles.integrasjon.rest.jersey.AbstractJerseyRestClient.HEADER_CORRELATION_ID;
import static no.nav.vedtak.log.mdc.MDCOperations.getCallId;
import static no.nav.vedtak.sikkerhet.context.SubjectHandler.getSubjectHandler;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

@Priority(HEADER_DECORATOR)
public class StandardHeadersRequestFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext ctx) throws IOException {
        ctx.getHeaders().add(DEFAULT_NAV_CALLID, getCallId());
        ctx.getHeaders().add(ALT_NAV_CALL_ID, getCallId());
        ctx.getHeaders().add(HEADER_CORRELATION_ID, getCallId());
        ctx.getHeaders().add(DEFAULT_NAV_CONSUMERID, getSubjectHandler().getConsumerId());
    }
}
