/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exception;

/**
 *
 * @author hao
 */
public class AlreadyRegisteredException extends Exception {

    /**
     * Creates a new instance of <code>AlreadyRegisteredException</code> without
     * detail message.
     */
    public AlreadyRegisteredException() {
        super("Already Registed for the Event");
    }

    /**
     * Constructs an instance of <code>AlreadyRegisteredException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public AlreadyRegisteredException(String msg) {
        super(msg);
    }
}
