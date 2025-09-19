package com.todo.dao;
import com.todo.model.Todo;
import java.util.List;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.todo.util.DatabaseConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TodoAppDAO {

    public Todo getTodo(ResultSet res) throws SQLException {

        Todo todo = new Todo();
        todo.setId(res.getInt("id"));
        todo.setTitle(res.getString("title"));
        todo.setDescription(res.getString("description"));
        todo.setCompleted(res.getBoolean("completed"));
        LocalDateTime created_at = res.getTimestamp("created_at").toLocalDateTime();

        todo.setCreated_at(created_at);
        LocalDateTime updated_at = res.getTimestamp("updated_at").toLocalDateTime();
        todo.setUpdated_at(updated_at);
        
        return todo;
    }

    public List<Todo> getAllTodos() throws SQLException {
        List<Todo> todos = new ArrayList<>();
        try(Connection cnn = new DatabaseConnection().getDBConnection();
            PreparedStatement stmt = cnn.prepareStatement("SELECT * FROM todos ORDER BY created_at DESC");
            ResultSet res = stmt.executeQuery();
        ){
            while(res.next()){
                todos.add(getTodo(res));   
            }
        }
        return todos;
    } 
    
}