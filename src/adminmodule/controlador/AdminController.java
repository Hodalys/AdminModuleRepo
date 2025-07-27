
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
        Doctor nuevoDoctor = new Doctor(
            cedula, nombres, apellidos, fechaNacimiento,
            sexo, correo, contrasena, "doctor", especialidad
        );
        return doctorDAO.guardar(nuevoDoctor);
    }

    public List<Doctor> obtenerTodosDoctores() {
        return doctorDAO.obtenerTodos();
    }

    public List<Paciente> obtenerTodosPacientes() {
        return new PacienteDAO().obtenerTodos();
    }
}