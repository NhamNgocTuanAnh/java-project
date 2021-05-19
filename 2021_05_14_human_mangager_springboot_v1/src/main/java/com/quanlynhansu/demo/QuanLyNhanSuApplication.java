package com.quanlynhansu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

// @EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
// 		DataSourceTransactionManagerAutoConfiguration.class })
public class QuanLyNhanSuApplication {

	// @Autowired
	// private EmployeeDao employeeDao;

	public static void main(String[] args) {
		SpringApplication.run(QuanLyNhanSuApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// Employee employee = getEmployee();
	// employeeDao.createEmployee(employee);
	// }

	// private Employee getEmployee() {
	// Employee employee = new Employee();
	// employee.setUsername("Sean Murphy");
	// employee.setPassword("mk");

	// return employee;
	// }
}
