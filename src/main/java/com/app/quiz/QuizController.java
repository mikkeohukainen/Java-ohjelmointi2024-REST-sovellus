package com.app.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {
    @Autowired
    QuizService quizService;
    
    // Etusivu ja ohjeet
    @GetMapping("/")
    public String getInfo() {
        return "Tervetuloa! Vastaa viiteen arvoitukseen, minkä jälkeen voit tarkistaa pisteesi.";
    }

    // Välitetään pelaajan nimi initGame() -metodille ja luodaan sen avulla 'uusi peli'
    // Palautetaan clientille status ja viesti
    @PostMapping("/newGame")
    public ResponseEntity<String> newGame(@RequestParam("name") String name) {
        quizService.initGame(name);
        String message = "Onnea peliin " + name;
        return ResponseEntity.ok(message);
    }

    // Haetaan seuraava kysymys ja palautetaan se merkkijonona
    @GetMapping("/nextQuestion")
    public String getNextQuestion() {
        return quizService.getNextQuestion();
    }

    // Välitetään parametrina saatu vastaus checkAnswer -metodille ja palautetaan oikein/väärin
    @PostMapping("/answer")
    public String checkAnswer(@RequestParam("answer") String answer) {
        if (quizService.checkAnswer(answer)) {
            return "Oikein!";
        }
        return "Väärin.";
    }

    // Haetaan Player -olio getStats() -metodin avulla ja palautetaan se kokonaisuudessaan
    @GetMapping("/stats")
    public Player getStats() {
        return quizService.getStats();
    }

    // Tästä endpointista saa haettua kaikki kyseisen pelisession kysymykset ja vastaukset (Quiz -oliot)
    @GetMapping("/cheat")
    public List<Quiz> getAllCurrent() {
        return quizService.getAllCurrent();
    }

    // Palautetaan tietyn Quiz -olion tiedot polkumuuttujan avulla
    @GetMapping("/by_id/{id}")
    public Quiz getById(@PathVariable int id) {
        return quizService.getQuizById(id);
    }

    // Palautetaan tietyn Quiz -olion tiedot parametrin avulla
    @GetMapping("/by_id")
    public Quiz getByIdParam(@RequestParam("id") int id) {
        return quizService.getQuizById(id);
    }

    // Välitetään uusi nimi editName() -metodille ja palautetaan metodin palauttama Player -olio uusine tietoineen
    @PostMapping("/editName")
    public Player editName(@RequestParam("newName") String name) {
        return quizService.editName(name);
    }
}
