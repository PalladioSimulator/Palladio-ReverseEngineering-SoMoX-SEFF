package org.somox.test.logic;

import org.somox.test.database.EmployeeTO;
import org.somox.test.database.StatementTO;
import org.somox.test.framework.CBFramework;

public class LogicFacade implements ILogic {

    IAccounting accounting = null;
    IEmployeeManagement employeeManagement = null;

    public LogicFacade() {
        this.accounting = CBFramework.instanciate("org.somox.test.logic.Accounting", IAccounting.class);
        this.employeeManagement = CBFramework.instanciate("org.somox.test.logic.EmployeeManagement",
                IEmployeeManagement.class);
    }

    @Override
    public void addEmployee(final EmployeeTO e) {
        this.employeeManagement.addEmployee(e);
    }

    @Override
    public void addStatement(final StatementTO s) {
        this.accounting.addStatement(s);
    }
}
