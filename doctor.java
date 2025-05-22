package HospitalManagementSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Doctor {
    private final Connection connection;
    public Doctor(Connection connection){
        this.connection = connection;
    }
    public void viewDoctors(){
        String query = "select * from doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+--------------------+---------------------+--------+---------+");
            System.out.println("| Doctor Id  | Name               | Specialization      |   Age  |  Gender |");
            System.out.println("+------------+--------------------+---------------------+--------+---------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                String specialization = resultSet.getString("Specialization");
                int age = resultSet.getInt("Age");
                String gender = resultSet.getString("Gender");
                System.out.printf("| %-10s | %-18s | %-20s | %-6s | %-7s| \n", id, name, specialization,age,gender);
                System.out.println("+---------+--------------------+--------------------+----------+-----------+");
            }
        }catch (SQLException e){
            System.err.println("Error checking doctor existence: " + e.getMessage());
        }
    }
    public boolean getDoctorById(int id){
        String query = "SELECT * FROM doctors WHERE id = ?";
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
