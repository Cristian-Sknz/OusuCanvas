package me.skiincraft.ousucanvas.utils;

import me.skiincraft.ousucanvas.ImageBuilder;
import me.skiincraft.ousucanvas.elements.ElementAlignment;
import me.skiincraft.ousucanvas.text.TextOrientation;

import java.awt.*;

public class BuilderTools {

    private Font font = new Font("Arial", Font.PLAIN, 12);
    private TextOrientation textOrientation = TextOrientation.LEFT;
    private ElementAlignment elementAlignment = ElementAlignment.BOTTOM_LEFT;
    private Color color = Color.WHITE;

    private final ImageBuilder imageBuilder;

    public BuilderTools(ImageBuilder imageBuilder) {
        this.imageBuilder = imageBuilder;
    }

    public TextOrientation getTextOrientation() {
        return textOrientation;
    }

    public void setTextOrientation(TextOrientation textOrientation) {
        this.textOrientation = textOrientation;
    }

    public Font getFont() {
        return font;
    }

    public ImageBuilder getImageBuilder() {
        return imageBuilder;
    }

    public void setAlignment(ElementAlignment elementAlignment) {
        this.elementAlignment = elementAlignment;
    }

    public ElementAlignment getAlignment() {
        return elementAlignment;
    }

    public Color getColor() {
        return color;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
