/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exception;

/**
 *
 * @author hao
 */
public class InvaildLoginException extends Exception {

    /**
     * Creates a new instance of <code>InvaildLoginException</code> without
     * detail message.
     */
    public InvaildLoginException() {
        super("Invalid Login Credentials");
    }

    /**
     * Constructs an instance of <code>InvaildLoginException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvaildLoginException(String msg) {
        super(msg);
    }
}
