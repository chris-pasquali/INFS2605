/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentmk3;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author chrispasquali
 */
public class Database {
    public static Connection conn;

    public static void openConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:fitnesFanaticsDB.db");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ResultSet getResultSet(String sqlstatement) throws SQLException {
        openConnection();
        java.sql.Statement statement = conn.createStatement();
        ResultSet RS = statement.executeQuery(sqlstatement);
        return RS;
    }

    public void insertStatement(String insert_query) throws SQLException {
        java.sql.Statement stmt = null;
        openConnection();
        try {
            System.out.println("Database opened successfully");
            stmt = conn.createStatement();
            System.out.println("The query was: " + insert_query);
            stmt.executeUpdate(insert_query);
            stmt.close();
            conn.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        stmt.close();
    }
    
    public static void createLoginTable() {
        PreparedStatement createLoginTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking LOGIN table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "LOGIN", null);
            if (!rs.next()) {
                createLoginTable = conn.prepareStatement("CREATE TABLE LOGIN (USERNAME NUMBER, PASSWORD VARCHAR(100))");
                createLoginTable.execute();
                System.out.println("LOGIN table created");
                insertDemoData = conn.prepareStatement("INSERT INTO LOGIN(USERNAME,PASSWORD)"
                        + "VALUES ('123456789','user123')");
                insertDemoData.execute();
            } else {
                System.out.println("LOGIN table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Display Fitness Infomation - e.g. steps, bmi, etc.
    public static void createFitnessHardwareTable() {
        PreparedStatement createFitnessHardwareTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking FITNESSDATA table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "FITNESSDATA", null);
            if (!rs.next()) {
                //FAT RATIO IS A PERCENTAGE, HEART RATE IS BEATS/MIN, SLEEP IN HOURS
                createFitnessHardwareTable = conn.prepareStatement("CREATE TABLE FITNESSDATA (DATE DATE, STEPS NUMBER, FLOORS NUMBER, BMI NUMBER, FAT_RATIO NUMBER, HEART_RATE NUMBER, SLEEP NUMBER)");
                createFitnessHardwareTable.execute();
                System.out.println("FITNESSDATA table created");
                insertDemoData = conn.prepareStatement("INSERT INTO FITNESSDATA(DATE, STEPS, FLOORS, BMI, FAT_RATIO, HEART_RATE, SLEEP) "
                        + "VALUES (12-04-2019, 11000, 7, 24.9, 25, 70, 8),"
                        + "(04-04-2019, 9000, 4, 22.9, 29, 80, 7), "
                        + "(29-03-2019, 7552, 6, 28, 27, 78, 8), "
                        + "(15-03-2019, 12000, 9, 27, 65, 7.5)"
                        + "(07-03-2019, 9512, 6, 30, 26, 52, 7)"
                        + "(04-03-2019, 9200, 4, 22.9, 29, 80, 7.5), "
                        + "(28-02-2019, 10532, 8, 27.5, 27, 78, 6), "
                        + "(22-02-2019, 11040, 9, 26.5, 62, 7.5)"
                        + "(17-02-2019, 9512, 6, 30, 26, 55, 7)"
                        + "(13-02-2019, 9000, 4, 22.9, 29, 80, 7), "
                        + "(09-02-2019, 7552, 6, 28, 27, 78, 8), "
                        + "(07-02-2019, 12000, 9, 27, 65, 7.5)"
                        + "(05-02-2019, 7644, 7, 32, 26, 50, 12)");
                insertDemoData.execute();
            } else {
                System.out.println("FITNESSDATA table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Display personal infomation
    public static void createMemberTable() {
        PreparedStatement createMemberTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking MEMBER table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "MEMBER", null);
            if (!rs.next()) {
                //HEIGHT IS IN CM, WEIGHT IN KGS
                createMemberTable = conn.prepareStatement("CREATE TABLE MEMBER (NAME VARCHAR(100), MEMBERSHIP_NUMBER NUMBER, AGE NUMBER, HEIGHT VARCHAR(5), WEIGHT VARCHAR(5))");
                createMemberTable.execute();
                System.out.println("MEMBER table created");
                insertDemoData = conn.prepareStatement("INSERT INTO MENBER(NAME,MEMBERSHIP_NUMBER,AGE,HEIGHT,WEIGHT) "
                        + "VALUES ('Jeff', 123456789, 35, 180, 95)");
                insertDemoData.execute();
            } else {
                System.out.println("MEMBER table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Users enter there own info about food
    public static void createFoodUserInputTable() {
        PreparedStatement createFoodUserInputTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking FITNESSINPUT table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "FITNESSINPUT", null);
            if (!rs.next()) {
                //FAT RATIO IS A PERCENTAGE, HEART RATE IS BEATS/MIN, SLEEP IN HOURS
                createFoodUserInputTable = conn.prepareStatement("CREATE TABLE FITNESSINPUT (DATE DATE, STEPS NUMBER, FLOORS NUMBER, BMI NUMBER, FAT_RATIO NUMBER, HEART_RATE NUMBER, SLEEP NUMBER)");
                createFoodUserInputTable.execute();
                System.out.println("FITNESSINPUT table created");
                insertDemoData = conn.prepareStatement("INSERT INTO FITNESSDATA(DATE, STEPS, FLOORS, BMI, FAT_RATIO, HEART_RATE, SLEEP) "
                        + "VALUES (12-04-2019, 11000, 7, 24.9, 25, 70, 8),"
                        + "(04-04-2019, 9000, 4, 22.9, 29, 80, 7), "
                        + "(29-03-2019, 7552, 6, 28, 27, 78, 8), "
                        + "(15-03-2019, 12000, 9, 27, 65, 7.5)"
                        + "(07-03-2019, 9512, 6, 30, 26, 52, 7)"
                        + "(04-03-2019, 9200, 4, 22.9, 29, 80, 7.5), "
                        + "(28-02-2019, 10532, 8, 27.5, 27, 78, 6), "
                        + "(22-02-2019, 11040, 9, 26.5, 62, 7.5)"
                        + "(17-02-2019, 9512, 6, 30, 26, 55, 7)"
                        + "(13-02-2019, 9000, 4, 22.9, 29, 80, 7), "
                        + "(09-02-2019, 7552, 6, 28, 27, 78, 8), "
                        + "(07-02-2019, 12000, 9, 27, 65, 7.5)"
                        + "(05-02-2019, 7644, 7, 32, 26, 50, 12)");
                insertDemoData.execute();
            } else {
                System.out.println("FITNESSINPUT table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static void createAerobicInputTable() {
        
    }
    
    public static void createResistanceInputTable() {
        
    }
    
    public static void createMentalHealthTable() {
        
    }
    
    public static void createHealthCheckUpTable() {
        PreparedStatement createHealthCheckUpTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking HEALTH_CHECK_UP table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "MEMBER", null);
            if (!rs.next()) {
                //HEIGHT IS IN CM, WEIGHT IN KGS
                createHealthCheckUpTable = conn.prepareStatement("CREATE TABLE HEALTH_CHECK_UP (DATE DATE, HEALTH_PROFESSIONAL)");
                createHealthCheckUpTable.execute();
                System.out.println("HEALTH_CHECK_UP table created");
                insertDemoData = conn.prepareStatement("INSERT INTO HEALTH_CHECK_UP(DATE, HEALTH_PROFESSIONAL)"
                        + "VALUES ('Jeff', 123456789, 35, 180, 95)");
                insertDemoData.execute();
            } else {
                System.out.println("HEALTH_CHECK_UP table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Fitness Goals
    public static void createFitnesGoalsTable() {
        
    }
    
//    public static void createHealthGoalsTable() {
//        
//    }
    
}
