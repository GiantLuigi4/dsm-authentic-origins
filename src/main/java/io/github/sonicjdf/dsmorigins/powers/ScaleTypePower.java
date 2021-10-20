package io.github.sonicjdf.dsmorigins.powers;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleRegistries;

import java.util.List;

public class ScaleTypePower extends Power {
	Identifier[] scaleTypes;
	float scale;
	
	public ScaleTypePower(PowerType<?> type, LivingEntity entity, Identifier[] scaleTypes, float scale) {
		super(type, entity);
		this.scaleTypes = scaleTypes;
		this.scale = scale;
	}
	
	@Override
	public void tick() {
		super.tick();
		for (Identifier scaleType : scaleTypes) {
			ScaleData data = ScaleRegistries.SCALE_TYPES.get(scaleType).getScaleData(entity);
			if (data.getScale() != scale) {
				if (isActive()) data.setScale(scale);
				else data.setScale(1);
			} else if (!isActive()) data.setScale(1);
		}
	}
	
	@Override
	public void onLost() {
		super.onLost();
		for (Identifier scaleType : scaleTypes) {
			ScaleData data = ScaleRegistries.SCALE_TYPES.get(scaleType).getScaleData(entity);
			data.setScale(1);
		}
	}
}
