package org.somox.test.logic;

import org.somox.test.database.EmployeeTO;
import org.somox.test.database.IDatabase;
import org.somox.test.framework.CBFramework;

public class EmployeeManagement implements IEmployeeManagement {
    private IDatabase requiredDB = null;

    public EmployeeManagement() {
        this.requiredDB = CBFramework.instanciate("org.somox.test.database.Database", IDatabase.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.somox.test.logic.IEmployeeManagement#addStatement(org.somox.test.database.StatementTO)
     */
    @Override
    public void addEmployee(final EmployeeTO e) {
        System.out.println("Executing employee update in logic");
        this.requiredDB.updateEmployee(e);
    }
}
