import javax.swing.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class PaymentPage extends JFrame {
    public PaymentPage(double amount) {
        setTitle("Payment Page");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        CaesarCipher cipher = new CaesarCipherImpl();

        JLabel label = new JLabel("Total: $" + amount);
        label.setBounds(30, 20, 300, 25);
        add(label);

        JTextField cardNumber = new JTextField("Card Number");
        cardNumber.setBounds(30, 60, 320, 25);
        add(cardNumber);

        JTextField nameField = new JTextField("Cardholder Name");
        nameField.setBounds(30, 100, 320, 25);
        add(nameField);

        JTextField expiry = new JTextField("MMYY");
        expiry.setBounds(30, 140, 150, 25);
        add(expiry);

        JTextField cvv = new JTextField("CVV");
        cvv.setBounds(200, 140, 150, 25);
        add(cvv);

        JButton pay = new JButton("Pay Now");
        pay.setBounds(130, 190, 120, 30);
        add(pay);

        pay.addActionListener(e -> {
            String card = cardNumber.getText().replaceAll("\\s", "");
            if (!card.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(this, "Invalid card number.");
            } else {
                JOptionPane.showMessageDialog(this, "Payment accepted.\nEncrypted Card: " + cipher.encrypt(card, 4));
                dispose();
            }
        });
        
        cardNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (cardNumber.getText().equals("Card Number")){
                    cardNumber.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (cardNumber.getText().equals("")){
                    cardNumber.setText("Card Number");
                }
            }
        });
        
        nameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (nameField.getText().equals("Cardholder Name")){
                    nameField.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()){
                    nameField.setText("Cardholder Name");
                }
            }
        });
        
        expiry.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (expiry.getText().equals("MMYY")){
                    expiry.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (expiry.getText().isEmpty()){
                    expiry.setText("MMYY");
                }
            }
        });
        
        cvv.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (cvv.getText().equals("CVV")){
                    cvv.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (cvv.getText().isEmpty()){
                    cvv.setText("CVV");
                }
            }
        });

        setVisible(true);
    }
}