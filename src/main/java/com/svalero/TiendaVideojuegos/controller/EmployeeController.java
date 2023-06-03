package com.svalero.TiendaVideojuegos.controller;

import com.svalero.TiendaVideojuegos.Util.ErrorMessage;
import com.svalero.TiendaVideojuegos.domain.Employee;
import com.svalero.TiendaVideojuegos.domain.dto.EmployeeInDTO;
import com.svalero.TiendaVideojuegos.domain.dto.EmployeeOutDTO;
import com.svalero.TiendaVideojuegos.exception.EmployeeNotFoundException;
import com.svalero.TiendaVideojuegos.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeOutDTO>> getEmployees(@RequestParam Map<String, String> map) throws EmployeeNotFoundException {
        logger.info("GET Employee");


        if (map.isEmpty()) {
            logger.info("Showing all employees");
            return ResponseEntity.ok(employeeService.findAll());
        } else if(map.containsKey("id")) {
            logger.info("Id: " + map.get("id"));
            List<EmployeeOutDTO> employees = new ArrayList<>();
            employees.add(employeeService.findById(Long.parseLong(map.get(("id")))));
            logger.info("Showing all employees by ID");
            return ResponseEntity.ok(employees);
        }else if(map.containsKey("name")) {
            logger.info("Name: " + map.get("name"));
            List<EmployeeOutDTO> employees = employeeService.findByName(map.get(("name")));
            logger.info("Showing all employees by name");
            return ResponseEntity.ok(employees);
        } else if(map.containsKey("email")) {
            logger.info("Email: " + map.get("email"));
            List<EmployeeOutDTO> employees = employeeService.findByEmail(map.get(("email")));
            logger.info("Showing all employees by email");
            return ResponseEntity.ok(employees);
        } else if((map.containsKey("boss"))) {
            if (map.get("boss").equals("true")) {
                List<EmployeeOutDTO> employees = employeeService.findByBoss(Boolean.TRUE);
                logger.info("Showing all employees by Boss TRUE");
                return ResponseEntity.ok(employees);
            }
            if (map.get("boss").equals("false")) {
                List<EmployeeOutDTO> employees = employeeService.findByBoss(Boolean.FALSE);
                logger.info("Showing all employees by Boss FALSE");
                return ResponseEntity.ok(employees);
            } else {
                logger.error("Bad Request 1 ");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        logger.info("Bad Request");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeInDTO employeeInDTO){
        logger.info("POST Adding employee");
        Employee employee = employeeService.addEmployee(employeeInDTO);
        logger.info("POST END");
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long id) throws EmployeeNotFoundException {
        logger.info("DELETE employee");
        employeeService.deleteEmployee(id);
        logger.info("DELETE END");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> modifyEmployee(@PathVariable long id, @RequestBody Employee employee) throws EmployeeNotFoundException {
        logger.info("PUT modify employee");

        Employee modEmployee = employeeService.modifyEmployee(id, employee);
        logger.info("PUT END");

        return ResponseEntity.status(HttpStatus.OK).body(modEmployee);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEmployeeNotFoundException(EmployeeNotFoundException enfe) {
        logger.error(enfe.getMessage(), enfe);
        ErrorMessage errorMessage = new ErrorMessage(404, enfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
