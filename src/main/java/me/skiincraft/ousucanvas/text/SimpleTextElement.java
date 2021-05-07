package me.skiincraft.ousucanvas.text;

import me.skiincraft.ousucanvas.elements.Element;
import me.skiincraft.ousucanvas.elements.ElementContainer;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SimpleTextElement implements Element {

    private final StringBuilder text;
    private Font font;
    private Color color;

    private final List<ElementContainer> elementList = new ArrayList<>();

    public SimpleTextElement(String text) {
        this(text, new Font("Arial", Font.PLAIN, 12));
    }

    public SimpleTextElement(String text, Font font) {
        this(text, font, Color.WHITE);
    }

    public SimpleTextElement(String text, Font font, Color color) {
        this.text = new StringBuilder(text);
        this.font = font;
        this.color = color;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getText() {
        return text.toString();
    }

    public StringBuilder setText(String string) {
        if (text.length() != 0)
            text.delete(0, text.length() - 1);

        return text.append(string);
    }

    public StringBuilder appendText(String append){
        return text.append(append);
    }

    @Override
    public int getWidth() {
        return (int) font.getStringBounds(text.toString(), new FontRenderContext(null, true, true))
                .getWidth();
    }

    @Override
    public int getHeight() {
        return (int) TextOrientation.getStringHeight(text.toString(), font);
    }

    @Override
    public List<ElementContainer> getElements() {
        return elementList;
    }

    @Override
    public BufferedImage toImage() {
        BufferedImage bufferedImage = Element.super.toImage();
        draw(((Graphics2D) bufferedImage.getGraphics()));
        return bufferedImage;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setColor(color);
        graphics2D.setFont(font);
        graphics2D.drawString(getText(), 0, (float) TextOrientation.getStringBounds(getText(), font).getHeight());

        applyElements(graphics2D);
        graphics2D.dispose();
    }
}
