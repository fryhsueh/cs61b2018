public class Planet {
    private static double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(
                Math.pow(this.xxPos - p.xxPos, 2) + Math.pow(this.yyPos - p.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p) {
        double distance = this.calcDistance(p);
        return G * this.mass * p.mass / Math.pow(distance, 2);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double distance = calcDistance(p);
        return calcForceExertedBy(p) * dx / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        double distance = calcDistance(p);
        return calcForceExertedBy(p) * dy / distance;
    }

    public double calcNetForceExertedByX(Planet[] allBodys) {
        double netForceX = 0;
        for (Planet b : allBodys) {
            if (!b.equals(this)) {
                netForceX += this.calcForceExertedByX(b);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allBodys) {
        double netForceY = 0;
        for (Planet b : allBodys) {
            if (!b.equals(this)) {
                netForceY += this.calcForceExertedByY(b);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel = xxVel + ax * dt;
        yyVel = yyVel + ay * dt;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }

    public void draw() {
        String imgFileName = "images/" + this.imgFileName;
        StdDraw.picture(xxPos, yyPos, imgFileName);
    }
}