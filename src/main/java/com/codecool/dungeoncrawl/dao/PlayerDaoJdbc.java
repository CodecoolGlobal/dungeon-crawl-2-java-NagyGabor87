package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private final DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO player (player_name, hp, x, y, inventory) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.setString(5, player.getInventory());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE player SET player_name = ?, hp = ?, x = ?, y = ?, inventory = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.setString(5, player.getInventory());
            statement.setInt(6, player.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerModel get(int id) {
        PlayerModel player;
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, player_name, hp, x, y, inventory FROM player WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }

            int player_id = resultSet.getInt(1);
            String player_name = resultSet.getString(2);
            int hp = resultSet.getInt(3);
            int x = resultSet.getInt(4);
            int y = resultSet.getInt(5);
            String inventory = resultSet.getString(6);

            player = new PlayerModel(player_name, x, y, inventory, hp, player_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return player;
    }

    @Override
    public List<PlayerModel> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, player_name, hp, x, y, inventory FROM player";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            List<PlayerModel> players = new ArrayList<>();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String playerName = resultSet.getString(2);
                int hp = resultSet.getInt(3);
                int x = resultSet.getInt(4);
                int y = resultSet.getInt(5);
                String inventory = resultSet.getString(6);
                PlayerModel player = new PlayerModel(playerName, x, y, inventory, hp, id);
                players.add(player);
            }
            return players;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getPlayerIdByName(PlayerModel playerModel) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id FROM player WHERE player_name = ?";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, playerModel.getPlayerName());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
