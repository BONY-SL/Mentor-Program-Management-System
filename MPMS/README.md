# Mentor Programe Management System

## Project Description
The Mentor Programe Management System is a JavaFX-based desktop application designed to manage mentor-mentee relationships efficiently. Admins can create and manage groups consisting of mentors and mentees, facilitating streamlined communication and organization. This application uses Hibernate for ORM and MySQL as the database.

## Key Features
- **Admin Dashboard**: A centralized interface for admins to manage groups, mentors, and mentees.
- **Mentor Dashboard**: Show Each Mentors Groups.
- **Mentee Dashboard**: Show Each Mentees Groups.
- **Group Creation**: Allows admins to create mentor-mentee groups with unique IDs and names.
- **Dynamic Data Binding**: Automatically fetches mentor and mentee IDs from the database and displays their names.
- **Reset and Submit Options**: Ensures clean form handling and successful data submission.
- **Alerts**: Provides informative alerts for errors and success states.

## Technologies Used
- **JavaFX**: For creating a responsive and visually appealing UI.
- **MySQL**: Database for storing user and group information.
- **IntelliJ IDEA**: IDE used for development.
- **Scene Builder**: For designing FXML files.


## How to Run
1. **Setup Database**:
   - Import the provided SQL script(database.sql) to create and populate the necessary databse and tables in MySQL.
2. **Run the Application**:
   - Open the project in IntelliJ IDEA.
   - Import The Mysql Conector j Jar File For Intelij Project Structure > Module > Dependencey
   - Set Your MYSQL Local Host Password To DBConnection Java Class
   - Build the project to ensure all dependencies are resolved.
   - Execute the `HelloApplication.java` file to launch the application.
3. **Use the Application**:
   - Log in as an admin.
   - Navigate to the Admin Dashboard to create and manage mentor groups.

## Example Usage
### Create a New Group
1. Navigate to the Admin Dashboard.
2. Click the "Create New Group" button.
3. Fill in the group name, select a mentor and mentee.
4. Click "Submit" to save the group.
5. A success message will appear, and the form will reset.

## Database Design
### Tables
#### `users`
| Column     | Type         | Description         |
|------------|--------------|---------------------|
| `id`       | INT          | Primary Key         |
| `email`    | VARCHAR(255) | User email address  |
| `password` | VARCHAR(255) | Encrypted password  |
| `first_name` | VARCHAR(255)| First name          |
| `last_name` | VARCHAR(255) | Last name           |
| `role`     | VARCHAR(50)  | Role (MENTOR/MENTEE)|

#### `mentor_groups`
| Column       | Type         | Description                   |
|--------------|--------------|-------------------------------|
| `id`         | INT          | Primary Key                   |
| `group_name` | VARCHAR(255) | Name of the group             |
| `mentor_id`  | INT          | Foreign Key referencing `users`|
| `mentee_id`  | INT          | Foreign Key referencing `users`|
| `mentor_name`  | Stirng          | |
| `mentee_name`  | String          | |

## Future Improvements
- Add functionality for editing and deleting groups.
- Implement a search feature for mentors and mentees.
- Enhance UI with modern JavaFX styling.
- Add authentication for different user roles.




