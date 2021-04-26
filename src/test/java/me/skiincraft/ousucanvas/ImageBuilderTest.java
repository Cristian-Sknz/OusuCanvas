package me.skiincraft.ousucanvas;

import me.skiincraft.ousucanvas.elements.ElementAlignment;
import me.skiincraft.ousucanvas.elements.ElementContainer;
import me.skiincraft.ousucanvas.image.ImageElement;
import me.skiincraft.ousucanvas.text.SimpleTextElement;
import me.skiincraft.ousucanvas.text.TextAreaElement;
import me.skiincraft.ousucanvas.text.TextOrientation;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageBuilderTest {

    private static final String TITLE = "OusuCanvas";
    private static final String DESCRIPTION = "Uma util para facilitar a manipulação de imagens no Java";

    @Test
    public void makeALogoforGithub() throws IOException {
        ImageBuilder builder = new ImageBuilder(780,300, BufferedImage.TYPE_INT_RGB);
        ImageElement backgroundImage = new ImageElement(ImageIO.read(new URL("https://i.ytimg.com/vi/lRTtMcx6rSM/maxresdefault.jpg")));
        SimpleTextElement simpleText = new SimpleTextElement(TITLE, new Font("Radio Classic Demo", Font.PLAIN, 120));
        TextAreaElement textArea = new TextAreaElement(DESCRIPTION, new Font("Impact", Font.PLAIN, 22), 340, 85);

        simpleText.setColor(Color.WHITE);
        textArea.setOrientation(TextOrientation.MIDDLE);
        textArea.setColor(new Color(6, 86, 156));

        builder.setBackground(backgroundImage);
        builder.addElement(new ElementContainer(simpleText, ElementAlignment.CENTER, builder.getWidth() / 2, builder.getHeight()/ 2 - 50));
        builder.addElement(new ElementContainer(textArea, ElementAlignment.CENTER, builder.getWidth() / 2, builder.getHeight() / 2 + 50));

        BufferedImage image = builder.build();
    }
    
}
