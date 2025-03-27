import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        // Read input.json file
        String jsonContent = new String(Files.readAllBytes(Paths.get("input.json")));

        // Extract values using basic string operations (no JAR needed)
        String firstName = extractValue(jsonContent, "first_name").toLowerCase();
        String rollNumber = extractValue(jsonContent, "roll_number").toLowerCase();

        // Concatenate and compute MD5 hash
        String md5Hash = getMD5Hash(firstName + rollNumber);

        // Write the result to output.txt
        Files.write(Paths.get("output.txt"), md5Hash.getBytes());

        System.out.println("MD5 Hash: " + md5Hash);
    }

    // Method to extract values manually
    private static String extractValue(String json, String key) {
        int startIndex = json.indexOf("\"" + key + "\"") + key.length() + 3;
        int endIndex = json.indexOf("\"", startIndex);
        return json.substring(startIndex, endIndex);
    }

    // Method to generate MD5 hash
    public static String getMD5Hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return String.format("%032x", number);
    }
}
