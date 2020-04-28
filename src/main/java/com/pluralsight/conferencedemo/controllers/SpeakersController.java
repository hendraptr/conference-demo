package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/speakers")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakersRepository;

    @GetMapping
    public List<Speaker> list() {
        return speakersRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return speakersRepository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker) {
        return speakersRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        speakersRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
        Speaker existingSpeaker = speakersRepository.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakersRepository.saveAndFlush(existingSpeaker);
    }
}
