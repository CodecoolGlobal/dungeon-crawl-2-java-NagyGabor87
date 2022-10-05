package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import io.github.cdimascio.dotenv.Dotenv;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Date;

public class GameDatabaseManager {
    private Dotenv dotenv;
    public GameDatabaseManager() {
        dotenv = Dotenv.load();
    }

    private PlayerDao playerDao;
    private GameStateDao gameStateDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource);
    }

    public void saveState(GameMap map) {
        PlayerModel playerModel = new PlayerModel(map.getPlayer());
        playerDao.add(playerModel);
        map.getPlayer().setId(playerModel.getId());

        Date date = new java.sql.Date(System.currentTimeMillis());
        String currentMap = map.toString();
        GameState state = new GameState(currentMap, date, playerModel, map.getLevel());
        gameStateDao.add(state);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = dotenv.get("MY_PSQL_DBNAME");
        String user = dotenv.get("MY_PSQL_USER");
        String password = dotenv.get("MY_PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public Integer getPlayerId(Player player) {
        PlayerModel playerModel = new PlayerModel(player);
        return playerDao.getPlayerIdByName(playerModel);
    }

    public Integer getGameState(int playerID) {
        return gameStateDao.getGameStateIdByPlayerID(playerID);
    }

    public void updateState(Integer stateId, GameMap map) {
        PlayerModel playerModel = new PlayerModel(map.getPlayer());
        Date date = new java.sql.Date(System.currentTimeMillis());
        String currentMap = map.toString();
        GameState gameState = new GameState(currentMap, date, playerModel, map.getLevel());
        gameState.setId(stateId);
        gameStateDao.update(gameState);
    }
}
