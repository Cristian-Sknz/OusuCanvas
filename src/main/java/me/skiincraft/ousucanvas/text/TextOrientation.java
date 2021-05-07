package me.skiincraft.ousucanvas.text;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Comparator;

public enum TextOrientation {

    LEFT, MIDDLE, RIGHT;


    public int[] align(String text, Font font, int width){
        return this.align(text, font, width, (int) TextOrientation.getStringBounds(text, font).getHeight());
    }

    public int[] align(String text, Font font, int width, int y){
        switch (this){
            default:
                return new int[] {0, y};
            case MIDDLE:
                return new int[] {(int) (width / 2 - getStringWidth(text, font)/2), y};
            case RIGHT:
                return new int[] {(int) (width - getStringWidth(text, font)), y};
        }
    }

    public static Rectangle2D getStringBounds(String string, Font font){
        return font.getStringBounds(Arrays.stream(string.split("\n"))
                .max(Comparator.comparingLong(String::length))
                .orElse(""), new FontRenderContext(null, true, true));
    }

    public static double getStringWidth(String string, Font font){
        return getStringBounds(string, font).getWidth();
    }

    public static double getStringHeight(String string, Font font){
        FontRenderContext frc = new FontRenderContext(null, true, true);
        return getStringBounds(string, font).getHeight() + font.getLineMetrics(string, frc).getDescent();
    }

}
