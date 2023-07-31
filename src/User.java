import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class User{

    private final String login;
    private final String email;
    private final String password;

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
        if(emailProper()&& passwordProper() && loginProper()) {
            try {
                FileWriter writer = new FileWriter("Data.txt",true);
                String data = login + " " + password + " " + email+"\n";
                writer.write(data);
                writer.close();
                JOptionPane.showMessageDialog(null,"Registered");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }



    }

    private boolean emailProper(){
        if(email.contains("@") && !email.contains(" ")) return true;
        JOptionPane.showMessageDialog(null,"Wrong Email");
        return false;
    }
    private boolean passwordProper(){
        if(password.contains(" ")) {
            JOptionPane.showMessageDialog(null,"Wrong password");
            return false;
        }
        return true;
    }
    private boolean loginProper(){
        if(login.contains(" ")){
            JOptionPane.showMessageDialog(null,"Wrong login");
            return false;
        }
        return true;
    }
    public static boolean checkIfUserExists(String login, String password){
        try {
            BufferedReader br = new BufferedReader((new FileReader("Data.txt")));
            String line;
            while((line=br.readLine())!=null){
                String p = line.split(" ")[1];
                String l = line.split(" ")[0];
                if(l.equals(login) && p.equals(password)){
                    return true;
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }

    public static boolean checkIfUserExists(String login){
        try {
            BufferedReader br = new BufferedReader((new FileReader("Data.txt")));
            String line;
            while((line=br.readLine())!=null){
                String l = line.split(" ")[0];
                if(l.equals(login)){
                    return true;
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }
}
