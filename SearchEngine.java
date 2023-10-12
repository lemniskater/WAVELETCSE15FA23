import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {

    String strs = "";

    int numberOfStrings = 0;

    ArrayList<String> listOfStrings = new ArrayList<String>();

    ArrayList<String> searchedStrings = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Number of Strings: %d", listOfStrings.size());
            } 
        else if (url.getPath().equals("/list")) {
            return String.format("List of strings: " + listOfStrings.toString());
        }
        else if (url.getPath().equals("/search")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                for (String s: listOfStrings) {
                    if( s.contains(parameters[1]) ) {
                        searchedStrings.add(s);
                        System.out.println(searchedStrings.toString());
                    }
                return String.format("String not found!");
                }
            }
        }
        else { 
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                    if (parameters[0].equals("s")); {
                        listOfStrings.add(parameters[1]);
                        return String.format("List of strings increased, it is now: " + listOfStrings.size());
                    }
                } 
            }
            return "404 Not Found!";
        }
    }

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        
        Server.start(port, new Handler());
    }
}
