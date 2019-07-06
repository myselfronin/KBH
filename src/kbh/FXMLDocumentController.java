/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbh;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Rabinson
 */
public class FXMLDocumentController implements Initializable {
    
  
    
    
    //LABELS
    @FXML
    private Label label;
    
    @FXML
    private Label loginLabel;
    
    //Anchor pane
    @FXML
    private AnchorPane bck;
    
    
    //BUTTONS
    @FXML
    private JFXButton exitBtn;
    @FXML
    private JFXButton loginExitBtn;
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXButton playBtn;
    
    //TEXT FIELDS
    @FXML
    private JFXTextField usernameTextField;
    @FXML
    private JFXPasswordField passwordField;
    
    //PANES
    @FXML
    private Pane loginPane;
    
    //FOR JDBC 
    private Connection Con;
    private PreparedStatement PreStmt;
    
    
    //button functions
    public void loginBtnPushed() throws ClassNotFoundException{
        loginPane.toFront();
        
    }
    
    public void loginExtBtnPushed() throws ClassNotFoundException{
        loginPane.toBack();
    }
    
    public void ExtBtnPushed() throws ClassNotFoundException{
        System.exit(0);
    }
    
    public void loginNxtBtnPushed() throws NullPointerException, SQLException, ClassNotFoundException, IOException{
            String sql = "SELECT * FROM login_info where Username = ? AND Password = ?;";
           
            PreStmt = Con.prepareStatement(sql);
            String user = usernameTextField.getText();
            String pass = passwordField.getText();
            PreStmt.setString(1, user);
            PreStmt.setString(2, pass);
            
            ResultSet rs = PreStmt.executeQuery();
            if (rs.next()) {
                //System.out.println("hello");
                usernameTextField.setText("");
                passwordField.setText("");
                loginPane.toBack();
                rs.close();
                Parent root = (Parent) FXMLLoader.load(getClass().getResource("/kbh/QueSet.fxml"));
               
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
               

            } else {
                loginLabel.setVisible(true);
                usernameTextField.setText("");
                passwordField.setText("");
               // managerStatus.setVisible(true);
            }
            

    }
    
    public void playBtnPushed() throws IOException, SQLException{
        
        Stage stage1 = (Stage) playBtn.getScene().getWindow();
        stage1.close();
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/kbh/GameBoard.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
         SqliteConnect connect = new SqliteConnect();
         Con = connect.Connector();
        loginLabel.setVisible(false);
         
    }    
    
}
