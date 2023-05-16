package ohm.softa.a09.model;

import ohm.softa.a09.model.empire.TieBomber;
import ohm.softa.a09.model.empire.TieFighter;
import ohm.softa.a09.model.empire.TieInterceptor;
import ohm.softa.a09.model.rebellion.AWing;
import ohm.softa.a09.model.rebellion.XWing;
import ohm.softa.a09.model.rebellion.YWing;
import ohm.softa.a09.resource.FxImageLoaderAdapter;
import ohm.softa.a09.resource.ResourceLoader;
import ohm.softa.a09.util.NameGenerator;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Factory to create new fighters
 * Creates random fighters
 *
 * @author Peter Kurfer
 */
public final class FighterFactory {

	private static final int NumberOfKnownFighterTypes = 6;
	private final Random random;
	private final NameGenerator nameGenerator;
	private final ResourceLoader<Image> imageResourceLoader;
	private final FxImageLoaderAdapter loaderAdapter;

	private final Map< String ,Image> imageResource = new HashMap<>();

	private Image getIfInImageResource(String path) {
		if(imageResource.containsKey(path)) return imageResource.get(path);
		else {
			Image newImage =
				imageResourceLoader.loadResource(ClassLoader.getSystemClassLoader(), path);
			imageResource.put(path, newImage);
			return newImage;
		}

	}


	public FighterFactory() {
		nameGenerator = new NameGenerator();
		random = new Random();
		imageResourceLoader = new ResourceLoader<>(Image::new);
		loaderAdapter = new FxImageLoaderAdapter();
	}

	/**
	 * Create a random Fighter
	 *
	 * @implNote the method has an implementation flaw because it always loads the fighters image
	 * @return a new Fighter instance
	 */
	public Fighter createFighter() {
		switch (random.nextInt(NumberOfKnownFighterTypes)) {
			case 0:
				return new AWing(nameGenerator.generateName(), getIfInImageResource("fighter/awing.jpg"));
			case 1:
				return new XWing(nameGenerator.generateName(),getIfInImageResource("fighter/xwing.jpg"));
			case 2:
				return new YWing(nameGenerator.generateName(), getIfInImageResource( "fighter/ywing.jpg"));
			case 3:
				return new TieBomber(nameGenerator.generateName(), getIfInImageResource("fighter/tiebomber.jpg"));
			case 4:
				return new TieFighter(nameGenerator.generateName(), getIfInImageResource( "fighter/tiefighter.jpg"));
			default:
				return new TieInterceptor(nameGenerator.generateName(), getIfInImageResource( "fighter/tieinterceptor.jpg"));
		}
	}
}
