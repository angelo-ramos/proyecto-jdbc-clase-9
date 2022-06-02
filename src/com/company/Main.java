package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Configurando conexion: Inicio
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //Configurando conexion: Fin


        System.out.println("Bienvenido al sistema");
        Scanner sc = new Scanner(System.in);

        GestorEmpleados gestorEmpleados = new GestorEmpleados();
        //if(gestorEmpleados.login()){
        //    System.out.println("acceso concedido");
        //    ArrayList<Employee> listaEmpleados = gestorEmpleados.listarEmpleados();


        /*
        System.out.print("Ingrese la letra a buscar: ");
        String letra = sc.nextLine();

        ArrayList<Employee> listaEmpleados = gestorEmpleados.buscarEmpleadoPorLetra(letra);

            //Imprimiendo los empleados
            for (Employee e : listaEmpleados) {
                System.out.println("id: " + e.getId() + " | nombre: " + e.getFirstName() + " | apellido: " + e.getLastName());
            }*/

        /*}else{
            System.out.println("credenciales erroneas :(");
        }*/


        /*
        Job job = new Job();
        System.out.println("Ingrese los datos del trabajo: ");
        System.out.print("Job id: ");
        job.setJobId(sc.nextLine());

        System.out.print("Job title: ");
        job.setJobTitle(sc.nextLine());

        System.out.print("min salary: ");
        job.setMinSalary(Integer.parseInt(sc.nextLine()));

        System.out.print("max salary: ");
        job.setMaxSalary(Integer.parseInt(sc.nextLine()));

        GestorTrabajos gestorTrabajos = new GestorTrabajos();
        gestorTrabajos.insertarJob(job);
        System.out.println("Trabajo creado exitosamente"); */


        /*
        Employee employee = new Employee();
        System.out.print("nombre: ");
        employee.setFirstName(sc.nextLine());
        System.out.print("apellido: ");
        employee.setLastName(sc.nextLine());

        GestorEmpleados ge = new GestorEmpleados();
        ge.crearEmpleado(employee);

        System.out.println("Empleado creado exitosamente");*/


        Employee employee = new Employee();
        System.out.print("nombre: ");
        employee.setFirstName(sc.nextLine());
        System.out.print("apellido: ");
        employee.setLastName(sc.nextLine());

        GestorEmpleados ge = new GestorEmpleados();
        int idCreado = ge.crearEmpleadoDevolverIdCreado(employee);
        System.out.println("id Creado: "+idCreado);
    }
}
