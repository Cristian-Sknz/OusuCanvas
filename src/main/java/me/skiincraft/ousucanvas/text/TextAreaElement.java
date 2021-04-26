package me.skiincraft.ousucanvas.text;

import me.skiincraft.ousucanvas.elements.Element;
import me.skiincraft.ousucanvas.elements.ElementContainer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TextAreaElement implements Element {

    private final StringBuilder text;
    private StringBuilder wrapper = new StringBuilder();
    private TextOrientation orientation = TextOrientation.LEFT;
    private Font font;

    private int width;
    private int height;

    private float space;
    private Color color;

    private final List<ElementContainer> elementList = new ArrayList<>();

    public TextAreaElement(String text, int width, int height) {
        this.text = new StringBuilder(text);
        this.width = width;
        this.font = new Font("Arial", Font.PLAIN, 12);
        this.height = height;
        this.space = fit((float)width,0.1F);
    }

    public TextAreaElement(String text, Font font, int width, int height) {
        this.text = new StringBuilder(text);
        this.width = width;
        this.font = font;
        this.height = height;
        this.space = fit((float)width,0.1F);
    }

    public void adjustTextBox(){
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        wrapper = new StringBuilder();
        int size = 0;
        // scaling the text according to the width
        for (char ch : text.toString().toCharArray()) {
            if (size + fm.charWidth(ch) >= width){
                wrapper.append("\n");
                size = 0;
            }
            wrapper.append(ch);
            size += fm.charWidth(ch);
        }

        String[] ln = wrapper.toString().split("\n");
        final int height = fm.getAscent() + fm.getDescent();
        int ht = (ln.length == 1) ? height + fm.getDescent() : (fm.getAscent() * ln.length -1) + ((int) fit(width,space * 11)) + fm.getDescent();
        if (ht > getHeight()){
            if (font.getSize() <= 5) {
                return;
            }
            font = font.deriveFont(font.getSize2D() - 0.5F);
            adjustTextBox();
        }
    }

    private float fit(float de, float por){
        return (por*100)/de;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public TextOrientation getOrientation() {
        return orientation;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setOrientation(TextOrientation orientation) {
        this.orientation = orientation;
    }

    public void setText(String string) {
        if (text.length() != 0) {
            text.delete(0, text.length());
        }
        text.append(string);
    }

    public TextAreaElement appendText(String string){
        text.append(string);
        return this;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getSpace() {
        return space;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getText() {
        return text.toString();
    }

    private void drawLeftString(Graphics2D graphic, FontMetrics fm, String string, int x, int y) {
        graphic.drawString(string, x, (fm.getAscent() + ((y * 2) - (fm.getAscent() + fm.getDescent())) / 2));
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
        adjustTextBox();
        draw((Graphics2D) bufferedImage.getGraphics());

        return bufferedImage;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.setFont(font);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        FontMetrics fm = graphics2D.getFontMetrics();
        int y = fm.getAscent() - fm.getDescent();

        for (String string : wrapper.toString().split("\n")) {
            if (orientation == TextOrientation.LEFT) {
                drawLeftString(graphics2D, fm, string, 0, y);
            }
            if (orientation == TextOrientation.MIDDLE) {
                drawMiddleString(graphics2D, fm, string, getWidth() / 2, y);
            }
            if (orientation == TextOrientation.RIGHT) {
                drawRightString(graphics2D, fm, string, getWidth(), y);
            }

            y += fm.getAscent() + fit(width,space * 3);
        }
        graphics2D.dispose();
    }

    @Override
    public List<ElementContainer> getElements() {
        return elementList;
    }
}
