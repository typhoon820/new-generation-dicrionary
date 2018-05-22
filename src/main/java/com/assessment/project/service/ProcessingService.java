package com.assessment.project.service;


import com.assessment.project.component.WordStat;

import java.util.List;
import java.util.Set;

public interface ProcessingService {
    WordStat getWordStat(String receivedText, int percent);
    Set<String> getMainClauses(String recievedText);
    Set<String> getMainClasesAdvanced(String recievedText);
}
