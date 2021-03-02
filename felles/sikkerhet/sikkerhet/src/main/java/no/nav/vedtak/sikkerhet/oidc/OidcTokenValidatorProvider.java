package no.nav.vedtak.sikkerhet.oidc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import no.nav.vedtak.sikkerhet.domene.IdentType;
import no.nav.vedtak.util.env.Environment;

public class OidcTokenValidatorProvider {
    static final String AGENT_NAME_KEY = "agentName";
    static final String PASSWORD_KEY = "password";
    static final String HOST_URL_KEY = "hostUrl";
    static final String ISSUER_URL_KEY = "issuerUrl";
    static final String JWKS_URL_KEY = "jwksUrl";
    static final String ALT_ISSUER_URL_KEY = "issuer.url";
    static final String ALT_JWKS_URL_KEY = "jwks.url";
    static final String PROVIDERNAME_OPEN_AM = "oidc_OpenAM.";
    static final String PROVIDERNAME_STS = "oidc_sts.";
    static final String PROVIDERNAME_AAD_B2C = "oidc_aad_b2c.";

    static final Environment ENV = Environment.current();

    static final Set<IdentType> interneIdentTyper = new HashSet<>(Arrays.asList(IdentType.InternBruker, IdentType.Systemressurs));
    static final Set<IdentType> eksterneIdentTyper = new HashSet<>(Arrays.asList(IdentType.EksternBruker));

    private static volatile OidcTokenValidatorProvider instance; // NOSONAR
    private final Map<String, OidcTokenValidator> validators;

    private OidcTokenValidatorProvider() {
        validators = init();
    }

    private OidcTokenValidatorProvider(Map<String, OidcTokenValidator> validators) {
        this.validators = validators;
    }

    public static OidcTokenValidatorProvider instance() {
        var inst = instance;
        if (inst == null) {
            inst = new OidcTokenValidatorProvider();
            instance = inst;
        }
        return inst;
    }

    // For test
    static void clearInstance() {
        instance = null;
    }

    // For test
    static void setValidators(Map<String, OidcTokenValidator> validators) {
        instance = new OidcTokenValidatorProvider(validators);
    }

    public OidcTokenValidator getValidator(String issuer) {
        return validators.get(issuer);
    }

    private Map<String, OidcTokenValidator> init() {
        var configs = new OpenIDProviderConfigProvider().getConfigs();
        return configs.stream().collect(Collectors.toMap(
                config -> config.getIssuer().toExternalForm(),
                OidcTokenValidator::new));
    }
}
