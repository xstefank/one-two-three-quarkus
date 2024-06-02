package service;

import entity.Score;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ScoreService {

    public void persistRank(List<GameService.Runner> rank, int gameId) {
        if (rank == null || rank.isEmpty()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < rank.size(); i++) {
            persistScore(rank.get(i).name(), i + 1, gameId, now);
        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void persistScore(String name, int position, int gameId, LocalDateTime now) {
        Score score = new Score();
        score.name = name;
        score.position = position;
        score.gameId = gameId;
        score.dateTime = now;

        score.persist();
    }
}
