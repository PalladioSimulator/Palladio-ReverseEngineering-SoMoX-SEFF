package org.somox.test.client;

import org.somox.test.database.EmployeeTO;
import org.somox.test.database.StatementTO;
import org.somox.test.framework.CBFramework;
import org.somox.test.logic.ILogic;

public class Client {
	private static final String LOGIC_INSTANCE_NAME = "org.somox.test.logic.LogicFacade";
	ILogic businessLogic = null;
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		ILogic bl = CBFramework.instanciate(LOGIC_INSTANCE_NAME, ILogic.class);
		new Client().setBusinessLogic(bl).run();
	}

	private Client setBusinessLogic(ILogic bl) {
		this.businessLogic = bl;
		return this;
	}

	private void run() {
		System.out.println("Executing client");
		businessLogic.addEmployee(new EmployeeTO());
		businessLogic.addStatement(new StatementTO());
	}
}
