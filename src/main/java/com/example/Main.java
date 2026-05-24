package com.example;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import com.example.repository.DetallePedidoRepository;
import com.example.repository.PedidoRepository;
import com.example.repository.ProductoRepository;
import com.example.repository.ProveedorRepository;
import com.example.repository.UsuarioRepository;
import com.example.util.JpaUtil;

import jakarta.persistence.EntityManager;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EntityManager em;
    private static DetallePedidoRepository detallePedidoRepo;
    private static PedidoRepository pedidoRepo;
    private static ProductoRepository productoRepo;
    private static ProveedorRepository proveedorRepo;
    private static UsuarioRepository usuarioRepo;

    public static void main(String[] args) {
        em = JpaUtil.getEntityManager();
        detallePedidoRepo = new DetallePedidoRepository(em);
        pedidoRepo = new PedidoRepository(em);
        productoRepo = new ProductoRepository(em);
        proveedorRepo = new ProveedorRepository(em);
        usuarioRepo = new UsuarioRepository(em);
    

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar Productos");
            System.out.println("2. Gestionar Usuarios");
            System.out.println("3. Gestionar Pedidos");
            System.out.println("4. Gestionar Proveedores");
            System.out.println("5. Gestionar Detalles de Pedido");

            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1 -> menuProductos();
                case 2 -> menuUsuarios();
                case 3 -> menuPedidos();
                case 4 -> menuProveedores();
                case 5 -> menuDetallesPedido();

                case 0 -> exit = true;
                default -> System.out.println("Opción no válida.");
            }
        }
        JpaUtil.close();
        System.out.println("Saliendo...");
    }

    private static void menuProductos() {
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
                String nombre = scanner.nextLine();
                System.out.print("Descripción: ");
                String descripcion = scanner.nextLine();
                System.out.print("Precio: ");
                double precio = scanner.nextDouble();
                System.out.print("Stock: ");
                int stock = scanner.nextInt();
                productoRepo.save(new Producto(nombre, descripcion, precio, stock));
                System.out.println("Producto guardado.");
            }
            case 2 -> productoRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                Optional<Producto> p = productoRepo.findById(id);
                p.ifPresentOrElse(prod -> {
                    System.out.print("Nuevo nombre: ");
                    prod.setNombre(scanner.nextLine());
                    System.out.print("Nueva descripción: ");
                    prod.setDescripcion(scanner.nextLine());
                    System.out.print("Nuevo precio: ");
                    prod.setPrecio(scanner.nextDouble());
                    System.out.print("Nuevo stock: ");
                    prod.setStock(scanner.nextInt());
                    productoRepo.update(prod);
                    System.out.println("Producto actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                long id = scanner.nextLong();
                productoRepo.findById(id).ifPresentOrElse(prod -> {
                    productoRepo.delete(prod);
                    System.out.println("Producto eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuUsuarios() {
        System.out.println("\n--- GESTIÓN DE USUARIOS ---");
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
                String nombre = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.err.println("Contraseña: ");
                String contraseña = scanner.nextLine();
                System.out.print("Dirección: ");
                String direccion = scanner.nextLine();
                System.out.print("Teléfono: ");
                String telefono = scanner.nextLine();
                usuarioRepo.save(new Usuario(nombre, email, contraseña, direccion, telefono));
                System.out.println("Cliente guardado.");
            }
            case 2 -> usuarioRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                usuarioRepo.findById(id).ifPresentOrElse(u -> {
                    System.out.print("Nuevo nombre: ");
                    u.setNombre(scanner.nextLine());
                    System.out.print("Nuevo email: ");
                    u.setEmail(scanner.nextLine());
                    System.out.print("Nueva contraseña: ");
                    u.setContaseña(scanner.nextLine());
                    System.out.print("Nueva dirección: ");
                    u.setDireccion(scanner.nextLine());
                    System.out.print("Nuevo teléfono: ");
                    u.setTelefono(scanner.nextLine());
                    usuarioRepo.update(u);
                    System.out.println("Cliente actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                long id = scanner.nextLong();
                usuarioRepo.findById(id).ifPresentOrElse(u -> {
                    usuarioRepo.delete(u);
                    System.out.println("Cliente eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuPedidos() {
        System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        switch (opt) {
            case 1 -> {
                System.out.print("Fecha: ");
                Date fecha = new Date();
                System.out.print("Total: ");
                Double total = scanner.nextDouble();
                System.out.print("Estado: ");
                String estado = scanner.nextLine();
                pedidoRepo.save(new Pedido(fecha, total, estado));
                System.out.println("Pedido guardado.");
            }
            case 2 -> pedidoRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                pedidoRepo.findById(id).ifPresentOrElse(p -> {
                    System.out.print("Nueva fecha: ");
                    p.setFecha(new Date());
                    System.out.print("Nuevo total: ");
                    p.setTotal(scanner.nextDouble());
                    System.out.print("Nuevo estado: ");
                    p.setEstado(scanner.nextLine());
                    pedidoRepo.update(p);
                    System.out.println("Pedido actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                long id = scanner.nextLong();
                pedidoRepo.findById(id).ifPresentOrElse(p -> {
                    pedidoRepo.delete(p);
                    System.out.println("Pedido eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuProveedores() {
        System.out.println("\n--- GESTIÓN DE PROVEEDORES ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        switch (opt) {
            case 1 -> {
                System.out.print("NombreEmpresa: ");
                String nombreEmpresa = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Teléfono: ");
                String telefono = scanner.nextLine();
                proveedorRepo.save(new Proveedor(nombreEmpresa, email, telefono));
                System.out.println("Proveedor guardado.");
            }
            case 2 -> proveedorRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                proveedorRepo.findById(id).ifPresentOrElse(p -> {
                    System.out.print("Nuevo nombreEmpresa: ");
                    p.setNombreEmpresa(scanner.nextLine());
                    System.out.print("Nuevo email: ");
                    p.setEmail(scanner.nextLine());
                    System.out.print("Nuevo teléfono: ");
                    p.setTelefono(scanner.nextLine());
                    proveedorRepo.update(p);
                    System.out.println("Proveedor actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                long id = scanner.nextLong();
                proveedorRepo.findById(id).ifPresentOrElse(p -> {
                    proveedorRepo.delete(p);
                    System.out.println("Proveedor eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuDetallesPedido() {
        System.out.println("\n--- GESTIÓN DE DETALLES DE PEDIDO ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        switch (opt) {
            case 1 -> {
                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();
                System.out.print("PrecioUnitario: ");
                double precioUnitario = scanner.nextDouble();
                detallePedidoRepo.save(new DetallePedido(cantidad, precioUnitario));
                System.out.println("Detalle de pedido guardado.");
            }
            case 2 -> detallePedidoRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                detallePedidoRepo.findById(id).ifPresentOrElse(d -> {
                    System.out.print("Nueva cantidad: ");
                    d.setCantidad(scanner.nextInt());
                    System.out.print("Nuevo precio unitario: ");
                    d.setPrecioUnitario(scanner.nextDouble());
                    detallePedidoRepo.update(d);
                    System.out.println("Detalle de pedido actualizado.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                long id = scanner.nextLong();
                detallePedidoRepo.findById(id).ifPresentOrElse(d -> {
                    detallePedidoRepo.delete(d);
                    System.out.println("Detalle de pedido eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

}