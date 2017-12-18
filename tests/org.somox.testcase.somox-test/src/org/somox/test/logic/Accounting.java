package org.somox.test.logic;

import org.somox.test.database.IDatabase;
import org.somox.test.database.StatementTO;
import org.somox.test.framework.CBFramework;

public class Accounting implements IAccounting {
    IDatabase requiredDB = null;

    public Accounting() {
        this.requiredDB = CBFramework.instanciate("org.somox.test.database.Database", IDatabase.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.test.logic.IAccounting#addEmployee(org.somox.test.database.EmployeeTO)
     */
    @Override
    public void addStatement(final StatementTO s) {
        System.out.println("Executing statement update in logic");
        this.requiredDB.updateStatement(s);
    }
}
