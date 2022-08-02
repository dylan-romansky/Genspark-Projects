import javax.json.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Scores {
    private ArrayList<Score> scores = new ArrayList<>(5);
    private Score score;
    public void readHighScores() {
        try {
            score = new Score();
            JsonReader read = Json.createReader(new FileInputStream("scores.txt"));
            JsonObject js = read.readObject();
            score.setName(js.getString("name1"));
            score.setScore(score.getName().length() != 0 ? js.getInt("score1") : 0);
            scores.add(score);
            score = new Score();
            score.setName(js.getString("name2"));
            score.setScore(score.getName().length() != 0 ? js.getInt("score2") : 0);
            scores.add(score);
            score = new Score();
            score.setName(js.getString("name3"));
            score.setScore(score.getName().length() != 0 ? js.getInt("score3") : 0);
            scores.add(score);
            score = new Score();
            score.setName(js.getString("name4"));
            score.setScore(score.getName().length() != 0 ? js.getInt("score4") : 0);
            scores.add(score);
            score = new Score();
            score.setName(js.getString("name5"));
            score.setScore(score.getName().length() != 0 ? js.getInt("score5") : 0);
            scores.add(score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeHighScores()   {
        JsonObjectBuilder sc = Json.createObjectBuilder();
        score = scores.get(0);
        sc.add("name1", score.getName());
        sc.add("score1", score.getScore());
        score = scores.get(1);
        sc.add("name2", score.getName());
        sc.add("score2", score.getScore());
        score = scores.get(2);
        sc.add("name3", score.getName());
        sc.add("score3", score.getScore());
        score = scores.get(3);
        sc.add("name4", score.getName());
        sc.add("score4", score.getScore());
        score = scores.get(4);
        sc.add("name5", score.getName());
        sc.add("score5", score.getScore());
        File outf = new File("scores.txt");
        try {
            if (!outf.exists())
                outf.createNewFile();
            FileOutputStream out = new FileOutputStream(outf);
            JsonWriter writer = Json.createWriter(out);
            writer.write(sc.build());
            writer.close();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addScore(String name, int scor)    {
        score = new Score();
        score.setName(name);
        score.setScore(scor);
        scores.add(score);
        scores.stream().sorted((a, b) -> a.getScore() - b.getScore()).collect(Collectors.toCollection(ArrayList::new)).remove(5);
    }
    public String[] getScores() {
        String[] sco = new String[5];
        score = scores.get(0);
        sco[0] = score.getName();
        if (sco[0].length() != 0)
            sco[0] += ": " + score.getScore();
        score = scores.get(1);
        sco[1] = score.getName();
        if (sco[1].length() != 0)
            sco[1] += ": " + score.getScore();
        score = scores.get(2);
        sco[2] = score.getName();
        if (sco[2].length() != 0)
            sco[2] += ": " + score.getScore();
        score = scores.get(3);
        sco[3] = score.getName();
        if (sco[3].length() != 0)
            sco[3] += ": " + score.getScore();
        score = scores.get(4);
        sco[4] = score.getName();
        if (sco[4].length() != 0)
            sco[4] += ": " + score.getScore();
        return sco;
    }
    private class Score {
        private String name;
        private int Score;
        public void setName(String n)       {
            name = n;
        }
        public void setScore(int s) {
            Score = s;
        }
        public String getName() {
            return name;
        }
        public int getScore()   {
            return Score;
        }
    }
}