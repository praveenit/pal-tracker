package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {


    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        ResponseEntity response = null;
        try {
            timeEntryToCreate = timeEntryRepository.create(timeEntryToCreate);
            response = new ResponseEntity(timeEntryToCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable(value = "id") long id) {


        ResponseEntity response = null;
        try {
            TimeEntry timeEntry = timeEntryRepository.find(id);
            if (timeEntry == null) {
                response = new ResponseEntity(HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity(timeEntry, HttpStatus.OK);
            }
        } catch (Exception e) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        ResponseEntity response = null;
        try {
            List<TimeEntry> timeEntryList = timeEntryRepository.list();
            if (timeEntryList == null) {
                response = new ResponseEntity(HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity(timeEntryList, HttpStatus.OK);
            }
        } catch (Exception e) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable(value = "id") long id, @RequestBody TimeEntry timeEntry) {

        ResponseEntity response = null;
        try {
            TimeEntry timeEntryUpdated = timeEntryRepository.update(id, timeEntry);
            if (timeEntryUpdated != null) {
                response = new ResponseEntity(timeEntryUpdated, HttpStatus.OK);
            } else {
                response = new ResponseEntity(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable(value = "id") long id) {

        ResponseEntity response = null;
        try {
            timeEntryRepository.delete(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
