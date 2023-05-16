package ohm.softa.a09.resource;

import javafx.scene.image.Image;
public class FxImageLoaderAdapter {

	private ResourceLoader<Image> resourceLoader = new ResourceLoader<Image>(Image::new);

	public Image loadImage(String resourcePath) {
		return resourceLoader.loadResource(ClassLoader.getSystemClassLoader(), resourcePath);
	}
}
