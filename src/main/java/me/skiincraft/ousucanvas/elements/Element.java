package me.skiincraft.ousucanvas.elements;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

public interface Element {

    int getWidth();
    int getHeight();
    default int getType() {
        return BufferedImage.TYPE_INT_ARGB;
    }

    List<ElementContainer> getElements();
    default void addElement(ElementContainer container){
        if (getElements() != null)
            getElements().add(container);
    }

    default void addElement(Element element, Dimension size, int x, int y){
        if (getElements() != null)
            getElements().add(new ElementContainer(element,size,x,y));
    }

    default void removeElement(ElementContainer container){
        if (getElements() != null)
            getElements().remove(container);
    }

    default void removeElement(Element element){
        if (getElements() != null)
            getElements().removeAll(getElements().stream().filter(container -> container.getElement().equals(element)).collect(Collectors.toList()));
    }

    default void removeElement(int x, int y){
        if (getElements() != null)
            getElements().removeAll(getElements().stream().filter(container -> container.getX() == x && container.getY() == y).collect(Collectors.toList()));
    }

    default void applyElement(Graphics2D graphics, ElementContainer element){
        if (element == null)
            return;

            ElementAlignment align = element.getAlignment();
            int x = element.getX();
            int y = element.getY();
            int width = element.getWidth();
            int height = element.getHeight();

            if (align == ElementAlignment.CENTER) {
                x =  element.getX() - (width /2);
                y =  element.getY() - (height / 2);
            }

            if (align == ElementAlignment.LEFT)
                y =  element.getY()- (height / 2);

            if (align == ElementAlignment.RIGHT) {
                x =  element.getX()- (width);
                y =  element.getY()- (height / 2);
            }

            if (align == ElementAlignment.BOTTOM)
                x =  element.getX()- (width /2);

            if (align == ElementAlignment.TOP) {
                x =  element.getX()- (width /2);
                y =  element.getY()- (height);
            }

            if (align == ElementAlignment.TOP_LEFT)
                y =  element.getY()- (height);

            if (align == ElementAlignment.BOTTOM_RIGHT)
                x =  element.getX()- (width);

            if (align == ElementAlignment.TOP_RIGHT) {
                x =  element.getX()- (width);
                y =  element.getY()- (height);
            }
            graphics.drawImage(element.getElement().toImage(), x, y, element.getWidth(), element.getHeight(), null);
        }

    default void applyElements(Graphics2D graphics){
        if (getElements() == null)
            return;

        for (ElementContainer element : getElements()){
            ElementAlignment align = element.getAlignment();
            int x = element.getX();
            int y = element.getY();
            int width = element.getWidth();
            int height = element.getHeight();

            if (align == ElementAlignment.CENTER) {
                x =  element.getX() - (width /2);
                y =  element.getY() - (height / 2);
            }

            if (align == ElementAlignment.LEFT)
                y =  element.getY()- (height / 2);

            if (align == ElementAlignment.RIGHT) {
                x =  element.getX()- (width);
                y =  element.getY()- (height / 2);
            }

            if (align == ElementAlignment.BOTTOM)
                x =  element.getX()- (width /2);

            if (align == ElementAlignment.TOP) {
                x =  element.getX()- (width /2);
                y =  element.getY()- (height);
            }

            if (align == ElementAlignment.TOP_LEFT)
                y =  element.getY()- (height);

            if (align == ElementAlignment.BOTTOM_RIGHT)
                x =  element.getX()- (width);

            if (align == ElementAlignment.TOP_RIGHT) {
                x =  element.getX()- (width);
                y =  element.getY()- (height);
            }
            graphics.drawImage(element.getElement().toImage(), x, y, element.getWidth(), element.getHeight(), null);
        }
    }

    default BufferedImage toImage() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), getType());
        Graphics2D graphics2D = (Graphics2D) image.getGraphics().create();
        graphics2D.setComposite(AlphaComposite.Clear);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());

        return image;
    }
}
