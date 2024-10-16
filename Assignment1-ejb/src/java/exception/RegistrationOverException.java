/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exception;

/**
 *
 * @author hao
 */
public class RegistrationOverException extends Exception{

    /**
     * Creates a new instance of <code>RegistrationOverException</code> without
     * detail message.
     */
    public RegistrationOverException() {
        super("Event Over Registration Deadline");
    }

    /**
     * Constructs an instance of <code>RegistrationOverException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RegistrationOverException(String msg) {
        super(msg);
    }
}
