public class NBody {
    /** read the radius of the universe */
    public static double readRadius(String filePathName) {
        In in = new In(filePathName);
        in.readInt();
        double radius = in.readDouble();
        in.close();
        return radius;
    }
    /** read the planets */
    public static Planet[] readPlanets(String filePathName) {
        In in = new In(filePathName);
        int numberOfPlanets = in.readInt();
        in.readDouble();
        Planet[] parr = new Planet[numberOfPlanets];
        for (int i = 0; i < numberOfPlanets; i++) {
            parr[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        in.close();
        return parr;
    }
    /** main */
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] parr = readPlanets(filename);
        double time = 0;
        int showTime = 10;
        StdDraw.setScale(-1 * radius, radius);
        StdDraw.enableDoubleBuffering();
        double[] xForces = new double[parr.length];
        double[] yForces = new double[parr.length];
        while (time < T) {
            for (int i = 0; i < parr.length; i++) {
                xForces[i] = parr[i].calcNetForceExertedByX(parr);
                yForces[i] = parr[i].calcNetForceExertedByY(parr);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (int i = 0; i < parr.length; i++) {
                parr[i].update(dt, xForces[i], yForces[i]);
                parr[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(showTime);
            time += dt;
        }
        StdOut.printf("%d\n", parr.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < parr.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            parr[i].xxPos, parr[i].yyPos, parr[i].xxVel,
            parr[i].yyVel, parr[i].mass, parr[i].imgFileName);   
        }
    }
}
