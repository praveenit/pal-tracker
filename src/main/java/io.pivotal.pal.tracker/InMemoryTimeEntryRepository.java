package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntryMap = new HashMap<>();


    public TimeEntry create(TimeEntry timeEntry) {

        timeEntry.setId(timeEntryMap.size()+1);
        timeEntryMap.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    public TimeEntry find(Long id) {
        return timeEntryMap.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryMap.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {

        if(find(id)!=null) {
            timeEntry.setId(id);
            timeEntryMap.put(id, timeEntry);
        }else{
            return null;
        }
        return timeEntry;
    }

    public void delete(Long id) {
        timeEntryMap.remove(id);
    }
}
