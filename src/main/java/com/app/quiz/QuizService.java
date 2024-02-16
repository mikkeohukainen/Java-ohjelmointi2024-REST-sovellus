package com.app.quiz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class QuizService {

    private List<Quiz> quizes = new ArrayList<>();  // Kaikki arvoitukset
    private Player player = new Player("");
    private List<Quiz> currentQuizes = new ArrayList<>();   // Yhden pelisession arvoitukset (5kpl)

    private int currentIndex = 0;   // Pelisession arvoitustaulukon indeksilaskuri

    public QuizService() {
        quizes.add(new Quiz("Mikä tulee sitä isommaksi, mitä enemmän otat siitä pois?", "kuoppa"));
        quizes.add(new Quiz("Mikä on aina edessä, ei koskaan takana tai sivuilla, mutta sitä ei voi nähdä?", "tulevaisuus"));
        quizes.add(new Quiz("Missä on metsät, mutta ei puita, järvet, mutta ei vettä, tiet, mutta ei autoja?", "kartassa"));
        quizes.add(new Quiz("Se ei pysähdy koskaan, eikä hengästy. Mikä se on?", "aika"));
        quizes.add(new Quiz("Pidempi suurinta puuta, matalampi pienintä kukkaa. Mikä se on?", "tie"));
        quizes.add(new Quiz("Kulkee ympäri kaupungin, muttei koskaan liiku. Mikä se on?", "muuri"));
        quizes.add(new Quiz("Mikä nousee, mutta ei koskaan laske?", "ikä"));
        quizes.add(new Quiz("Millä on lakki, mutta ei päätä, ja jalka, mutta ei kenkiä?", "sieni"));
        quizes.add(new Quiz("Yhdestä ovesta sisään mennään, kahdesta ulos tullaan. Mikä se on?", "housut"));
        quizes.add(new Quiz("Mikä kävelee aamulla neljällä jalalla, päivällä kahdella jalalla, ja illalla kolmella jalalla?", "ihminen"));
        quizes.add(new Quiz("Kun tarvitset sitä heität sen menemään ja kun et tarvitse sitä nostat sen ylös. Mikä se on?", "ankkuri"));
    }

    // Palauttaa listan jossa on viisi uniikkia arvoitusta
    private List<Quiz> getFive() {
        Set<Quiz> quizSet = new HashSet<>();
        Random rand = new Random();

        while (quizSet.size() < 5) {
            quizSet.add(quizes.get(rand.nextInt(quizes.size())));
        }

        return new ArrayList<>(quizSet);
    }

    public Quiz getRandom() {
        Random rand = new Random();
        return quizes.get(rand.nextInt(quizes.size()));
    }

    // Alustetaan/luodaan uusi pelisessio
    public void initGame(String name) {
        currentIndex = 0;
        player.setPlayerName(name);
        player.setCorrectAnswers(0);
        this.currentQuizes = this.getFive();
        for (Quiz q : currentQuizes) {
            System.out.println(q);
        }
    }

    // Seuraava arvoitus indeksin mukaan
    public String getNextQuestion() {
        if (currentIndex > 4) {
            return "Ei kysymyksiä jäljellä.";
        }

        String question = currentQuizes.get(currentIndex).getQuestion();

        return question;
    }

    public boolean checkAnswer(String answer) {
        System.out.println(answer);
        System.out.println(currentQuizes.get(currentIndex).getAnswer());
        if (answer.equals(currentQuizes.get(currentIndex).getAnswer())) {
            player.incrementCorrectAnswers();
            currentIndex++;
            return true;
        }
        currentIndex++;
        return false;
    }

    public Player getStats() {
        return player;
    }

    public Player editName(String name) {
        player.setPlayerName(name);
        return player;
    }

    public List<Quiz> getAllCurrent() {
        return currentQuizes;
    }

    public Quiz getQuizById(int id) {
        return quizes.get(id);
    }

}
