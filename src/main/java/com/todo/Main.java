package com.todo;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.todo.util.DatabaseConnection;
import com.todo.gui.TodoAppGUI;
public class Main {
    public static void main(String[] args) {
        DatabaseConnection db_Connection = new DatabaseConnection();
        try {
            Connection cn = db_Connection.getDBConnection();
            System.out.println("Database connected successfully.");
        } catch (Exception e){
            System.err.println("Error: Unable to connect to database.");
            System.exit(1);
        }
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Error: Unable to set look and feel. " + e.getMessage());
        }
        SwingUtilities.invokeLater(() -> {
            try{
                new TodoAppGUI().setVisible(true);
            }
            catch(Exception e){
                System.err.println("Error: Unable to launch application. " + e.getLocalizedMessage());
            }
        });
    }
}