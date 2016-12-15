package ru.ncedu.java.tasks;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;


public class WordCounterImpl implements WordCounter {
    private String text;

    public static void main(String[] args) throws FileNotFoundException {
        String text = "";
        Scanner in = new Scanner(new File("1.txt"));
        while (in.hasNext()) {
            text += in.nextLine() + "\r\n";
        }
        //System.out.println(text);
        in.close();

        WordCounterImpl o = new WordCounterImpl();
        o.setText(text);
        o.getWordCounts();
        for (Map.Entry<String, Long> e : o.getWordCounts().entrySet()) {
            // System.out.println(e);
        }
        for (Map.Entry<String, Long> e : o.getWordCountsSorted()) {
            //     System.out.println(e);
        }
        // o.printWordCounts(System.out);
        o.printWordCountsSorted(System.out);

    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Map<String, Long> getWordCounts() throws IllegalStateException {
        checkText();

        String[] a = text.replaceAll("\\W|\\d", " ").trim().toLowerCase().split("\\s+");
        Map<String, Long> words = new HashMap<>();
        for (String x : a) {
            if(!words.containsKey(x)) {
                long count = 0;
                for (String x2 : a) {
                    if (x.equals(x2)) {
                        count++;
                    }
                }
                words.put(x, count);
            }
        }
        return words;
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() throws IllegalStateException {
        checkText();
        return sortWordCounts(getWordCounts());
    }

    @Override
    public List<Map.Entry<String, Long>> sortWordCounts(Map<String, Long> orig) {
        Map<String, Long> words = new TreeMap<>(orig);
        long maxValue = 0;
        for (Map.Entry<String,Long> e: words.entrySet()){
            if(e.getValue()>maxValue){
                maxValue = e.getValue();
            }
        }
        List<Map.Entry<String, Long>> list = new ArrayList<>();
        for (int i = (int) maxValue; i >=0 ; i--) {
            for (Map.Entry<String,Long> e: words.entrySet()) {
                if(i == e.getValue()){
                    list.add(e);
                }
            }
        }
        return list;
    }

    @Override
    public void printWordCounts(PrintStream ps) throws IllegalStateException {
        checkText();
        for (Map.Entry<String, Long> map : getWordCounts().entrySet()) {
            ps.println(map.getKey() + " " + map.getValue());
        }
    }

    @Override
    public void printWordCountsSorted(PrintStream ps) throws IllegalStateException {
        checkText();
        for (Map.Entry<String, Long> map : getWordCountsSorted()) {
            ps.println(map.getKey() + " " + map.getValue());
        }
    }

    private void checkText() {
        if (this.text == null) {
            throw new IllegalStateException("Text isn't set");
        }
    }

}