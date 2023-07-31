import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MainWindow extends JFrame {

    private JPasswordField passwordField;
    private final Font font = new Font("Arial", Font.BOLD,22);
    private JTextField loginField,emailField;
    private JLabel loginLabel,passwordLabel,emailLabel;
    private JButton loginButton;
    private JButton registrationButton;
    private final int fieldWidth = 250;

    public MainWindow(){
        initialize();
    }
    private void initialize(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Login System");
        this.setSize(800,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        makePasswordFields();
        makeLoginFields();
        makeLoginButton();
        makeRegistrationButton();
        this.getRootPane().setDefaultButton(loginButton);

        this.setVisible(true);
    }

    private void makePasswordFields(){

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(180,250,200,40);
        passwordLabel.setFont(font);
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(font);
        passwordField.setBounds(300,250,fieldWidth,40);
        this.add(passwordField);
    }

    private void makeLoginFields(){

        loginLabel = new JLabel("Login:");
        loginLabel.setBounds(180,200,200,40);
        loginLabel.setFont(font);
        this.add(loginLabel);

        loginField = new JTextField();
        loginField.setFont(font);
        loginField.setBounds(300,200,fieldWidth,40);
        this.add(loginField);
    }
    private void makeLoginButton(){
        loginButton = new JButton("Login");
        loginButton.setFont(font);
        loginButton.setFocusable(false);
        loginButton.setBounds(400,295,100,30);

        loginButton.addActionListener(e->{
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            if(User.checkIfUserExists(login,password)){
                JOptionPane.showMessageDialog(null,"Logged");
            }
            else{
                JOptionPane.showMessageDialog(null,"Wrong login or password.");
                makeLoginFields();
            }
            loginField.setText("");
            passwordField.setText("");
        });


        this.add(loginButton);
    }
    private void makeRegistrationButton(){
        registrationButton = new JButton("Register");
        registrationButton.setFont(font);
        registrationButton.setFocusable(false);
        registrationButton.setBounds(300,330,200,30);
        registrationButton.addActionListener(e-> makeRegistrationWindow());

        this.add(registrationButton);
    }
    private void makeEmailFields(){

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(180,150,200,40);
        emailLabel.setFont(font);
        this.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(font);
        emailField.setBounds(300,150,fieldWidth,40);
        this.add(emailField);
    }

    private void makeRegistrationWindow(){
        this.remove(loginButton);
        this.getRootPane().setDefaultButton(registrationButton);
        JLabel regisLabel =new JLabel("Registration menu");
        regisLabel.setBounds(180,100,200,40);
        regisLabel.setFont(font);
        this.add(regisLabel);

        makeEmailFields();

        this.repaint();
        this.revalidate();
        registrationButton.removeActionListener(registrationButton.getActionListeners()[0]);

        registrationButton.addActionListener(l->{
            String emailInput = emailField.getText();
            createNewUser(emailInput);

            this.add(loginButton);
            this.getRootPane().setDefaultButton(loginButton);

            this.remove(regisLabel);
            this.remove(emailField);
            this.remove(emailLabel);

            this.revalidate();
            this.repaint();

        });
        registrationButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    String emailInput = emailField.getText();
                    createNewUser(emailInput);

                }
                JOptionPane.showMessageDialog(null,"Registered");
            }
        });


    }
    private void createNewUser(String emailInput){
        String loginInput = loginField.getText();
        String passwordInput = passwordField.getText();
        if(User.checkIfUserExists(loginInput)){
            JOptionPane.showMessageDialog(null,"User already exists");

        }else {
            new User(loginInput, emailInput, passwordInput);
            registrationButton.removeActionListener(registrationButton.getActionListeners()[0]);
            registrationButton.addActionListener(e -> makeRegistrationWindow());
        }

        makeRegistrationButton();
        this.add(loginButton);
        this.getRootPane().setDefaultButton(loginButton);

        this.remove(emailField);
        this.remove(emailLabel);

        loginField.setText("");
        passwordField.setText(" ");

        this.revalidate();
        this.repaint();
    }



}
