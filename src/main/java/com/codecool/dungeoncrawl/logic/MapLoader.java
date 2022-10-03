package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String level) {
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
                            map.addMonsterToMovableMonsters(new Skeleton(cell));
                            break;
                        case 'K':
                            cell.setType(CellType.SWORD);
                            new Sword(cell);
                            break;
                        case 'h':
                            cell.setType(CellType.HELMET);
                            new Helmet(cell);
                            break;
                        case 'Y':
                            cell.setType(CellType.KEY);
                            new Key(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            map.addMonsterToMovableMonsters(new Ghost(cell));
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new Bat(cell);
                            break;
                        case 'Q':
                            cell.setType(CellType.QUIT);
                            break;
                        case 'R':
                            cell.setType(CellType.REPEAT);
                            break;
                        case 'p':
                            cell.setType(CellType.POTION);
                            new Potion(cell);
                            break;
                        case 'P':
                            cell.setType(CellType.POTION_SELLER);
                            new PotionSeller(cell);
                        case 'w':
                            cell.setType(CellType.SPIDERWEB);
                            break;
                        case 't':
                            cell.setType(CellType.TORCH);
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
