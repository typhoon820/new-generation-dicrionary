package com.assessment.project.service;


import com.assessment.project.component.WordStat;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.trees.Tree;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("ProcessingService")
public class ProcessingServiceImpl implements ProcessingService {

    private final static String SPACE = " ";
    private final static String ANY_APOSTROPHE = "[`]";
    private final static String AVAILABLE_APOSTROPHE = "'";
    private final static String ONLY_LATIN_CHARACTERS = "[^a-z\\s]";
    private final static String SPACES_MORE_ONE = "\\s{2,}";
    private CoreDocument coreDocument;

    public String filterText(String text) {
        String res = text.toLowerCase().replaceAll(ANY_APOSTROPHE, AVAILABLE_APOSTROPHE)
                .replaceAll(ONLY_LATIN_CHARACTERS, SPACE).replaceAll(SPACES_MORE_ONE, SPACE);
        return res;
    }

    private Map<String, Integer> getWordsMap(String text) {

        Map<String, Integer> wordsMap = new HashMap<String, Integer>();

        String newWord = "";

        Pattern patternWord = Pattern.compile("(?<word>[a-z']+)");
        Matcher matcherWord = patternWord.matcher(text);

        // поиск слов в тексте по паттерну
        while (matcherWord.find()) {
            newWord = matcherWord.group("word");
            if (wordsMap.containsKey(newWord)) {
                // если слово уже есть в Map то увеличиваеи его количество на 1
                wordsMap.replace(newWord, wordsMap.get(newWord) + 1);
            } else {
                wordsMap.put(newWord, 1);
            }
        }

        return wordsMap;
    }

    private int getCountOfWords(Map<String, Integer> wordsMap) {

        int countOfWords = 0;
        // считаем в цикле сумму значений для всех слов в Map
        for (Integer value : wordsMap.values())
            countOfWords += value;
        return countOfWords;
    }

    private int getPercent(int number100Percents, int numberXPercents) {
        return (numberXPercents * 100) / number100Percents;
    }

    private Map<String, Integer> filterWordsMap(Map<String, Integer> wordsMap, int countOfWords, int percent) {

        // LinkedHashMap - ассоциативный массив, который запоминает порядок
        // добавления элементов
        Map<String, Integer> resultMap = new LinkedHashMap<String, Integer>();

        int sumPercentOfWords = 0;
        // создаёт поток из Map с записями Entry<String, Integer>,
        // отсортированными по убыванию
        Stream<Map.Entry<String, Integer>> streamWords = wordsMap.entrySet()
                .stream().sorted(Map.Entry.comparingByValue(
                        (Integer value1, Integer value2) -> (
                                value1.equals(value2)) ? 0 : ((value1 < value2) ? 1 : -1)
                        )
                );

        // создаём итератор для обхода всех записей потока
        Iterator<Map.Entry<String, Integer>> iterator = streamWords.iterator();
        while (iterator.hasNext() && (sumPercentOfWords < percent)) {

            Map.Entry<String, Integer> wordEntry = iterator.next();
            if (wordEntry.getKey().length() > 3) {
                resultMap.put(wordEntry.getKey(), wordEntry.getValue());
            }
            sumPercentOfWords += getPercent(countOfWords, wordEntry.getValue());

        }
        return resultMap;
    }

    @Override
    public WordStat getWordStat(String receivedText, int percent) {

        WordStat wordStat = new WordStat(receivedText);

        Map<String, Integer> wordsMap = getWordsMap(filterText(receivedText));

        wordStat.setWordsCount(getCountOfWords(wordsMap));

        wordStat.setFrequency(
                filterWordsMap(wordsMap, wordStat.getWordsCount(), percent)
        );

        return wordStat;
    }

    @Override
    public Set<String> getMainClauses(String recievedText) {
        Set<String> res = new LinkedHashSet<>();
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,depparse");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object
        CoreDocument document = new CoreDocument(recievedText);
        // annnotate the document
        pipeline.annotate(document);
        // second sentence
        coreDocument = document;

        for (CoreSentence sentence : document.sentences()) {
            SemanticGraph dependencyParse = sentence.dependencyParse();
            for (IndexedWord root : dependencyParse.getRoots()) {
                res.add(root.word());
            }
            List<SemanticGraphEdge> subjects = dependencyParse.edgeListSorted().stream()
                    .filter(edge -> edge.getRelation().getShortName().equals("nsubj")).collect(Collectors.toList());
            for (SemanticGraphEdge edge: subjects){
                res.add(edge.getTarget().word());
            }

        }
        return res;
    }

    @Override
    public Set<String> getMainClasesAdvanced(String recievedText) {
        Set<String> res = new LinkedHashSet<>();
        for (CoreSentence sentence : coreDocument.sentences()) {
            SemanticGraph dependencyParse = sentence.dependencyParse();
            for (IndexedWord root : dependencyParse.getRoots()) {
                res.add(root.word());
            }

            for (IndexedWord word: dependencyParse.getAllNodesByPartOfSpeechPattern("VBG||JJ")){
                res.add(word.word());
            }
        }
        return res;
    }
}
