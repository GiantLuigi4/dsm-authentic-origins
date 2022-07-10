package io.github.sonicjdf.dsmorigins.mixin;

import io.github.sonicjdf.dsmorigins.registry.ScaleTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {
	@Inject(at = @At("TAIL"), method = "<init>")
	public void postInit(RunArgs args, CallbackInfo ci) {
		ScaleTypes.init();
	}
}
