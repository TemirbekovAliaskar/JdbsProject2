package org.example.Dao.impl;

import org.example.Dao.JobDao;
import org.example.config.JdbcConfig;
import org.example.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao{

    private final Connection connection = JdbcConfig.getConnection();
    @Override
    public void createJobTable() {
        String query = """
    create table jobs (
    id serial primary key,
    position varchar,
    profession varchar,
    description varchar,
    experience int
    );
    """;
        try (Statement statement = connection.createStatement()){
            statement.execute(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addJob(Job job) {
        String queru = """
                insert into jobs (position,profession,description,experience)
                values (?,?,?,?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(queru)){
            preparedStatement.setString(1,job.getPosition());
            preparedStatement.setString(2,job.getProfession());
            preparedStatement.setString(3,job.getDescription());
            preparedStatement.setInt(4,job.getExperience());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Job getJobById(Long jobId) {
        Job job = new Job();
        String query = "select * from jobs where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        String query = "select * from jobs order by experience asc ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
                jobs.add(job);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return jobs;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = new Job();
        String query = """
                select * from jobs j inner join employees e on j.id = e.job_id where e.id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setExperience(resultSet.getInt("experience"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String query = "alter table jobs drop column description";
        try (Statement statement = connection.createStatement()){
              statement.executeUpdate(query);
            System.out.println("Deleted !");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
