
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;

public class WeatherApp {
    public static void main(String[] args) throws IOException {

        // Create the main window (JFrame)
        JFrame frame = new JFrame("Weather App");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Label asking the user to enter a city name
        JLabel label = new JLabel("Please enter the city name:");
        label.setBounds(30, 30, 150, 25);
        frame.add(label);

        // Input to enter the city name
        JTextField textField = new JTextField();
        textField.setBounds(150, 30, 180, 25);
        frame.add(textField);

        // Submit button
        JButton button = new JButton("SUBMIT");
        button.setBounds(150, 70, 100, 30);
        frame.add(button);

        // Adding action of the button
        button.addActionListener(e-> {
                String city = textField.getText().trim().replace(" ", "+");

            try {
                // Getting the url of the weather website
                URL url = new URL("https://wttr.in/" + city + "?format=3");
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // Reading the results from the url
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String results = reader.readLine();
                reader.close();
                JOptionPane.showMessageDialog(frame, results);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error fetching weather: " + ex.getMessage());
            }
        });
        frame.setVisible(true);
    }
}