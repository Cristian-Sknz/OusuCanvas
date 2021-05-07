package me.skiincraft.ousucanvas.text;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WrapTextElement extends TextElement {

    private int wrap;

    public WrapTextElement(String text, int wrap) {
        this(text, new Font("Arial", Font.PLAIN, 12), TextOrientation.LEFT, wrap);
    }

    public WrapTextElement(String text, Font font, int wrap) {
        this(text, font, TextOrientation.LEFT, wrap);
    }

    public WrapTextElement(String text, Font font, TextOrientation orientation, int wrap) {
        super(text, font, orientation);
        this.wrap = wrap;
    }

    public int getWrap() {
        return wrap;
    }

    public void setWrap(int wrap) {
        this.wrap = wrap;
    }

    @Override
    public int getWidth() {
        return (int) TextOrientation.getStringWidth(getWrappedText(), getFont());
    }

    @Override
    public int getHeight() {
        int height = (int) TextOrientation.getStringHeight(getText(), getFont());
        int lines = getLinesLength();

        return (lines > 1) ? height * lines - 1 : height;
    }

    public String getWrappedText(){
        return String.join("\n", getLines());
    }

    @Override
    protected List<String> getLines() {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(getText());
        int count = 0;
        while (scanner.hasNext()) {
            String next = scanner.next();
            if (count >= wrap){
                if (!(next.length() >= 5 && next.length() <= 10)){
                    builder.append("\n").append(next).append(" ");
                    count = 0;
                    continue;
                }
            }
            count += next.length();
            builder.append(next).append((scanner.hasNext()) ? " " : "");
        }
        return Arrays.asList(builder.toString().split("\n"));
    }
}