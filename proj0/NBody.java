public class NBody {
    /** get radius from given file, which is the second double */
    public static double readRadius(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();

        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
        }

        return planets;
    }

    private static void drawBackGround(double radius) {
        StdDraw.setScale(radius * -1, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
    }

    private static void drawplanets(Planet[] planets) {
        for (Planet b : planets) {
            b.draw();
        }
    }

    public static void main(String[] args) {

        if (args.length > 0) {
            double T = Double.parseDouble(args[0]);
            double dt = Double.parseDouble(args[1]);
            String filename = args[2];
            double radius = readRadius(filename);
            Planet[] planets = readPlanets(filename);

            // better draw performence
            StdDraw.enableDoubleBuffering();

            int size = planets.length;

            double[] xForces = new double[size];
            double[] yForces = new double[size];

            double time = 0;

            // draw loop
            while (time < T) {
                //calculate the nex x and y forces for each Planet
                for (int i = 0; i < size; i++) {
                    xForces[i] = planets[i].calcNetForceExertedByX(planets);
                    yForces[i] = planets[i].calcNetForceExertedByY(planets);
                }

                //update each Planet's position, velocity, and acceleration
                for (int i = 0; i < size; i++) {
                    planets[i].update(dt, xForces[i], yForces[i]);
                }

                StdDraw.clear();
                drawBackGround(radius);
                drawplanets(planets);

                StdDraw.show();
                StdDraw.pause(10);
                time += dt;
            }

            StdOut.printf("%d\n", planets.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < planets.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
            }

        }

    }
}