
package session.stateless;

import entity.EventRegistration;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EventRegistrationSessionBeanLocal {

    public EventRegistration getEventRegistrationByAttendee(long attendeeId, long eventId);

    public List<EventRegistration> getEventRegistrationByEvent(long eventId);

    public void createEventRegistration(long attendeeId, long eventId);

    public void deleteEventRegistration(EventRegistration eventRegistration);

    public void updateEventRegistration(EventRegistration er);

    public List<EventRegistration> getEventRegistrationByEvent(long eventId, String searchString);
    
}
