/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exception;

/**
 *
 * @author hao
 */
public class UsernameExistException extends Exception {

    /**
     * Creates a new instance of <code>UsernameExistException</code> without
     * detail message.
     */
    public UsernameExistException() {
        super("Username Exist");
    }

    /**
     * Constructs an instance of <code>UsernameExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UsernameExistException(String msg) {
        super(msg);
    }
}
