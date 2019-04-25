/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.connector;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author User
 */
public class DbUtil {
 private static DbUtil dbUtil;
 private static BasicDataSource ds;
 
 private DbUtil() {
     ds = new BasicDataSource();
     ds.setUrl("jdbc:mysql://localhost:3306/autorent?useSSL=false&serverTimezone=UTC");
     ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
     ds.setUsername("root");
     ds.setPassword("toor");
 }
 
 public static Connection getConnection() throws SQLException {
     return ds.getConnection();     
 }
 
 public void close() throws SQLException {
     ds.close();
 }
 
 public static DbUtil getInstance() {
     if(dbUtil == null){
         dbUtil=new DbUtil();
     }
     return dbUtil;
 }
}
