import java.io.IOException;
import java.net.URI;
import java.java.util.ArrayList;

class StringServer implements URLHandler {

    String strs = "";

    int numberOfStrings = 0;

    ArrayList<String> listOfStrings = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Number of Strings: %d", numberOfStrings);
            } 
        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if ((parameters[0]).equals("s")); {
                listOfStrings.add(parameters[1]);
                return String.format("List of strings increased, it is now: " + listOfStrings.size());
            }
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        
        Server.start(port, new StringServer());
    }
}