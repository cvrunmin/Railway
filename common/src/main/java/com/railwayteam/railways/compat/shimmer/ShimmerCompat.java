package com.railwayteam.railways.compat.shimmer;

import com.lowdragmc.shimmer.client.light.ColorPointLight;
import com.lowdragmc.shimmer.client.light.LightManager;
import com.lowdragmc.shimmer.client.postprocessing.PostProcessing;
import com.lowdragmc.shimmer.client.shader.RenderUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.railwayteam.railways.Railways;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiConsumer;

public class ShimmerCompat {

    public static void init() {
        Railways.LOGGER.info("Loaded Shimmer Compat");

//        LightManager.INSTANCE.registerBlockLight(Blocks.TORCH, (state, pos) -> new ColorPointLight.Template(8, 0xff74F1F5));
    }

    public static void renderWithBloom(PoseStack poseStack, BiConsumer<PoseStack, MultiBufferSource> renderFunction) {
        PoseStack finalStack = RenderUtils.copyPoseStack(poseStack);
        PostProcessing.BLOOM_UNREAL.postEntity(b -> renderFunction.accept(finalStack, b));
    }
}
