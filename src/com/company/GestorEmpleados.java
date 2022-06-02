package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorEmpleados {

    String user = "root";
    String pass = "root";
    String ip = "localhost";
    String url = "jdbc:mysql://" + ip + ":3306/hr";   //Aqui cambiar hr por la base de datos de nuestro proyecto

    public boolean login(){
        System.out.print("Ingrese su username: ");
        Scanner sc = new Scanner(System.in);
        String firstName = sc.nextLine();
        System.out.print("Ingrese su password: ");
        String lastName = sc.nextLine();

        //Haciendo consultas (try con recursos para sentencias closeables)
        String sql = "select * from employees where first_name = ? and last_name = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            //Nota para resultset: insert, update, delete --> executeUpdate(sql);

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);

            try (ResultSet resultSet0 = pstmt.executeQuery()) {

                return resultSet0.next();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Employee> listarEmpleados(){
        //Creando lista empleados
        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from employees");

            //Listando todos
            while (resultSet.next()) {
                //Almacenando los empleados en una lista:
                Employee e = new Employee();
                e.setId(resultSet.getInt(1));
                e.setFirstName(resultSet.getString("first_name"));
                e.setLastName(resultSet.getString(3));
                listaEmpleados.add(e); //Aqui agrego al empleado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }

    public ArrayList<Employee> buscarEmpleadoPorLetra(String letra){
        //Creando lista empleados
        ArrayList<Employee> listaEmpleados = new ArrayList<>();

        String sql = "select * from employees where lower(first_name) like ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,"%" + letra + "%");

            try(ResultSet resultSet = pstmt.executeQuery()){
                //Listando todos
                while (resultSet.next()) {
                    //Almacenando los empleados en una lista:
                    Employee e = new Employee();
                    e.setId(resultSet.getInt(1));
                    e.setFirstName(resultSet.getString("first_name"));
                    e.setLastName(resultSet.getString(3));
                    listaEmpleados.add(e); //Aqui agrego al empleado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }

    public void crearEmpleado(Employee employee){    //Si el id es autoincremental pues ya no lo pongo, en values quito un ?
        String sql = "insert into employees (first_name, last_name, email, " +
                "phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id) " +
                "VALUES (?,?,?,?,now(),?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,employee.getFirstName());
            pstmt.setString(2,employee.getLastName());
            pstmt.setString(3,"prueba" + employee.getFirstName());
            pstmt.setString(4,"123");
            pstmt.setString(5,"IT_PROG");
            pstmt.setInt(6,20);
            pstmt.setNull(7,Types.DECIMAL);  //Asi se escribe el Null
            pstmt.setInt(8,100);
            pstmt.setInt(9,80);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int crearEmpleadoDevolverIdCreado(Employee employee){
        String sql = "insert into employees (first_name, last_name, email, " +
                "phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id) " +
                "VALUES (?,?,?,?,now(),?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1,employee.getFirstName());
            pstmt.setString(2,employee.getLastName());
            pstmt.setString(3,"prueba" + employee.getFirstName());
            pstmt.setString(4,"123");
            pstmt.setString(5,"IT_PROG");
            pstmt.setInt(6,20);
            pstmt.setNull(7,Types.DECIMAL);  //Asi se escribe el Null
            pstmt.setInt(8,100);
            pstmt.setInt(9,80);

            pstmt.executeUpdate();  //Para insertar los datos

            //Para obtener la llave o id del ultimo empleado creado, esto por si no se, se le pide a un usuario
            //que se registre y con esa llave se desea ingresar mas informacion en otras tablas
            //Esto es algo que usaremos en nuestro proyecto asi que estar atentos
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);   //Esto para verificar en caso haya un problema creando el id
            }else {                    //por ejemplo que no haya sido autoincremental y yo pensaba que si
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
