package uet.oop.bomberman.LoadLevelGame;

import javafx.scene.Group;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;

public class LoadLevel {
    private static int WIDTH;
    private static int HEIGHT;
    private static String fullMap = "";

    public static void createMap(int level, Group root) {
        final char wall = '#', brick = '*', portal = 'x', bomber = 'p', balloon = '1',
                oneal = '2', bombItemp = 'b', flameItem = 'f', speedItem = 's';

        try {
            getFileMap(level);
        } catch (IOException exception) {
            System.out.println("Khong tim thay file.");
        }

        String[] map = fullMap.split("\n");
        Entity object;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                    BombermanGame.addStillObject(object);
                } else {
                    switch (map[j].charAt(i)) {
                        case brick:
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            BombermanGame.addStillObject(object);
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            BombermanGame.addStillObject(object);
                            break;
                        case portal:
                            object = new Portal(i, j, Sprite.portal.getFxImage());
                            BombermanGame.addStillObject(object);
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            BombermanGame.addStillObject(object);
                            break;
                        case balloon:
                            object = new Balloon(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.balloom_left1.getFxImage());
                            BombermanGame.addEntity(object);
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            BombermanGame.addStillObject(object);
                            break;
                        case oneal:
                            object = new Oneal(i * Sprite.SCALED_SIZE, j * Sprite.SCALED_SIZE, Sprite.oneal_left1.getFxImage());
                            BombermanGame.addEntity(object);
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            BombermanGame.addStillObject(object);
                            break;
                        case bombItemp:
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            BombermanGame.addStillObject(object);
                            object = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                            BombermanGame.addStillObject(object);
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            BombermanGame.addStillObject(object);
                            break;
                        case flameItem:
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            BombermanGame.addStillObject(object);
                            object = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                            BombermanGame.addStillObject(object);
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            BombermanGame.addStillObject(object);
                            break;
                        case speedItem:
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            BombermanGame.addStillObject(object);
                            object = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                            BombermanGame.addStillObject(object);
                            object = new Brick(i, j, Sprite.brick.getFxImage());
                            BombermanGame.addStillObject(object);
                            break;
                        case wall:
                            object = new Wall(i, j, Sprite.wall.getFxImage());
                            BombermanGame.addStillObject(object);
                            break;
                        default:
                            object = new Grass(i, j, Sprite.grass.getFxImage());
                            BombermanGame.addStillObject(object);
                    }
                }

            }
        }
    }

    private static void getFileMap(int level) throws IOException {
        String path = "res/levels/Level" + (char)(level + '0') + ".txt";
        File fileMap = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(fileMap));
        String line = reader.readLine();
        String parameter[] = line.split(" ");
        WIDTH = Integer.parseInt(parameter[2]);
        HEIGHT = Integer.parseInt(parameter[1]);
        for (int i = 0; i < HEIGHT; i++) {
            line = reader.readLine().trim() + "\n";
            fullMap += line;
        }
    }
}
