public class Planet {
    /** instance variables for Planet */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    /** some constants */
    public double exponentForDistance = 2.0;
    public double gravitationalConstant = 6.67e-11;
    // /** constructor */
    // public Planet() {}
    /** constructor */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    /** constructor */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    /** calculate the distance between this planet and another one */
    public double calcDistance(Planet p) {
        return Math.pow(Math.pow(xxPos - p.xxPos, exponentForDistance) + Math.pow(yyPos - p.yyPos, exponentForDistance), 1 / exponentForDistance);
    }
    /** calculate the force exerted by another planet */
    public double calcForceExertedBy(Planet p) {
        return (gravitationalConstant * mass * p.mass) / Math.pow(this.calcDistance(p), exponentForDistance);
    }
    /** calculate the force exerted in the X direction */
    public double calcForceExertedByX(Planet p) {
        return ((p.xxPos - xxPos) * calcForceExertedBy(p)) / calcDistance(p);
    }
    /** calculate the force exerted in the Y direction */
    public double calcForceExertedByY(Planet p) {
        return ((p.yyPos - yyPos) * calcForceExertedBy(p)) / calcDistance(p);
    }
    /** judge if this planet equals another one */
    public boolean equals(Planet p) {
        return calcDistance(p) == 0;
    }
    /** calculate the net X force */
    public double calcNetForceExertedByX(Planet[] parr) {
        double xxForce = 0;
        for (Planet p : parr) {
            if (equals(p)) {
                continue;
            }
            xxForce = xxForce + calcForceExertedByX(p);
        }
        return xxForce;
    }
    /** calculate the net Y force */
    public double calcNetForceExertedByY(Planet[] parr) {
        double yyForce = 0;
        for (Planet p : parr) {
            if (equals(p)) {
                continue;
            }
            yyForce = yyForce + calcForceExertedByY(p);
        }
        return yyForce;
    }
    /** update the velocity and position */
    public void update(double dt, double fX, double fY) {
        double xxAcc = fX / mass;
        double yyAcc = fY / mass;
        xxVel = xxVel + dt * xxAcc;
        yyVel = yyVel + dt * yyAcc;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }
    /** draw the planet */
    public void draw() {
        String imgPath = "./images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgPath);
    }
}
