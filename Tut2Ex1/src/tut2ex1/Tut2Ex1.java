/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tut2ex1;

import java.lang.ClassNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author chrispasquali
 */
public class Tut2Ex1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
		connect();
		insertPets();
		printAllPets();		
		getPets("Cat");
		getPets("Frog");
						
	}
	public static void connect() throws ClassNotFoundException, SQLException {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
                Statement st = conn.createStatement();
                String deleteTable = "DROP TABLE IF EXISTS pets";
                st.execute(deleteTable);
                String createQuery = "CREATE TABLE IF NOT EXISTS pets"
                        + "(petID INTEGER PRIMARY KEY AUTOINCREMENT"
                        + ", name TEXT NOT NULL"
                        + ",species TEXT NOT NULL"
                        + ",colour TEXT NOT NULL"
                        + ", owner TEXT NOT NULL"
                        + ")" ;
                
                st.execute(createQuery);
                st.close();
                conn.close();
	}
	
	public static void insertPets() throws SQLException {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
                Statement st = conn.createStatement();
                
                ArrayList<String> insertStatements = new ArrayList<String>();
                insertStatements.add(
                        "INSERT INTO pets (petID, name, species, colour, owner) "
                        + "VALUES(5001, 'Kitty', 'Cat', 'Grey', 'Andrew');"
                );
                insertStatements.add(
                        "INSERT INTO pets (petID, name, species, colour, owner) "
                        + "VALUES(5002, 'Blair', 'Cat', 'White', 'Yenni');"        
                );
                insertStatements.add(
                        "INSERT INTO pets (petID, name, species, colour, owner) "
                        + "VALUES(5003, 'Mimi', 'Frog', 'Green', 'Hatherine');"
                );
                insertStatements.add(
                        "INSERT INTO pets (petID, name, species, colour, owner) "
                        + "VALUES(5004, 'QuackyMooMoo', 'Dog', 'Brown', 'Phoebe');"
                );
                
                for (String thisStatement : insertStatements) {
                    st.execute(thisStatement);
                }
                
                st.close();
                conn.close();     
		
	}
	
	public static void printAllPets() throws SQLException {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
                Statement st = conn.createStatement();
                
                System.out.println("** SEARCH ALL PETS **");
                
                String selectQuery = "SELECT name, species, colour, owner, petID FROM pets;";
                ResultSet rs= st.executeQuery(selectQuery);
                
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " owned by " + rs.getString(4)
                    + ", "
                    );
                }
                
                st.close();
                conn.close();
                
//                System.out.println("** SEARCH ALL PETS **");
        
	}
	
	public static void getPets(String species) throws SQLException {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
                Statement st = conn.createStatement();
                
                System.out.println("** SEARCH FOR SPECIES = " + species + " **");
                
                String query = "SELECT name, species, colour, owner, petID FROM pets WHERE species = " + "'" + species + "';";
                
                ResultSet rs= st.executeQuery(query);
                
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " owned by " + rs.getString(4)
                    + ", "
                    );
                } 
                
                st.close();
                conn.close();

		//create connection
		//create statement
		//write the SQL query to retrieve all pets that are of the species specified in the parameters of this method
                
//		System.out.println("** SEARCH FOR SPECIES = " + species + " **");
	}
	
}
