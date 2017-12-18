package org.somox.core;

import org.somox.core.furtherRequired.IRequiredExt1;
import org.somox.core.furtherRequired.IRequiredExt2;
import org.somox.core.subcore.MyClass1;
import org.somox.data.Data;
import org.somox.ui.UI;

public class Core implements IProvided, ICore {

    IRequired1 irq1;
    IRequired2 irq2;
    IRequiredExt1 irqe1;
    IRequiredExt2 irqe2;

    Data d = new Data();
    UI ui = new UI();
    MyClass1 c1 = new MyClass1();
    Helper h = new Helper();
    WithCore wc1 = new WithCore();
    WithCore wc2 = new WithCore();

    @Override
    public void doSth() {
        this.irq1.doSth();
        this.irq2.doSth();
        this.irqe1.doSth();
        this.irqe2.doSth();
        this.irqe2.doSth();
        if (this.d.equals(this.c1)) {
            this.irqe2.doSth();
        } else {
            this.irqe2.doSth();
        }
        this.wc1.withCore();
        this.wc1.withCore2();
        this.wc2.withCore();
        this.wc2.withCore2();

    }

    @Override
    public void doSthElse() {
        for (int x = 0; x < 20; x++) {
            this.irq1.doSth();
        }

        for (int x = 0; x < 30; x++) {
            this.irq1.doSth();

            for (int y = 0; y < 3; y++) {
                this.irqe1.doSth();
            }
        }

        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 3; y++) {
                if (true) {
                    this.irqe2.doSth();
                }
            }
        }
    }

    @Override
    public void doProvided() {
        this.h.execHelper();
        if (true) {
            this.irq2.doSth();
            for (int x = 0; x < 2; x++) {
                this.irq1.doSth();
                this.h.execHelper2(x);
            }
        }
        if (true) {
            this.irq2.doSth();
            this.wc1.withCore2(); // results in calling doSth
        }
    }

    @Override
    public Object doProvExceptionTest() {
        final IRequired1 localReq = null;
        final IRequiredExt2 localReq2 = null;
        localReq.doSthReq3();

        try {
            // localReq = localReq.getRequired();
            localReq.doSthReq2();
            for (int i = 0; i < 10; i++) {
                localReq.doSthReq3();
            }
            localReq.doSthReq2();
        } catch (final RuntimeException e) {
            localReq.doSthReq4();
        } finally {
            localReq2.doSth();
        }

        this.checkSth();

        return null;
        /*
         * ComplexOrderTO result = null;
         *
         * PersistenceContext pctx = persistmanager.getPersistenceContext(); TransactionContext tx =
         * null;
         *
         * try { tx = pctx.getTransactionContext(); tx.beginTransaction();
         *
         * ProductOrder order = storequery.queryOrderById(orderId, pctx); result =
         * FillTransferObjects.fillComplexOrderTO(order); tx.commit(); } catch (RuntimeException e)
         * { if (tx != null && tx.isActive()) tx.rollback(); e.printStackTrace(); throw e; // or
         * display error message } finally { pctx.close(); } return result;
         */
    }

    private void checkSth() {
        final IRequired1 localReq = null;
        localReq.doSthReq4();
    }

    @Override
    public IProvided getProvided() {
        // TODO Auto-generated method stub

        return null;
    }
}
