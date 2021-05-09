package me.skiincraft.ousucanvas;

import me.skiincraft.ousucanvas.elements.ElementAlignment;
import me.skiincraft.ousucanvas.elements.ElementContainer;
import me.skiincraft.ousucanvas.image.ImageElement;
import me.skiincraft.ousucanvas.text.TextElement;
import me.skiincraft.ousucanvas.text.TextOrientation;
import me.skiincraft.ousucanvas.text.WrapTextElement;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageBuilderTest {

    private static final String TITLE = "OusuCanvas";
    private static final String DESCRIPTION = "Uma util para facilitar a manipulação de imagens no Java";
    private static final String BACKGROUND = "https://wallpaperaccess.com/full/2165085.jpg";//"https://i.ytimg.com/vi/lRTtMcx6rSM/maxresdefault.jpg";

    @Test
    public void makeALogoforGithub() throws IOException {
        ImageBuilder builder = new ImageBuilder(780,300, BufferedImage.TYPE_INT_RGB);
        ImageElement backgroundImage = new ImageElement(BACKGROUND);
        TextElement simpleText = new TextElement(TITLE, new Font("Radio Classic Demo", Font.PLAIN, 120));
        WrapTextElement textArea = new WrapTextElement(DESCRIPTION, new Font("Impact", Font.PLAIN, 22), TextOrientation.MIDDLE, 30);

        simpleText.setColor(Color.WHITE);
        textArea.setColor(new Color(6, 86, 156));

        builder.setBackground(backgroundImage);
        builder.addElement(new ElementContainer(simpleText, ElementAlignment.CENTER, builder.getWidth() / 2, builder.getHeight()/ 2 - 50));
        builder.addElement(new ElementContainer(textArea, ElementAlignment.CENTER, builder.getWidth() / 2, builder.getHeight() / 2 + 50));
        //ImageIO.write(builder.build(), "png", new File("image.png"));
    }

}
