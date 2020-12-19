package me.skiincraft.ousucanvas.text;

import me.skiincraft.ousucanvas.elements.Element;
import me.skiincraft.ousucanvas.elements.ElementContainer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TextElement implements Element {

    private final StringBuilder text;

    private float space;
    private Font font;
    private TextOrientation orientation = TextOrientation.LEFT;
    private Color color;

    private final List<ElementContainer> elementList = new ArrayList<>();

    public TextElement(String text) {
        this.text = new StringBuilder(text);
        this.font = new Font("Arial", Font.PLAIN, 12);
        this.space = fit(0.1F);
    }

    public TextElement(String text, Font font) {
        this.text = new StringBuilder(text);
        this.font = font;
        this.space = fit(0.1F);
    }

    public TextElement(String text, Font font, TextOrientation orientation) {
        this.text = new StringBuilder(text);
        this.font = font;
        this.orientation = orientation;
        this.space = fit(0.1F);
    }

    public TextElement(String text, Font font, int space, TextOrientation orientation) {
        this.text = new StringBuilder(text);
        this.font = font;
        this.space = space;
        this.orientation = orientation;
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

    public float getSpace() {
        return space;
    }

    public TextOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(TextOrientation orientation) {
        this.orientation = orientation;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public void setText(String string) {
        if (text.length() != 0) {
            text.delete(0, text.length());
        }
        text.append(string);
    }

    public TextElement appendText(String append){
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

    private float fit(float por){
        return (por*100)/30;
    }

    @Override
    public int getWidth() {
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        return fm.stringWidth(Arrays.stream(text.toString().split("\n")).max(Comparator.comparingLong(String::length))
                .orElse(""));
    }

    @Override
    public int getHeight() {
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
        String[] ln = text.toString().split("\n");
        final int height = fm.getAscent() + fm.getDescent();
        return (ln.length == 1) ? height + fm.getDescent(): (fm.getAscent() * ln.length -1) + ((int) fit(space * 11)) + fm.getDescent();
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
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setColor(color);
        graphics2D.setFont(font);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        FontMetrics fm = graphics2D.getFontMetrics();
        int y = fm.getAscent() - fm.getDescent();

        for (String string : text.toString().split("\n")) {
            if (orientation == TextOrientation.LEFT) {
                drawLeftString(graphics2D, fm, string, 0, y);
            }
            if (orientation == TextOrientation.MIDDLE) {
                drawMiddleString(graphics2D, fm, string, getWidth() / 2, y);
            }
            if (orientation == TextOrientation.RIGHT) {
                drawRightString(graphics2D, fm, string, getWidth(), y);
            }
            y += fm.getAscent() + fit(space * 3);
        }

        applyElements(graphics2D);
        graphics2D.dispose();
        return bufferedImage;
    }
}
