package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    static class Position{
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 
     * @param world
     * @param p use p.x, p.y to specifies the lower left corner of the hexagon
     * @param s size of Hexagon
     * @param t
     */

     /*size of aa  is 2
      *       aaaa
      *        aa
      */
    public static void addHex(TETile[][] world, Position p, int s, TETile t) {
        int bottomX = p.x;
        int bottomY = p.y;
        int length = s;

        /*
         *         aaa  <- topLine
         *        aaaaa
         *       aaaaaaa
         *       aaaaaaa
         *        aaaaa 
         *         aaa  <- bottomLine, row = 0
         * 
         */
        for (int row = 0; row < s; row += 1) {
            addBottomLine(world, bottomX, bottomY, length, t);
            addTopLine(world, bottomX, bottomY, length, t, row, s);
            length += 2;
            bottomX -= 1;
            bottomY += 1;
        }

    }

    private static void addBottomLine(TETile[][] world, int x, int y, int length, TETile t) {
        addLine(world, x, y, length, t);
    }

    private static void addTopLine(TETile[][] world, int bottomX, int bottomY, int length, TETile t, int row, int size) {
        int x = bottomX;
        int y = bottomY + 2 * (size - row) - 1;
        addLine(world, x, y, length, t);

    }
    
    /** add TETiles to world at (x, y) as given length*/
    private static void addLine(TETile[][] world, int x, int y, int length, TETile t) {
      
        for (int i = x; i < x + length; i += 1) {
            if (!withinBoundary(i, y, world)) {
                continue;
            }
            world[i][y] = t;
        }
    }

    private static boolean withinBoundary(int x, int y, TETile[][] world) {
        return x >= 0
            && x < world.length
            && y >= 0
            && y < world[0].length;
    }

    public static void main(String[] args) {
        int HEIGHT = 30;
        int WIDTH = 60;

        TERenderer te = new TERenderer();
        te.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        HexWorld.Position p = new Position(10, 10);
        HexWorld.addHex(world, p, 2, Tileset.FLOWER);

        te.renderFrame(world);
    }
}
