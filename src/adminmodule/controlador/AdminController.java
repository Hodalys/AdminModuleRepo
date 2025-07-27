
package adminmodule.controlador;

import adminmodule.dao.*;
import adminmodule.modelo.*;
import java.util.Date;
import java.util.List;

public class AdminController {
    private final DoctorDAO doctorDAO;

    public AdminController() {
        this.doctorDAO = new DoctorDAO();
    }

    public boolean registrarDoctor(String cedula, String nombres, String apellidos,
                                 Date fechaNacimiento, String sexo, String correo,
                                 String contrasena, String especialidad) {
        if (doctorDAO.existe(cedula)) {
            System.out.println("Error: Ya existe un médico con esa cédula.");
            return false;
        }
        
        Doctor nuevoDoctor = new Doctor(
            cedula, nombres, apellidos, fechaNacimiento,
            sexo, correo, contrasena, "doctor", especialidad
        );
        return doctorDAO.guardar(nuevoDoctor);
    }

    public List<Doctor> obtenerTodosDoctores() {
        return doctorDAO.obtenerTodos();
    }

    public boolean actualizarDoctor(String cedula, String nombres, String apellidos,
                                   Date fechaNacimiento, String sexo, String correo,
                                   String contrasena, String especialidad) {
    
        Doctor doctorExistente = doctorDAO.obtenerPorCedula(cedula);
        if (doctorExistente == null) {
            System.out.println("Error: No existe un médico con esa cédula.");
            return false;
        }

        doctorExistente.setNombres(nombres);
        doctorExistente.setApellidos(apellidos);
        doctorExistente.setFechaNacimiento(fechaNacimiento);
        doctorExistente.setSexo(sexo);
        doctorExistente.setCorreo(correo);
        doctorExistente.setContrasena(contrasena);
        doctorExistente.setEspecialidad(especialidad);

        return doctorDAO.actualizar(doctorExistente);
    }

    public boolean eliminarDoctor(String cedula, boolean confirmacion) {
        if (!confirmacion) {
            System.out.println("Acción de eliminar cancelada por el usuario.");
            return false;
        }

        Doctor doctorExistente = doctorDAO.obtenerPorCedula(cedula);
        if (doctorExistente == null) {
            System.out.println("Error: No existe un médico con esa cédula.");
            return false;
        }
        return doctorDAO.eliminar(cedula);
    }

    public List<Paciente> obtenerTodosPacientes() {
        return new PacienteDAO().obtenerTodos();
    }
}