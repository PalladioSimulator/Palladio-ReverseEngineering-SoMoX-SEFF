package org.somox.test.logic;

import org.somox.test.database.EmployeeTO;
import org.somox.test.database.StatementTO;
import org.somox.test.framework.CBFramework;

public class LogicFacade implements ILogic {
	
	IAccounting accounting = null;
	IEmployeeManagement employeeManagement = null;
	
	public LogicFacade() {
		this.accounting = CBFramework.instanciate("org.somox.test.logic.Accounting",IAccounting.class);
		this.employeeManagement = CBFramework.instanciate("org.somox.test.logic.EmployeeManagement",IEmployeeManagement.class);
	}
	
	public void addEmployee(EmployeeTO e){
		employeeManagement.addEmployee(e);
	}
	
	public void addStatement(StatementTO s) {
		accounting.addStatement(s);
	}
}
