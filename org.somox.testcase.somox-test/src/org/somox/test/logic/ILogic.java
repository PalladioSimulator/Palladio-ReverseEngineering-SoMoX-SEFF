package org.somox.test.logic;

import org.somox.test.database.EmployeeTO;
import org.somox.test.database.StatementTO;

public interface ILogic {

    public void addEmployee(EmployeeTO e);

    public void addStatement(StatementTO s);

}
