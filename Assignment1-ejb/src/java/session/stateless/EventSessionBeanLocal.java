package session.stateless;

import entity.Event;
import exception.WrongDateException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EventSessionBeanLocal {

    void createEvent(Event e, long userId) throws WrongDateException;

    List<Event> findEventByName_organizer(long userId, String name);

    void deleteEvent(long eventId);

    Event getEventById(long eventId);

    public List<Event> findEventByOrganizer(long userId);

    public List<Event> findEventByLocation_organizer(long userId, String location);

    public void updateEvent(Event event);

    public List<Event> findEventByAttendee(long attendeeId);

    public List<Event> findEventByLocation_attendee(long attendeeId, String location);

    public List<Event> findEventByName_attendee(long attendeeId, String name);

    public List<Event> findEventByLocation(String location);

    public List<Event> findEventByNotLocation(String location);

    public List<Event> getAllEvent();

    public List<Event> findEventByName(String name);

}
