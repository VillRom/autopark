package ru.romanchev.autopark.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.romanchev.autopark.model.PassedWay;
import ru.romanchev.autopark.service.CalculateWay;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nmea")
@Slf4j
public class NmeaController {

    private final CalculateWay calculateWay;

    @GetMapping("/way")
    public String fileNmea(@RequestPart("file") MultipartFile fileToUpload, Model model) {
        log.info("Get request to calculate the way");
        String path = "src/main/resources/" + fileToUpload.getOriginalFilename();
        File nmea = new File(path);
        if (nmea.isFile() && !nmea.isDirectory()) {
            model.addAttribute("way", new PassedWay(calculateWay.calculateWay(nmea)));
        }
        else {
            try {
                Files.write(Path.of(path), fileToUpload.getBytes());
                model.addAttribute("way", new PassedWay(calculateWay.calculateWay(new File(path))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "nmea";
    }
}
