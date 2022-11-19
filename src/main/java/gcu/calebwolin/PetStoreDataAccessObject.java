package gcu.calebwolin;
import java.sql.*;
import java.util.*;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class PetStoreDataAccessObject 
{
    static final String DATABASE_URL = "jdbc:mysql://localhost:8889/pets";
    static final String USERNAME = "root";
    static final String PASSWORD = "root";

    public ArrayList<Pet> getAllPets()
    {
        // create an empty array of pets
        ArrayList<Pet> returnThese = new ArrayList<>();

        try
        {
            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        

            Statement stmt = conn.createStatement();
            String sqlString = "SELECT ID, NAME, PRICE, DESCRIPTION, PET_CATEGORIES_ID FROM Pets";

            ResultSet resultSet = stmt.executeQuery(sqlString);

            while(resultSet.next())
            {
                Pet p = new Pet();
                p.setId(resultSet.getInt("ID"));
                p.setName(resultSet.getString("NAME"));
                p.setPrice(resultSet.getDouble("PRICE"));
                p.setDescription(resultSet.getString("DESCRIPTION"));
                p.setCategoryId(resultSet.getInt("PET_CATEGORIES_ID"));
                
                returnThese.add(p);
            }

        }
        catch(SQLException e )
        {
            System.out.println(e.getMessage());
        }
        return returnThese;
    }

    public Pet getById(int Id)
    {
        ArrayList<Pet> returnThese = new ArrayList<>();
        // open a connection
        try {Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            
            String sqlString = "SELECT ID, NAME, PRICE, DESCRIPTION, PET_CATEGORIES_ID FROM Pets WHERE Id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
            preparedStatement.setInt(1, Id);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next())
            {
                Pet p = new Pet();
                p.setId(rs.getInt("ID"));
                p.setName(rs.getString("NAME"));
                p.setPrice(rs.getDouble("PRICE"));
                p.setDescription(rs.getString("DESCRIPTION"));
                p.setCategoryId(rs.getInt("PET_CATEGORIES_ID"));

                returnThese.add(p);

            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        if(returnThese.size() > 0)
        {
            return returnThese.get(0);
        }
        else
        {
            return null;
        }
    }

    public ArrayList<Pet> searchForMany (String searchTerm)
    {
        ArrayList<Pet> returnThese = new ArrayList<>();
        // open a connection
        try {Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            
            String sqlString = "SELECT ID, NAME, PRICE, DESCRIPTION, PET_CATEGORIES_ID FROM Pets WHERE NAME LIKE ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
            preparedStatement.setString(1, "%" + searchTerm + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                Pet p = new Pet();
                p.setId(resultSet.getInt("ID"));
                p.setName(resultSet.getString("NAME"));
                p.setPrice(resultSet.getDouble("PRICE"));
                p.setDescription(resultSet.getString("DESCRIPTION"));
                p.setCategoryId(resultSet.getInt("PET_CATEGORIES_ID"));
                
                returnThese.add(p);
            }
            
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return returnThese;
    }

    public int addOne(Pet newPet)
    {
        // open a connection
        try {Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            
            String updateString = "INSERT INTO PETS (ID, NAME, PRICE, DESCRIPTION, PET_CATEGORIES_ID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(updateString);

            preparedStatement.setInt(1, newPet.getId());
            preparedStatement.setString(2, newPet.getName());
            preparedStatement.setDouble(3, newPet.getPrice());
            preparedStatement.setString(4, newPet.getDescription());
            preparedStatement.setDouble(5, newPet.getCategoryId());

            int updates = preparedStatement.executeUpdate();
            return updates;
            
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public int updateOne(Pet newPet)
    {
        try {Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            
            conn.createStatement();
            String updateString = "UPDATE PETS SET NAME = ?, PRICE = ?, DESCRIPTION = ?, PET_CATEGORIES_ID = ? where ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(updateString);

            preparedStatement.setString(1, newPet.getName());
            preparedStatement.setDouble(2, newPet.getPrice());
            preparedStatement.setString(3, newPet.getDescription());
            preparedStatement.setInt(4, newPet.getCategoryId());
            preparedStatement.setInt(5, newPet.getId());

            int updates = preparedStatement.executeUpdate();

            return updates;
            
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public int deleteOne(Pet deletePet)
    {
        try {Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            
            conn.createStatement();
            String deleteString = "DELETE FROM PETS WHERE ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(deleteString);

            preparedStatement.setInt(1, deletePet.getId());
            
            int deletes = preparedStatement.executeUpdate();
            return deletes;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}

