package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String level, Player player) {
        InputStream is = MapLoader.class.getResourceAsStream(level);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY, level);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '|':
                            cell.setType(CellType.DISAPPEARING_WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 'c':
                            cell.setType(CellType.CLOSED_DOOR);
                            break;
                        case 'd':
                            cell.setType(CellType.OPEN_DOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            map.setSkeletonCount(map.getSkeletonCount() + 1);
                            break;
                        case 'K':
                            cell.setType(CellType.SWORD);
                            new Sword(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.HELMET);
                            new Helmet(cell);
                            break;
                        case 'y':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            if (player != null) {
                                player.setCell(cell);
                                map.setPlayer(player);
                            } else {
                                map.setPlayer(new Player(cell));
                            }
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new Bat(cell);
                            break;
                        case 'Q':
                            cell.setType(CellType.QUIT);
                            break;
                        case 'U':
                            cell.setType(CellType.U);
                            break;
                        case 'I':
                            cell.setType(CellType.I);
                            break;
                        case 'T':
                            cell.setType(CellType.T);
                            break;
                        case 'R':
                            cell.setType(CellType.REPEAT);
                            break;
                        case 'P':
                            cell.setType(CellType.PLAY);
                            break;
                        case 'L':
                            cell.setType(CellType.L);
                            break;
                        case 'A':
                            cell.setType(CellType.A);
                            break;
                        case 'Y':
                            cell.setType(CellType.Y);
                            break;
                        case 'a':
                            cell.setType(CellType.ARROW);
                            break;
                        case 'p':
                            cell.setType(CellType.POTION);
                            new Potion(cell);
                            break;
                        case 'S':
                            cell.setType(CellType.POTION_SELLER);
                            new PotionSeller(cell);
                        case 'w':
                            cell.setType(CellType.SPIDERWEB);
                            break;
                        case 't':
                            cell.setType(CellType.TORCH_A);
                            map.addTorch(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
