package org.somox.metrics.dsl.ui.contentassist.antlr.internal;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
// Hack: Use our own Lexer superclass by means of import.
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;

@SuppressWarnings("all")
public class InternalMetricDSLLexer extends Lexer {
    public static final int RULE_ID = 4;
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
    public static final int EOF = -1;
    public static final int RULE_SL_COMMENT = 9;
    public static final int RULE_DOUBLE = 6;
    public static final int RULE_ML_COMMENT = 8;
    public static final int T__19 = 19;
    public static final int T__30 = 30;
    public static final int T__31 = 31;
    public static final int T__32 = 32;
    public static final int RULE_STRING = 5;
    public static final int T__16 = 16;
    public static final int T__33 = 33;
    public static final int T__15 = 15;
    public static final int T__34 = 34;
    public static final int T__18 = 18;
    public static final int T__35 = 35;
    public static final int T__17 = 17;
    public static final int T__12 = 12;
    public static final int T__14 = 14;
    public static final int T__13 = 13;
    public static final int RULE_INT = 7;
    public static final int RULE_WS = 10;

    // delegates
    // delegators

    public InternalMetricDSLLexer() {
        ;
    }

    public InternalMetricDSLLexer(final CharStream input) {
        this(input, new RecognizerSharedState());
    }

    public InternalMetricDSLLexer(final CharStream input, final RecognizerSharedState state) {
        super(input, state);

    }

