package net.jayde.study.kodejava.example.lang;

public class EmailAddressValidation {
    public static final String EMAIL_REGEX =
            "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    public static void main(String[] args) {
        EmailAddressValidation validator = new EmailAddressValidation();

        System.out.println("isValid = "
                + validator.isValidEmailAddress("user@domain.com"));
        System.out.println("isValid = "
                + validator.isValidEmailAddress("user.name@domain.com"));
        System.out.println("isValid = "
                + validator.isValidEmailAddress("user.name@domain.com.sg"));
        System.out.println("isValid = "
                + validator.isValidEmailAddress("user.domain.com.sg"));
    }

    /**
     * Validates email address agains email regular expression.
     *
     * @param email an email address to check
     * @return true if email address is valid otherwise return false.
     */
    protected boolean isValidEmailAddress(String email) {
        return email.matches(EMAIL_REGEX);
    }
}
