import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
// I referenced NumberServer.java to understand how to create a webserver
class Handler implements URLHandler {

    String strs = "";

    int numberOfStrings = 0;

    ArrayList<String> listOfStrings = new ArrayList<String>();

    ArrayList<String> searchedStrings = new ArrayList<String>();

    StringBuilder addedStrings = new StringBuilder();

    int stringCount = 1;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Number of Strings: %d", listOfStrings.size());
            } 
        else if (url.getPath().equals("/list")) {
            return String.format("List of strings: " + listOfStrings.toString());
        }
        else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for (String s: listOfStrings) {
                    if( s.contains(parameters[1]) ) {
                        searchedStrings.add(s);
                    }
                }
                return String.format(searchedStrings.toString());
            }
        }
        else { 
            if (url.getPath().contains("/add-messages")) { // add-messages?s=<string>
                String[] parameters = url.getQuery().split("="); // s=<string>
                    if (parameters[0].equals("s")); { // <string>
                        listOfStrings.add(parameters[1]);
                        addedStrings.append(stringCount).append(". ").append(parameters[1]).append("\n");
                        stringCount++;
                        return addedStrings.toString();
                    }
                } 
            }
            return "404 Not Found!";
        }
    }

class StringServer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        
        Server.start(port, new Handler());
    }
}
