import java.util.*;
import java.util.Map.Entry;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String CALCULATE_ERROR = "Calculate Error";
    private static final String BLANK_SPACE = " ";
    private static final String NEW_LINE = "\n";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfos = getWordInfos(sentence);
            wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
            return joinerWordInfos(wordInfos);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<WordInfo> getWordInfos(String sentence) {
        List<WordInfo> wordInfos = new ArrayList<>();
        String[] words = sentence.split(SPACE_PATTERN);
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfos.add(wordInfo);
        }
        Map<String, List<WordInfo>> map = getListMap(wordInfos);
        List<WordInfo> tempWordInfos = new ArrayList<>();
        map.forEach((word, value) -> {
            WordInfo wordInfo = new WordInfo(word, value.size());
            tempWordInfos.add(wordInfo);
        });
        wordInfos = tempWordInfos;
        return wordInfos;
    }

    private String joinerWordInfos(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(NEW_LINE);
        for (WordInfo w : wordInfos) {
            String s = w.getValue() + BLANK_SPACE + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
