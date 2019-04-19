/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentmk3;

import assignmentmk3.Database;
import static assignmentmk3.Database.conn;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author chrispasquali
 */
public class FitnessDashboardController implements Initializable {

    
    @FXML
    private Text stepsTxt;
    
    @FXML
    public TextField steps;
    

//    @FXML
//    private Button stepsGraphbtn;
//    
//    @FXML
//    private Button floorsGraphbtn;
//    
//    @FXML
//    private Button BMIGraphbtn;
//    
//    @FXML
//    private Button fatRatioGraphbtn;
//    
//    @FXML
//    private Button heartRateGraphbtn;
//    
//    @FXML
//    private Button sleepGraphbtn;
    
    
    Database database = new Database();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stepsTxt.setText("SELECT STEPS FROM FITNESS_DATA"); 
        try {
            ResultSet rs = database.getResultSet("SELECT STEPS FROM FITNESS_DATA");
            PreparedStatement pst = conn.prepareStatement("SELECT STEPS FROM FITNESS_DATA"); 
            pst.setString(1,stepsTxt.getText());

            rs = pst.executeQuery();
            
                while (rs.next()) {
                    String steps = rs.getString("STEPS");
                    stepsTxt.setText("STEPS");
                } 
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        
        
        
    }    
    
}







 
//    @FXML
//    private void handleStepsGraphButtonAction(ActionEvent event) {
//        String stepGraph = step.getText().trim();
//        String password = pword.getText();
//        try {
//            ResultSet rs = d.getResultSet("SELECT * FROM LOGIN WHERE "
//                    + "USERNAME = '" + user + "' "
//                    + "AND PASSWORD = '" + password + "'");
//            if (!rs.next()) {
//                loginOutput.setText("Incorrect username or password");
//                loginOutput.setVisible(true);
//            } else {
//                loginOutput.setText("Login successful");
//                loginOutput.setVisible(true);
//                nextBtn.setVisible(true);
//            }
//            rs.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
    
//    private void LoginButtonActionPerformed(ActionEvent event) {
        
//    }



//    ObservableList<FitnessData> FitnessData = FXCollections.observableArrayList();
//    public void getSteps(Object name){
//        try {
//            String Sql = "select STEPS from FITNESS_DATA;";
//            ResultSet rs = Objects.requireNonNull(DBUtils.INSTANCE.databaseStatement()).executeQuery(Sql);
//            while (rs.next()) {
//                id=rs.getString("id");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            String Sql = "select * from Staff where id ='"+id+"' ;";
//            ResultSet rs = Objects.requireNonNull(DBUtils.INSTANCE.databaseStatement()).executeQuery(Sql);
//            while (rs.next()) {
//                UpdateStaffList.add(new UpdateStaffModel(rs.getString("UserName"), rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Mobile"),rs.getString("Email"),rs.getString("Designation"),rs.getString("IsActive")));
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();