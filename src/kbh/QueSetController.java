/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbh;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Rabinson
 */
public class QueSetController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
       // for JDBC
   
    Connection Con ;
    PreparedStatement PreStmt;
    
    @FXML
    private JFXTextField alterAnsTextField;

    @FXML
    private JFXTextField opt1TextField;

    @FXML
    private JFXTextField opt2TextField;

    @FXML
    private JFXTextField AnsTextField;

    @FXML
    private JFXTextArea QTextArea;

    @FXML
    private JFXTextField setNoTextField;

    @FXML
    private JFXTextField QNoTextField;

    @FXML
    private Label failLabel;
    
    @FXML
    private Label entryAlertLabel;
    
    @FXML
    private Label DbUpdatedLabel;

   public boolean inputQuery(String a,String b) throws SQLException{
       String sql = "SELECT * FROM QueSet where QNo = ? AND SetNo = ?";
       PreStmt = Con.prepareStatement(sql);
       PreStmt.setString(1, a);
       PreStmt.setString(2, b);
       ResultSet rs = PreStmt.executeQuery();
       if(rs.next()){
           rs.close();
           return true;       
           
       }
       else{
          rs.close();
           return false;
       }
  
       
   }

    @FXML
    void ResetDbPushed() throws SQLException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Reset the Database?!!!");
        alert.setHeaderText("It will reset all the played Questions");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String sql = "UPDATE QueSet set Played = 0";
            PreStmt = Con.prepareStatement(sql);
            PreStmt.executeUpdate();
            DbUpdatedLabel.setVisible(true);
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }
    @FXML
    void okBtnPushed() throws SQLException {
       // entryAlertLabel.setVisible(false);
               
        if(QNoTextField.getText().trim().isEmpty()||setNoTextField.getText().trim().isEmpty()
                ||QTextArea.getText().trim().isEmpty()||AnsTextField.getText().trim().isEmpty()
                ||AnsTextField.getText().trim().isEmpty()||alterAnsTextField.getText().trim().isEmpty()
                ||opt1TextField.getText().trim().isEmpty()||opt2TextField.getText().trim().isEmpty())
        {
            entryAlertLabel.setVisible(true);
        }

        else{
            if(inputQuery(QNoTextField.getText(),setNoTextField.getText())== true){
                failLabel.setVisible(true);
            }
            else{
                String sql = "Insert INTO QueSet(QNo, SetNo, Question, Answer, AltAns, Opt1, Opt2) VALUES(?,?,?,?,?,?,?)";
                PreStmt = Con.prepareStatement(sql);
                PreStmt.setString(1, QNoTextField.getText());
                PreStmt.setString(2, setNoTextField.getText());
                PreStmt.setString(3, QTextArea.getText());
                PreStmt.setString(4, AnsTextField.getText());
                PreStmt.setString(5, alterAnsTextField.getText());
                PreStmt.setString(6, opt1TextField.getText());
                PreStmt.setString(7, opt2TextField.getText());

                int executeUpdate = PreStmt.executeUpdate();

                if (executeUpdate == 1) {
                    alterAnsTextField.setText("");
                    setNoTextField.setText("");
                    QNoTextField.setText("");
                    QTextArea.setText("");
                    AnsTextField.setText("");
                    opt1TextField.setText("");
                    opt2TextField.setText("");
                    entryAlertLabel.setVisible(false);
                    failLabel.setVisible(false);
                }
            PreStmt.close();
           }
        }
    }
        
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Font myFontloadFontAirstreamNF20 = 
            Font.loadFont(getClass()
                .getResourceAsStream("/fonts/AirstreamNF.ttf"), 20);
        failLabel.setVisible(false);
        DbUpdatedLabel.setVisible(false);
        entryAlertLabel.setVisible(false);
        SqliteConnect connect = new SqliteConnect();
        Con = connect.Connector();
    }    
    
    
}
