import enums.TYPE_TRANSACTION;
import models.TransactionOperation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionOperationTest {

    private static final double AMOUNT_300 = 300.0;
    private static final double AMOUNT_400 = 400.0;
    private static final double AMOUNT_500 = 500.0;
    private static final double AMOUNT_600 = 600.0 ;

    private static final LocalDate LOCAL_DATE_03 = LocalDate.of(2023,04,03) ;
    private static final LocalDate LOCAL_DATE_07 = LocalDate.of(2023,04,07) ;

    @Test
    @DisplayName("Test the class TransactionOperation and its attributes for deposit operation")
    public void TransactionOperationClassTest() {
        // GIVEN
        TransactionOperation transactionOperation = new TransactionOperation(AMOUNT_300,AMOUNT_500,LOCAL_DATE_03,TYPE_TRANSACTION.DEPOSIT);

        // THEN
        assertEquals(AMOUNT_300,transactionOperation.getAmount());
        assertEquals(AMOUNT_500,transactionOperation.getBalanceTotal());
        assertEquals(LOCAL_DATE_03,transactionOperation.getDateTransaction());
        assertEquals(TYPE_TRANSACTION.DEPOSIT,transactionOperation.getTypeTransaction());
    }

    @Test
    @DisplayName("Test the class TransactionOperation and its attributes for withdraw operation")
    public void TransactionOperationWithDrawTest() {

        // GIVEN
        TransactionOperation transactionOperation = new TransactionOperation(AMOUNT_400,AMOUNT_600,LOCAL_DATE_07,TYPE_TRANSACTION.WITH_DRAW);

        // THEN
        assertEquals(AMOUNT_400,transactionOperation.getAmount());
        assertEquals(AMOUNT_600,transactionOperation.getBalanceTotal());
        assertEquals(LOCAL_DATE_07,transactionOperation.getDateTransaction());
        assertEquals(TYPE_TRANSACTION.WITH_DRAW,transactionOperation.getTypeTransaction());
    }
}
