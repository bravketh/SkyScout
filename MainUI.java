import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public MainUI() {
        setTitle("SkyScout - Login Portal");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(15, 15, 15, 15);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel logo = new JLabel("✈ SkyScout Flight Booker ✈");
        logo.setFont(new Font("SansSerif", Font.BOLD, 22));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(logo, c);

        c.gridwidth = 1;
        c.gridy++;
        c.gridx = 0;
        add(new JLabel("Username:"), c);
        c.gridx = 1;
        usernameField = new JTextField(20);
        add(usernameField, c);

        c.gridy++;
        c.gridx = 0;
        add(new JLabel("Password:"), c);
        c.gridx = 1;
        passwordField = new JPasswordField(20);
        add(passwordField, c);

        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login & Search Flights");
        add(loginButton, c);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                new User(username, password, 4);
                dispose();
                SwingUtilities.invokeLater(() -> new FlightSearchUI());
            } else {
                JOptionPane.showMessageDialog(this, "Both fields are required.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainUI::new);
    }
}