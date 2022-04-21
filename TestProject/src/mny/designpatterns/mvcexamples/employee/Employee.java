package mny.designpatterns.mvcexamples.employee;

public class Employee {
	
	public static class Build {
		private String name;
		private String id;
		private String department;
		
		public Build() {
		}

		public Build setName(String name) {
			this.name = name;
			return this;
		}

		public Build setId(String id) {
			this.id = id;
			return this;
		}

		public Build setDepartment(String department) {
			this.department = department;
			return this;
		}
		
		public Employee build() {
			Employee employee = new Employee();
			employee.setName(name);
			employee.setId(id);
			employee.setDepartment(department);
			
			return employee;
		}
		
	}
	
	private String name;
	private String id;
	private String department;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

}
