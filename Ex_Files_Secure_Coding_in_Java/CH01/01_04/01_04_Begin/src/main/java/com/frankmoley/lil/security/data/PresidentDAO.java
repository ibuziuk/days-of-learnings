package com.frankmoley.lil.security.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.frankmoley.lil.security.util.DatabaseUtil;

public class PresidentDAO {

    public List<President> getByLastName(final String lastName){
        Connection connection = DatabaseUtil.getConnection();
        List<President> resultList= new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select PRESIDENT_ID, FIRST_NAME, MIDDLE_INITIAL, LAST_NAME, EMAIL_ADDRESS from PRESIDENT where LAST_NAME = ?");
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                resultList.add(processResultSet(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return resultList;
    }


    private President processResultSet(ResultSet resultSet) throws SQLException{
        Long id = resultSet.getLong("PRESIDENT_ID");
        String firstName = resultSet.getString("FIRST_NAME");
        String middleInitial = resultSet.getString("MIDDLE_INITIAL");
        String lastName = resultSet.getString("LAST_NAME");
        String emailAddress = resultSet.getString("EMAIL_ADDRESS");
        return new President(id, firstName, middleInitial, lastName, emailAddress);
    }


}
