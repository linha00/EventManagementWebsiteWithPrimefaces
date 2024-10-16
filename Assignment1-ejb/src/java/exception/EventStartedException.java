/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exception;

/**
 *
 * @author hao
 */
public class EventStartedException extends Exception {

    /**
     * Creates a new instance of <code>EventOverException</code> without detail
     * message.
     */
    public EventStartedException() {
        super("Event has Started");
    }

    /**
     * Constructs an instance of <code>EventOverException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EventStartedException(String msg) {
        super(msg);
    }
}
