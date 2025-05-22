package HospitalManagementSystem;
import java.sql.*;
import java.util.Scanner;
public class Patient {
    private final Connection connection;
    private final Scanner scanner;
    public Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }
    public void addPatient(){
        System.out.print("Enter Patient First Name: ");
        String first_name = scanner.next();
        System.out.print("Enter Patient Last Name: ");
        String last_name = scanner.next();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender: ");
        String gender = scanner.next();
        try{
            String query = "INSERT INTO patients(first_name,last_name, age, gender) VALUES( ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, gender);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient Added Successfully!!");
            }else{
                System.out.println("Failed to add Patient!!");
            }
        }catch (SQLException e){
            System.err.println("Error checking doctor existence: " + e.getMessage());
        }
    }
    public void viewPatients(){
        String query = "select * from patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+---------------+--------------+----------+---------+");
            System.out.println("| Patient Id | First Name    |    Last Name | Age      | Gender  |");
            System.out.println("+------------+---------------+--------------+----------+---------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10s |   %-9s   |   %-9s  |  %-8s | %-7s|\n", id, first_name,last_name, age, gender);
                System.out.println("+-------+----------------+--------------+------------+-----------+");
            }
        }catch (SQLException e){
            System.err.println("Error checking doctor existence: " + e.getMessage());
        }
    }
    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            System.err.println("Error checking doctor existence: " + e.getMessage());
        }
        return false;
    }

}
