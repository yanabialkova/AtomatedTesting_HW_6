package data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class Card {
        String number;
        String balance;
    }

    public static Card getCard1() {
        return new Card("5559 0000 0000 0001", "10 000 RUB");
    }

    public static Card getCard2() {
        return new Card("5559 0000 0000 0002", "10 000 RUB");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

}
