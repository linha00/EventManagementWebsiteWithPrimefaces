/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exception;

/**
 *
 * @author hao
 */
public class WrongDateException extends Exception {

    /**
     * Creates a new instance of <code>WrongDateException</code> without detail
     * message.
     */
    public WrongDateException() {
        super("Wrong Date Entered");
    }

    /**
     * Constructs an instance of <code>WrongDateException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public WrongDateException(String msg) {
        super(msg);
    }
}
