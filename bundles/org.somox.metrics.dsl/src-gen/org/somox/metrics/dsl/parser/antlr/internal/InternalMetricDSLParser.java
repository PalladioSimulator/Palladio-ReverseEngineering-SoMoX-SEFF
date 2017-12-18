package org.somox.metrics.dsl.parser.antlr.internal;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.somox.metrics.dsl.services.MetricDSLGrammarAccess;

@SuppressWarnings("all")
public class InternalMetricDSLParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING",
            "RULE_DOUBLE", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER",
            "'import'", "';'", "'extern'", "'Metrics'", "'{'", "'}'", "'Metric'", "'('", "','", "')'", "'parameters'",
            "'};'", "'Parameter'", "'Const'", "'='", "'WeigthedSum'", "'Stepwise'", "'['", "']'", "'Ratio'", "'/'",
            "'<'", "'>'", "'.'" };
    public static final int RULE_ID = 6;
    public static final int T__29 = 29;
    public static final int T__28 = 28;
    public static final int T__27 = 27;
    public static final int T__26 = 26;
    public static final int T__25 = 25;
    public static final int T__24 = 24;
    public static final int T__23 = 23;
    public static final int T__22 = 22;
    public static final int RULE_ANY_OTHER = 11;
    public static final int T__21 = 21;
    public static final int T__20 = 20;
    public static final int RULE_SL_COMMENT = 9;
    public static final int EOF = -1;
    public static final int RULE_DOUBLE = 5;
    public static final int RULE_ML_COMMENT = 8;
    public static final int T__30 = 30;
    public static final int T__19 = 19;
    public static final int T__31 = 31;
    public static final int RULE_STRING = 4;
    public static final int T__32 = 32;
    public static final int T__33 = 33;
    public static final int T__16 = 16;
    public static final int T__34 = 34;
    public static final int T__15 = 15;
    public static final int T__35 = 35;
    public static final int T__18 = 18;
    public static final int T__17 = 17;
    public static final int T__12 = 12;
    public static final int T__14 = 14;
    public static final int T__13 = 13;
    public static final int RULE_INT = 7;
    public static final int RULE_WS = 10;

    // delegates
    // delegators

    public InternalMetricDSLParser(final TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public InternalMetricDSLParser(final TokenStream input, final RecognizerSharedState state) {
        super(input, state);

    }

    @Override
    public String[] getTokenNames() {
        return InternalMetricDSLParser.tokenNames;
    }

    @Override
    public String getGrammarFileName() {
        return "../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g";
    }

    private MetricDSLGrammarAccess grammarAccess;

    public InternalMetricDSLParser(final TokenStream input, final MetricDSLGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        this.registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
        return "MetricModel";
    }

    @Override
    protected MetricDSLGrammarAccess getGrammarAccess() {
        return this.grammarAccess;
    }

    // $ANTLR start "entryRuleMetricModel"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:67:1:
    // entryRuleMetricModel returns [EObject current=null] : iv_ruleMetricModel= ruleMetricModel EOF
    // ;
    public final EObject entryRuleMetricModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetricModel = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:68:2:
            // (iv_ruleMetricModel= ruleMetricModel EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:69:2:
            // iv_ruleMetricModel= ruleMetricModel EOF
            {
                this.newCompositeNode(this.grammarAccess.getMetricModelRule());
                this.pushFollow(FOLLOW_ruleMetricModel_in_entryRuleMetricModel75);
                iv_ruleMetricModel = this.ruleMetricModel();

                this.state._fsp--;

                current = iv_ruleMetricModel;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleMetricModel85);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMetricModel"

    // $ANTLR start "ruleMetricModel"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:76:1:
    // ruleMetricModel returns [EObject current=null] : ( (otherlv_0= 'import' ( (lv_importURI_1_0=
    // RULE_STRING ) ) otherlv_2= ';' )* (otherlv_3= 'extern' ( (lv_metrics_4_0= ruleExternalMetric
    // ) ) otherlv_5= ';' )* otherlv_6= 'Metrics' otherlv_7= '{' ( (lv_metrics_8_0=
    // ruleInternalMetric ) )+ otherlv_9= '}' ) ;
    public final EObject ruleMetricModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token lv_importURI_1_0 = null;
        Token otherlv_2 = null;
        Token otherlv_3 = null;
        Token otherlv_5 = null;
        Token otherlv_6 = null;
        Token otherlv_7 = null;
        Token otherlv_9 = null;
        EObject lv_metrics_4_0 = null;

        EObject lv_metrics_8_0 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:79:28:
            // ( ( (otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) ) otherlv_2= ';' )*
            // (otherlv_3= 'extern' ( (lv_metrics_4_0= ruleExternalMetric ) ) otherlv_5= ';' )*
            // otherlv_6= 'Metrics' otherlv_7= '{' ( (lv_metrics_8_0= ruleInternalMetric ) )+
            // otherlv_9= '}' ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:80:1:
            // ( (otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) ) otherlv_2= ';' )*
            // (otherlv_3= 'extern' ( (lv_metrics_4_0= ruleExternalMetric ) ) otherlv_5= ';' )*
            // otherlv_6= 'Metrics' otherlv_7= '{' ( (lv_metrics_8_0= ruleInternalMetric ) )+
            // otherlv_9= '}' )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:80:1:
                // ( (otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) ) otherlv_2= ';' )*
                // (otherlv_3= 'extern' ( (lv_metrics_4_0= ruleExternalMetric ) ) otherlv_5= ';' )*
                // otherlv_6= 'Metrics' otherlv_7= '{' ( (lv_metrics_8_0= ruleInternalMetric ) )+
                // otherlv_9= '}' )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:80:2:
                // (otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) ) otherlv_2= ';' )*
                // (otherlv_3= 'extern' ( (lv_metrics_4_0= ruleExternalMetric ) ) otherlv_5= ';' )*
                // otherlv_6= 'Metrics' otherlv_7= '{' ( (lv_metrics_8_0= ruleInternalMetric ) )+
                // otherlv_9= '}'
                {
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:80:2:
                    // (otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) ) otherlv_2= ';' )*
                    loop1: do {
                        int alt1 = 2;
                        final int LA1_0 = this.input.LA(1);

                        if ((LA1_0 == 12)) {
                            alt1 = 1;
                        }

                        switch (alt1) {
                        case 1:
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:80:4:
                        // otherlv_0= 'import' ( (lv_importURI_1_0= RULE_STRING ) ) otherlv_2= ';'
                        {
                            otherlv_0 = (Token) this.match(this.input, 12, FOLLOW_12_in_ruleMetricModel123);

                            this.newLeafNode(otherlv_0,
                                    this.grammarAccess.getMetricModelAccess().getImportKeyword_0_0());

                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:84:1:
                            // ( (lv_importURI_1_0= RULE_STRING ) )
                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:85:1:
                            // (lv_importURI_1_0= RULE_STRING )
                            {
                                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:85:1:
                                // (lv_importURI_1_0= RULE_STRING )
                                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:86:3:
                                // lv_importURI_1_0= RULE_STRING
                                {
                                    lv_importURI_1_0 = (Token) this.match(this.input, RULE_STRING,
                                            FOLLOW_RULE_STRING_in_ruleMetricModel140);

                                    this.newLeafNode(lv_importURI_1_0, this.grammarAccess.getMetricModelAccess()
                                            .getImportURISTRINGTerminalRuleCall_0_1_0());

                                    if (current == null) {
                                        current = this.createModelElement(this.grammarAccess.getMetricModelRule());
                                    }
                                    this.addWithLastConsumed(current, "importURI", lv_importURI_1_0, "STRING");

                                }

                            }

                            otherlv_2 = (Token) this.match(this.input, 13, FOLLOW_13_in_ruleMetricModel157);

                            this.newLeafNode(otherlv_2,
                                    this.grammarAccess.getMetricModelAccess().getSemicolonKeyword_0_2());

                        }
                            break;

                        default:
                            break loop1;
                        }
                    } while (true);

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:106:3:
                    // (otherlv_3= 'extern' ( (lv_metrics_4_0= ruleExternalMetric ) ) otherlv_5= ';'
                    // )*
                    loop2: do {
                        int alt2 = 2;
                        final int LA2_0 = this.input.LA(1);

                        if ((LA2_0 == 14)) {
                            alt2 = 1;
                        }

                        switch (alt2) {
                        case 1:
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:106:5:
                        // otherlv_3= 'extern' ( (lv_metrics_4_0= ruleExternalMetric ) ) otherlv_5=
                        // ';'
                        {
                            otherlv_3 = (Token) this.match(this.input, 14, FOLLOW_14_in_ruleMetricModel172);

                            this.newLeafNode(otherlv_3,
                                    this.grammarAccess.getMetricModelAccess().getExternKeyword_1_0());

                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:110:1:
                            // ( (lv_metrics_4_0= ruleExternalMetric ) )
                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:111:1:
                            // (lv_metrics_4_0= ruleExternalMetric )
                            {
                                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:111:1:
                                // (lv_metrics_4_0= ruleExternalMetric )
                                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:112:3:
                                // lv_metrics_4_0= ruleExternalMetric
                                {

                                    this.newCompositeNode(this.grammarAccess.getMetricModelAccess()
                                            .getMetricsExternalMetricParserRuleCall_1_1_0());

                                    this.pushFollow(FOLLOW_ruleExternalMetric_in_ruleMetricModel193);
                                    lv_metrics_4_0 = this.ruleExternalMetric();

                                    this.state._fsp--;

                                    if (current == null) {
                                        current = this
                                                .createModelElementForParent(this.grammarAccess.getMetricModelRule());
                                    }
                                    this.add(current, "metrics", lv_metrics_4_0, "ExternalMetric");
                                    this.afterParserOrEnumRuleCall();

                                }

                            }

                            otherlv_5 = (Token) this.match(this.input, 13, FOLLOW_13_in_ruleMetricModel205);

                            this.newLeafNode(otherlv_5,
                                    this.grammarAccess.getMetricModelAccess().getSemicolonKeyword_1_2());

                        }
                            break;

                        default:
                            break loop2;
                        }
                    } while (true);

                    otherlv_6 = (Token) this.match(this.input, 15, FOLLOW_15_in_ruleMetricModel219);

                    this.newLeafNode(otherlv_6, this.grammarAccess.getMetricModelAccess().getMetricsKeyword_2());

                    otherlv_7 = (Token) this.match(this.input, 16, FOLLOW_16_in_ruleMetricModel231);

                    this.newLeafNode(otherlv_7,
                            this.grammarAccess.getMetricModelAccess().getLeftCurlyBracketKeyword_3());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:140:1:
                    // ( (lv_metrics_8_0= ruleInternalMetric ) )+
                    int cnt3 = 0;
                    loop3: do {
                        int alt3 = 2;
                        final int LA3_0 = this.input.LA(1);

                        if ((LA3_0 == 18)) {
                            alt3 = 1;
                        }

                        switch (alt3) {
                        case 1:
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:141:1:
                        // (lv_metrics_8_0= ruleInternalMetric )
                        {
                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:141:1:
                            // (lv_metrics_8_0= ruleInternalMetric )
                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:142:3:
                            // lv_metrics_8_0= ruleInternalMetric
                            {

                                this.newCompositeNode(this.grammarAccess.getMetricModelAccess()
                                        .getMetricsInternalMetricParserRuleCall_4_0());

                                this.pushFollow(FOLLOW_ruleInternalMetric_in_ruleMetricModel252);
                                lv_metrics_8_0 = this.ruleInternalMetric();

                                this.state._fsp--;

                                if (current == null) {
                                    current = this.createModelElementForParent(this.grammarAccess.getMetricModelRule());
                                }
                                this.add(current, "metrics", lv_metrics_8_0, "InternalMetric");
                                this.afterParserOrEnumRuleCall();

                            }

                        }
                            break;

                        default:
                            if (cnt3 >= 1) {
                                break loop3;
                            }
                            final EarlyExitException eee = new EarlyExitException(3, this.input);
                            throw eee;
                        }
                        cnt3++;
                    } while (true);

                    otherlv_9 = (Token) this.match(this.input, 17, FOLLOW_17_in_ruleMetricModel265);

                    this.newLeafNode(otherlv_9,
                            this.grammarAccess.getMetricModelAccess().getRightCurlyBracketKeyword_5());

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleMetricModel"

    // $ANTLR start "entryRuleExternalMetric"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:172:1:
    // entryRuleExternalMetric returns [EObject current=null] : iv_ruleExternalMetric=
    // ruleExternalMetric EOF ;
    public final EObject entryRuleExternalMetric() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExternalMetric = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:173:2:
            // (iv_ruleExternalMetric= ruleExternalMetric EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:174:2:
            // iv_ruleExternalMetric= ruleExternalMetric EOF
            {
                this.newCompositeNode(this.grammarAccess.getExternalMetricRule());
                this.pushFollow(FOLLOW_ruleExternalMetric_in_entryRuleExternalMetric303);
                iv_ruleExternalMetric = this.ruleExternalMetric();

                this.state._fsp--;

                current = iv_ruleExternalMetric;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleExternalMetric313);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExternalMetric"

    // $ANTLR start "ruleExternalMetric"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:181:1:
    // ruleExternalMetric returns [EObject current=null] : ( (lv_name_0_0= ruleMYID ) ) ;
    public final EObject ruleExternalMetric() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:184:28:
            // ( ( (lv_name_0_0= ruleMYID ) ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:185:1:
            // ( (lv_name_0_0= ruleMYID ) )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:185:1:
                // ( (lv_name_0_0= ruleMYID ) )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:186:1:
                // (lv_name_0_0= ruleMYID )
                {
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:186:1:
                    // (lv_name_0_0= ruleMYID )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:187:3:
                    // lv_name_0_0= ruleMYID
                    {

                        this.newCompositeNode(
                                this.grammarAccess.getExternalMetricAccess().getNameMYIDParserRuleCall_0());

                        this.pushFollow(FOLLOW_ruleMYID_in_ruleExternalMetric358);
                        lv_name_0_0 = this.ruleMYID();

                        this.state._fsp--;

                        if (current == null) {
                            current = this.createModelElementForParent(this.grammarAccess.getExternalMetricRule());
                        }
                        this.set(current, "name", lv_name_0_0, "MYID");
                        this.afterParserOrEnumRuleCall();

                    }

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleExternalMetric"

    // $ANTLR start "entryRuleInternalMetric"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:211:1:
    // entryRuleInternalMetric returns [EObject current=null] : iv_ruleInternalMetric=
    // ruleInternalMetric EOF ;
    public final EObject entryRuleInternalMetric() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInternalMetric = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:212:2:
            // (iv_ruleInternalMetric= ruleInternalMetric EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:213:2:
            // iv_ruleInternalMetric= ruleInternalMetric EOF
            {
                this.newCompositeNode(this.grammarAccess.getInternalMetricRule());
                this.pushFollow(FOLLOW_ruleInternalMetric_in_entryRuleInternalMetric393);
                iv_ruleInternalMetric = this.ruleInternalMetric();

                this.state._fsp--;

                current = iv_ruleInternalMetric;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleInternalMetric403);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInternalMetric"

    // $ANTLR start "ruleInternalMetric"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:220:1:
    // ruleInternalMetric returns [EObject current=null] : (otherlv_0= 'Metric' ( (lv_name_1_0=
    // ruleMYID ) ) otherlv_2= '(' ( (lv_shortName_3_0= RULE_STRING ) ) otherlv_4= ',' (
    // (lv_description_5_0= RULE_STRING ) ) otherlv_6= ')' otherlv_7= '{' (otherlv_8= 'parameters'
    // otherlv_9= '{' ( (lv_parameter_10_0= ruleNumber ) )* otherlv_11= '}' )? (
    // (lv_definition_12_0= ruleMetricDefinition ) ) otherlv_13= '};' ) ;
    public final EObject ruleInternalMetric() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_2 = null;
        Token lv_shortName_3_0 = null;
        Token otherlv_4 = null;
        Token lv_description_5_0 = null;
        Token otherlv_6 = null;
        Token otherlv_7 = null;
        Token otherlv_8 = null;
        Token otherlv_9 = null;
        Token otherlv_11 = null;
        Token otherlv_13 = null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_parameter_10_0 = null;

        EObject lv_definition_12_0 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:223:28:
            // ( (otherlv_0= 'Metric' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '(' (
            // (lv_shortName_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_description_5_0= RULE_STRING
            // ) ) otherlv_6= ')' otherlv_7= '{' (otherlv_8= 'parameters' otherlv_9= '{' (
            // (lv_parameter_10_0= ruleNumber ) )* otherlv_11= '}' )? ( (lv_definition_12_0=
            // ruleMetricDefinition ) ) otherlv_13= '};' ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:224:1:
            // (otherlv_0= 'Metric' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '(' ( (lv_shortName_3_0=
            // RULE_STRING ) ) otherlv_4= ',' ( (lv_description_5_0= RULE_STRING ) ) otherlv_6= ')'
            // otherlv_7= '{' (otherlv_8= 'parameters' otherlv_9= '{' ( (lv_parameter_10_0=
            // ruleNumber ) )* otherlv_11= '}' )? ( (lv_definition_12_0= ruleMetricDefinition ) )
            // otherlv_13= '};' )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:224:1:
                // (otherlv_0= 'Metric' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '(' (
                // (lv_shortName_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_description_5_0=
                // RULE_STRING ) ) otherlv_6= ')' otherlv_7= '{' (otherlv_8= 'parameters' otherlv_9=
                // '{' ( (lv_parameter_10_0= ruleNumber ) )* otherlv_11= '}' )? (
                // (lv_definition_12_0= ruleMetricDefinition ) ) otherlv_13= '};' )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:224:3:
                // otherlv_0= 'Metric' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '(' (
                // (lv_shortName_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_description_5_0=
                // RULE_STRING ) ) otherlv_6= ')' otherlv_7= '{' (otherlv_8= 'parameters' otherlv_9=
                // '{' ( (lv_parameter_10_0= ruleNumber ) )* otherlv_11= '}' )? (
                // (lv_definition_12_0= ruleMetricDefinition ) ) otherlv_13= '};'
                {
                    otherlv_0 = (Token) this.match(this.input, 18, FOLLOW_18_in_ruleInternalMetric440);

                    this.newLeafNode(otherlv_0, this.grammarAccess.getInternalMetricAccess().getMetricKeyword_0());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:228:1:
                    // ( (lv_name_1_0= ruleMYID ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:229:1:
                    // (lv_name_1_0= ruleMYID )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:229:1:
                        // (lv_name_1_0= ruleMYID )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:230:3:
                        // lv_name_1_0= ruleMYID
                        {

                            this.newCompositeNode(
                                    this.grammarAccess.getInternalMetricAccess().getNameMYIDParserRuleCall_1_0());

                            this.pushFollow(FOLLOW_ruleMYID_in_ruleInternalMetric461);
                            lv_name_1_0 = this.ruleMYID();

                            this.state._fsp--;

                            if (current == null) {
                                current = this.createModelElementForParent(this.grammarAccess.getInternalMetricRule());
                            }
                            this.set(current, "name", lv_name_1_0, "MYID");
                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_2 = (Token) this.match(this.input, 19, FOLLOW_19_in_ruleInternalMetric473);

                    this.newLeafNode(otherlv_2,
                            this.grammarAccess.getInternalMetricAccess().getLeftParenthesisKeyword_2());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:250:1:
                    // ( (lv_shortName_3_0= RULE_STRING ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:251:1:
                    // (lv_shortName_3_0= RULE_STRING )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:251:1:
                        // (lv_shortName_3_0= RULE_STRING )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:252:3:
                        // lv_shortName_3_0= RULE_STRING
                        {
                            lv_shortName_3_0 = (Token) this.match(this.input, RULE_STRING,
                                    FOLLOW_RULE_STRING_in_ruleInternalMetric490);

                            this.newLeafNode(lv_shortName_3_0, this.grammarAccess.getInternalMetricAccess()
                                    .getShortNameSTRINGTerminalRuleCall_3_0());

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getInternalMetricRule());
                            }
                            this.setWithLastConsumed(current, "shortName", lv_shortName_3_0, "STRING");

                        }

                    }

                    otherlv_4 = (Token) this.match(this.input, 20, FOLLOW_20_in_ruleInternalMetric507);

                    this.newLeafNode(otherlv_4, this.grammarAccess.getInternalMetricAccess().getCommaKeyword_4());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:272:1:
                    // ( (lv_description_5_0= RULE_STRING ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:273:1:
                    // (lv_description_5_0= RULE_STRING )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:273:1:
                        // (lv_description_5_0= RULE_STRING )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:274:3:
                        // lv_description_5_0= RULE_STRING
                        {
                            lv_description_5_0 = (Token) this.match(this.input, RULE_STRING,
                                    FOLLOW_RULE_STRING_in_ruleInternalMetric524);

                            this.newLeafNode(lv_description_5_0, this.grammarAccess.getInternalMetricAccess()
                                    .getDescriptionSTRINGTerminalRuleCall_5_0());

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getInternalMetricRule());
                            }
                            this.setWithLastConsumed(current, "description", lv_description_5_0, "STRING");

                        }

                    }

                    otherlv_6 = (Token) this.match(this.input, 21, FOLLOW_21_in_ruleInternalMetric541);

                    this.newLeafNode(otherlv_6,
                            this.grammarAccess.getInternalMetricAccess().getRightParenthesisKeyword_6());

                    otherlv_7 = (Token) this.match(this.input, 16, FOLLOW_16_in_ruleInternalMetric553);

                    this.newLeafNode(otherlv_7,
                            this.grammarAccess.getInternalMetricAccess().getLeftCurlyBracketKeyword_7());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:298:1:
                    // (otherlv_8= 'parameters' otherlv_9= '{' ( (lv_parameter_10_0= ruleNumber ) )*
                    // otherlv_11= '}' )?
                    int alt5 = 2;
                    final int LA5_0 = this.input.LA(1);

                    if ((LA5_0 == 22)) {
                        alt5 = 1;
                    }
                    switch (alt5) {
                    case 1:
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:298:3:
                    // otherlv_8= 'parameters' otherlv_9= '{' ( (lv_parameter_10_0= ruleNumber ) )*
                    // otherlv_11= '}'
                    {
                        otherlv_8 = (Token) this.match(this.input, 22, FOLLOW_22_in_ruleInternalMetric566);

                        this.newLeafNode(otherlv_8,
                                this.grammarAccess.getInternalMetricAccess().getParametersKeyword_8_0());

                        otherlv_9 = (Token) this.match(this.input, 16, FOLLOW_16_in_ruleInternalMetric578);

                        this.newLeafNode(otherlv_9,
                                this.grammarAccess.getInternalMetricAccess().getLeftCurlyBracketKeyword_8_1());

                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:306:1:
                        // ( (lv_parameter_10_0= ruleNumber ) )*
                        loop4: do {
                            int alt4 = 2;
                            final int LA4_0 = this.input.LA(1);

                            if (((LA4_0 >= 24 && LA4_0 <= 25))) {
                                alt4 = 1;
                            }

                            switch (alt4) {
                            case 1:
                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:307:1:
                            // (lv_parameter_10_0= ruleNumber )
                            {
                                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:307:1:
                                // (lv_parameter_10_0= ruleNumber )
                                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:308:3:
                                // lv_parameter_10_0= ruleNumber
                                {

                                    this.newCompositeNode(this.grammarAccess.getInternalMetricAccess()
                                            .getParameterNumberParserRuleCall_8_2_0());

                                    this.pushFollow(FOLLOW_ruleNumber_in_ruleInternalMetric599);
                                    lv_parameter_10_0 = this.ruleNumber();

                                    this.state._fsp--;

                                    if (current == null) {
                                        current = this.createModelElementForParent(
                                                this.grammarAccess.getInternalMetricRule());
                                    }
                                    this.add(current, "parameter", lv_parameter_10_0, "Number");
                                    this.afterParserOrEnumRuleCall();

                                }

                            }
                                break;

                            default:
                                break loop4;
                            }
                        } while (true);

                        otherlv_11 = (Token) this.match(this.input, 17, FOLLOW_17_in_ruleInternalMetric612);

                        this.newLeafNode(otherlv_11,
                                this.grammarAccess.getInternalMetricAccess().getRightCurlyBracketKeyword_8_3());

                    }
                        break;

                    }

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:328:3:
                    // ( (lv_definition_12_0= ruleMetricDefinition ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:329:1:
                    // (lv_definition_12_0= ruleMetricDefinition )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:329:1:
                        // (lv_definition_12_0= ruleMetricDefinition )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:330:3:
                        // lv_definition_12_0= ruleMetricDefinition
                        {

                            this.newCompositeNode(this.grammarAccess.getInternalMetricAccess()
                                    .getDefinitionMetricDefinitionParserRuleCall_9_0());

                            this.pushFollow(FOLLOW_ruleMetricDefinition_in_ruleInternalMetric635);
                            lv_definition_12_0 = this.ruleMetricDefinition();

                            this.state._fsp--;

                            if (current == null) {
                                current = this.createModelElementForParent(this.grammarAccess.getInternalMetricRule());
                            }
                            this.set(current, "definition", lv_definition_12_0, "MetricDefinition");
                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_13 = (Token) this.match(this.input, 23, FOLLOW_23_in_ruleInternalMetric647);

                    this.newLeafNode(otherlv_13,
                            this.grammarAccess.getInternalMetricAccess().getRightCurlyBracketSemicolonKeyword_10());

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleInternalMetric"

    // $ANTLR start "entryRuleNumber"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:358:1:
    // entryRuleNumber returns [EObject current=null] : iv_ruleNumber= ruleNumber EOF ;
    public final EObject entryRuleNumber() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumber = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:359:2:
            // (iv_ruleNumber= ruleNumber EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:360:2:
            // iv_ruleNumber= ruleNumber EOF
            {
                this.newCompositeNode(this.grammarAccess.getNumberRule());
                this.pushFollow(FOLLOW_ruleNumber_in_entryRuleNumber683);
                iv_ruleNumber = this.ruleNumber();

                this.state._fsp--;

                current = iv_ruleNumber;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleNumber693);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNumber"

    // $ANTLR start "ruleNumber"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:367:1:
    // ruleNumber returns [EObject current=null] : (this_Parameter_0= ruleParameter |
    // this_Constant_1= ruleConstant ) ;
    public final EObject ruleNumber() throws RecognitionException {
        EObject current = null;

        EObject this_Parameter_0 = null;

        EObject this_Constant_1 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:370:28:
            // ( (this_Parameter_0= ruleParameter | this_Constant_1= ruleConstant ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:371:1:
            // (this_Parameter_0= ruleParameter | this_Constant_1= ruleConstant )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:371:1:
                // (this_Parameter_0= ruleParameter | this_Constant_1= ruleConstant )
                int alt6 = 2;
                final int LA6_0 = this.input.LA(1);

                if ((LA6_0 == 24)) {
                    alt6 = 1;
                } else if ((LA6_0 == 25)) {
                    alt6 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException("", 6, 0, this.input);

                    throw nvae;
                }
                switch (alt6) {
                case 1:
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:372:5:
                // this_Parameter_0= ruleParameter
                {

                    this.newCompositeNode(this.grammarAccess.getNumberAccess().getParameterParserRuleCall_0());

                    this.pushFollow(FOLLOW_ruleParameter_in_ruleNumber740);
                    this_Parameter_0 = this.ruleParameter();

                    this.state._fsp--;

                    current = this_Parameter_0;
                    this.afterParserOrEnumRuleCall();

                }
                    break;
                case 2:
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:382:5:
                // this_Constant_1= ruleConstant
                {

                    this.newCompositeNode(this.grammarAccess.getNumberAccess().getConstantParserRuleCall_1());

                    this.pushFollow(FOLLOW_ruleConstant_in_ruleNumber767);
                    this_Constant_1 = this.ruleConstant();

                    this.state._fsp--;

                    current = this_Constant_1;
                    this.afterParserOrEnumRuleCall();

                }
                    break;

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleNumber"

    // $ANTLR start "entryRuleParameter"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:398:1:
    // entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:399:2:
            // (iv_ruleParameter= ruleParameter EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:400:2:
            // iv_ruleParameter= ruleParameter EOF
            {
                this.newCompositeNode(this.grammarAccess.getParameterRule());
                this.pushFollow(FOLLOW_ruleParameter_in_entryRuleParameter802);
                iv_ruleParameter = this.ruleParameter();

                this.state._fsp--;

                current = iv_ruleParameter;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleParameter812);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"

    // $ANTLR start "ruleParameter"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:407:1:
    // ruleParameter returns [EObject current=null] : (otherlv_0= 'Parameter' ( (lv_name_1_0=
    // ruleMYID ) ) otherlv_2= '(' ( (lv_shortname_3_0= RULE_STRING ) ) otherlv_4= ',' (
    // (lv_description_5_0= RULE_STRING ) ) otherlv_6= ',' ( (lv_defaultValue_7_0= RULE_DOUBLE ) )
    // otherlv_8= ')' otherlv_9= ';' ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_2 = null;
        Token lv_shortname_3_0 = null;
        Token otherlv_4 = null;
        Token lv_description_5_0 = null;
        Token otherlv_6 = null;
        Token lv_defaultValue_7_0 = null;
        Token otherlv_8 = null;
        Token otherlv_9 = null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:410:28:
            // ( (otherlv_0= 'Parameter' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '(' (
            // (lv_shortname_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_description_5_0= RULE_STRING
            // ) ) otherlv_6= ',' ( (lv_defaultValue_7_0= RULE_DOUBLE ) ) otherlv_8= ')' otherlv_9=
            // ';' ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:411:1:
            // (otherlv_0= 'Parameter' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '(' (
            // (lv_shortname_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_description_5_0= RULE_STRING
            // ) ) otherlv_6= ',' ( (lv_defaultValue_7_0= RULE_DOUBLE ) ) otherlv_8= ')' otherlv_9=
            // ';' )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:411:1:
                // (otherlv_0= 'Parameter' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '(' (
                // (lv_shortname_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_description_5_0=
                // RULE_STRING ) ) otherlv_6= ',' ( (lv_defaultValue_7_0= RULE_DOUBLE ) ) otherlv_8=
                // ')' otherlv_9= ';' )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:411:3:
                // otherlv_0= 'Parameter' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '(' (
                // (lv_shortname_3_0= RULE_STRING ) ) otherlv_4= ',' ( (lv_description_5_0=
                // RULE_STRING ) ) otherlv_6= ',' ( (lv_defaultValue_7_0= RULE_DOUBLE ) ) otherlv_8=
                // ')' otherlv_9= ';'
                {
                    otherlv_0 = (Token) this.match(this.input, 24, FOLLOW_24_in_ruleParameter849);

                    this.newLeafNode(otherlv_0, this.grammarAccess.getParameterAccess().getParameterKeyword_0());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:415:1:
                    // ( (lv_name_1_0= ruleMYID ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:416:1:
                    // (lv_name_1_0= ruleMYID )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:416:1:
                        // (lv_name_1_0= ruleMYID )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:417:3:
                        // lv_name_1_0= ruleMYID
                        {

                            this.newCompositeNode(
                                    this.grammarAccess.getParameterAccess().getNameMYIDParserRuleCall_1_0());

                            this.pushFollow(FOLLOW_ruleMYID_in_ruleParameter870);
                            lv_name_1_0 = this.ruleMYID();

                            this.state._fsp--;

                            if (current == null) {
                                current = this.createModelElementForParent(this.grammarAccess.getParameterRule());
                            }
                            this.set(current, "name", lv_name_1_0, "MYID");
                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_2 = (Token) this.match(this.input, 19, FOLLOW_19_in_ruleParameter882);

                    this.newLeafNode(otherlv_2, this.grammarAccess.getParameterAccess().getLeftParenthesisKeyword_2());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:437:1:
                    // ( (lv_shortname_3_0= RULE_STRING ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:438:1:
                    // (lv_shortname_3_0= RULE_STRING )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:438:1:
                        // (lv_shortname_3_0= RULE_STRING )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:439:3:
                        // lv_shortname_3_0= RULE_STRING
                        {
                            lv_shortname_3_0 = (Token) this.match(this.input, RULE_STRING,
                                    FOLLOW_RULE_STRING_in_ruleParameter899);

                            this.newLeafNode(lv_shortname_3_0,
                                    this.grammarAccess.getParameterAccess().getShortnameSTRINGTerminalRuleCall_3_0());

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getParameterRule());
                            }
                            this.setWithLastConsumed(current, "shortname", lv_shortname_3_0, "STRING");

                        }

                    }

                    otherlv_4 = (Token) this.match(this.input, 20, FOLLOW_20_in_ruleParameter916);

                    this.newLeafNode(otherlv_4, this.grammarAccess.getParameterAccess().getCommaKeyword_4());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:459:1:
                    // ( (lv_description_5_0= RULE_STRING ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:460:1:
                    // (lv_description_5_0= RULE_STRING )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:460:1:
                        // (lv_description_5_0= RULE_STRING )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:461:3:
                        // lv_description_5_0= RULE_STRING
                        {
                            lv_description_5_0 = (Token) this.match(this.input, RULE_STRING,
                                    FOLLOW_RULE_STRING_in_ruleParameter933);

                            this.newLeafNode(lv_description_5_0,
                                    this.grammarAccess.getParameterAccess().getDescriptionSTRINGTerminalRuleCall_5_0());

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getParameterRule());
                            }
                            this.setWithLastConsumed(current, "description", lv_description_5_0, "STRING");

                        }

                    }

                    otherlv_6 = (Token) this.match(this.input, 20, FOLLOW_20_in_ruleParameter950);

                    this.newLeafNode(otherlv_6, this.grammarAccess.getParameterAccess().getCommaKeyword_6());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:481:1:
                    // ( (lv_defaultValue_7_0= RULE_DOUBLE ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:482:1:
                    // (lv_defaultValue_7_0= RULE_DOUBLE )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:482:1:
                        // (lv_defaultValue_7_0= RULE_DOUBLE )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:483:3:
                        // lv_defaultValue_7_0= RULE_DOUBLE
                        {
                            lv_defaultValue_7_0 = (Token) this.match(this.input, RULE_DOUBLE,
                                    FOLLOW_RULE_DOUBLE_in_ruleParameter967);

                            this.newLeafNode(lv_defaultValue_7_0, this.grammarAccess.getParameterAccess()
                                    .getDefaultValueDOUBLETerminalRuleCall_7_0());

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getParameterRule());
                            }
                            this.setWithLastConsumed(current, "defaultValue", lv_defaultValue_7_0, "DOUBLE");

                        }

                    }

                    otherlv_8 = (Token) this.match(this.input, 21, FOLLOW_21_in_ruleParameter984);

                    this.newLeafNode(otherlv_8, this.grammarAccess.getParameterAccess().getRightParenthesisKeyword_8());

                    otherlv_9 = (Token) this.match(this.input, 13, FOLLOW_13_in_ruleParameter996);

                    this.newLeafNode(otherlv_9, this.grammarAccess.getParameterAccess().getSemicolonKeyword_9());

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"

    // $ANTLR start "entryRuleConstant"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:515:1:
    // entryRuleConstant returns [EObject current=null] : iv_ruleConstant= ruleConstant EOF ;
    public final EObject entryRuleConstant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstant = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:516:2:
            // (iv_ruleConstant= ruleConstant EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:517:2:
            // iv_ruleConstant= ruleConstant EOF
            {
                this.newCompositeNode(this.grammarAccess.getConstantRule());
                this.pushFollow(FOLLOW_ruleConstant_in_entryRuleConstant1032);
                iv_ruleConstant = this.ruleConstant();

                this.state._fsp--;

                current = iv_ruleConstant;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleConstant1042);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstant"

    // $ANTLR start "ruleConstant"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:524:1:
    // ruleConstant returns [EObject current=null] : (otherlv_0= 'Const' ( (lv_name_1_0= ruleMYID )
    // ) otherlv_2= '=' ( (lv_value_3_0= RULE_DOUBLE ) ) otherlv_4= ';' ) ;
    public final EObject ruleConstant() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_2 = null;
        Token lv_value_3_0 = null;
        Token otherlv_4 = null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:527:28:
            // ( (otherlv_0= 'Const' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '=' ( (lv_value_3_0=
            // RULE_DOUBLE ) ) otherlv_4= ';' ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:528:1:
            // (otherlv_0= 'Const' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '=' ( (lv_value_3_0=
            // RULE_DOUBLE ) ) otherlv_4= ';' )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:528:1:
                // (otherlv_0= 'Const' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '=' ( (lv_value_3_0=
                // RULE_DOUBLE ) ) otherlv_4= ';' )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:528:3:
                // otherlv_0= 'Const' ( (lv_name_1_0= ruleMYID ) ) otherlv_2= '=' ( (lv_value_3_0=
                // RULE_DOUBLE ) ) otherlv_4= ';'
                {
                    otherlv_0 = (Token) this.match(this.input, 25, FOLLOW_25_in_ruleConstant1079);

                    this.newLeafNode(otherlv_0, this.grammarAccess.getConstantAccess().getConstKeyword_0());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:532:1:
                    // ( (lv_name_1_0= ruleMYID ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:533:1:
                    // (lv_name_1_0= ruleMYID )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:533:1:
                        // (lv_name_1_0= ruleMYID )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:534:3:
                        // lv_name_1_0= ruleMYID
                        {

                            this.newCompositeNode(
                                    this.grammarAccess.getConstantAccess().getNameMYIDParserRuleCall_1_0());

                            this.pushFollow(FOLLOW_ruleMYID_in_ruleConstant1100);
                            lv_name_1_0 = this.ruleMYID();

                            this.state._fsp--;

                            if (current == null) {
                                current = this.createModelElementForParent(this.grammarAccess.getConstantRule());
                            }
                            this.set(current, "name", lv_name_1_0, "MYID");
                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_2 = (Token) this.match(this.input, 26, FOLLOW_26_in_ruleConstant1112);

                    this.newLeafNode(otherlv_2, this.grammarAccess.getConstantAccess().getEqualsSignKeyword_2());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:554:1:
                    // ( (lv_value_3_0= RULE_DOUBLE ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:555:1:
                    // (lv_value_3_0= RULE_DOUBLE )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:555:1:
                        // (lv_value_3_0= RULE_DOUBLE )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:556:3:
                        // lv_value_3_0= RULE_DOUBLE
                        {
                            lv_value_3_0 = (Token) this.match(this.input, RULE_DOUBLE,
                                    FOLLOW_RULE_DOUBLE_in_ruleConstant1129);

                            this.newLeafNode(lv_value_3_0,
                                    this.grammarAccess.getConstantAccess().getValueDOUBLETerminalRuleCall_3_0());

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getConstantRule());
                            }
                            this.setWithLastConsumed(current, "value", lv_value_3_0, "DOUBLE");

                        }

                    }

                    otherlv_4 = (Token) this.match(this.input, 13, FOLLOW_13_in_ruleConstant1146);

                    this.newLeafNode(otherlv_4, this.grammarAccess.getConstantAccess().getSemicolonKeyword_4());

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstant"

    // $ANTLR start "entryRuleMetricDefinition"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:584:1:
    // entryRuleMetricDefinition returns [EObject current=null] : iv_ruleMetricDefinition=
    // ruleMetricDefinition EOF ;
    public final EObject entryRuleMetricDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetricDefinition = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:585:2:
            // (iv_ruleMetricDefinition= ruleMetricDefinition EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:586:2:
            // iv_ruleMetricDefinition= ruleMetricDefinition EOF
            {
                this.newCompositeNode(this.grammarAccess.getMetricDefinitionRule());
                this.pushFollow(FOLLOW_ruleMetricDefinition_in_entryRuleMetricDefinition1182);
                iv_ruleMetricDefinition = this.ruleMetricDefinition();

                this.state._fsp--;

                current = iv_ruleMetricDefinition;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleMetricDefinition1192);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMetricDefinition"

    // $ANTLR start "ruleMetricDefinition"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:593:1:
    // ruleMetricDefinition returns [EObject current=null] : (this_WeightedMetric_0=
    // ruleWeightedMetric | this_StepwiseMetric_1= ruleStepwiseMetric | this_RatioMetric_2=
    // ruleRatioMetric ) ;
    public final EObject ruleMetricDefinition() throws RecognitionException {
        EObject current = null;

        EObject this_WeightedMetric_0 = null;

        EObject this_StepwiseMetric_1 = null;

        EObject this_RatioMetric_2 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:596:28:
            // ( (this_WeightedMetric_0= ruleWeightedMetric | this_StepwiseMetric_1=
            // ruleStepwiseMetric | this_RatioMetric_2= ruleRatioMetric ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:597:1:
            // (this_WeightedMetric_0= ruleWeightedMetric | this_StepwiseMetric_1=
            // ruleStepwiseMetric | this_RatioMetric_2= ruleRatioMetric )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:597:1:
                // (this_WeightedMetric_0= ruleWeightedMetric | this_StepwiseMetric_1=
                // ruleStepwiseMetric | this_RatioMetric_2= ruleRatioMetric )
                int alt7 = 3;
                switch (this.input.LA(1)) {
                case 27: {
                    alt7 = 1;
                }
                    break;
                case 28: {
                    alt7 = 2;
                }
                    break;
                case 31: {
                    alt7 = 3;
                }
                    break;
                default:
                    final NoViableAltException nvae = new NoViableAltException("", 7, 0, this.input);

                    throw nvae;
                }

                switch (alt7) {
                case 1:
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:598:5:
                // this_WeightedMetric_0= ruleWeightedMetric
                {

                    this.newCompositeNode(
                            this.grammarAccess.getMetricDefinitionAccess().getWeightedMetricParserRuleCall_0());

                    this.pushFollow(FOLLOW_ruleWeightedMetric_in_ruleMetricDefinition1239);
                    this_WeightedMetric_0 = this.ruleWeightedMetric();

                    this.state._fsp--;

                    current = this_WeightedMetric_0;
                    this.afterParserOrEnumRuleCall();

                }
                    break;
                case 2:
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:608:5:
                // this_StepwiseMetric_1= ruleStepwiseMetric
                {

                    this.newCompositeNode(
                            this.grammarAccess.getMetricDefinitionAccess().getStepwiseMetricParserRuleCall_1());

                    this.pushFollow(FOLLOW_ruleStepwiseMetric_in_ruleMetricDefinition1266);
                    this_StepwiseMetric_1 = this.ruleStepwiseMetric();

                    this.state._fsp--;

                    current = this_StepwiseMetric_1;
                    this.afterParserOrEnumRuleCall();

                }
                    break;
                case 3:
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:618:5:
                // this_RatioMetric_2= ruleRatioMetric
                {

                    this.newCompositeNode(
                            this.grammarAccess.getMetricDefinitionAccess().getRatioMetricParserRuleCall_2());

                    this.pushFollow(FOLLOW_ruleRatioMetric_in_ruleMetricDefinition1293);
                    this_RatioMetric_2 = this.ruleRatioMetric();

                    this.state._fsp--;

                    current = this_RatioMetric_2;
                    this.afterParserOrEnumRuleCall();

                }
                    break;

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleMetricDefinition"

    // $ANTLR start "entryRuleWeightedMetric"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:634:1:
    // entryRuleWeightedMetric returns [EObject current=null] : iv_ruleWeightedMetric=
    // ruleWeightedMetric EOF ;
    public final EObject entryRuleWeightedMetric() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWeightedMetric = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:635:2:
            // (iv_ruleWeightedMetric= ruleWeightedMetric EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:636:2:
            // iv_ruleWeightedMetric= ruleWeightedMetric EOF
            {
                this.newCompositeNode(this.grammarAccess.getWeightedMetricRule());
                this.pushFollow(FOLLOW_ruleWeightedMetric_in_entryRuleWeightedMetric1328);
                iv_ruleWeightedMetric = this.ruleWeightedMetric();

                this.state._fsp--;

                current = iv_ruleWeightedMetric;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleWeightedMetric1338);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWeightedMetric"

    // $ANTLR start "ruleWeightedMetric"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:643:1:
    // ruleWeightedMetric returns [EObject current=null] : (otherlv_0= 'WeigthedSum' otherlv_1= '{'
    // ( (lv_weights_2_0= ruleMetricAndWeight ) )+ otherlv_3= '}' ) ;
    public final EObject ruleWeightedMetric() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_1 = null;
        Token otherlv_3 = null;
        EObject lv_weights_2_0 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:646:28:
            // ( (otherlv_0= 'WeigthedSum' otherlv_1= '{' ( (lv_weights_2_0= ruleMetricAndWeight )
            // )+ otherlv_3= '}' ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:647:1:
            // (otherlv_0= 'WeigthedSum' otherlv_1= '{' ( (lv_weights_2_0= ruleMetricAndWeight ) )+
            // otherlv_3= '}' )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:647:1:
                // (otherlv_0= 'WeigthedSum' otherlv_1= '{' ( (lv_weights_2_0= ruleMetricAndWeight )
                // )+ otherlv_3= '}' )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:647:3:
                // otherlv_0= 'WeigthedSum' otherlv_1= '{' ( (lv_weights_2_0= ruleMetricAndWeight )
                // )+ otherlv_3= '}'
                {
                    otherlv_0 = (Token) this.match(this.input, 27, FOLLOW_27_in_ruleWeightedMetric1375);

                    this.newLeafNode(otherlv_0, this.grammarAccess.getWeightedMetricAccess().getWeigthedSumKeyword_0());

                    otherlv_1 = (Token) this.match(this.input, 16, FOLLOW_16_in_ruleWeightedMetric1387);

                    this.newLeafNode(otherlv_1,
                            this.grammarAccess.getWeightedMetricAccess().getLeftCurlyBracketKeyword_1());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:655:1:
                    // ( (lv_weights_2_0= ruleMetricAndWeight ) )+
                    int cnt8 = 0;
                    loop8: do {
                        int alt8 = 2;
                        final int LA8_0 = this.input.LA(1);

                        if ((LA8_0 == 33)) {
                            alt8 = 1;
                        }

                        switch (alt8) {
                        case 1:
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:656:1:
                        // (lv_weights_2_0= ruleMetricAndWeight )
                        {
                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:656:1:
                            // (lv_weights_2_0= ruleMetricAndWeight )
                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:657:3:
                            // lv_weights_2_0= ruleMetricAndWeight
                            {

                                this.newCompositeNode(this.grammarAccess.getWeightedMetricAccess()
                                        .getWeightsMetricAndWeightParserRuleCall_2_0());

                                this.pushFollow(FOLLOW_ruleMetricAndWeight_in_ruleWeightedMetric1408);
                                lv_weights_2_0 = this.ruleMetricAndWeight();

                                this.state._fsp--;

                                if (current == null) {
                                    current = this
                                            .createModelElementForParent(this.grammarAccess.getWeightedMetricRule());
                                }
                                this.add(current, "weights", lv_weights_2_0, "MetricAndWeight");
                                this.afterParserOrEnumRuleCall();

                            }

                        }
                            break;

                        default:
                            if (cnt8 >= 1) {
                                break loop8;
                            }
                            final EarlyExitException eee = new EarlyExitException(8, this.input);
                            throw eee;
                        }
                        cnt8++;
                    } while (true);

                    otherlv_3 = (Token) this.match(this.input, 17, FOLLOW_17_in_ruleWeightedMetric1421);

                    this.newLeafNode(otherlv_3,
                            this.grammarAccess.getWeightedMetricAccess().getRightCurlyBracketKeyword_3());

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleWeightedMetric"

    // $ANTLR start "entryRuleStepwiseMetric"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:685:1:
    // entryRuleStepwiseMetric returns [EObject current=null] : iv_ruleStepwiseMetric=
    // ruleStepwiseMetric EOF ;
    public final EObject entryRuleStepwiseMetric() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStepwiseMetric = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:686:2:
            // (iv_ruleStepwiseMetric= ruleStepwiseMetric EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:687:2:
            // iv_ruleStepwiseMetric= ruleStepwiseMetric EOF
            {
                this.newCompositeNode(this.grammarAccess.getStepwiseMetricRule());
                this.pushFollow(FOLLOW_ruleStepwiseMetric_in_entryRuleStepwiseMetric1457);
                iv_ruleStepwiseMetric = this.ruleStepwiseMetric();

                this.state._fsp--;

                current = iv_ruleStepwiseMetric;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleStepwiseMetric1467);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStepwiseMetric"

    // $ANTLR start "ruleStepwiseMetric"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:694:1:
    // ruleStepwiseMetric returns [EObject current=null] : (otherlv_0= 'Stepwise' otherlv_1= '[' ( (
    // ruleMYID ) ) otherlv_3= ']' otherlv_4= '{' ( (lv_steps_5_0= ruleBoundAndWeight ) )*
    // otherlv_6= '}' ) ;
    public final EObject ruleStepwiseMetric() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_1 = null;
        Token otherlv_3 = null;
        Token otherlv_4 = null;
        Token otherlv_6 = null;
        EObject lv_steps_5_0 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:697:28:
            // ( (otherlv_0= 'Stepwise' otherlv_1= '[' ( ( ruleMYID ) ) otherlv_3= ']' otherlv_4=
            // '{' ( (lv_steps_5_0= ruleBoundAndWeight ) )* otherlv_6= '}' ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:698:1:
            // (otherlv_0= 'Stepwise' otherlv_1= '[' ( ( ruleMYID ) ) otherlv_3= ']' otherlv_4= '{'
            // ( (lv_steps_5_0= ruleBoundAndWeight ) )* otherlv_6= '}' )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:698:1:
                // (otherlv_0= 'Stepwise' otherlv_1= '[' ( ( ruleMYID ) ) otherlv_3= ']' otherlv_4=
                // '{' ( (lv_steps_5_0= ruleBoundAndWeight ) )* otherlv_6= '}' )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:698:3:
                // otherlv_0= 'Stepwise' otherlv_1= '[' ( ( ruleMYID ) ) otherlv_3= ']' otherlv_4=
                // '{' ( (lv_steps_5_0= ruleBoundAndWeight ) )* otherlv_6= '}'
                {
                    otherlv_0 = (Token) this.match(this.input, 28, FOLLOW_28_in_ruleStepwiseMetric1504);

                    this.newLeafNode(otherlv_0, this.grammarAccess.getStepwiseMetricAccess().getStepwiseKeyword_0());

                    otherlv_1 = (Token) this.match(this.input, 29, FOLLOW_29_in_ruleStepwiseMetric1516);

                    this.newLeafNode(otherlv_1,
                            this.grammarAccess.getStepwiseMetricAccess().getLeftSquareBracketKeyword_1());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:706:1:
                    // ( ( ruleMYID ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:707:1:
                    // ( ruleMYID )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:707:1:
                        // ( ruleMYID )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:708:3:
                        // ruleMYID
                        {

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getStepwiseMetricRule());
                            }

                            this.newCompositeNode(this.grammarAccess.getStepwiseMetricAccess()
                                    .getInnerMetricMetricCrossReference_2_0());

                            this.pushFollow(FOLLOW_ruleMYID_in_ruleStepwiseMetric1539);
                            this.ruleMYID();

                            this.state._fsp--;

                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_3 = (Token) this.match(this.input, 30, FOLLOW_30_in_ruleStepwiseMetric1551);

                    this.newLeafNode(otherlv_3,
                            this.grammarAccess.getStepwiseMetricAccess().getRightSquareBracketKeyword_3());

                    otherlv_4 = (Token) this.match(this.input, 16, FOLLOW_16_in_ruleStepwiseMetric1563);

                    this.newLeafNode(otherlv_4,
                            this.grammarAccess.getStepwiseMetricAccess().getLeftCurlyBracketKeyword_4());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:729:1:
                    // ( (lv_steps_5_0= ruleBoundAndWeight ) )*
                    loop9: do {
                        int alt9 = 2;
                        final int LA9_0 = this.input.LA(1);

                        if ((LA9_0 == 33)) {
                            alt9 = 1;
                        }

                        switch (alt9) {
                        case 1:
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:730:1:
                        // (lv_steps_5_0= ruleBoundAndWeight )
                        {
                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:730:1:
                            // (lv_steps_5_0= ruleBoundAndWeight )
                            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:731:3:
                            // lv_steps_5_0= ruleBoundAndWeight
                            {

                                this.newCompositeNode(this.grammarAccess.getStepwiseMetricAccess()
                                        .getStepsBoundAndWeightParserRuleCall_5_0());

                                this.pushFollow(FOLLOW_ruleBoundAndWeight_in_ruleStepwiseMetric1584);
                                lv_steps_5_0 = this.ruleBoundAndWeight();

                                this.state._fsp--;

                                if (current == null) {
                                    current = this
                                            .createModelElementForParent(this.grammarAccess.getStepwiseMetricRule());
                                }
                                this.add(current, "steps", lv_steps_5_0, "BoundAndWeight");
                                this.afterParserOrEnumRuleCall();

                            }

                        }
                            break;

                        default:
                            break loop9;
                        }
                    } while (true);

                    otherlv_6 = (Token) this.match(this.input, 17, FOLLOW_17_in_ruleStepwiseMetric1597);

                    this.newLeafNode(otherlv_6,
                            this.grammarAccess.getStepwiseMetricAccess().getRightCurlyBracketKeyword_6());

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleStepwiseMetric"

    // $ANTLR start "entryRuleRatioMetric"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:759:1:
    // entryRuleRatioMetric returns [EObject current=null] : iv_ruleRatioMetric= ruleRatioMetric EOF
    // ;
    public final EObject entryRuleRatioMetric() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRatioMetric = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:760:2:
            // (iv_ruleRatioMetric= ruleRatioMetric EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:761:2:
            // iv_ruleRatioMetric= ruleRatioMetric EOF
            {
                this.newCompositeNode(this.grammarAccess.getRatioMetricRule());
                this.pushFollow(FOLLOW_ruleRatioMetric_in_entryRuleRatioMetric1633);
                iv_ruleRatioMetric = this.ruleRatioMetric();

                this.state._fsp--;

                current = iv_ruleRatioMetric;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleRatioMetric1643);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRatioMetric"

    // $ANTLR start "ruleRatioMetric"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:768:1:
    // ruleRatioMetric returns [EObject current=null] : (otherlv_0= 'Ratio' otherlv_1= '{' ( (
    // ruleMYID ) ) otherlv_3= '/' ( ( ruleMYID ) ) otherlv_5= '}' ) ;
    public final EObject ruleRatioMetric() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_1 = null;
        Token otherlv_3 = null;
        Token otherlv_5 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:771:28:
            // ( (otherlv_0= 'Ratio' otherlv_1= '{' ( ( ruleMYID ) ) otherlv_3= '/' ( ( ruleMYID ) )
            // otherlv_5= '}' ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:772:1:
            // (otherlv_0= 'Ratio' otherlv_1= '{' ( ( ruleMYID ) ) otherlv_3= '/' ( ( ruleMYID ) )
            // otherlv_5= '}' )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:772:1:
                // (otherlv_0= 'Ratio' otherlv_1= '{' ( ( ruleMYID ) ) otherlv_3= '/' ( ( ruleMYID )
                // ) otherlv_5= '}' )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:772:3:
                // otherlv_0= 'Ratio' otherlv_1= '{' ( ( ruleMYID ) ) otherlv_3= '/' ( ( ruleMYID )
                // ) otherlv_5= '}'
                {
                    otherlv_0 = (Token) this.match(this.input, 31, FOLLOW_31_in_ruleRatioMetric1680);

                    this.newLeafNode(otherlv_0, this.grammarAccess.getRatioMetricAccess().getRatioKeyword_0());

                    otherlv_1 = (Token) this.match(this.input, 16, FOLLOW_16_in_ruleRatioMetric1692);

                    this.newLeafNode(otherlv_1,
                            this.grammarAccess.getRatioMetricAccess().getLeftCurlyBracketKeyword_1());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:780:1:
                    // ( ( ruleMYID ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:781:1:
                    // ( ruleMYID )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:781:1:
                        // ( ruleMYID )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:782:3:
                        // ruleMYID
                        {

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getRatioMetricRule());
                            }

                            this.newCompositeNode(this.grammarAccess.getRatioMetricAccess()
                                    .getNominatorMetricMetricCrossReference_2_0());

                            this.pushFollow(FOLLOW_ruleMYID_in_ruleRatioMetric1715);
                            this.ruleMYID();

                            this.state._fsp--;

                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_3 = (Token) this.match(this.input, 32, FOLLOW_32_in_ruleRatioMetric1727);

                    this.newLeafNode(otherlv_3, this.grammarAccess.getRatioMetricAccess().getSolidusKeyword_3());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:799:1:
                    // ( ( ruleMYID ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:800:1:
                    // ( ruleMYID )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:800:1:
                        // ( ruleMYID )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:801:3:
                        // ruleMYID
                        {

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getRatioMetricRule());
                            }

                            this.newCompositeNode(this.grammarAccess.getRatioMetricAccess()
                                    .getDenominatorMetricMetricCrossReference_4_0());

                            this.pushFollow(FOLLOW_ruleMYID_in_ruleRatioMetric1750);
                            this.ruleMYID();

                            this.state._fsp--;

                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_5 = (Token) this.match(this.input, 17, FOLLOW_17_in_ruleRatioMetric1762);

                    this.newLeafNode(otherlv_5,
                            this.grammarAccess.getRatioMetricAccess().getRightCurlyBracketKeyword_5());

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleRatioMetric"

    // $ANTLR start "entryRuleBoundAndWeight"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:826:1:
    // entryRuleBoundAndWeight returns [EObject current=null] : iv_ruleBoundAndWeight=
    // ruleBoundAndWeight EOF ;
    public final EObject entryRuleBoundAndWeight() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBoundAndWeight = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:827:2:
            // (iv_ruleBoundAndWeight= ruleBoundAndWeight EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:828:2:
            // iv_ruleBoundAndWeight= ruleBoundAndWeight EOF
            {
                this.newCompositeNode(this.grammarAccess.getBoundAndWeightRule());
                this.pushFollow(FOLLOW_ruleBoundAndWeight_in_entryRuleBoundAndWeight1798);
                iv_ruleBoundAndWeight = this.ruleBoundAndWeight();

                this.state._fsp--;

                current = iv_ruleBoundAndWeight;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleBoundAndWeight1808);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBoundAndWeight"

    // $ANTLR start "ruleBoundAndWeight"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:835:1:
    // ruleBoundAndWeight returns [EObject current=null] : (otherlv_0= '<' ( ( ruleMYID ) )
    // otherlv_2= ',' ( ( ruleMYID ) ) otherlv_4= '>' ) ;
    public final EObject ruleBoundAndWeight() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_2 = null;
        Token otherlv_4 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:838:28:
            // ( (otherlv_0= '<' ( ( ruleMYID ) ) otherlv_2= ',' ( ( ruleMYID ) ) otherlv_4= '>' ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:839:1:
            // (otherlv_0= '<' ( ( ruleMYID ) ) otherlv_2= ',' ( ( ruleMYID ) ) otherlv_4= '>' )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:839:1:
                // (otherlv_0= '<' ( ( ruleMYID ) ) otherlv_2= ',' ( ( ruleMYID ) ) otherlv_4= '>' )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:839:3:
                // otherlv_0= '<' ( ( ruleMYID ) ) otherlv_2= ',' ( ( ruleMYID ) ) otherlv_4= '>'
                {
                    otherlv_0 = (Token) this.match(this.input, 33, FOLLOW_33_in_ruleBoundAndWeight1845);

                    this.newLeafNode(otherlv_0,
                            this.grammarAccess.getBoundAndWeightAccess().getLessThanSignKeyword_0());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:843:1:
                    // ( ( ruleMYID ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:844:1:
                    // ( ruleMYID )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:844:1:
                        // ( ruleMYID )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:845:3:
                        // ruleMYID
                        {

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getBoundAndWeightRule());
                            }

                            this.newCompositeNode(this.grammarAccess.getBoundAndWeightAccess()
                                    .getUpperBoundNumberCrossReference_1_0());

                            this.pushFollow(FOLLOW_ruleMYID_in_ruleBoundAndWeight1868);
                            this.ruleMYID();

                            this.state._fsp--;

                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_2 = (Token) this.match(this.input, 20, FOLLOW_20_in_ruleBoundAndWeight1880);

                    this.newLeafNode(otherlv_2, this.grammarAccess.getBoundAndWeightAccess().getCommaKeyword_2());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:862:1:
                    // ( ( ruleMYID ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:863:1:
                    // ( ruleMYID )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:863:1:
                        // ( ruleMYID )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:864:3:
                        // ruleMYID
                        {

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getBoundAndWeightRule());
                            }

                            this.newCompositeNode(
                                    this.grammarAccess.getBoundAndWeightAccess().getWeightNumberCrossReference_3_0());

                            this.pushFollow(FOLLOW_ruleMYID_in_ruleBoundAndWeight1903);
                            this.ruleMYID();

                            this.state._fsp--;

                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_4 = (Token) this.match(this.input, 34, FOLLOW_34_in_ruleBoundAndWeight1915);

                    this.newLeafNode(otherlv_4,
                            this.grammarAccess.getBoundAndWeightAccess().getGreaterThanSignKeyword_4());

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleBoundAndWeight"

    // $ANTLR start "entryRuleMetricAndWeight"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:889:1:
    // entryRuleMetricAndWeight returns [EObject current=null] : iv_ruleMetricAndWeight=
    // ruleMetricAndWeight EOF ;
    public final EObject entryRuleMetricAndWeight() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetricAndWeight = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:890:2:
            // (iv_ruleMetricAndWeight= ruleMetricAndWeight EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:891:2:
            // iv_ruleMetricAndWeight= ruleMetricAndWeight EOF
            {
                this.newCompositeNode(this.grammarAccess.getMetricAndWeightRule());
                this.pushFollow(FOLLOW_ruleMetricAndWeight_in_entryRuleMetricAndWeight1951);
                iv_ruleMetricAndWeight = this.ruleMetricAndWeight();

                this.state._fsp--;

                current = iv_ruleMetricAndWeight;
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleMetricAndWeight1961);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMetricAndWeight"

    // $ANTLR start "ruleMetricAndWeight"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:898:1:
    // ruleMetricAndWeight returns [EObject current=null] : (otherlv_0= '<' ( ( ruleMYID ) )
    // otherlv_2= ',' ( ( ruleMYID ) ) otherlv_4= '>' ) ;
    public final EObject ruleMetricAndWeight() throws RecognitionException {
        EObject current = null;

        Token otherlv_0 = null;
        Token otherlv_2 = null;
        Token otherlv_4 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:901:28:
            // ( (otherlv_0= '<' ( ( ruleMYID ) ) otherlv_2= ',' ( ( ruleMYID ) ) otherlv_4= '>' ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:902:1:
            // (otherlv_0= '<' ( ( ruleMYID ) ) otherlv_2= ',' ( ( ruleMYID ) ) otherlv_4= '>' )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:902:1:
                // (otherlv_0= '<' ( ( ruleMYID ) ) otherlv_2= ',' ( ( ruleMYID ) ) otherlv_4= '>' )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:902:3:
                // otherlv_0= '<' ( ( ruleMYID ) ) otherlv_2= ',' ( ( ruleMYID ) ) otherlv_4= '>'
                {
                    otherlv_0 = (Token) this.match(this.input, 33, FOLLOW_33_in_ruleMetricAndWeight1998);

                    this.newLeafNode(otherlv_0,
                            this.grammarAccess.getMetricAndWeightAccess().getLessThanSignKeyword_0());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:906:1:
                    // ( ( ruleMYID ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:907:1:
                    // ( ruleMYID )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:907:1:
                        // ( ruleMYID )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:908:3:
                        // ruleMYID
                        {

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getMetricAndWeightRule());
                            }

                            this.newCompositeNode(
                                    this.grammarAccess.getMetricAndWeightAccess().getMetricMetricCrossReference_1_0());

                            this.pushFollow(FOLLOW_ruleMYID_in_ruleMetricAndWeight2021);
                            this.ruleMYID();

                            this.state._fsp--;

                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_2 = (Token) this.match(this.input, 20, FOLLOW_20_in_ruleMetricAndWeight2033);

                    this.newLeafNode(otherlv_2, this.grammarAccess.getMetricAndWeightAccess().getCommaKeyword_2());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:925:1:
                    // ( ( ruleMYID ) )
                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:926:1:
                    // ( ruleMYID )
                    {
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:926:1:
                        // ( ruleMYID )
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:927:3:
                        // ruleMYID
                        {

                            if (current == null) {
                                current = this.createModelElement(this.grammarAccess.getMetricAndWeightRule());
                            }

                            this.newCompositeNode(
                                    this.grammarAccess.getMetricAndWeightAccess().getWeightNumberCrossReference_3_0());

                            this.pushFollow(FOLLOW_ruleMYID_in_ruleMetricAndWeight2056);
                            this.ruleMYID();

                            this.state._fsp--;

                            this.afterParserOrEnumRuleCall();

                        }

                    }

                    otherlv_4 = (Token) this.match(this.input, 34, FOLLOW_34_in_ruleMetricAndWeight2068);

                    this.newLeafNode(otherlv_4,
                            this.grammarAccess.getMetricAndWeightAccess().getGreaterThanSignKeyword_4());

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleMetricAndWeight"

    // $ANTLR start "entryRuleMYID"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:952:1:
    // entryRuleMYID returns [String current=null] : iv_ruleMYID= ruleMYID EOF ;
    public final String entryRuleMYID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleMYID = null;

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:953:2:
            // (iv_ruleMYID= ruleMYID EOF )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:954:2:
            // iv_ruleMYID= ruleMYID EOF
            {
                this.newCompositeNode(this.grammarAccess.getMYIDRule());
                this.pushFollow(FOLLOW_ruleMYID_in_entryRuleMYID2105);
                iv_ruleMYID = this.ruleMYID();

                this.state._fsp--;

                current = iv_ruleMYID.getText();
                this.match(this.input, EOF, FOLLOW_EOF_in_entryRuleMYID2116);

            }

        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMYID"

    // $ANTLR start "ruleMYID"
    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:961:1:
    // ruleMYID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0=
    // RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleMYID() throws RecognitionException {
        final AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0 = null;
        Token kw = null;
        Token this_ID_2 = null;

        this.enterRule();

        try {
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:964:28:
            // ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:965:1:
            // (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:965:1:
                // (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
                // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:965:6:
                // this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
                {
                    this_ID_0 = (Token) this.match(this.input, RULE_ID, FOLLOW_RULE_ID_in_ruleMYID2156);

                    current.merge(this_ID_0);

                    this.newLeafNode(this_ID_0, this.grammarAccess.getMYIDAccess().getIDTerminalRuleCall_0());

                    // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:972:1:
                    // (kw= '.' this_ID_2= RULE_ID )*
                    loop10: do {
                        int alt10 = 2;
                        final int LA10_0 = this.input.LA(1);

                        if ((LA10_0 == 35)) {
                            alt10 = 1;
                        }

                        switch (alt10) {
                        case 1:
                        // ../org.somox.metrics.dsl/src-gen/org/somox/metrics/dsl/parser/antlr/internal/InternalMetricDSL.g:973:2:
                        // kw= '.' this_ID_2= RULE_ID
                        {
                            kw = (Token) this.match(this.input, 35, FOLLOW_35_in_ruleMYID2175);

                            current.merge(kw);
                            this.newLeafNode(kw, this.grammarAccess.getMYIDAccess().getFullStopKeyword_1_0());

                            this_ID_2 = (Token) this.match(this.input, RULE_ID, FOLLOW_RULE_ID_in_ruleMYID2190);

                            current.merge(this_ID_2);

                            this.newLeafNode(this_ID_2, this.grammarAccess.getMYIDAccess().getIDTerminalRuleCall_1_1());

                        }
                            break;

                        default:
                            break loop10;
                        }
                    } while (true);

                }

            }

            this.leaveRule();
        }

        catch (final RecognitionException re) {
            this.recover(this.input, re);
            this.appendSkippedTokens();
        } finally {
        }
        return current;
    }
    // $ANTLR end "ruleMYID"

    // Delegated rules

    public static final BitSet FOLLOW_ruleMetricModel_in_entryRuleMetricModel75 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleMetricModel85 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_12_in_ruleMetricModel123 = new BitSet(new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_STRING_in_ruleMetricModel140 = new BitSet(
            new long[] { 0x0000000000002000L });
    public static final BitSet FOLLOW_13_in_ruleMetricModel157 = new BitSet(new long[] { 0x000000000000D000L });
    public static final BitSet FOLLOW_14_in_ruleMetricModel172 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleExternalMetric_in_ruleMetricModel193 = new BitSet(
            new long[] { 0x0000000000002000L });
    public static final BitSet FOLLOW_13_in_ruleMetricModel205 = new BitSet(new long[] { 0x000000000000C000L });
    public static final BitSet FOLLOW_15_in_ruleMetricModel219 = new BitSet(new long[] { 0x0000000000010000L });
    public static final BitSet FOLLOW_16_in_ruleMetricModel231 = new BitSet(new long[] { 0x0000000000040000L });
    public static final BitSet FOLLOW_ruleInternalMetric_in_ruleMetricModel252 = new BitSet(
            new long[] { 0x0000000000060000L });
    public static final BitSet FOLLOW_17_in_ruleMetricModel265 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleExternalMetric_in_entryRuleExternalMetric303 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleExternalMetric313 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleExternalMetric358 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleInternalMetric_in_entryRuleInternalMetric393 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleInternalMetric403 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_18_in_ruleInternalMetric440 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleInternalMetric461 = new BitSet(
            new long[] { 0x0000000000080000L });
    public static final BitSet FOLLOW_19_in_ruleInternalMetric473 = new BitSet(new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_STRING_in_ruleInternalMetric490 = new BitSet(
            new long[] { 0x0000000000100000L });
    public static final BitSet FOLLOW_20_in_ruleInternalMetric507 = new BitSet(new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_STRING_in_ruleInternalMetric524 = new BitSet(
            new long[] { 0x0000000000200000L });
    public static final BitSet FOLLOW_21_in_ruleInternalMetric541 = new BitSet(new long[] { 0x0000000000010000L });
    public static final BitSet FOLLOW_16_in_ruleInternalMetric553 = new BitSet(new long[] { 0x0000000098400000L });
    public static final BitSet FOLLOW_22_in_ruleInternalMetric566 = new BitSet(new long[] { 0x0000000000010000L });
    public static final BitSet FOLLOW_16_in_ruleInternalMetric578 = new BitSet(new long[] { 0x0000000003020000L });
    public static final BitSet FOLLOW_ruleNumber_in_ruleInternalMetric599 = new BitSet(
            new long[] { 0x0000000003020000L });
    public static final BitSet FOLLOW_17_in_ruleInternalMetric612 = new BitSet(new long[] { 0x0000000098400000L });
    public static final BitSet FOLLOW_ruleMetricDefinition_in_ruleInternalMetric635 = new BitSet(
            new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_ruleInternalMetric647 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleNumber_in_entryRuleNumber683 = new BitSet(new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleNumber693 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleParameter_in_ruleNumber740 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleConstant_in_ruleNumber767 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleParameter_in_entryRuleParameter802 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleParameter812 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_24_in_ruleParameter849 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleParameter870 = new BitSet(new long[] { 0x0000000000080000L });
    public static final BitSet FOLLOW_19_in_ruleParameter882 = new BitSet(new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_STRING_in_ruleParameter899 = new BitSet(new long[] { 0x0000000000100000L });
    public static final BitSet FOLLOW_20_in_ruleParameter916 = new BitSet(new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_RULE_STRING_in_ruleParameter933 = new BitSet(new long[] { 0x0000000000100000L });
    public static final BitSet FOLLOW_20_in_ruleParameter950 = new BitSet(new long[] { 0x0000000000000020L });
    public static final BitSet FOLLOW_RULE_DOUBLE_in_ruleParameter967 = new BitSet(new long[] { 0x0000000000200000L });
    public static final BitSet FOLLOW_21_in_ruleParameter984 = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet FOLLOW_13_in_ruleParameter996 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleConstant_in_entryRuleConstant1032 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleConstant1042 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_25_in_ruleConstant1079 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleConstant1100 = new BitSet(new long[] { 0x0000000004000000L });
    public static final BitSet FOLLOW_26_in_ruleConstant1112 = new BitSet(new long[] { 0x0000000000000020L });
    public static final BitSet FOLLOW_RULE_DOUBLE_in_ruleConstant1129 = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet FOLLOW_13_in_ruleConstant1146 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleMetricDefinition_in_entryRuleMetricDefinition1182 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleMetricDefinition1192 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleWeightedMetric_in_ruleMetricDefinition1239 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleStepwiseMetric_in_ruleMetricDefinition1266 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRatioMetric_in_ruleMetricDefinition1293 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleWeightedMetric_in_entryRuleWeightedMetric1328 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleWeightedMetric1338 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_27_in_ruleWeightedMetric1375 = new BitSet(new long[] { 0x0000000000010000L });
    public static final BitSet FOLLOW_16_in_ruleWeightedMetric1387 = new BitSet(new long[] { 0x0000000200000000L });
    public static final BitSet FOLLOW_ruleMetricAndWeight_in_ruleWeightedMetric1408 = new BitSet(
            new long[] { 0x0000000200020000L });
    public static final BitSet FOLLOW_17_in_ruleWeightedMetric1421 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleStepwiseMetric_in_entryRuleStepwiseMetric1457 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleStepwiseMetric1467 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_28_in_ruleStepwiseMetric1504 = new BitSet(new long[] { 0x0000000020000000L });
    public static final BitSet FOLLOW_29_in_ruleStepwiseMetric1516 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleStepwiseMetric1539 = new BitSet(
            new long[] { 0x0000000040000000L });
    public static final BitSet FOLLOW_30_in_ruleStepwiseMetric1551 = new BitSet(new long[] { 0x0000000000010000L });
    public static final BitSet FOLLOW_16_in_ruleStepwiseMetric1563 = new BitSet(new long[] { 0x0000000200020000L });
    public static final BitSet FOLLOW_ruleBoundAndWeight_in_ruleStepwiseMetric1584 = new BitSet(
            new long[] { 0x0000000200020000L });
    public static final BitSet FOLLOW_17_in_ruleStepwiseMetric1597 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleRatioMetric_in_entryRuleRatioMetric1633 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleRatioMetric1643 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_31_in_ruleRatioMetric1680 = new BitSet(new long[] { 0x0000000000010000L });
    public static final BitSet FOLLOW_16_in_ruleRatioMetric1692 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleRatioMetric1715 = new BitSet(new long[] { 0x0000000100000000L });
    public static final BitSet FOLLOW_32_in_ruleRatioMetric1727 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleRatioMetric1750 = new BitSet(new long[] { 0x0000000000020000L });
    public static final BitSet FOLLOW_17_in_ruleRatioMetric1762 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleBoundAndWeight_in_entryRuleBoundAndWeight1798 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleBoundAndWeight1808 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_33_in_ruleBoundAndWeight1845 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleBoundAndWeight1868 = new BitSet(
            new long[] { 0x0000000000100000L });
    public static final BitSet FOLLOW_20_in_ruleBoundAndWeight1880 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleBoundAndWeight1903 = new BitSet(
            new long[] { 0x0000000400000000L });
    public static final BitSet FOLLOW_34_in_ruleBoundAndWeight1915 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleMetricAndWeight_in_entryRuleMetricAndWeight1951 = new BitSet(
            new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleMetricAndWeight1961 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_33_in_ruleMetricAndWeight1998 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleMetricAndWeight2021 = new BitSet(
            new long[] { 0x0000000000100000L });
    public static final BitSet FOLLOW_20_in_ruleMetricAndWeight2033 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_ruleMYID_in_ruleMetricAndWeight2056 = new BitSet(
            new long[] { 0x0000000400000000L });
    public static final BitSet FOLLOW_34_in_ruleMetricAndWeight2068 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ruleMYID_in_entryRuleMYID2105 = new BitSet(new long[] { 0x0000000000000000L });
    public static final BitSet FOLLOW_EOF_in_entryRuleMYID2116 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleMYID2156 = new BitSet(new long[] { 0x0000000800000002L });
    public static final BitSet FOLLOW_35_in_ruleMYID2175 = new BitSet(new long[] { 0x0000000000000040L });
    public static final BitSet FOLLOW_RULE_ID_in_ruleMYID2190 = new BitSet(new long[] { 0x0000000800000002L });

}