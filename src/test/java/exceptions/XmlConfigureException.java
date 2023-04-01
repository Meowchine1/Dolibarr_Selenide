package exceptions;

public class XmlConfigureException extends Throwable {
    public XmlConfigureException(String errorMessage) {
        System.out.print(errorMessage);
    }
}
