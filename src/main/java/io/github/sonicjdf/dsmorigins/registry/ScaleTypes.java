package io.github.sonicjdf.dsmorigins.registry;

import net.minecraft.util.Identifier;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleModifier;
import virtuoel.pehkui.api.ScaleRegistries;
import virtuoel.pehkui.api.ScaleType;

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
		ScaleRegistries.SCALE_MODIFIERS.put(new Identifier("dsmorigins:scale_modifier"), modifier);
		scaleModifier = modifier;
		ScaleType type = ScaleType.Builder.create()
				.affectsDimensions()
				.addDependentModifier(scaleModifier)
				.build();
		ScaleRegistries.SCALE_TYPES.put(new Identifier("dsmorigins:resize"), type);
		ScaleType.BASE.getDefaultBaseValueModifiers().add(modifier);
		scaleType = type;
	}
	
	public static void init() {
	}
}
