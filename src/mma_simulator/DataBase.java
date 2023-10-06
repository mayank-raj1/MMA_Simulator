package mma_simulator;

import java.rmi.NoSuchObjectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    protected static Connection connection;
    private static Statement statement;

    public static void establishConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://Mayanks-MacBook-Pro.local:3306/Mma_Simulator", "root", "mayankraj");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected static void endConnection(){
        try {
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    protected static Fighter getFighter(String fighterId) throws NoSuchObjectException {
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Fighters WHERE id = "+fighterId+" ;");
            resultSet.next();
            return new Fighter(resultSet.getString("name"), resultSet.getDouble("strength"), resultSet.getDouble("speed"), resultSet.getDouble("skill"), resultSet.getInt("age"), resultSet.getDouble("weight"), resultSet.getString("id"));
        }
        catch (SQLException e){
            throw new NoSuchObjectException("Fighter not in the database");
        }
    }

    protected static HashMap<String, Fighter> getAllFighters(){
        try {
            HashMap<String, Fighter> temp = new HashMap<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Fighters;");
            while (resultSet.next()) {
                temp.put(resultSet.getString("id"),new Fighter(resultSet.getString("name"), resultSet.getDouble("strength"), resultSet.getDouble("speed"), resultSet.getDouble("skill"), resultSet.getInt("age"), resultSet.getDouble("weight"), resultSet.getString("id")));
            }
            return temp;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    protected static void addFighter(Fighter fighter){
        try{
            String statementString = "INSERT INTO Fighters (id, name, strength, speed, skill, age, weight, status, balance) "
                    +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statementString);
            preparedStatement.setString(1, fighter.getId());
            preparedStatement.setString(2, fighter.getName());
            preparedStatement.setDouble(3, fighter.getStrength());
            preparedStatement.setDouble(4, fighter.getSpeed());
            preparedStatement.setDouble(5, fighter.getSkill());
            preparedStatement.setInt(6, fighter.getAge());
            preparedStatement.setDouble(7, fighter.getWeight());
            preparedStatement.setString(8, fighter.getStatus().toString());
            preparedStatement.setDouble(9, fighter.getBalance());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

   protected static void addMatch(Match match){
        try{
            String statementString = "INSERT INTO Matches (match_id, fighter1, fighter2, winner, fighter1_score, fighter2_score)"
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statementString);
            preparedStatement.setString(1, match.getMatchId());
            preparedStatement.setString(2, match.getFighter1().getName());
            preparedStatement.setString(3, match.getFighter2().getName());
            preparedStatement.setString(4, match.getWinner().getName());
            preparedStatement.setInt(5, match.getRecord().score()[0]);
            preparedStatement.setInt(6, match.getRecord().score()[1]);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}