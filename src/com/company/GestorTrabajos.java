package com.company;

import java.sql.*;

public class GestorTrabajos {

    String user = "root";
    String pass = "root";
    String ip = "localhost";
    String url = "jdbc:mysql://" + ip + ":3306/hr";

    public void insertarJob(Job job){
        String sql = "insert into jobs (job_id, job_title, min_salary, max_salary) VALUES (?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,job.getJobId());
            pstmt.setString(2,job.getJobTitle());
            pstmt.setInt(3,job.getMinSalary());
            pstmt.setInt(4,job.getMaxSalary());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}