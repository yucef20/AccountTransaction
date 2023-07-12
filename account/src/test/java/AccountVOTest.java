import models.AccountVO;
import enums.TYPE_TRANSACTION;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountVOTest {

    private static final double AMOUNT_700 = 700.0;
    private static final double AMOUNT_300 = 300.0;
    private static final double AMOUNT_400 = 400.0;
    private static final double AMOUNT_500 = 500.0;
    private static final double AMOUNT_900 = 900.0;
    private static final double AMOUNT_1000 = 1000.0;
    private static final double AMOUNT_600 = 600.0 ;
    private static final int SIZE_0 = 0 ;
    private static final int SIZE_1 = 1 ;
    private static final LocalDate LOCAL_DATE_NOW= LocalDate.now() ;


    public static final String EXPECTED_HISTORY_TRANSACTIONS = "DATE_TRANSACTION\t\tTYPE_TRANSACTION\tAMOUNT_MONEY\tBALANCE_OF_ACCOUNT\r\n" +
            "2023-07-12\tDEPOSIT\t500.0\t1500.0\r\n" +
            "2023-07-12\tWITH_DRAW\t-400.0\t1100.0\r\n" +
            "2023-07-12\tWITH_DRAW\t-300.0\t800.0\r\n" +
            "2023-07-12\tDEPOSIT\t600.0\t1400.0";


    @ParameterizedTest
    @CsvSource({
            "100.0,100.0",
            "200.0,200.0"
    })
    @DisplayName("Test the total balance of an account ")
    public void AccountVOTest(double balance, double expectedAmount) {
        // GIVEN
        AccountVO accountVO = new AccountVO(balance);

        // THEN
        assertEquals(expectedAmount, accountVO.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "100.0,300.0,400.0",
            "200.0,400.0,600.0"
    })
    @DisplayName("Test deposit an amount of money in an account ")
    public void depositAmountTest(double balance, double amount , double expectedAmount) {

        // GIVEN
        AccountVO accountVO = new AccountVO(balance);

        // WHEN
        accountVO.depositAmount(amount);

        //THEN
        assertEquals(expectedAmount,accountVO.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "500.0,300.0,200.0",
            "600.0,400.0,200.0"
    })
    @DisplayName("Test withdraw an amount of money from an account ")
    public void withDrawAmountTest(double balance, double amount , double expectedAmount) {
        // GIVEN
        AccountVO accountVO = new AccountVO(balance);

        //WHEN
        accountVO.withDrawAmount(amount);

        //THEN
        assertEquals(expectedAmount, accountVO.getBalance());
    }

    @Test
    @DisplayName("Test a transaction deposit from an account")
    public void getTransactionFromAccount() {
        // GIVEN
        AccountVO accountVO = new AccountVO(AMOUNT_300);

        // THEN
        assertEquals(SIZE_0,accountVO.getTransactions().size());

        //WHEN
        accountVO.depositAmount(AMOUNT_400);

        //THEN
        assertEquals(SIZE_1, accountVO.getTransactions().size());
        assertEquals(TYPE_TRANSACTION.DEPOSIT, accountVO.getTransactions().get(0).getTypeTransaction());
        assertEquals(LOCAL_DATE_NOW, accountVO.getTransactions().get(0).getDateTransaction());
        assertEquals(AMOUNT_400, accountVO.getTransactions().get(0).getAmount());
        assertEquals(AMOUNT_700, accountVO.getTransactions().get(0).getBalanceTotal());
    }

    @Test
    @DisplayName("Test a transaction withdraw from an account")
    public void getTransactionWithDraw() {
        //GIVEN
        AccountVO accountVO = new AccountVO(AMOUNT_900);

        //WHEN
        accountVO.withDrawAmount(AMOUNT_400);

        // THEN
        assertEquals(SIZE_1, accountVO.getTransactions().size());
        assertEquals(TYPE_TRANSACTION.WITH_DRAW, accountVO.getTransactions().get(0).getTypeTransaction());
        assertEquals(LOCAL_DATE_NOW, accountVO.getTransactions().get(0).getDateTransaction());
        assertEquals(-AMOUNT_400, accountVO.getTransactions().get(0).getAmount());
        assertEquals(AMOUNT_500, accountVO.getTransactions().get(0).getBalanceTotal());
        assertEquals(AMOUNT_500, accountVO.getBalance());
    }

    @Test
    @DisplayName("Test Print History Transactions from an account")
    public void printHistoryTransactionsTest() {
        // GIVEN
        AccountVO accountVO = new AccountVO(AMOUNT_1000);
        accountVO.depositAmount(AMOUNT_500);
        accountVO.withDrawAmount(AMOUNT_400);
        accountVO.withDrawAmount(AMOUNT_300);
        accountVO.depositAmount(AMOUNT_600);


        //When
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        accountVO.printHistoryTransactionsTest();

        String histroryTransactionsPrinted = outputStream.toString().trim();

        // Then
        assertEquals(EXPECTED_HISTORY_TRANSACTIONS, histroryTransactionsPrinted);
    }
}