    @Override
    public String getGrammarFileName() {
        return "../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g";
    }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            final int _type = T__12;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:11:7:
            // ( 'Metrics' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:11:9:
            // 'Metrics'
            {
                this.match("Metrics");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            final int _type = T__13;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:12:7:
            // ( '{' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:12:9:
            // '{'
            {
                this.match('{');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            final int _type = T__14;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:13:7:
            // ( '}' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:13:9:
            // '}'
            {
                this.match('}');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            final int _type = T__15;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:14:7:
            // ( 'import' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:14:9:
            // 'import'
            {
                this.match("import");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            final int _type = T__16;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:15:7:
            // ( ';' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:15:9:
            // ';'
            {
                this.match(';');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            final int _type = T__17;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:16:7:
            // ( 'extern' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:16:9:
            // 'extern'
            {
                this.match("extern");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            final int _type = T__18;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:17:7:
            // ( 'Metric' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:17:9:
            // 'Metric'
            {
                this.match("Metric");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            final int _type = T__19;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:18:7:
            // ( '(' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:18:9:
            // '('
            {
                this.match('(');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            final int _type = T__20;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:19:7:
            // ( ',' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:19:9:
            // ','
            {
                this.match(',');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            final int _type = T__21;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:20:7:
            // ( ')' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:20:9:
            // ')'
            {
                this.match(')');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            final int _type = T__22;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:21:7:
            // ( '};' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:21:9:
            // '};'
            {
                this.match("};");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            final int _type = T__23;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:22:7:
            // ( 'parameters' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:22:9:
            // 'parameters'
            {
                this.match("parameters");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            final int _type = T__24;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:23:7:
            // ( 'Parameter' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:23:9:
            // 'Parameter'
            {
                this.match("Parameter");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            final int _type = T__25;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:24:7:
            // ( 'Const' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:24:9:
            // 'Const'
            {
                this.match("Const");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            final int _type = T__26;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:25:7:
            // ( '=' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:25:9:
            // '='
            {
                this.match('=');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            final int _type = T__27;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:26:7:
            // ( 'WeigthedSum' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:26:9:
            // 'WeigthedSum'
            {
                this.match("WeigthedSum");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            final int _type = T__28;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:27:7:
            // ( 'Stepwise' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:27:9:
            // 'Stepwise'
            {
                this.match("Stepwise");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            final int _type = T__29;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:28:7:
            // ( '[' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:28:9:
            // '['
            {
                this.match('[');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            final int _type = T__30;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:29:7:
            // ( ']' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:29:9:
            // ']'
            {
                this.match(']');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            final int _type = T__31;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:30:7:
            // ( 'Ratio' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:30:9:
            // 'Ratio'
            {
                this.match("Ratio");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            final int _type = T__32;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:31:7:
            // ( '/' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:31:9:
            // '/'
            {
                this.match('/');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            final int _type = T__33;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:32:7:
            // ( '<' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:32:9:
            // '<'
            {
                this.match('<');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            final int _type = T__34;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:33:7:
            // ( '>' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:33:9:
            // '>'
            {
                this.match('>');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            final int _type = T__35;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:34:7:
            // ( '.' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:34:9:
            // '.'
            {
                this.match('.');

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "RULE_DOUBLE"
    public final void mRULE_DOUBLE() throws RecognitionException {
        try {
            final int _type = RULE_DOUBLE;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3220:13:
            // ( ( '-' )? RULE_INT '.' RULE_INT )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3220:15:
            // ( '-' )? RULE_INT '.' RULE_INT
            {
                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3220:15:
                // ( '-' )?
                int alt1 = 2;
                final int LA1_0 = this.input.LA(1);

                if ((LA1_0 == '-')) {
                    alt1 = 1;
                }
                switch (alt1) {
                case 1:
                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3220:15:
                // '-'
                {
                    this.match('-');

                }
                    break;

                }

                this.mRULE_INT();
                this.match('.');
                this.mRULE_INT();

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RULE_DOUBLE"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            final int _type = RULE_ID;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3222:9:
            // ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' ..
            // '9' )* )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3222:11:
            // ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' ..
            // '9' )*
            {
                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3222:11:
                // ( '^' )?
                int alt2 = 2;
                final int LA2_0 = this.input.LA(1);

                if ((LA2_0 == '^')) {
                    alt2 = 1;
                }
                switch (alt2) {
                case 1:
                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3222:11:
                // '^'
                {
                    this.match('^');

                }
                    break;

                }

                if ((this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z') || this.input.LA(1) == '_'
                        || (this.input.LA(1) >= 'a' && this.input.LA(1) <= 'z')) {
                    this.input.consume();

                } else {
                    final MismatchedSetException mse = new MismatchedSetException(null, this.input);
                    this.recover(mse);
                    throw mse;
                }

                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3222:40:
                // ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
                loop3: do {
                    int alt3 = 2;
                    final int LA3_0 = this.input.LA(1);

                    if (((LA3_0 >= '0' && LA3_0 <= '9') || (LA3_0 >= 'A' && LA3_0 <= 'Z') || LA3_0 == '_'
                            || (LA3_0 >= 'a' && LA3_0 <= 'z'))) {
                        alt3 = 1;
                    }

                    switch (alt3) {
                    case 1:
                    // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:
                    {
                        if ((this.input.LA(1) >= '0' && this.input.LA(1) <= '9')
                                || (this.input.LA(1) >= 'A' && this.input.LA(1) <= 'Z') || this.input.LA(1) == '_'
                                || (this.input.LA(1) >= 'a' && this.input.LA(1) <= 'z')) {
                            this.input.consume();

                        } else {
                            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
                            this.recover(mse);
                            throw mse;
                        }

                    }
                        break;

                    default:
                        break loop3;
                    }
                } while (true);

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            final int _type = RULE_INT;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3224:10:
            // ( ( '0' .. '9' )+ )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3224:12:
            // ( '0' .. '9' )+
            {
                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3224:12:
                // ( '0' .. '9' )+
                int cnt4 = 0;
                loop4: do {
                    int alt4 = 2;
                    final int LA4_0 = this.input.LA(1);

                    if (((LA4_0 >= '0' && LA4_0 <= '9'))) {
                        alt4 = 1;
                    }

                    switch (alt4) {
                    case 1:
                    // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3224:13:
                    // '0' .. '9'
                    {
                        this.matchRange('0', '9');

                    }
                        break;

                    default:
                        if (cnt4 >= 1) {
                            break loop4;
                        }
                        final EarlyExitException eee = new EarlyExitException(4, this.input);
                        throw eee;
                    }
                    cnt4++;
                } while (true);

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            final int _type = RULE_STRING;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:13:
            // ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ (
            // ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' |
            // '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:15:
            // ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( (
            // '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"'
            // | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:15:
                // ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~
                // ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' |
                // 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
                int alt7 = 2;
                final int LA7_0 = this.input.LA(1);

                if ((LA7_0 == '\"')) {
                    alt7 = 1;
                } else if ((LA7_0 == '\'')) {
                    alt7 = 2;
                } else {
                    final NoViableAltException nvae = new NoViableAltException("", 7, 0, this.input);

                    throw nvae;
                }
                switch (alt7) {
                case 1:
                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:16:
                // '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ (
                // ( '\\\\' | '\"' ) ) )* '\"'
                {
                    this.match('\"');
                    // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:20:
                    // ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ (
                    // ( '\\\\' | '\"' ) ) )*
                    loop5: do {
                        int alt5 = 3;
                        final int LA5_0 = this.input.LA(1);

                        if ((LA5_0 == '\\')) {
                            alt5 = 1;
                        } else if (((LA5_0 >= '\u0000' && LA5_0 <= '!') || (LA5_0 >= '#' && LA5_0 <= '[')
                                || (LA5_0 >= ']' && LA5_0 <= '\uFFFF'))) {
                            alt5 = 2;
                        }

                        switch (alt5) {
                        case 1:
                        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:21:
                        // '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                        {
                            this.match('\\');
                            if (this.input.LA(1) == '\"' || this.input.LA(1) == '\'' || this.input.LA(1) == '\\'
                                    || this.input.LA(1) == 'b' || this.input.LA(1) == 'f' || this.input.LA(1) == 'n'
                                    || this.input.LA(1) == 'r'
                                    || (this.input.LA(1) >= 't' && this.input.LA(1) <= 'u')) {
                                this.input.consume();

                            } else {
                                final MismatchedSetException mse = new MismatchedSetException(null, this.input);
                                this.recover(mse);
                                throw mse;
                            }

                        }
                            break;
                        case 2:
                        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:66:
                        // ~ ( ( '\\\\' | '\"' ) )
                        {
                            if ((this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '!')
                                    || (this.input.LA(1) >= '#' && this.input.LA(1) <= '[')
                                    || (this.input.LA(1) >= ']' && this.input.LA(1) <= '\uFFFF')) {
                                this.input.consume();

                            } else {
                                final MismatchedSetException mse = new MismatchedSetException(null, this.input);
                                this.recover(mse);
                                throw mse;
                            }

                        }
                            break;

                        default:
                            break loop5;
                        }
                    } while (true);

                    this.match('\"');

                }
                    break;
                case 2:
                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:86:
                // '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~
                // ( ( '\\\\' | '\\'' ) ) )* '\\''
                {
                    this.match('\'');
                    // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:91:
                    // ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ (
                    // ( '\\\\' | '\\'' ) ) )*
                    loop6: do {
                        int alt6 = 3;
                        final int LA6_0 = this.input.LA(1);

                        if ((LA6_0 == '\\')) {
                            alt6 = 1;
                        } else if (((LA6_0 >= '\u0000' && LA6_0 <= '&') || (LA6_0 >= '(' && LA6_0 <= '[')
                                || (LA6_0 >= ']' && LA6_0 <= '\uFFFF'))) {
                            alt6 = 2;
                        }

                        switch (alt6) {
                        case 1:
                        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:92:
                        // '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                        {
                            this.match('\\');
                            if (this.input.LA(1) == '\"' || this.input.LA(1) == '\'' || this.input.LA(1) == '\\'
                                    || this.input.LA(1) == 'b' || this.input.LA(1) == 'f' || this.input.LA(1) == 'n'
                                    || this.input.LA(1) == 'r'
                                    || (this.input.LA(1) >= 't' && this.input.LA(1) <= 'u')) {
                                this.input.consume();

                            } else {
                                final MismatchedSetException mse = new MismatchedSetException(null, this.input);
                                this.recover(mse);
                                throw mse;
                            }

                        }
                            break;
                        case 2:
                        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3226:137:
                        // ~ ( ( '\\\\' | '\\'' ) )
                        {
                            if ((this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '&')
                                    || (this.input.LA(1) >= '(' && this.input.LA(1) <= '[')
                                    || (this.input.LA(1) >= ']' && this.input.LA(1) <= '\uFFFF')) {
                                this.input.consume();

                            } else {
                                final MismatchedSetException mse = new MismatchedSetException(null, this.input);
                                this.recover(mse);
                                throw mse;
                            }

                        }
                            break;

                        default:
                            break loop6;
                        }
                    } while (true);

                    this.match('\'');

                }
                    break;

                }

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            final int _type = RULE_ML_COMMENT;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3228:17:
            // ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3228:19:
            // '/*' ( options {greedy=false; } : . )* '*/'
            {
                this.match("/*");

                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3228:24:
                // ( options {greedy=false; } : . )*
                loop8: do {
                    int alt8 = 2;
                    final int LA8_0 = this.input.LA(1);

                    if ((LA8_0 == '*')) {
                        final int LA8_1 = this.input.LA(2);

                        if ((LA8_1 == '/')) {
                            alt8 = 2;
                        } else if (((LA8_1 >= '\u0000' && LA8_1 <= '.') || (LA8_1 >= '0' && LA8_1 <= '\uFFFF'))) {
                            alt8 = 1;
                        }

                    } else if (((LA8_0 >= '\u0000' && LA8_0 <= ')') || (LA8_0 >= '+' && LA8_0 <= '\uFFFF'))) {
                        alt8 = 1;
                    }

                    switch (alt8) {
                    case 1:
                    // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3228:52:
                    // .
                    {
                        this.matchAny();

                    }
                        break;

                    default:
                        break loop8;
                    }
                } while (true);

                this.match("*/");

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            final int _type = RULE_SL_COMMENT;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3230:17:
            // ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3230:19:
            // '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
                this.match("//");

                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3230:24:
                // (~ ( ( '\\n' | '\\r' ) ) )*
                loop9: do {
                    int alt9 = 2;
                    final int LA9_0 = this.input.LA(1);

                    if (((LA9_0 >= '\u0000' && LA9_0 <= '\t') || (LA9_0 >= '\u000B' && LA9_0 <= '\f')
                            || (LA9_0 >= '\u000E' && LA9_0 <= '\uFFFF'))) {
                        alt9 = 1;
                    }

                    switch (alt9) {
                    case 1:
                    // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3230:24:
                    // ~ ( ( '\\n' | '\\r' ) )
                    {
                        if ((this.input.LA(1) >= '\u0000' && this.input.LA(1) <= '\t')
                                || (this.input.LA(1) >= '\u000B' && this.input.LA(1) <= '\f')
                                || (this.input.LA(1) >= '\u000E' && this.input.LA(1) <= '\uFFFF')) {
                            this.input.consume();

                        } else {
                            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
                            this.recover(mse);
                            throw mse;
                        }

                    }
                        break;

                    default:
                        break loop9;
                    }
                } while (true);

                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3230:40:
                // ( ( '\\r' )? '\\n' )?
                int alt11 = 2;
                final int LA11_0 = this.input.LA(1);

                if ((LA11_0 == '\n' || LA11_0 == '\r')) {
                    alt11 = 1;
                }
                switch (alt11) {
                case 1:
                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3230:41:
                // ( '\\r' )? '\\n'
                {
                    // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3230:41:
                    // ( '\\r' )?
                    int alt10 = 2;
                    final int LA10_0 = this.input.LA(1);

                    if ((LA10_0 == '\r')) {
                        alt10 = 1;
                    }
                    switch (alt10) {
                    case 1:
                    // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3230:41:
                    // '\\r'
                    {
                        this.match('\r');

                    }
                        break;

                    }

                    this.match('\n');

                }
                    break;

                }

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            final int _type = RULE_WS;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3232:9:
            // ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3232:11:
            // ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
                // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3232:11:
                // ( ' ' | '\\t' | '\\r' | '\\n' )+
                int cnt12 = 0;
                loop12: do {
                    int alt12 = 2;
                    final int LA12_0 = this.input.LA(1);

                    if (((LA12_0 >= '\t' && LA12_0 <= '\n') || LA12_0 == '\r' || LA12_0 == ' ')) {
                        alt12 = 1;
                    }

                    switch (alt12) {
                    case 1:
                    // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:
                    {
                        if ((this.input.LA(1) >= '\t' && this.input.LA(1) <= '\n') || this.input.LA(1) == '\r'
                                || this.input.LA(1) == ' ') {
                            this.input.consume();

                        } else {
                            final MismatchedSetException mse = new MismatchedSetException(null, this.input);
                            this.recover(mse);
                            throw mse;
                        }

                    }
                        break;

                    default:
                        if (cnt12 >= 1) {
                            break loop12;
                        }
                        final EarlyExitException eee = new EarlyExitException(12, this.input);
                        throw eee;
                    }
                    cnt12++;
                } while (true);

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            final int _type = RULE_ANY_OTHER;
            final int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3234:16:
            // ( . )
            // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:3234:18:
            // .
            {
                this.matchAny();

            }

            this.state.type = _type;
            this.state.channel = _channel;
        } finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    @Override
    public void mTokens() throws RecognitionException {
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:8:
        // ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 |
        // T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 |
        // T__34 | T__35 | RULE_DOUBLE | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT |
        // RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt13 = 32;
        alt13 = this.dfa13.predict(this.input);
        switch (alt13) {
        case 1:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:10:
        // T__12
        {
            this.mT__12();

        }
            break;
        case 2:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:16:
        // T__13
        {
            this.mT__13();

        }
            break;
        case 3:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:22:
        // T__14
        {
            this.mT__14();

        }
            break;
        case 4:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:28:
        // T__15
        {
            this.mT__15();

        }
            break;
        case 5:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:34:
        // T__16
        {
            this.mT__16();

        }
            break;
        case 6:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:40:
        // T__17
        {
            this.mT__17();

        }
            break;
        case 7:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:46:
        // T__18
        {
            this.mT__18();

        }
            break;
        case 8:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:52:
        // T__19
        {
            this.mT__19();

        }
            break;
        case 9:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:58:
        // T__20
        {
            this.mT__20();

        }
            break;
        case 10:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:64:
        // T__21
        {
            this.mT__21();

        }
            break;
        case 11:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:70:
        // T__22
        {
            this.mT__22();

        }
            break;
        case 12:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:76:
        // T__23
        {
            this.mT__23();

        }
            break;
        case 13:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:82:
        // T__24
        {
            this.mT__24();

        }
            break;
        case 14:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:88:
        // T__25
        {
            this.mT__25();

        }
            break;
        case 15:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:94:
        // T__26
        {
            this.mT__26();

        }
            break;
        case 16:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:100:
        // T__27
        {
            this.mT__27();

        }
            break;
        case 17:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:106:
        // T__28
        {
            this.mT__28();

        }
            break;
        case 18:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:112:
        // T__29
        {
            this.mT__29();

        }
            break;
        case 19:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:118:
        // T__30
        {
            this.mT__30();

        }
            break;
        case 20:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:124:
        // T__31
        {
            this.mT__31();

        }
            break;
        case 21:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:130:
        // T__32
        {
            this.mT__32();

        }
            break;
        case 22:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:136:
        // T__33
        {
            this.mT__33();

        }
            break;
        case 23:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:142:
        // T__34
        {
            this.mT__34();

        }
            break;
        case 24:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:148:
        // T__35
        {
            this.mT__35();

        }
            break;
        case 25:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:154:
        // RULE_DOUBLE
        {
            this.mRULE_DOUBLE();

        }
            break;
        case 26:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:166:
        // RULE_ID
        {
            this.mRULE_ID();

        }
            break;
        case 27:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:174:
        // RULE_INT
        {
            this.mRULE_INT();

        }
            break;
        case 28:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:183:
        // RULE_STRING
        {
            this.mRULE_STRING();

        }
            break;
        case 29:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:195:
        // RULE_ML_COMMENT
        {
            this.mRULE_ML_COMMENT();

        }
            break;
        case 30:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:211:
        // RULE_SL_COMMENT
        {
            this.mRULE_SL_COMMENT();

        }
            break;
        case 31:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:227:
        // RULE_WS
        {
            this.mRULE_WS();

        }
            break;
        case 32:
        // ../org.somox.metrics.dsl.ui/src-gen/org/somox/metrics/dsl/ui/contentassist/antlr/internal/InternalMetricDSL.g:1:235:
        // RULE_ANY_OTHER
        {
            this.mRULE_ANY_OTHER();

        }
            break;

        }

    }

    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS = "\1\uffff\1\40\1\uffff\1\43\1\40\1\uffff\1\40\3\uffff\3\40\1\uffff"
            + "\2\40\2\uffff\1\40\1\65\3\uffff\1\36\1\72\1\36\1\uffff\2\36\2\uffff"
            + "\1\40\4\uffff\1\40\1\uffff\1\40\3\uffff\3\40\1\uffff\2\40\2\uffff"
            + "\1\40\10\uffff\1\72\2\uffff\27\40\1\136\2\40\1\141\1\143\1\144\1"
            + "\145\2\40\1\uffff\2\40\1\uffff\1\152\3\uffff\4\40\1\uffff\3\40\1"
            + "\162\1\40\1\164\1\40\1\uffff\1\166\1\uffff\1\40\1\uffff\1\170\1" + "\uffff";
    static final String DFA13_eofS = "\171\uffff";
    static final String DFA13_minS = "\1\0\1\145\1\uffff\1\73\1\155\1\uffff\1\170\3\uffff\2\141\1\157"
            + "\1\uffff\1\145\1\164\2\uffff\1\141\1\52\3\uffff\1\60\1\56\1\101"
            + "\1\uffff\2\0\2\uffff\1\164\4\uffff\1\160\1\uffff\1\164\3\uffff\2"
            + "\162\1\156\1\uffff\1\151\1\145\2\uffff\1\164\10\uffff\1\56\2\uffff"
            + "\1\162\1\157\1\145\2\141\1\163\1\147\1\160\2\151\2\162\2\155\2\164"
            + "\1\167\1\157\1\143\1\164\1\156\2\145\1\60\1\150\1\151\4\60\2\164"
            + "\1\uffff\1\145\1\163\1\uffff\1\60\3\uffff\2\145\1\144\1\145\1\uffff"
            + "\2\162\1\123\1\60\1\163\1\60\1\165\1\uffff\1\60\1\uffff\1\155\1" + "\uffff\1\60\1\uffff";
    static final String DFA13_maxS = "\1\uffff\1\145\1\uffff\1\73\1\155\1\uffff\1\170\3\uffff\2\141\1"
            + "\157\1\uffff\1\145\1\164\2\uffff\1\141\1\57\3\uffff\2\71\1\172\1"
            + "\uffff\2\uffff\2\uffff\1\164\4\uffff\1\160\1\uffff\1\164\3\uffff"
            + "\2\162\1\156\1\uffff\1\151\1\145\2\uffff\1\164\10\uffff\1\71\2\uffff"
            + "\1\162\1\157\1\145\2\141\1\163\1\147\1\160\2\151\2\162\2\155\2\164"
            + "\1\167\1\157\1\143\1\164\1\156\2\145\1\172\1\150\1\151\4\172\2\164"
            + "\1\uffff\1\145\1\163\1\uffff\1\172\3\uffff\2\145\1\144\1\145\1\uffff"
            + "\2\162\1\123\1\172\1\163\1\172\1\165\1\uffff\1\172\1\uffff\1\155" + "\1\uffff\1\172\1\uffff";
    static final String DFA13_acceptS = "\2\uffff\1\2\2\uffff\1\5\1\uffff\1\10\1\11\1\12\3\uffff\1\17\2\uffff"
            + "\1\22\1\23\2\uffff\1\26\1\27\1\30\3\uffff\1\32\2\uffff\1\37\1\40"
            + "\1\uffff\1\32\1\2\1\13\1\3\1\uffff\1\5\1\uffff\1\10\1\11\1\12\3"
            + "\uffff\1\17\2\uffff\1\22\1\23\1\uffff\1\35\1\36\1\25\1\26\1\27\1"
            + "\30\1\31\1\33\1\uffff\1\34\1\37\40\uffff\1\16\2\uffff\1\24\1\uffff"
            + "\1\7\1\4\1\6\4\uffff\1\1\7\uffff\1\21\1\uffff\1\15\1\uffff\1\14" + "\1\uffff\1\20";
    static final String DFA13_specialS = "\1\2\32\uffff\1\0\1\1\134\uffff}>";
    static final String[] DFA13_transitionS = {
            "\11\36\2\35\2\36\1\35\22\36\1\35\1\36\1\33\4\36\1\34\1\7\1\11"
                    + "\2\36\1\10\1\27\1\26\1\23\12\30\1\36\1\5\1\24\1\15\1\25\2\36"
                    + "\2\32\1\14\11\32\1\1\2\32\1\13\1\32\1\22\1\17\3\32\1\16\3\32"
                    + "\1\20\1\36\1\21\1\31\1\32\1\36\4\32\1\6\3\32\1\4\6\32\1\12\12" + "\32\1\2\1\36\1\3\uff82\36",
            "\1\37", "", "\1\42", "\1\44", "", "\1\46", "", "", "", "\1\52", "\1\53", "\1\54", "", "\1\56", "\1\57", "",
            "", "\1\62", "\1\63\4\uffff\1\64", "", "", "", "\12\71", "\1\71\1\uffff\12\73",
            "\32\40\4\uffff\1\40\1\uffff\32\40", "", "\0\74", "\0\74", "", "", "\1\76", "", "", "", "", "\1\77", "",
            "\1\100", "", "", "", "\1\101", "\1\102", "\1\103", "", "\1\104", "\1\105", "", "", "\1\106", "", "", "",
            "", "", "", "", "", "\1\71\1\uffff\12\73", "", "", "\1\107", "\1\110", "\1\111", "\1\112", "\1\113",
            "\1\114", "\1\115", "\1\116", "\1\117", "\1\120", "\1\121", "\1\122", "\1\123", "\1\124", "\1\125",
            "\1\126", "\1\127", "\1\130", "\1\131", "\1\132", "\1\133", "\1\134", "\1\135",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40", "\1\137", "\1\140",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\22\40\1\142\7\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40", "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\146", "\1\147", "", "\1\150", "\1\151", "", "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40", "", "",
            "", "\1\153", "\1\154", "\1\155", "\1\156", "", "\1\157", "\1\160", "\1\161",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40", "\1\163",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40", "\1\165", "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40", "", "\1\167", "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40", "" };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        final int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(final BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }

        @Override
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | RULE_DOUBLE | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }

        @Override
        public int specialStateTransition(int s, final IntStream _input) throws NoViableAltException {
            final IntStream input = _input;
            final int _s = s;
            switch (s) {
            case 0:
                final int LA13_27 = input.LA(1);

                s = -1;
                if (((LA13_27 >= '\u0000' && LA13_27 <= '\uFFFF'))) {
                    s = 60;
                } else {
                    s = 30;
                }

                if (s >= 0) {
                    return s;
                }
                break;
            case 1:
                final int LA13_28 = input.LA(1);

                s = -1;
                if (((LA13_28 >= '\u0000' && LA13_28 <= '\uFFFF'))) {
                    s = 60;
                } else {
                    s = 30;
                }

                if (s >= 0) {
                    return s;
                }
                break;
            case 2:
                final int LA13_0 = input.LA(1);

                s = -1;
                if ((LA13_0 == 'M')) {
                    s = 1;
                }

                else if ((LA13_0 == '{')) {
                    s = 2;
                }

                else if ((LA13_0 == '}')) {
                    s = 3;
                }

                else if ((LA13_0 == 'i')) {
                    s = 4;
                }

                else if ((LA13_0 == ';')) {
                    s = 5;
                }

                else if ((LA13_0 == 'e')) {
                    s = 6;
                }

                else if ((LA13_0 == '(')) {
                    s = 7;
                }

                else if ((LA13_0 == ',')) {
                    s = 8;
                }

                else if ((LA13_0 == ')')) {
                    s = 9;
                }

                else if ((LA13_0 == 'p')) {
                    s = 10;
                }

                else if ((LA13_0 == 'P')) {
                    s = 11;
                }

                else if ((LA13_0 == 'C')) {
                    s = 12;
                }

                else if ((LA13_0 == '=')) {
                    s = 13;
                }

                else if ((LA13_0 == 'W')) {
                    s = 14;
                }

                else if ((LA13_0 == 'S')) {
                    s = 15;
                }

                else if ((LA13_0 == '[')) {
                    s = 16;
                }

                else if ((LA13_0 == ']')) {
                    s = 17;
                }

                else if ((LA13_0 == 'R')) {
                    s = 18;
                }

                else if ((LA13_0 == '/')) {
                    s = 19;
                }

                else if ((LA13_0 == '<')) {
                    s = 20;
                }

                else if ((LA13_0 == '>')) {
                    s = 21;
                }

                else if ((LA13_0 == '.')) {
                    s = 22;
                }

                else if ((LA13_0 == '-')) {
                    s = 23;
                }

                else if (((LA13_0 >= '0' && LA13_0 <= '9'))) {
                    s = 24;
                }

                else if ((LA13_0 == '^')) {
                    s = 25;
                }

                else if (((LA13_0 >= 'A' && LA13_0 <= 'B') || (LA13_0 >= 'D' && LA13_0 <= 'L')
                        || (LA13_0 >= 'N' && LA13_0 <= 'O') || LA13_0 == 'Q' || (LA13_0 >= 'T' && LA13_0 <= 'V')
                        || (LA13_0 >= 'X' && LA13_0 <= 'Z') || LA13_0 == '_' || (LA13_0 >= 'a' && LA13_0 <= 'd')
                        || (LA13_0 >= 'f' && LA13_0 <= 'h') || (LA13_0 >= 'j' && LA13_0 <= 'o')
                        || (LA13_0 >= 'q' && LA13_0 <= 'z'))) {
                    s = 26;
                }

                else if ((LA13_0 == '\"')) {
                    s = 27;
                }

                else if ((LA13_0 == '\'')) {
                    s = 28;
                }

                else if (((LA13_0 >= '\t' && LA13_0 <= '\n') || LA13_0 == '\r' || LA13_0 == ' ')) {
                    s = 29;
                }

                else if (((LA13_0 >= '\u0000' && LA13_0 <= '\b') || (LA13_0 >= '\u000B' && LA13_0 <= '\f')
                        || (LA13_0 >= '\u000E' && LA13_0 <= '\u001F') || LA13_0 == '!'
                        || (LA13_0 >= '#' && LA13_0 <= '&') || (LA13_0 >= '*' && LA13_0 <= '+') || LA13_0 == ':'
                        || (LA13_0 >= '?' && LA13_0 <= '@') || LA13_0 == '\\' || LA13_0 == '`' || LA13_0 == '|'
                        || (LA13_0 >= '~' && LA13_0 <= '\uFFFF'))) {
                    s = 30;
                }

                if (s >= 0) {
                    return s;
                }
                break;
            }
            final NoViableAltException nvae = new NoViableAltException(this.getDescription(), 13, _s, input);
            this.error(nvae);
            throw nvae;
        }
    }

}