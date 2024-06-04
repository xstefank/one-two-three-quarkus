package utils;

import entity.Game;
import entity.Score;
import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import service.ScoreService;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ApplicationScoped
public class LoadRanks {

    @Inject
    ScoreService scoreService;

    @Transactional
    public void start(@Observes StartupEvent ev) {
        if (LaunchMode.current() == LaunchMode.DEVELOPMENT) {
            Game game = new Game();
            game.dateTime = LocalDateTime.now();
            game.scores = new ArrayList<>();
            game.scores.add(createScore("Toce", 1));
            game.scores.add(createScore("Zipp", 2));

            game.persist();
        }
    }

    private Score createScore(String name, int position) {
        Score score = new Score();
        score.name = name;
        score.position = position;
        return score;
    }

//    @Transactional
//    public void start(@Observes StartupEvent event) {
//        if (LaunchMode.current() == LaunchMode.DEVELOPMENT) {
//            GameService.Runner r1 = new GameService.Runner(1);
//            GameService.Runner r2 = new GameService.Runner(2);
//            r1.newState(100, 1, GameService.RunnerState.Status.saved);
//            r2.newState(100, 1, GameService.RunnerState.Status.saved);
//
//            GameService.Runner r3 = new GameService.Runner(1);
//            GameService.Runner r4 = new GameService.Runner(2);
//            r3.newState(100, 1, GameService.RunnerState.Status.saved);
//            r4.newState(100, 1, GameService.RunnerState.Status.saved);
//
//            GameService.Runner r5 = new GameService.Runner(1);
//            GameService.Runner r6 = new GameService.Runner(2);
//            r5.newState(100, 1, GameService.RunnerState.Status.saved);
//            r6.newState(100, 1, GameService.RunnerState.Status.saved);
//
//            List<GameService.Runner> rank1 = List.of(r1, r2);
//            List<GameService.Runner> rank2 = List.of(r3, r4);
//            List<GameService.Runner> rank3 = List.of(r5, r6);
//
//            scoreService.persistRank(rank1);
//            scoreService.persistRank(rank2);
//            scoreService.persistRank(rank3);
//        }
//    }
}
