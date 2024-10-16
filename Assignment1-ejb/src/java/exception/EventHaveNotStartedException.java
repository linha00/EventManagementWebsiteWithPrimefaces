/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exception;

/**
 *
 * @author hao
 */
public class EventHaveNotStartedException extends Exception {

    /**
     * Creates a new instance of <code>EventHaveNotStartedException</code>
     * without detail message.
     */
    public EventHaveNotStartedException() {
        super("Event not Started");
    }

    /**
     * Constructs an instance of <code>EventHaveNotStartedException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public EventHaveNotStartedException(String msg) {
        super(msg);
    }
}
