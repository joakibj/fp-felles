package no.nav.vedtak.felles.integrasjon.infotrygdberegningsgrunnlag;

import no.nav.tjeneste.virksomhet.infotrygdberegningsgrunnlag.v1.binding.FinnGrunnlagListePersonIkkeFunnet;
import no.nav.tjeneste.virksomhet.infotrygdberegningsgrunnlag.v1.binding.FinnGrunnlagListeSikkerhetsbegrensning;
import no.nav.tjeneste.virksomhet.infotrygdberegningsgrunnlag.v1.binding.FinnGrunnlagListeUgyldigInput;
import no.nav.tjeneste.virksomhet.infotrygdberegningsgrunnlag.v1.meldinger.FinnGrunnlagListeRequest;
import no.nav.tjeneste.virksomhet.infotrygdberegningsgrunnlag.v1.meldinger.FinnGrunnlagListeResponse;

public interface InfotrygdBeregningsgrunnlagConsumer {

    FinnGrunnlagListeResponse finnBeregningsgrunnlagListe(FinnGrunnlagListeRequest finnGrunnlagListeRequest) throws FinnGrunnlagListeSikkerhetsbegrensning, FinnGrunnlagListeUgyldigInput, FinnGrunnlagListePersonIkkeFunnet;

}