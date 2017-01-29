package com.example.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.dao.UserDao;
import com.example.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	private static final String setSchemaQuery = "set search_path to training;";
	private static final String selectUserByIdQuery = "select * from users where user_id = ?;";
	private static final String selectUserByNameQuery = "select * from users where user_name = ?;";
	private static final String updateUserQuery = "update users set last_usage_time = ? , user_name = ? where user_id = ?;";
	private static final String insertUserQuery = "insert into users(user_id,user_name,last_usage_time) values (?,?,?);";
	private static final String generate_next_sequence_query = " SELECT nextval('users_user_id_seq')";

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public int insert(User user) {
		jdbcTemplate.execute(setSchemaQuery);
		user.setId(jdbcTemplate.queryForObject(generate_next_sequence_query,Integer.class));
		jdbcTemplate.update(insertUserQuery, new Object[] { user.getId(), user.getUserName(), user.getLastUsageTime() });
		return user.getId();
	}

	@Override
	public void update(User user) {
		jdbcTemplate.execute(setSchemaQuery);
		jdbcTemplate.update(updateUserQuery, new Object[] { user.getLastUsageTime(),user.getUserName(), user.getId() });
		
	}
		

	@Override
	public User findById(int userId) {
		jdbcTemplate.execute(setSchemaQuery);
		try{
			User user = jdbcTemplate.queryForObject(selectUserByIdQuery, new Object[] { userId },new UserRowMapper());
			return user;
		}
		catch(DataAccessException ex){
			System.out.println(ex.getMessage());
			return null;
		}
		
		
	}

	@Override
	public User findByName(String userName) {
		try{
			jdbcTemplate.execute(setSchemaQuery);
			User user = jdbcTemplate.queryForObject(selectUserByNameQuery, new Object[] { userName },new UserRowMapper());
			return user;
		}
		catch(DataAccessException ex){
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@Override
	public void setSearchPath(){
		jdbcTemplate.execute(setSchemaQuery);
	}
	
	private static class UserRowMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			User user = new User();
			user.setId(resultSet.getInt("user_id"));
			user.setUserName(resultSet.getString("user_name"));
			user.setLastUsageTime(resultSet.getTimestamp("last_usage_time"));
			
			return user;
		}
		
	}

}
