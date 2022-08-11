import javax.json.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Scores {
    private ArrayList<Score> scores = new ArrayList<>(5);
    private Score score;
    public Scores() {
        score = new Score();
        score.setName("");
        score.setScore(0);
        IntStream.range(0, 5).forEach(i -> scores.add(score));
    }
    public Scores(File scoreFile)   {
        readHighScores(scoreFile);
    }
    public void readHighScores()    {
        readHighScores(new File("scores.json"));
    }
    public void readHighScores(File scoreFile) {
        try {
            this.score = new Score();
            JsonReader read = Json.createReader(new FileInputStream(scoreFile));
            JsonObject js = read.readObject();
            IntStream.range(1, 6).forEach(i ->  {
                this.score = new Score();
                this.score.setName(js.getString("name" + i));
                this.score.setScore(this.score.getName().length() != 0 ? js.getInt("score" + i) : 0);
                add(this.score);
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("If the game is running for the first time, this is not an error");
        }
    }
    public void writeHighScores()   {
        writeHighScores(new File("scores.json"));
    }
    public void writeHighScores(File scoreFile)   {
        JsonObjectBuilder sc = Json.createObjectBuilder();
        IntStream.range(0, 5).forEach(i -> {
            score = scores.get(i);
            sc.add("name" + (i + 1), score.getName());
            sc.add("score" + (i + 1), score.getScore());
        });
        try {
            if (!scoreFile.exists())
                scoreFile.createNewFile();
            FileOutputStream out = new FileOutputStream(scoreFile);
            JsonWriter writer = Json.createWriter(out);
            writer.write(sc.build());
            writer.close();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void add(String name, int score) {
        add(new Score(name, score));
    }
    public void add(Score score){
        scores.add(score);
        scores = scores.stream().sorted((a, b) -> b.getScore() - a.getScore()).collect(Collectors.toCollection(ArrayList::new));
        while (scores.size() > 5)
            scores.remove(5);
    }
    public String[] getScores() {
        String[] sco = new String[5];
        IntStream.range(0, 5).forEach(i -> {
            score = scores.get(i);
            sco[i] = score.getName();
            if (sco[i].length() != 0)
                sco[i] += ": " + score.getScore();
        });
        return sco;
    }
    private class Score {
        private String name;
        private int score;
        Score() {
            name = "";
            score = 0;
        }
        Score(String name, int score)   {
            this.name = name;
            this.score = score;
        }
        public void setName(String n)       {
            name = n;
        }
        public void setScore(int s) {
            score = s;
        }
        public String getName() {
            return name;
        }
        public int getScore()   {
            return score;
        }
        @Override
        public String toString()    {
            return (name + ": " + score);
        }
    }
}