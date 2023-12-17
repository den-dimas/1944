package main.utils;

import java.awt.*;

public class GameSettings {
    public final static int FPS = 60;

    public final static int tileSize = 16;
    public final static int scale = 2;

    public final static int scaledTileSize = tileSize * scale;
    public final static int maxCol = 18;
    public final static int maxRow = 24;

    public final static Dimension screenDimension = new Dimension(maxCol * scaledTileSize, maxRow * scaledTileSize);
}
