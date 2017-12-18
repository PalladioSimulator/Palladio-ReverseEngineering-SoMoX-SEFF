/**
 *
 */
package org.somox.test.database;

/**
 * @author Snowball
 *
 */
public class Database implements IDatabase {

    /*
     * (non-Javadoc)
     *
     * @see org.somox.test.database.IDatabase#updateEmployee(org.somox.test.database.EmployeeTO)
     */
    @Override
    public void updateEmployee(final EmployeeTO e) {
        System.out.println("DB updates employee");
    }

    /*
     * (non-Javadoc)
     *
     * @see org.somox.test.database.IDatabase#updateStatement(org.somox.test.database.StatementTO)
     */
    @Override
    public void updateStatement(final StatementTO s) {
        System.out.println("DB updates statement");
    }

}
