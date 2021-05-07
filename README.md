# OusuCanvas
<p align="center">
<img src="https://github.com/Cristian-Sknz/OusuCanvas/blob/master/image.png" alt="OusuCanvas example" width="650"/>
</p>

Essa imagem foi feita com essa linha de codigo abaixo.
```java
    private static final String TITLE = "OusuCanvas";
    private static final String DESCRIPTION = "Uma util para facilitar a manipulação de imagens no Java";
    private static final String BACKGROUND = "https://wallpaperaccess.com/full/2165085.jpg";//"https://i.ytimg.com/vi/lRTtMcx6rSM/maxresdefault.jpg";

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
```
