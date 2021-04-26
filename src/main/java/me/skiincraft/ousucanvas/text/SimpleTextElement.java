package me.skiincraft.ousucanvas.text;

import me.skiincraft.ousucanvas.elements.Element;
import me.skiincraft.ousucanvas.elements.ElementContainer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SimpleTextElement implements Element {

    private StringBuilder text;
    private Font font;
    private Color color = Color.BLACK;

    private TextOrientation orientation = TextOrientation.LEFT;

    private List<ElementContainer> elementList = new ArrayList<>();

    public SimpleTextElement(String text) {
        this.text = new StringBuilder(text);
        this.font = new Font("Arial", Font.PLAIN, 12);
    }

    public SimpleTextElement(String text, Font font) {
        this.text = new StringBuilder(text);
        this.font = font;
    }

    public SimpleTextElement(String text, Font font, TextOrientation orientation) {
        this.text = new StringBuilder(text);
        this.font = font;
        this.orientation = orientation;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setText(String string) {
        if (text.length() != 0)
            text.delete(0, text.length()-1);
        text.append(string);
    }

    public SimpleTextElement appendText(String append){
        text.append(append);
        return this;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setFontSize(float size){
        setFont(this.font.deriveFont(size));
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }

    public String getText() {
        return text.toString();
    }

    @Override
    public int getWidth() {
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        return fm.stringWidth(text.toString());
    }

    @Override
    public int getHeight() {
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        return fm.getAscent() + fm.getDescent();
    }

    @Override
    public List<ElementContainer> getElements() {
        return elementList;
    }

    private void drawLeftString(Graphics2D graphic, FontMetrics fm, String string, int x, int y) {
        graphic.drawString(string, 0, (fm.getAscent() + ((y * 2) - (fm.getAscent() + fm.getDescent())) / 2));
    }

    private void drawMiddleString(Graphics2D graphic, FontMetrics fm, String string, int x, int y) {
        graphic.drawString(string, ((x * 2) - fm.stringWidth(string)) / 2, (fm.getAscent() + ((y * 2) - (fm.getAscent() + fm.getDescent())) / 2));
    }

    private void drawRightString(Graphics2D graphic, FontMetrics fm, String string, int x, int y) {
        graphic.drawString(string, (x - fm.stringWidth(string)), (fm.getAscent() + ((y * 2) - (fm.getAscent() + fm.getDescent())) / 2));
    }

    @Override
    public BufferedImage toImage() {
        BufferedImage bufferedImage = Element.super.toImage();
        draw(((Graphics2D) bufferedImage.getGraphics()));
        return bufferedImage;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.setFont(font);
        if (orientation == TextOrientation.LEFT) {
            drawLeftString(graphics2D, graphics2D.getFontMetrics(), text.toString(), 0, getHeight()/2);
        }
        if (orientation == TextOrientation.MIDDLE) {
            drawMiddleString(graphics2D, graphics2D.getFontMetrics(), text.toString(), getWidth() / 2, getHeight()/2);
        }
        if (orientation == TextOrientation.RIGHT) {
            drawRightString(graphics2D, graphics2D.getFontMetrics(), text.toString(), getWidth(), getHeight()/2);
        }
        applyElements(graphics2D);
        graphics2D.dispose();
    }
}
