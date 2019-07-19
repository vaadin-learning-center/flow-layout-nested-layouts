package demo;


import org.apache.commons.cli.*;
import org.stagemonitor.core.Stagemonitor;

import static demo.CoreUIService.CORE_UI_SERVER_HOST;
import static demo.CoreUIService.CORE_UI_SERVER_PORT;

/**
 * see https://github.com/Nano-Vaadin-Demos
 */
public class HelloWorld {
  private HelloWorld() {
  }

  public static final String CLI_HOST = "host";
  public static final String CLI_PORT = "port";

  public static void main(String[] args) throws ParseException {

    Options options = new Options();
    options.addOption(CLI_HOST, true, "host to use");
    options.addOption(CLI_PORT, true, "port to use");

    CommandLineParser parser = new DefaultParser();
    CommandLine       cmd    = parser.parse(options, args);

    if( cmd.hasOption( CLI_HOST ) ) {
      System.setProperty(CORE_UI_SERVER_HOST, cmd.getOptionValue( CLI_HOST ));
    }
    if( cmd.hasOption( CLI_PORT ) ) {
      System.setProperty(CORE_UI_SERVER_PORT, cmd.getOptionValue( CLI_PORT));
    }

    Stagemonitor.init();

    new CoreUIService().startup();
  }
}
