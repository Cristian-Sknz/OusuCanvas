package me.skiincraft.ousucanvas.text;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextElement extends SimpleTextElement {

    private TextOrientation orientation;
    private int space;

    public TextElement(String text) {
        this(text, new Font("Arial", Font.PLAIN, 12), TextOrientation.LEFT);
    }

    public TextElement(String text, Font font) {
        this(text, font, TextOrientation.LEFT);
    }

    public TextElement(String text, Font font, TextOrientation orientation) {
        super(text, font);
        this.orientation = orientation;
    }

    protected int getLinesLength(){
        return getLines().size();
    }

    protected List<String> getLines(){
        return new ArrayList<>(Arrays.asList(getText().split("\n")));
    }

    public TextOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(TextOrientation orientation) {
        this.orientation = orientation;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    @Override
    public int getWidth() {
        return (int) TextOrientation.getStringWidth(getText(), getFont());
    }

    @Override
    public int getHeight() {
        int height = super.getHeight();
        int lines = getLinesLength();

        return (lines > 1) ? (height + space/2) * lines : height;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setColor(getColor());
        graphics2D.setFont(getFont());
        float height = (float) TextOrientation.getStringBounds(getText(), getFont()).getHeight();
        float y = height;
        for (String lines : getLines()){
            graphics2D.drawString(lines, orientation.align(lines, getFont(), getWidth())[0], y);
            y += height + space;
        }

        applyElements(graphics2D);
        graphics2D.dispose();
    }
}
