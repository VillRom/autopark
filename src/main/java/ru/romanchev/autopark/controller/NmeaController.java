package ru.romanchev.autopark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.romanchev.autopark.service.Impl.NmeaServ;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nmea")
public class NmeaController {

    private final NmeaServ nmeaServ;

    @GetMapping
    public void fileNmea(@RequestPart("file") MultipartFile file) throws IOException {
        nmeaServ.readLogFile(file.getResource().getFile());//TODO 
        Files.write(Path.of("src/main/resources/" + file.getOriginalFilename()), file.getBytes());
    }
}
