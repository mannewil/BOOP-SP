package kolekce;

/**
 *
 * @author karel@simerda.cz
 */
public class KolekceException extends Exception {

    private static final long serialVersionUID = 1L;

    public KolekceException(String message) {
        super(message);
    }

    public KolekceException() {
        super("");
    }

}
