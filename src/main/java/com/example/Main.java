package com.example;

import com.example.repository.ProductRepository;
import com.example.repository.ClientRepository;
import com.example.repository.EmployeeRepository;

import com.example.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Scanner;
import java.util.Optional;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EntityManager em;
    private static ProductRepository productRepo;
    private static ClientRepository clientRepo;
    private static EmployeeRepository employeeRepo;

    public static void main(String[] args) {
        em = JpaUtil.getEntityManager();
        productRepo = new ProductRepository(em);
        clientRepo = new ClientRepository(em);
        employeeRepo = new EmployeeRepository(em);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar Productos");
            System.out.println("2. Gestionar Clientes");
            System.out.println("3. Gestionar Empleados");

            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1 -> menuProducts();
                case 2 -> menuClients();
                case 3 -> menuEmployees();

                case 0 -> exit = true;
                default -> System.out.println("Opción no válida.");
            }
        }
        JpaUtil.close();
        System.out.println("Saliendo...");
    }

    private static void menuProducts() {
        System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        switch (opt) {
            case 1 -> {
                System.out.print("Nombre: ");
                String name = scanner.nextLine();
                System.out.print("Precio: ");
                double price = scanner.nextDouble();
                System.out.print("Stock: ");
                int stock = scanner.nextInt();
                productRepo.save(new Product(name, price, stock));
                System.out.println("Producto guardado.");
            }
            case 2 -> productRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                Optional<Product> p = productRepo.findById(id);
                p.ifPresentOrElse(prod -> {
                    System.out.print("Nuevo nombre: ");
                    prod.setName(scanner.nextLine());
                    System.out.print("Nuevo precio: ");
                    prod.setPrice(scanner.nextDouble());
                    System.out.print("Nuevo stock: ");
                    prod.setStock(scanner.nextInt());
                    productRepo.update(prod);
                    System.out.println("Producto actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                long id = scanner.nextLong();
                productRepo.findById(id).ifPresentOrElse(prod -> {
                    productRepo.delete(prod);
                    System.out.println("Producto eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuClients() {
        System.out.println("\n--- GESTIÓN DE CLIENTES ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        switch (opt) {
            case 1 -> {
                System.out.print("Nombre: ");
                String name = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Teléfono: ");
                String phone = scanner.nextLine();
                clientRepo.save(new Client(name, email, phone));
                System.out.println("Cliente guardado.");
            }
            case 2 -> clientRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                clientRepo.findById(id).ifPresentOrElse(c -> {
                    System.out.print("Nuevo nombre: ");
                    c.setName(scanner.nextLine());
                    System.out.print("Nuevo email: ");
                    c.setEmail(scanner.nextLine());
                    System.out.print("Nuevo teléfono: ");
                    c.setPhone(scanner.nextLine());
                    clientRepo.update(c);
                    System.out.println("Cliente actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                long id = scanner.nextLong();
                clientRepo.findById(id).ifPresentOrElse(c -> {
                    clientRepo.delete(c);
                    System.out.println("Cliente eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuEmployees() {
        System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        switch (opt) {
            case 1 -> {
                System.out.print("Nombre: ");
                String name = scanner.nextLine();
                System.out.print("Cargo: ");
                String pos = scanner.nextLine();
                System.out.print("Salario: ");
                double sal = scanner.nextDouble();
                employeeRepo.save(new Employee(name, pos, sal));
                System.out.println("Empleado guardado.");
            }
            case 2 -> employeeRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                employeeRepo.findById(id).ifPresentOrElse(e -> {
                    System.out.print("Nuevo nombre: ");
                    e.setName(scanner.nextLine());
                    System.out.print("Nuevo cargo: ");
                    e.setPosition(scanner.nextLine());
                    System.out.print("Nuevo salario: ");
                    e.setSalary(scanner.nextDouble());
                    employeeRepo.update(e);
                    System.out.println("Empleado actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                long id = scanner.nextLong();
                employeeRepo.findById(id).ifPresentOrElse(e -> {
                    employeeRepo.delete(e);
                    System.out.println("Empleado eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

}