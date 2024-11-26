import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIRegex {
    public static void main(String[] args) {
        try {
            // 1. Make a GET Request to the API
            String apiUrl = "https://randomuser.me/api/?results=5"; // API link from assignment
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            
            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonResponse = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
            reader.close();

            // 2. Extract Emails and Websites using Regex
            String response = jsonResponse.toString();

            // Regex for extracting emails
            String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
            Pattern emailPattern = Pattern.compile(emailRegex);
            Matcher emailMatcher = emailPattern.matcher(response);

            // Regex for extracting URLs
            String urlRegex = "https?://[\\w./%-]+";
            Pattern urlPattern = Pattern.compile(urlRegex);
            Matcher urlMatcher = urlPattern.matcher(response);

            // Print Emails
            System.out.println("Extracted Emails:");
            while (emailMatcher.find()) {
                System.out.println(emailMatcher.group());
            }

            // Print Websites (URLs)
            System.out.println("\nExtracted Websites:");
            while (urlMatcher.find()) {
                System.out.println(urlMatcher.group());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
