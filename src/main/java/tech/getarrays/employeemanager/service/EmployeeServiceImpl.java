package tech.getarrays.employeemanager.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.exception.UserNotFoundException;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteEmployeeById(id);
    }
}
