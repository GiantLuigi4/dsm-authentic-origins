package io.github.sonicjdf.dsmorigins.registry;

import net.minecraft.util.Identifier;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleModifier;
import virtuoel.pehkui.api.ScaleRegistries;
import virtuoel.pehkui.api.ScaleType;

import java.util.Optional;

public class ScaleTypes {
	public static final ScaleModifier scaleModifier;
	public static final ScaleType scaleType;
	
	static {
		// https://github.com/GiantLuigi4/Smaller-Units/blob/b78d593492316e4d650571ab07e8441f87ad2d45/src/main/java/tfc/smallerunits/Smallerunits.java#L132-L146
		ScaleModifier modifier = new ScaleModifier() {
			@Override
			public float modifyScale(ScaleData scaleData, float modifiedScale, float delta) {
				return scaleType.getScaleData(scaleData.getEntity()).getScale(delta) * modifiedScale;
			}
		};
		ScaleRegistries.register(ScaleRegistries.SCALE_MODIFIERS, new Identifier("dsmorigins:scale_modifier"), modifier);
		scaleModifier = modifier;
		ScaleType type = ScaleType.Builder.create()
				.affectsDimensions()
				.addDependentModifier(scaleModifier)
				.build();
		ScaleRegistries.register(ScaleRegistries.SCALE_TYPES, new Identifier("dsmorigins:resize"), type);
		scaleType = type;
		Optional<ScaleType> baseType = getType("base");
		// luigi: suppress warning because I don't want to risk accidental class loading, nor do I want intelliJ constantly warning me about the fact that I do this
		//noinspection OptionalIsPresent
		if (baseType.isPresent())
			baseType.get().getDefaultBaseValueModifiers().add(modifier);
	}
	
	public static void init() {
	}
	
	// using optional to prevent accidental class loading
	public static Optional<ScaleType> getType(String name) {
		// luigi: this is actually better than what I had in SU
		if (name.contains(":"))
			return Optional.of(ScaleRegistries.getEntry(ScaleRegistries.SCALE_TYPES, new Identifier(name)));
		return Optional.of(ScaleRegistries.getEntry(ScaleRegistries.SCALE_TYPES, new Identifier("pehkui", name)));
	}
}
