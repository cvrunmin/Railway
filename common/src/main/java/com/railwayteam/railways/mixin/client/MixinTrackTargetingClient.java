package com.railwayteam.railways.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.railwayteam.railways.util.CustomTrackOverlayRendering;
import com.simibubi.create.content.logistics.trains.management.edgePoint.EdgePointType;
import com.simibubi.create.content.logistics.trains.management.edgePoint.TrackTargetingClient;
import com.simibubi.create.content.logistics.trains.track.BezierTrackPointLocation;
import com.simibubi.create.foundation.render.SuperRenderTypeBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TrackTargetingClient.class, remap = false)
public abstract class MixinTrackTargetingClient {
    @Shadow
    static EdgePointType<?> lastType;

    @Shadow
    static BlockPos lastHovered;

    @Shadow
    static boolean lastDirection;

    @Shadow
    static BezierTrackPointLocation lastHoveredBezierSegment;

    @Inject(method = "render", at = @At(
        value = "FIELD", opcode = Opcodes.GETSTATIC,
        target = "Lcom/simibubi/create/content/logistics/trains/management/edgePoint/TrackTargetingClient;lastType:Lcom/simibubi/create/content/logistics/trains/management/edgePoint/EdgePointType;",
        ordinal = 0
    ), cancellable = true)
    private static void renderCustom(PoseStack ms, SuperRenderTypeBuffer buffer, CallbackInfo ci) {
        if (CustomTrackOverlayRendering.CUSTOM_OVERLAYS.containsKey(lastType)) {
            Minecraft mc = Minecraft.getInstance();
            BlockPos pos = lastHovered;
            int light = LevelRenderer.getLightColor(mc.level, pos);
            Direction.AxisDirection direction = lastDirection ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE;

            CustomTrackOverlayRendering.renderOverlay(mc.level, pos, direction, lastHoveredBezierSegment, ms, buffer, light,
                OverlayTexture.NO_OVERLAY, lastType, 1 + 1 / 16f);
            ci.cancel();
        }
    }
}
