import models.AccountVO;

public class ApplicationMain {

    private static final double AMOUNT_400 = 400.0;
    private static final double AMOUNT_500 = 500.0;
    private static final double AMOUNT_1000 = 1000.0;
    private static final double AMOUNT_600 = 600.0 ;

    public static void main(String[] args){

        AccountVO accountVO = new AccountVO(AMOUNT_1000);
        accountVO.depositAmount(AMOUNT_500);
        accountVO.withDrawAmount(AMOUNT_400);
        accountVO.depositAmount(AMOUNT_600);

        accountVO.printHistoryTransactionsTest();

    }
}
