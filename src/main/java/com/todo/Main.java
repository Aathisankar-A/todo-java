package com.todo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.todo.gui.TodoAppGUI;
import com.todo.util.DatabaseConnection;

public class Main
{
    public static void main(String args[])
    {
        DatabaseConnection db_Connection = new DatabaseConnection();

        try{
            Connection cn = db_Connection.getDBConnection();
        }
        catch(SQLException e){
            System.out.println("Connection Failed");
            System.exit(1);
        }
        // System.out.println("Connection Success");
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException |  InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
            System.err.println("Could not set look and feel " + e.getMessage());
        }

        SwingUtilities.invokeLater(
            () -> {
                try{
                    new TodoAppGUI().setVisible(true);
                }
                catch(Exception e){
                    System.err.println("Error starting the application" + e.getLocalizedMessage());
                }
            }
        );
    }
}