package ru.romanchev.autopark.service.Impl;

import net.sf.marineapi.nmea.parser.SentenceFactory;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.SentenceValidator;
import net.sf.marineapi.nmea.sentence.ZDASentence;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class NmeaServ {

    private final List<Sentence> sentences = new ArrayList<>();

    private final HashMap<Date, Integer> indexForDate = new HashMap<>();

    public void readLogFile(File file) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while (bf.ready()) {
                String event = bf.readLine();
                if (SentenceValidator.isValid(event) && !event.contains(",,,")) {
                    SentenceFactory sf = SentenceFactory.getInstance();
                    Sentence sentence = sf.createParser(event);
                    if (sentence.getSentenceId().equals("ZDA")) {
                        ZDASentence zda = (ZDASentence) sentence;
                        indexForDate.put(zda.getDate().toDate(), sentences.size());
                    }
                    sentences.add(sentence);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
