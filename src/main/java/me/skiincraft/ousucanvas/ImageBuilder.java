package me.skiincraft.ousucanvas;

import me.skiincraft.ousucanvas.elements.Element;
import me.skiincraft.ousucanvas.elements.ElementAlignment;
import me.skiincraft.ousucanvas.elements.ElementContainer;
import me.skiincraft.ousucanvas.image.ImageElement;
import me.skiincraft.ousucanvas.shape.ShapeElement;
import me.skiincraft.ousucanvas.text.SimpleTextElement;
import me.skiincraft.ousucanvas.text.TextElement;
import me.skiincraft.ousucanvas.text.TextOrientation;
import me.skiincraft.ousucanvas.utils.BuilderTools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageBuilder implements Element {

    private ImageElement background;
    private Color backgroundColor;

    private final BuilderTools tools = new BuilderTools(this);
    private final List<ElementContainer> elementList = new ArrayList<>();

    private final int width;
    private final int height;
    private final int imageType;

    public ImageBuilder(int width, int height, int imageType) {
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    public ImageBuilder drawString(String string, Font font, Color color, TextOrientation textOrientation, ElementAlignment alignment, int x, int y) {
        if (string.contains("\n")){
            TextElement text = new TextElement(string, font, textOrientation);
            text.setColor(color);
            elementList.add(new ElementContainer(text, alignment, x, y));
            return this;
        }
        SimpleTextElement text = new SimpleTextElement(string, font, textOrientation);
        text.setColor(color);
        elementList.add(new ElementContainer(text, alignment, x, y));
        return this;
    }

    public ImageBuilder drawString(String string, Font font, Color color, TextOrientation orientation, int x, int y){
        return drawString(string, font, color, orientation, tools.getAlignment(), x, y);
    }

    public ImageBuilder drawString(String string, Font font, Color color, ElementAlignment alignment, int x, int y){
        return drawString(string, font, color, tools.getTextOrientation(), alignment, x, y);
    }

    public ImageBuilder drawString(String string, Font font, TextOrientation orientation, int x, int y){
        return drawString(string, font, tools.getColor(), orientation, tools.getAlignment(), x, y);
    }

    public ImageBuilder drawString(String string, Font font, int x, int y){
        return drawString(string, font, tools.getColor(), tools.getTextOrientation(), tools.getAlignment(), x, y);
    }

    public ImageBuilder drawString(String string, int x, int y){
        return drawString(string, tools.getFont(), tools.getColor(), tools.getTextOrientation(), tools.getAlignment(), x, y);
    }

    public ImageBuilder drawRect(Dimension size, ElementAlignment alignment, boolean fill, int x, int y) {
        ShapeElement<Rectangle> rectangle = new ShapeElement<>(new Rectangle((int) size.getWidth(), (int) size.getHeight()));
        rectangle.setFill(fill);
        getElements().add(new ElementContainer(rectangle, alignment, x, y));
        return this;
    }

    public ImageBuilder drawRect(Dimension size, boolean fill, int x, int y) {
        return drawRect(size, tools.getAlignment(), fill, x, y);
    }

    public ImageBuilder drawImage(Image image, Dimension size, ElementAlignment alignment,int x, int y){
        ImageElement img = new ImageElement(image, size);
        getElements().add(new ElementContainer(img, alignment, x, y));
        return this;
    }

    public ImageBuilder setBackground(ImageElement image){
        background = image;
        return this;
    }

    public ImageBuilder setBackgroundColor(Color color){
        backgroundColor = color;
        return this;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public ImageElement getBackground() {
        return background;
    }

    public BuilderTools getTools() {
        return tools;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getType() {
        return imageType;
    }

    public BufferedImage build(){
        return toImage();
    }

    @Override
    public BufferedImage toImage() {
        BufferedImage bufferedImage = Element.super.toImage();
        draw((Graphics2D) bufferedImage.getGraphics());

        return bufferedImage;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (backgroundColor != null) {
            ShapeElement<Rectangle> rectangle = new ShapeElement<>(new Rectangle(getWidth(), getHeight()));
            rectangle.setFill(true);
            rectangle.setColor(backgroundColor);
            applyElement(graphics2D, new ElementContainer(rectangle, ElementAlignment.CENTER, getWidth()/2, getHeight()/2));
        }
        graphics2D.setColor(Color.BLACK);

        if (background != null){
            applyElement(graphics2D, new ElementContainer(background, ElementAlignment.CENTER, getWidth()/2, getHeight()/2));
        }

        applyElements(graphics2D);
        graphics2D.dispose();
    }

    @Override
    public List<ElementContainer> getElements() {
        return elementList;
    }
}
