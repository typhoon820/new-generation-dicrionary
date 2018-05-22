package com.assessment.project.controller;

import com.assessment.project.model.LevelEntity;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.util.Locale;

@Controller
public class LevelFormatter implements Formatter<LevelEntity> {

    @Override
    public LevelEntity parse(String s, Locale locale) throws ParseException {
        LevelEntity statusEntity = new LevelEntity();
        String[] data = s.split("_");
        statusEntity.setId(Integer.parseInt(data[0]));
        statusEntity.setLevel(data[1]);
        return statusEntity;

    }

    @Override
    public String print(LevelEntity statusEntity, Locale locale) {
        String res;
        res = String.valueOf(statusEntity.getId()) + "_" + statusEntity.getLevel();
        return res;
    }
}
