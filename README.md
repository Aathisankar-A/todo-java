# Java Todo Application

A simple Java Swing-based Todo application that uses MySQL for persistent data storage.

## Features

- Create, read, update, and delete (CRUD) todos
- Mark todos as completed or pending
- Filter todos by completion status
- Persistent storage using MySQL database
- User-friendly GUI built with Java Swing

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Apache Maven 3.6 or higher
- MySQL Server 8.0 or higher

## Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   cd java-todo
   ```

2. Install dependencies using Maven:
   ```
   mvn clean install
   ```

## Database Setup

1. Install and start MySQL Server.

2. Create a database named `todo`:
   ```sql
   CREATE DATABASE todo;
   ```

3. Create the `todos` table:
   ```sql
   USE todo;

   CREATE TABLE todos (
       id INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       description TEXT,
       completed BOOLEAN DEFAULT FALSE,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
   );
   ```

4. Update database credentials in [`src/main/java/com/todo/util/DatabaseConnection.java`](src/main/java/com/todo/util/DatabaseConnection.java:6-9) if necessary:
   - Change the `URL`, `USERNAME`, and `PASSWORD` constants to match your MySQL setup.

## Running the Application

### Using Maven Exec Plugin
```
mvn exec:java
```

### Or build and run the JAR
```
mvn clean package
java -jar target/todo-application-1.0.0.jar
```

The application will attempt to connect to the database on startup and launch the GUI.

## Usage

- **Add Todo**: Click "Add Todo" button, enter title and description.
- **Edit Todo**: Select a todo from the list and click "Edit".
- **Delete Todo**: Select a todo and click "Delete".
- **Mark Complete**: Select a todo and click "Mark Complete".
- **Filter**: Use the checkboxes to filter completed or pending todos.

## Project Structure

- `src/main/java/com/todo/Main.java` - Application entry point
- `src/main/java/com/todo/model/Todo.java` - Todo model class
- `src/main/java/com/todo/dao/TodoAppDAO.java` - Data Access Object for database operations
- `src/main/java/com/todo/gui/TodoAppGUI.java` - Swing GUI implementation
- `src/main/java/com/todo/util/DatabaseConnection.java` - Database connection utility

## Technologies Used

- Java 11
- Java Swing (for GUI)
- MySQL (for database)
- Maven (for build management)

## License

This project is licensed under the MIT License - see the LICENSE file for details.
