package com.todo.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.todo.model.Todo;
import com.todo.dao.TodoAppDAO;

public class TodoAppGUI extends JFrame{
    private TodoAppDAO todoDAO;
    private JTable todoTable;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JCheckBox completedCheckbox;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JComboBox<String> filterComboBox;

    public TodoAppGUI() {
        this.todoDAO = new TodoAppDAO();
        initializeComponent();
        setupLayout();
        setupEventListeners();
        
    }

    private void initializeComponent(){
        setTitle("Todo Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Title", "Description", "Completed", "Created At", "Updated At"};

        tableModel = new DefaultTableModel(null, columnNames){
            @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }
        };

        todoTable = new JTable(tableModel);
        todoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoTable.getSelectionModel().addListSelectionListener(
            (e) -> {
                if(!e.getValueIsAdjusting())
                {
                    // loadSelectedTodo();
                }
            }
        );

        titleField = new JTextField(20);
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        completedCheckbox = new JCheckBox("Completed");

        addButton = new JButton("Add Todo");
        deleteButton = new JButton("Delete Todo");
        updateButton = new JButton("Update Todo");
        refreshButton = new JButton("Refresh Todo");

        String[] filterOptions = {"All", "Completed", "Pending"};
        filterComboBox = new JComboBox<>(filterOptions);
        filterComboBox.addActionListener(
            (e) -> {
                // filterTodos();
            }
        );
    }

    private void setupLayout()
    {
        setLayout( new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        inputPanel.add(new JLabel("Title : "), gbc);

        gbc.gridx = 1;
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(new JLabel("Description : "), gbc);

        gbc.gridx = 1;
        inputPanel.add(descriptionArea, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(completedCheckbox, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Filter : "));
        filterPanel.add(filterComboBox);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(filterPanel, BorderLayout.NORTH);
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);

        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // northPanel.add(filterPanel, gbc);

        // gbc.gridx = 1;
        // gbc.gridy = 1;
        // northPanel.add(inputPanel, gbc);

        // gbc.gridx = 1;
        // gbc.gridy = 2;
        // northPanel.add(buttoPanel, gbc);

        add(northPanel, BorderLayout.NORTH);

        add(new JScrollPane(todoTable), BorderLayout.CENTER);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.add(new JLabel("Select a row to edit or delete"));
        add(statusPanel, BorderLayout.SOUTH);
    }

    private void setupEventListeners() {
        addButton.addActionListener((e)-> {addTodo();});
        updateButton.addActionListener((e)-> {updateTodo();});
        deleteButton.addActionListener((e)-> {deleteTodo();});
        refreshButton.addActionListener((e)-> {refreshTodo();});
        filterComboBox.addActionListener((e)-> {filterTodos();});
    }
    private void addTodo() {
        
    }
    private void updateTodo() {

    }
    private void deleteTodo() {

    }
    private void refreshTodo() {
        loadTodos();
    }
    private void filterTodos() {
        
    }
    private void loadTodos() {
        try{
            List<Todo> todos = todoDAO.getAllTodos();
            updateTable(todos);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Error Loading todos : "+e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable(List<Todo> todos)
    {
        tableModel.setRowCount(0);
        for(Todo todo : todos)
        {
            Object[] row = {todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted(), todo.getCreated_at(), todo.getUpdated_at()};
            tableModel.addRow(row);
        }
    }
}