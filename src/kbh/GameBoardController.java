/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kbh;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rabinson
 */
public class GameBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection Con;
    PreparedStatement PreStmt;

    //initial parameters
    String playerName;
    int newGame = 1;
       //for score
    int score = 0;
    //for totalwin
    String win;
    //for given answer
    String givenAns;
    //for alterAns
    String altAns;
    //for correct Answer
    String correctAns;
    //Anchorpane for selected button
    AnchorPane globalpane;
    // pane of correct answer
    AnchorPane anspane;
    //label of correct answer
    Label anslabel;
    //label of alter answer
    Label altLabel;
    //for 50:50
    int fifty = 0;
    //for expert
    int expert = 0;

    //for option suffling in line 280
    String a[] = new String[4];

    //Set number for current game
    int SetNo;

    @FXML
    private AnchorPane ResultAnchorPane;

    @FXML
    private Label totalLabel;
    
    @FXML
    private Label playerLabel; 
    @FXML
    private JFXButton replayBtn;

    @FXML
    private AnchorPane p14;

    @FXML
    private AnchorPane p13;

    @FXML
    private AnchorPane p12;

    @FXML
    private AnchorPane p11;

    @FXML
    private AnchorPane p10;

    @FXML
    private AnchorPane p9;

    @FXML
    private AnchorPane p8;

    @FXML
    private AnchorPane p7;

    @FXML
    private AnchorPane p6;

    @FXML
    private AnchorPane p5;

    @FXML
    private AnchorPane p4;

    @FXML
    private AnchorPane p3;

    @FXML
    private AnchorPane p2;

    @FXML
    private AnchorPane p1;

    @FXML
    private Label QuesLabel;

    @FXML
    private AnchorPane AlabelAnchorpane;

    @FXML
    private Label alabel;

    @FXML
    private JFXButton ABtn;

    @FXML
    private AnchorPane BlabelAnchorPane;

    @FXML
    private Label blabel;

    @FXML
    private JFXButton BBtn;

    @FXML
    private AnchorPane ClabelAnchorPane;

    @FXML
    private Label clabel;

    @FXML
    private JFXButton CBtn;

    @FXML
    private AnchorPane DlabelAnchorPane;

    @FXML
    private Label dlabel;

    @FXML
    private JFXButton DBtn;

    @FXML
    private JFXButton OptionBtn;

    @FXML
    private JFXButton NxtBtn;

    @FXML
    private JFXButton CheckBtn;

    @FXML
    private JFXButton expertBtn;

    @FXML
    private JFXButton fiftyBtn;

    @FXML
    private Label QNoLabel;
    
    
    

    //for resetting css of options
    void resetOptionColor() {
        AlabelAnchorpane.getStyleClass().clear();
        BlabelAnchorPane.getStyleClass().clear();
        ClabelAnchorPane.getStyleClass().clear();
        DlabelAnchorPane.getStyleClass().clear();
    }

    //for resetting Optionslabel
    void resetOptions() {
        alabel.setText("");
        blabel.setText("");
        clabel.setText("");
        dlabel.setText("");
    }

    //for getting anspane
    void getanspane() {
        if (alabel.getText().equals(correctAns)) {
            anspane = AlabelAnchorpane;
            anslabel = alabel;
        } else if (blabel.getText().equals(correctAns)) {
            anspane = BlabelAnchorPane;
            anslabel = blabel;
        } else if (clabel.getText().equals(correctAns)) {
            anspane = ClabelAnchorPane;
            anslabel = clabel;
        } else if (dlabel.getText().equals(correctAns)) {
            anspane = DlabelAnchorPane;
            anslabel = dlabel;
        }
    }

    //void getting altanspane
    void getalterAnsLabel() {
        if (alabel.getText().equals(altAns)) {
            altLabel = alabel;
        } else if (blabel.getText().equals(altAns)) {
            altLabel = blabel;
        } else if (clabel.getText().equals(altAns)) {
            altLabel = clabel;
        } else if (dlabel.getText().equals(altAns)) {
            altLabel = dlabel;
        }
    }

    //for resetting css for score
    void resetScoreColor() {
        p1.getStyleClass().clear();
        p2.getStyleClass().clear();
        p3.getStyleClass().clear();
        p4.getStyleClass().clear();
        p5.getStyleClass().clear();
        p6.getStyleClass().clear();
        p7.getStyleClass().clear();
        p8.getStyleClass().clear();
        p9.getStyleClass().clear();
        p10.getStyleClass().clear();
        p11.getStyleClass().clear();
        p12.getStyleClass().clear();
        p13.getStyleClass().clear();
        p14.getStyleClass().clear();

    }

    // Score board updating
    void updateScoreBoard() {

        if (score == 1) {
            win = "Rs 20";
            p1.getStyleClass().add("scoreincrease");
        }
        if (score == 2) {
            win = "Rs 40";
            resetScoreColor();
            p2.getStyleClass().add("scoreincrease");
        }
        if (score == 3) {
            win = "Rs 60";
            resetScoreColor();
            p3.getStyleClass().add("scoreincrease");
        }
        if (score == 4) {
            win = "Rs 80";
            resetScoreColor();
            p4.getStyleClass().add("scoreincrease");
        }
        if (score == 5) {
            win = "Rs 100";
            resetScoreColor();
            p5.getStyleClass().add("scoreincrease");
        }
        if (score == 6) {
            win = "Rs 200";
            resetScoreColor();
            p6.getStyleClass().add("scoreincrease");
        }
        if (score == 7) {
            win = "Rs 300";
            resetScoreColor();
            p7.getStyleClass().add("scoreincrease");
        }
        if (score == 8) {
            win = "Rs 400 ";
            resetScoreColor();
            p8.getStyleClass().add("scoreincrease");
        }
        if (score == 9) {
            win = "Rs 500";
            resetScoreColor();
            p9.getStyleClass().add("scoreincrease");
        }
        if (score == 10) {
            win = "Rs 600";
            resetScoreColor();
            p10.getStyleClass().add("scoreincrease");
        }
        if (score == 11) {
            win = "Rs 700";
            resetScoreColor();
            p11.getStyleClass().add("scoreincrease");
        }
        if (score == 12) {
            win = "Rs 800";
            resetScoreColor();
            p12.getStyleClass().add("scoreincrease");
        }
        if (score == 13) {
            win = "Rs 900";
            resetScoreColor();
            p13.getStyleClass().add("scoreincrease");
        }
        if (score == 14) {
            win = "Rs 1000";
            resetScoreColor();
            p14.getStyleClass().add("scoreincrease");
        }

    }

    //Calculate win
    public String calculateWin() {
        String a =" ";
        if (score == 0) {
            a = "Rs. 0";
        } else if (score == 1) {
            a = "Rs. 20";
        } else if (score == 2) {
            a = "Rs. 40";
        } else if (score == 3) {
            a = "Rs. 60";
        } else if (score == 4) {
            a = "Rs. 80";
        } else if (score == 5) {
            a = "Rs. 100";
        } else if (score == 6) {
            a = "Rs. 200";
        } else if (score == 7) {
            a = "Rs. 300";
        } else if (score == 8) {
            a = "Rs. 400";
        } else if (score == 9) {
            a = "Rs. 500";
        } else if (score == 10) {
            a = "Rs. 600";
        } else if (score == 11) {
            a = "Rs. 700";
        } else if (score == 12) {
            a = "Rs. 800";
        } else if (score == 13) {
            a = "Rs. 900";
        } else if (score == 14) {
            a = "Rs. 1000";
        }
        return a;
    }

    void homePage() throws IOException{
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("/kbh/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        stage1.show();
    }
    

    @FXML
    void ABtnPushed() {
        CheckBtn.setDisable(false);
        resetOptionColor();
        AlabelAnchorpane.getStyleClass().add("select");
        givenAns = alabel.getText();
        globalpane = AlabelAnchorpane;  // for getting the selected pane to change color
    }

    @FXML
    void BBtnPushed() {
        CheckBtn.setDisable(false);
        resetOptionColor();
        BlabelAnchorPane.getStyleClass().add("select");
        givenAns = blabel.getText();
        globalpane = BlabelAnchorPane;  // for getting the selected pane to change color

    }

    @FXML
    void CBtnPushed() {
        CheckBtn.setDisable(false);
        resetOptionColor();
        ClabelAnchorPane.getStyleClass().add("select");
        givenAns = clabel.getText();
        globalpane = ClabelAnchorPane; // for getting the selected pane to change color

    }

    @FXML
    void DBtnPushed() {
        CheckBtn.setDisable(false);
        resetOptionColor();
        DlabelAnchorPane.getStyleClass().add("select");
        givenAns = dlabel.getText();
        globalpane = DlabelAnchorPane;// for getting the selected pane to change color

    }

    @FXML
    void NxtBtnPushed() throws SQLException, IOException {

        fiftyBtn.setDisable(true);
        expertBtn.setDisable(true);
        resetOptions();
        OptionBtn.setDisable(false);
        expertBtn.setDisable(true);
        NxtBtn.setDisable(true);
        if (newGame == 1) {
            newGame = 0;
            String sql = "SELECT * FROM QueSet where SetNo NOT IN (SELECT SetNo FROM QueSet where Played = 1) ORDER BY QNo ASC";
            PreStmt = Con.prepareStatement(sql);
            ResultSet rs = PreStmt.executeQuery();
            if (rs.next()) {
                correctAns = rs.getString("Answer");
                altAns = rs.getString("AltAns");
                SetNo = rs.getInt("SetNo");
                QuesLabel.setText(rs.getString("Question"));
                QNoLabel.setText(rs.getString("QNo"));
                a[0] = rs.getString("Answer");
                a[1] = rs.getString("AltAns");
                a[2] = rs.getString("Opt1");
                a[3] = rs.getString("Opt2");
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ko Banxa Hajarpati");
                alert.setHeaderText("This error is due to no questions in the database.");
                alert.setContentText("Login for Reseting or Adding New Question set");

                ButtonType buttonTypeOne = new ButtonType("Login");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    
                    homePage();
                }else {
                    System.exit(0);
                }
            }
            rs.close();
        } else {
            System.out.println("hello");
            String sql = "SELECT * FROM QueSet where SetNo = ? and Played = 0 ORDER BY QNo ASC";
            PreStmt = Con.prepareStatement(sql);
            PreStmt.setInt(1, SetNo);
            ResultSet rs = PreStmt.executeQuery();
            if (rs.next()) {
                correctAns = rs.getString("Answer");
                altAns = rs.getString("AltAns");
                SetNo = rs.getInt("SetNo");
                QuesLabel.setText(rs.getString("Question"));
                QNoLabel.setText(rs.getString("QNo"));
                a[0] = rs.getString("Answer");
                a[1] = rs.getString("AltAns");
                a[2] = rs.getString("Opt1");
                a[3] = rs.getString("Opt2");
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Congratulation");
                alert.setHeaderText("CONGRATULATION, Best wishes from KBH");
                alert.setContentText("You have complete the game.");

                ButtonType buttonTypeOne = new ButtonType("Play Again");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    replayBtnPushed();       
                }else {
                    System.exit(0);
                }
                
                resetOptionColor();
                resetOptions();
            }
            resetOptions();
            rs.close();
        }
        resetOptionColor();
        //Enabling the Options Button
        ABtn.setDisable(false);
        BBtn.setDisable(false);
        CBtn.setDisable(false);
        DBtn.setDisable(false);
        CheckBtn.setDisable(true);

    }

    @FXML
    void OptionBtnPushed() {
        if(expert == 0){
            expertBtn.setDisable(false);
        }        
        if(fifty == 0){
        fiftyBtn.setDisable(false);}
       // newGame = 0;
        List<String> list = Arrays.asList(a);
        Collections.shuffle(list);
        alabel.setText(list.get(0));
        blabel.setText(list.get(1));
        clabel.setText(list.get(2));
        dlabel.setText(list.get(3));
        OptionBtn.setDisable(true);

    }

    @FXML
    void checkBtnPushed() throws SQLException {
        String sql = "SELECT * from QueSet where QNo = ? and SetNo = ?";
        PreStmt = Con.prepareStatement(sql);
        PreStmt.setString(1, QNoLabel.getText());
        PreStmt.setInt(2, SetNo);
        ResultSet rs = PreStmt.executeQuery();
        if (rs.next()) {
            String answer = rs.getString("Answer");
            if (answer.equals(givenAns)) {
                score++;
                //updating score board
                updateScoreBoard();
                globalpane.getStyleClass().clear();
                globalpane.getStyleClass().add("correct");

            } else {
                ResultAnchorPane.toFront();
                globalpane.getStyleClass().clear();
                globalpane.getStyleClass().add("incorrect");
                if (score < 5) {
                    totalLabel.setText("Rs 0");
                } else if (score < 9) {
                    totalLabel.setText("Rs 100");
                } else if (score == 14) {
                    totalLabel.setText("Rs 1000");
                }
                getanspane();
                anspane.getStyleClass().clear();
                anspane.getStyleClass().add("correct");
            }
        }
        rs.close();

        //Disabling the Options Button
        ABtn.setDisable(true);
        BBtn.setDisable(true);
        CBtn.setDisable(true);
        DBtn.setDisable(true);
        NxtBtn.setDisable(false);
        CheckBtn.setDisable(true);

        //Updating Valid to 0
        String sql2 = "UPDATE QueSet set Played = 1 where QNo = ? and SetNo = ?";
        PreStmt = Con.prepareStatement(sql2);
        PreStmt.setString(1, QNoLabel.getText());
        PreStmt.setInt(2,SetNo);
        PreStmt.executeUpdate();

    }

    @FXML
    void expertBtnPushed() {
        expertBtn.getStyleClass().add("already");
        expert = 1;
        expertBtn.setDisable(true);
    }

    @FXML
    void fiftyBtnPushed() {
        getanspane();
        getalterAnsLabel();

        Label[] lbl = new Label[4];
        lbl[0] = alabel;
        lbl[1] = blabel;
        lbl[2] = clabel;
        lbl[3] = dlabel;
        
        for(int i = 0 ; i < 4 ; i++){
           if(lbl[i]==anslabel || lbl[i] == altLabel){
               
           }
           else{
               lbl[i].setText("");
           }
        
        }
        fifty = 1;
        fiftyBtn.setDisable(true);
        fiftyBtn.getStyleClass().add("already");

    }

    @FXML
    void quitBtnPushed() {

        totalLabel.setText(calculateWin());
        ResultAnchorPane.toFront();

    }

    @FXML
    void replayBtnPushed() throws IOException {
        Stage stage = (Stage) replayBtn.getScene().getWindow();
        stage.close();
        homePage();
    }
    void getName() throws IOException {
        TextInputDialog dialog = new TextInputDialog("PlayerName");

        dialog.setTitle("Welcome to Ko Banxa Hajarpati");
        dialog.setHeaderText("Enter the Player Name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();
       
        if (result.isPresent()){
        playerName = result.get();
        }
        else{
            
            replayBtnPushed();
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
          
            SqliteConnect connect = new SqliteConnect();
            Con = connect.Connector();
            getName();
            playerLabel.setText(playerName);

            NxtBtnPushed();
        } catch (SQLException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
