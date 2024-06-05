package ru.romanchev.autopark.service.Impl;

import lombok.RequiredArgsConstructor;
import net.sf.marineapi.nmea.sentence.GGASentence;
import net.sf.marineapi.nmea.sentence.Sentence;
import net.sf.marineapi.nmea.sentence.VTGSentence;
import net.sf.marineapi.nmea.util.Position;
import org.springframework.stereotype.Service;
import ru.romanchev.autopark.service.CalculateWay;
import ru.romanchev.autopark.service.ReadLogFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculateWayImpl implements CalculateWay {

    private final ReadLogFile readLogFile;

    @Override
    public HashMap<Date, String> calculateWay(File file) {
        HashMap<Date, List<Sentence>> sentencesForDays = readLogFile.splitByDays(file);
        HashMap<Date, String> wayForDays = new HashMap<>();
        for (Date date : sentencesForDays.keySet()) {
            wayForDays.put(date, String.format("%.2f", calculateWayForDay(sentencesForDays.get(date))) + "м");
        }
        return wayForDays;
    }

    private Double calculateWayForDay(List<Sentence> sentences) {
        Position positionBefore = null;
        double track = 0;
        List<GGASentence> ggaSentences = filterGGASentence(sentences);
        for (GGASentence gga : ggaSentences) {
            if (positionBefore != null) {
                track += positionBefore.distanceTo(gga.getPosition());
            }
            positionBefore = gga.getPosition();
        }
        return track;
    }

    private List<GGASentence> filterGGASentence(List<Sentence> sentences) {
        List<GGASentence> ggaSentences = new ArrayList<>();
        for (int i = 0; i < sentences.size(); i++) {
            if (sentences.get(i).getSentenceId().equals("GGA") && i + 1 != sentences.size()) {
                if (sentences.get(i + 1).getSentenceId().equals("VTG")) {
                    VTGSentence sentence = (VTGSentence) sentences.get(i + 1);
                    if (sentence.getSpeedKmh() >= 1) {
                        GGASentence ggaSentence = (GGASentence) sentences.get(i);
                        ggaSentences.add(ggaSentence);
                    }
                }
            }
        }
        return ggaSentences;
    }
}
