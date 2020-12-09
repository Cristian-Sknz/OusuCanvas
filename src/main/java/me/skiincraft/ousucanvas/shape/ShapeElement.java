package me.skiincraft.ousucanvas.shape;

import me.skiincraft.ousucanvas.elements.ElementContainer;
import me.skiincraft.ousucanvas.elements.GenericElement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ShapeElement<T extends Shape> implements GenericElement<T> {

    private final T object;
    private final List<ElementContainer> elementList = new ArrayList<>();
    private Color color = Color.BLACK;

    private boolean fill = true;

    public ShapeElement(T object) {
        this.object = object;
    }

    @Override
    public int getWidth() {
        return (int) object.getBounds2D().getWidth();
    }

    @Override
    public int getHeight() {
        return (int) object.getBounds2D().getHeight();
    }

    @Override
    public List<ElementContainer> getElements() {
        return elementList;
    }

    @Override
    public T getGeneric() {
        return object;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public boolean isFill() {
        return fill;
    }

    @Override
    public BufferedImage toImage() {
        BufferedImage bufferedImage = GenericElement.super.toImage();
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setColor(color);
        graphics2D.draw(getGeneric());
        if (isFill()){
            graphics2D.fill(getGeneric());
        }
        applyElements(graphics2D);
        graphics2D.dispose();
        return bufferedImage;
    }
}
