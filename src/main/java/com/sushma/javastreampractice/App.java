package com.sushma.javastreampractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		List<Employee> employeeList = Arrays.asList(new Employee("Amit", 20000), new Employee("Priya", 22000),
				new Employee("Sukanya", 20000), new Employee("Rahul", 25000), new Employee("Kiran", 25000));

		// employeeList.stream().collect(Collector.)
		Map<Integer, Long> salaryCountMap = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getSalary, Collectors.counting()));
		System.out.println(salaryCountMap);

		long employeeCount = employeeList.stream().count();

		System.out.println("Employee count: " + employeeCount);

		List<String> employeeNames = employeeList.stream().map(Employee::getName).toList();

		System.out.println("Employee names: " + employeeNames);

		List<Employee> salaryFilterList = employeeList.stream().filter(n -> n.getSalary() > 20000).toList();
		salaryFilterList.forEach(System.out::println);

		employeeList.stream().filter(e -> e.getSalary() > 20000).forEach(System.out::println);

		Long count = employeeList.stream().filter(n -> n.getSalary() == 20000).count();

		System.out.println(count);

		List<Employee> empl = employeeList.stream().toList();

		empl.forEach(System.out::println);

		Integer totalSalary = employeeList.stream().map(n -> n.getSalary()).reduce(0, Integer::sum);

		System.out.println("total salary: " + totalSalary);
		
		Integer totalsalary1 = employeeList.stream().collect(Collectors.reducing(0,Employee::getSalary,Integer::sum));
		
		System.out.println("total salary: " + totalsalary1);

		Double average = employeeList.stream().mapToDouble(n -> n.getSalary()).average().orElse(0.0);
		
		Double average1= employeeList.stream().collect(Collectors.averagingInt(Employee::getSalary));

		System.out.println("average: " + average);
		
		System.out.println("average1: " + average1);

		Double averageDouble = employeeList.stream().collect(Collectors.averagingInt(n -> n.getSalary()));

		System.out.println("average: " + averageDouble);

		Integer highestSalary = employeeList.stream().map(Employee::getSalary).max(Integer::compareTo).orElse(0);

		System.out.println("highestSalary: " + highestSalary);
		
		Employee highestsalaryEmp = employeeList.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary))).orElse(null);
		
		System.out.println("highestsalaryEmp: " + highestsalaryEmp);

		List<Employee> highestSalaryEmployee = employeeList.stream().filter(n -> n.getSalary().equals(highestSalary))
				.toList();

		System.out.println("highestSalaryEmployees: " + highestSalaryEmployee);

		Integer lowestSalary = employeeList.stream().map(Employee::getSalary).min(Integer::compareTo).orElse(0);

		System.out.println("lowestSalary: " + lowestSalary);

		List<Employee> lowestSalaryEmployee = employeeList.stream().filter(n -> n.getSalary().equals(lowestSalary))
				.toList();

		System.out.println("lowestSalaryEmployees : " + lowestSalaryEmployee);

		List<Employee> ascSorting = employeeList.stream().sorted(Comparator.comparingInt(Employee::getSalary)).toList();

		System.out.println("ascSorting : " + ascSorting);

		List<Employee> descSorting = employeeList.stream()
				.sorted(Comparator.comparingInt(Employee::getSalary).reversed()).toList();

		System.out.println("descSorting: " + descSorting);

		List<Employee> namesorting = employeeList.stream().sorted(Comparator.comparing(Employee::getName)).toList();
		
		Integer secondHighestSalary = employeeList.stream().map(Employee::getSalary)
				.sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(0);

		System.out.println("namesorting: " + namesorting);

		List<Employee> namesortingDes = employeeList.stream().sorted(Comparator.comparing(Employee::getName).reversed())
				.toList();

		System.out.println("namesortingDes: " + namesortingDes);

		Map<Integer, List<Employee>> groupingBySalary = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getSalary));

		System.out.println("groupingBySalary: " + groupingBySalary);

		Map<Integer, List<String>> groupingBySalary1 = employeeList.stream().collect(
				Collectors.groupingBy(Employee::getSalary, Collectors.mapping(Employee::getName, Collectors.toList())));

		System.out.println("groupingBySalary1: " + groupingBySalary1);

		Map<Integer, Long> emplyeesCountBySalary = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getSalary, Collectors.counting()));
		
		System.out.println("emplyeesCountBySalary: " + emplyeesCountBySalary);
		
		Map<Boolean,List<Employee>> partionEx=employeeList.stream()
				.collect(Collectors.partitioningBy(n->n.getSalary()>21000));
		
		System.out.println("partionEx: " + partionEx);
		
		System.out.println("true  -> High salary employees: " + partionEx.get(true));
		System.out.println("false -> Others: " + partionEx.get(false));
		
		Employee secondHighestSalaryEmp= employeeList.stream()
				.sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(2).findFirst().orElse(null);
		System.out.println("secondHighestSalaryEmp: " + secondHighestSalaryEmp);
		
		Integer secondHighestSalary1= employeeList.stream().map(Employee::getSalary)
				.distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(0);
		
		System.out.println("secondHighestSalary1: " + secondHighestSalary1);
		
		List<Integer> distinctSalary=employeeList.stream().map(Employee::getSalary).distinct().toList();
		
		System.out.println("distinctSalary: " + distinctSalary);
		
		List<Employee> salaryGreaterthan = employeeList.stream().filter(n->n.getSalary()>24000).toList();
		
		System.out.println("salaryGreaterthan: " + salaryGreaterthan);
		
		String joiningOnName=employeeList.stream().map(Employee::getName).collect(Collectors.joining(","));
		
		System.out.println("joiningOnName: " + joiningOnName);		
		
		Map<String,Integer> convertedToMap = employeeList.stream()
				.collect(Collectors.toMap(Employee::getName,Employee::getSalary));
		
		System.out.println("convertedToMap: " + convertedToMap);
	}
	
}

class Employee {
	public String name;
	public Integer salary;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Employee(String name, Integer salary) {
		super();
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + "]";
	}

}
