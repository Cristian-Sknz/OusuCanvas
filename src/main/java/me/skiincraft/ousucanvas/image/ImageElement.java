package me.skiincraft.ousucanvas.image;

import me.skiincraft.ousucanvas.elements.Element;
import me.skiincraft.ousucanvas.elements.ElementContainer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageElement implements Element {

    private final Image image;

    private int width;
    private int height;

    private Color backgroundColor;

    private List<ElementContainer> elementList = new ArrayList<>();

    public ImageElement(Image image) {
        this(image, image.getWidth(null), image.getHeight(null));
    }

    public ImageElement(String url) throws IOException {
        this.image = ImageIO.read(new URL(url));
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public ImageElement(Image image, Dimension size) {
        this(image, size.width, size.height);
    }

    public ImageElement(String url, Dimension size) throws IOException {
        this(url, size.width, size.height);
    }

    public ImageElement(Image image, int width, int height) {
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public ImageElement(String url, int width, int height) throws IOException {
        this.image = ImageIO.read(new URL(url));
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    public List<ElementContainer> getElements(){
        return elementList;
    }

    @Override
    public BufferedImage toImage() {
        BufferedImage bufferedImage = Element.super.toImage();
        draw((Graphics2D) bufferedImage.getGraphics());
        return bufferedImage;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (backgroundColor != null) {
            graphics2D.setColor(backgroundColor);
            graphics2D.fillRect(0, 0, width, height);
        }
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0,0, width, height,null);
        applyElements(graphics2D);
        graphics2D.dispose();
    }
}
