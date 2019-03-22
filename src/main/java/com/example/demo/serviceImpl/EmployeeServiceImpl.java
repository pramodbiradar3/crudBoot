package com.example.demo.serviceImpl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.utility.JpaResponseUtils;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	Employee employee = null;

	@Override
	public Employee saveEmployee() {
		Employee employee = new Employee();
		employee.setFirstName("FirstName");
		employee.setLastName("LastName");
		
		Address address1 = new Address();
		address1.setAddress1("Emp_Address1");
		address1.setAddress2("Emp_Address2");
		address1.setCity("Emp_City1");
		address1.setState("Emp_State1");
		address1.setZip("Emp_State1");
		
		Address address2 = new Address();
		address2.setAddress1("Emp_Address3");
		address2.setAddress2("Emp_Address4");
		address2.setCity("Emp_City2");
		address2.setState("Emp_State2");
		address2.setZip("Emp_State2");
		
		java.util.Set<Address> addressList = new HashSet<>();
		addressList.add(address1);
		addressList.add(address2);
		employee.setAddress(addressList);
		
		employee.getAddress().stream().forEach(d->{
			d.setEmployee(employee);
		});
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Integer employeeId) {
			try {
				employee = null;
				employee = JpaResponseUtils.optionalToObject(employeeRepository.findById(employeeId), Employee.class); 
				
				if(employee!=null) {
					Address address3 = new Address();
					address3.setAddress1("Emp_Address5");
					address3.setAddress2("Emp_Address6");
					address3.setCity("Emp_City3");
					address3.setState("Emp_State3");
					address3.setZip("Emp_State3");
					
					java.util.Set<Address> addressList = employee.getAddress();
					
					employee.getAddress().clear();
					employee.setAddress(addressList);
					employee.getAddress().add(address3);
					employee.getAddress().stream().forEach(d->{
						d.setEmployee(employee);
					});
					return employeeRepository.save(employee);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
}
