package io.github.sonicjdf.dsmorigins.registry;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import io.github.sonicjdf.dsmorigins.powers.ScaleTypePower;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Powers {
	public static final PowerFactory<Power> RESIZE_FACTORY = new PowerFactory<>(
			new Identifier("dsmorigins:rescale"),
			new SerializableData().add(
					"scale_types",
					SerializableDataTypes.IDENTIFIERS,
					Collections.singletonList(new Identifier("dsmorigins:resize"))
			).add(
					"scale",
					SerializableDataTypes.FLOAT,
					0.5f
			),
			(data)->{
				// no reason to constantly get this over and over again
				Identifier[] ids = ((List<Identifier>) data.get("scale_types")).toArray(new Identifier[0]);
				float scale = data.getFloat("scale");
				return (type, entity) -> new ScaleTypePower(type, entity, Arrays.copyOf(ids, ids.length), scale);
			}
	);
	
	public static void init() {
		Registry.register(ApoliRegistries.POWER_FACTORY, RESIZE_FACTORY.getSerializerId(), RESIZE_FACTORY);
	}
}
