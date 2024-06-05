package ru.romanchev.autopark.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

public interface CalculateWay {

    HashMap<Date, String> calculateWay(File file);
}
