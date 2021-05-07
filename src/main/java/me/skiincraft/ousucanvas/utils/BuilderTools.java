package me.skiincraft.ousucanvas.utils;

import me.skiincraft.ousucanvas.ImageBuilder;
import me.skiincraft.ousucanvas.elements.ElementAlignment;
import me.skiincraft.ousucanvas.text.TextOrientation;

import java.awt.*;

public class BuilderTools {

    private Font font;
    private TextOrientation textOrientation;
    private ElementAlignment elementAlignment;
    private Color color;

    private final ImageBuilder imageBuilder;

    public BuilderTools(ImageBuilder imageBuilder) {
        this.imageBuilder = imageBuilder;
        this.font = new Font("Arial", Font.PLAIN, 12);
        this.textOrientation = TextOrientation.LEFT;
        this.elementAlignment = ElementAlignment.BOTTOM_LEFT;
        this.color = Color.WHITE;
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
