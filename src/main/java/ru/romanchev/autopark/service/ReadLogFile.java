package ru.romanchev.autopark.service;

import net.sf.marineapi.nmea.sentence.Sentence;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface ReadLogFile {

    HashMap<Date, List<Sentence>> splitByDays(File file);
}
