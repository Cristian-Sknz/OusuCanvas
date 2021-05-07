package me.skiincraft.ousucanvas.elements;

import javax.annotation.Nonnull;
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
        getElements().add(container);
    }

    default void addElement(Element element, int x, int y){
        getElements().add(new ElementContainer(element, new Dimension(element.getWidth(), element.getHeight()), x, y));
    }

    default void removeElement(ElementContainer container){
        getElements().remove(container);
    }

    default void removeElement(Element element){
        getElements().removeAll(getElements().stream().filter(container -> container.getElement().equals(element)).collect(Collectors.toList()));
    }

    default void removeElement(int x, int y){
        getElements().removeAll(getElements().stream().filter(container -> container.getX() == x && container.getY() == y).collect(Collectors.toList()));
    }

    default void applyElement(Graphics2D graphics, @Nonnull ElementContainer element) {
        int[] align = element.getAlignment().align(element);
        graphics.drawImage(element.getElement().toImage(), align[0], align[1], element.getWidth(), element.getHeight(), null);
    }

    default void applyElements(Graphics2D graphics){
        for (ElementContainer element : getElements()){
            applyElement(graphics, element);
        }
    }

    default BufferedImage toImage() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), getType());
        draw((Graphics2D) image.getGraphics().create());

        return image;
    }

    default void draw(Graphics2D graphics2D){
        graphics2D.setComposite(AlphaComposite.Clear);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
    }
}
