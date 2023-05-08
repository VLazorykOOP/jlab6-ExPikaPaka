package Task1;

import java.awt.*;

public class Circle {
    private int x, y;       // center coord
    private int _radius;    // static radius
    private int radius;     // radius
    private int minRadius;  // parameter to squeeze circle a little
    private int speed;      // speed in pixels
    private int dx, dy;     // direction vector

    private Color col;

    public Circle(int x, int y, int radius, int speed, Color col) {
        set(x, y, radius, speed, col);
    }

    public void set(int x, int y, int radius, int speed, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this._radius = radius;
        this.minRadius = (int)((float)radius - (float)radius * 0.1);
        this.speed = speed;
        this.dx = speed;
        this.dy = speed;
        this.col = color;
    };

    public void update(int minX, int minY, int width, int height) {
        // Updating position
        x += dx;
        y += dy;


        radius += speed;
        if(radius > _radius) radius = _radius;

        // Collision check
        if (x - radius < minX) {
            radius -= speed * 2;
            if (x - minRadius < minX) {
                dx = speed;
            }
        }


        if (x + radius > width) {
            radius -= speed * 2;
            if (x + minRadius > width) {
                dx = -speed;
            }
        }



        if (y - radius <= minY) {
            radius -= speed * 2;
            if (y - minRadius <= minY) {
                dy = speed;
            }
        }



        if (y + radius > height) {
            radius -= speed * 2;
            if (y + minRadius > height) {
                dy = -speed;
            }
        }

    }

    public void draw(Graphics g) {
//        g.setColor(Color.YELLOW);
//        g.fillOval(x - _radius, y - _radius, _radius * 2, _radius * 2);
        g.setColor(col);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
//        g.setColor(Color.GREEN);
//        g.fillOval(x - minRadius, y - minRadius, minRadius * 2, minRadius * 2);
    }
}
