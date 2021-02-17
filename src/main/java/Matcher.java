public class Matcher {

    private static final String STATUS_UPDATE = "/paymentNotification/statusChange";
    private static final String STATUS_ACCEPT = "STATUS=9";


    private static final String ORDER_ID_ENTRY = "?orderID=";
    private static final String CURRENCY_ENTRY = "&currency=";

    public String getTransactionCode(String raw) {
        if (raw != null && raw.contains(STATUS_UPDATE) && raw.contains(STATUS_ACCEPT)) {
            return raw.substring(raw.indexOf(ORDER_ID_ENTRY) + ORDER_ID_ENTRY.length(), raw.indexOf(CURRENCY_ENTRY));
        }
        return null;
    }
}
