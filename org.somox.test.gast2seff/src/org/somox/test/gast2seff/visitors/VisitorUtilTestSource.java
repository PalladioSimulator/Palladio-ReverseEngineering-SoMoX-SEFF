package org.somox.test.gast2seff.visitors;

public class VisitorUtilTestSource {

    private class CommandLineParser {

        public CommandLineParser addCategory(final String string) {
            // TODO Auto-generated method stub
            return null;
        }

        public CommandLineParser addOption(final Option option) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    private class OptionBuilder {

        public OptionBuilder(final String string) {
            // TODO Auto-generated constructor stub
        }

        public OptionBuilder arg(final String string) {
            // TODO Auto-generated method stub
            return null;
        }

        public OptionBuilder description(final String string) {
            // TODO Auto-generated method stub
            return null;
        }

        public Option create() {
            // TODO Auto-generated method stub
            return null;
        }

        public OptionBuilder deprecated() {
            // TODO Auto-generated method stub
            return null;
        }

        public OptionBuilder countArgs(final boolean b) {
            // TODO Auto-generated method stub
            return null;
        }

    }

    private class Option {

    }

    private StringBuilder string;
    private String string2;

    public void visitorUtilTestMethodWithOneMethodCallAppend() {
        this.string.append("test");
    }

    public void visitorUtilTestMethodWithTwoMethodCallsAppendToString() {
        this.string.append(1).toString();
    }

    public void visitorUtilTestMethodWithTwoMethodCallsReverseAppend() {
        this.string.append(this.string.reverse());
    }

    public void visitorUtilTestMethodWithThreeMethodCallsReverseAppendToString() {
        this.string.append(this.string.reverse()).toString();
    }

    public void visitorUtilTestMethodWithFourMethodCallsReverseAppendgetIntAppendCodePoint() {
        this.string.append(this.string.reverse()).appendCodePoint(this.getInt());
    }

    public void visitorUtilTestMethodWithManyMethodCalls() {
        new CommandLineParser().addCategory("settings options")
                .addOption(new OptionBuilder("settings").arg("settingsfile").description("use given file for settings")
                        .create())
                .addOption(new OptionBuilder("cache").arg("cachedir").description("use given directory for cache")
                        .create())
                .addOption(
                        new OptionBuilder("novalidate").description("do not validate ivy files against xsd").create())
                .addOption(new OptionBuilder("m2compatible").description("use maven2 compatibility").create())
                .addOption(
                        new OptionBuilder("conf").arg("settingsfile").deprecated()
                                .description("use given file for settings").create())
                .addOption(new OptionBuilder("useOrigin").deprecated().description(
                        "use original artifact location " + "with local resolvers instead of copying to thecache")
                        .create())

                .addCategory("resolve options")
                .addOption(new OptionBuilder("ivy").arg("ivyfile").description("use given file as ivyfile").create())
                .addOption(new OptionBuilder("refresh").description("refresh dynamic resolvedrevisions").create())
                .addOption(new OptionBuilder("dependency").arg("organisation").arg("module").arg("revision")
                        .description("use this instead of ivy file to do the rest "
                                + "of the work with this as a dependency.")
                        .create())
                .addOption(new OptionBuilder("confs").arg("configurations").countArgs(false)
                        .description("resolve given configurations").create())
                .addOption(new OptionBuilder("types").arg("types").countArgs(false)
                        .description("comma separated list of accepted artifact types").create())
                .addOption(new OptionBuilder("mode").arg("resolvemode").description("the resolve mode touse").create())
                .addOption(new OptionBuilder("notransitive").description("do not resolve dependencies transitively")
                        .create())

                .addCategory("retrieve options")
                .addOption(new OptionBuilder("retrieve").arg("retrievepattern")
                        .description("use given pattern as retrieve pattern").create())
                .addOption(new OptionBuilder("ivypattern").arg("pattern")
                        .description("use given pattern to copy the ivy files").create())
                .addOption(new OptionBuilder("sync").description("use sync mode for retrieve").create())
                .addOption(new OptionBuilder("symlink").description("create symbolic links").create())

                .addCategory("cache path options")
                .addOption(new OptionBuilder("cachepath").arg("cachepathfile")
                        .description("outputs a classpath consisting of all dependencies in cache "
                                + "(including transitive ones) " + "of the given ivy file to the given cachepathfile")
                        .create())

                .addCategory("deliver options").addOption(new OptionBuilder("deliverto").arg("ivypattern")
                        .description("use given pattern as resolved ivy file pattern").create())

                .addCategory("publish options")
                .addOption(new OptionBuilder("publish").arg("resolvername")
                        .description("use given resolver to publish to").create())
                .addOption(new OptionBuilder("publishpattern").arg("artpattern")
                        .description("use given pattern to find artifacts to publish").create())
                .addOption(new OptionBuilder("revision").arg("revision")
                        .description("use given revision to publish the module").create())
                .addOption(new OptionBuilder("status").arg("status")
                        .description("use given status to publish the module").create())
                .addOption(new OptionBuilder("overwrite").description("overwrite files in the repository if they exist")
                        .create())

                .addCategory("http auth options")
                .addOption(new OptionBuilder("realm").arg("realm").description("use given realm for HTTPAUTH").create())
                .addOption(new OptionBuilder("host").arg("host").description("use given host for HTTPAUTH").create())
                .addOption(new OptionBuilder("username").arg("username").description("use given usernamefor HTTP AUTH")
                        .create())
                .addOption(new OptionBuilder("passwd").arg("passwd").description("use given password for HTTP AUTH")
                        .create())

                .addCategory("launcher options")
                .addOption(new OptionBuilder("main").arg("main").description("the FQCN of the main class to launch")
                        .create())
                .addOption(new OptionBuilder("args").arg("args").countArgs(false)
                        .description("the arguments to give to the launched process").create())
                .addOption(new OptionBuilder("cp").arg("cp")
                        .description("extra classpath to use when launching process").create())

                .addCategory("message options")
                .addOption(new OptionBuilder("debug").description("set message level to debug").create())
                .addOption(new OptionBuilder("verbose").description("set message level to verbose").create())
                .addOption(new OptionBuilder("warn").description("set message level to warn").create())
                .addOption(new OptionBuilder("error").description("set message level to error").create())

                .addCategory("help options").addOption(new OptionBuilder("?").description("display this help").create())
                .addOption(new OptionBuilder("deprecated").description("show deprecated options").create())
                .addOption(new OptionBuilder("version").description("displays version information").create());
    }

    private int getInt() {
        // TODO Auto-generated method stub
        return 0;
    }

}
