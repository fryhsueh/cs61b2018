package byog.lab5;

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
         */
        for (int row = 0; row < s; row += 1) {
            addLine(world, bottomX, bottomY, length, t); //add bottomLine

            int topX = bottomX;
            int topY = bottomY + 2 * (s - row) - 1;
            addLine(world, topX, topY, length, t); //add topLine

            length += 2;
            bottomX -= 1;
            bottomY += 1;
        }

    }
    
    /** add a line of TETiles to world at (x, y) as given length*/
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
    
    /**
     * 
     * @param size num of hexagons at each side
     * @param hexagonSize size of a single hexagon
     */
    private static void drawHexagons(int size, int hexagonSize) {

        /*initialize*/
        int HEIGHT = 45;
        int WIDTH = 45;

        TERenderer te = new TERenderer();
        te.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        /*add hexagons at given positions */

        addHexs(world, size, 10, 10, hexagonSize);

        /*draw */

        te.renderFrame(world);
    }


    /**
     *<pre>
     * eg:
     *      **
     *     ****
     *    ******
     *    ******  <- long side == 6
     *     ****
     *      **  <- short side , hexagon size == 2
     *</pre>
     */
    
    private static void addHexs(TETile[][] world, int size, int leftPosX, int leftPosY, int hexagonSize) {
        int num = size;
        int heightOfHex = hexagonSize * 2;
        int shortSide = hexagonSize;
        int longSide = hexagonSize + 2 * (hexagonSize - 1);
    
        /*adding form outside to inside */
        for (int vertical = 0; vertical < size - 1; vertical += 1) {
            addVertical(world, num, leftPosX, leftPosY, heightOfHex, hexagonSize); //add left vertical line

            int rightPosX = leftPosX + (size - vertical - 1) * (shortSide + longSide) ;
            int rightPosY = leftPosY;
            addVertical(world, num, rightPosX, rightPosY, heightOfHex, hexagonSize); //add rigth vertical line

            leftPosX += (longSide - shortSide) / 2 + shortSide;
            leftPosY -= hexagonSize;
            num += 1;
        }
        
        addVertical(world, num, leftPosX, leftPosY, heightOfHex, hexagonSize); //add middle line
    
    }

   
    /*at given (x, y) add hexagons from bottom to top*/
    private static void addVertical(TETile[][] world, int num, int x, int y, int height, int hexagonSize) {
        Random r = new Random();


        for (int i = 0; i < num; i += 1) {

            TETile tile = Tileset.random();
            TETile t = TETile.colorVariant(tile, 100, 100, 100, r);

            int posY = y + i * height;
            addHex(world, new Position(x, posY), hexagonSize, t);

        }
    }
    
    private static void drawAHexTest() {
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
        HexWorld.addHex(world, p, 3, Tileset.FLOWER);

        te.renderFrame(world);

    }


    public static void main(String[] args) {
    //drawAHexTest();
    drawHexagons(3, 3);

    }
}
