package Grid;

import java.util.Random;
import custom_classes.Person;

public class BehaviorAgent {
    private GridPanel grid;
    private int width;
    private int height;

    public BehaviorAgent(GridPanel grid) {
        this.grid = grid;
        this.width = grid.getViewableWidth();
        this.height = grid.getViewableHeight();
    }

    public int roam(Person p) {
        Random rand = new Random();
        int x = p.getX();
        int y = p.getY();
        y += (int) (3 * rand.nextFloat()) - 1;
        x += (int) (3 * rand.nextFloat()) - 1;
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > width) x = width;
        if (y > height) y = height;
        Tile t = grid.getTile(x,y);
        if (t.isAccessible()) {
            t.takePerson(grid.getTile(p.getX(), p.getY()));
            return 1;
        }

        return 0;
    }
}

