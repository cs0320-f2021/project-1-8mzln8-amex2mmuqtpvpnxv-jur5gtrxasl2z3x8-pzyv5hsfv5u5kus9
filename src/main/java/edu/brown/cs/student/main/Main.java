package edu.brown.cs.student.main;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import edu.brown.cs.student.client.ApiClient;
import freemarker.template.Configuration;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {
  private Galaxy galaxy;

  // use port 4567 by default when running server
  private static final int DEFAULT_PORT = 4567;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  @SuppressWarnings("checkstyle:TodoComment")
  private void run() {
    // set up parsing of command line flags
    OptionParser parser = new OptionParser();
    ApiClient client = new ApiClient();
    // "./run --gui" will start a web server
    parser.accepts("gui");

    // use "--port <n>" to specify what port on which the server runs
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
            .defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);
    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = input.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)");
          // https://stackabuse.com/regex-splitting-by-character-unless-in-quotes/
          if(arguments[0].equals("test")) {
            ApiAggregator api = new ApiAggregator();
            List<Object> list = api.getData(arguments[1]);
            for (Object o : list) {
              System.out.print(o.toString());
            }
          }else if  (arguments[0].equals("json")){
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(arguments[2]));
            ApiAggregator api = new ApiAggregator();
            Type type = api.setType(arguments[1]);
            List<Rent> list = gson.fromJson(reader, type);
            reader.close();
          }

          else if (arguments[0].equals("stars")) {
            StarsCommands StarOperations = new StarsCommands();
            StarOperations.createGalaxy(arguments[1]);

          } else if (arguments[0].equals("naive_neighbors")) {
            NaiveNeighborsCommands NNOperator = new NaiveNeighborsCommands();

            try {
              ArrayList<Integer> nearestKNeighbors;
              if (arguments[1].equals("0")) {
                System.out.println("Read "
                        + this.galaxy.getSize() +
                        " stars from "
                        + this.galaxy.getStarDataFile());
              }

              else {
                if (arguments.length > 3) {
                  NNOperator.NN_Coord(arguments[1], arguments[2], arguments[3], arguments[4],this.galaxy );
                } else {
                  NNOperator.NN_Star(arguments[1],arguments[2],this.galaxy);
                }
              }
            } catch (Exception e) {
              System.out.println("ERROR: Incorrect arguments");
            }
          } else {
            System.out.println("Read " + this.galaxy.getSize() + " stars from "
                    + this.galaxy.getStarDataFile());
            System.out.println("ERROR: Command does not exist");
          }


          if (arguments[0].equals("add")) {
            MathBotCommands MathBotOperator = new MathBotCommands();
            MathBotOperator.sum(arguments[1],arguments[2]);
          }
          if (arguments[0].equals("subtract")) {
            MathBotCommands MathBotOperator = new MathBotCommands();
            MathBotOperator.subtract(arguments[1],arguments[2]);
          }

          //TODO:Implement API Command
          if (arguments[0].equals("users")) {
            // TODO: Basic GET request
            if (arguments[0].equals("basicGet")) {
            }
            //TODO: API online implementation
            if (arguments[1].equals("online")) {
            } else {
              //TODO: URL Implementation
            }
          }

        } catch (Exception e) {
          // e.printStackTrace();
          System.out.println("ERROR: We couldn't process your input");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ERROR: Invalid input for REPL");
    }
  }

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration(Configuration.VERSION_2_3_0);

    // this is the directory where FreeMarker templates are placed
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n",
              templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  private void runSparkServer(int port) {
    // set port to run the server on
    Spark.port(port);

    // specify location of static resources (HTML, CSS, JS, images, etc.)
    Spark.externalStaticFileLocation("src/main/resources/static");

    // when there's a server error, use ExceptionPrinter to display error on GUI
    Spark.exception(Exception.class, new ExceptionPrinter());

    // initialize FreeMarker template engine (converts .ftl templates to HTML)
    FreeMarkerEngine freeMarker = createEngine();

    // setup Spark Routes
    Spark.get("/", new MainHandler(), freeMarker);
  }

  /**
   * Display an error page when an exception occurs in the server.
   */
  private static class ExceptionPrinter implements ExceptionHandler<Exception> {
    @Override
    public void handle(Exception e, Request req, Response res) {
      // status 500 generally means there was an internal server error
      res.status(500);

      // write stack trace to GUI
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }

  /**
   * A handler to serve the site's main page.
   *
   * @return ModelAndView to render.
   * (main.ftl).
   */
  private static class MainHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      // this is a map of variables that are used in the FreeMarker template
      Map<String, Object> variables = ImmutableMap.of("title",
              "Go go GUI");

      return new ModelAndView(variables, "main.ftl");
    }
  }
}
