package me.skiincraft.ousucanvas.elements;

import java.awt.*;

public class ElementContainer {

    private Element element;
    private int x;
    private int y;
    private int width = -1;
    private int height = -1;

    private ElementAlignment alignment = ElementAlignment.LEFT;

    public ElementContainer(Element element, Dimension size, int x, int y) {
        this.element = element;
        this.width = (int) size.getWidth();
        this.height = (int) size.getHeight();
        this.x = x;
        this.y = y;
    }

    public ElementContainer(Element element, Dimension size, ElementAlignment alignment, int x, int y) {
        this.element = element;
        this.width = (int) size.getWidth();
        this.height = (int) size.getHeight();
        this.alignment = alignment;
        this.x = x;
        this.y = y;
    }

    public ElementContainer(Element element, int x, int y) {
        this.element = element;
        this.x = x;
        this.y = y;
    }

    public ElementContainer(Element element, ElementAlignment alignment, int x, int y) {
        this.element = element;
        this.alignment = alignment;
        this.x = x;
        this.y = y;
    }

    public ElementAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(ElementAlignment alignment) {
        this.alignment = alignment;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return (width == -1) ? element.getWidth() : width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return (height == -1) ? element.getHeight() : height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
