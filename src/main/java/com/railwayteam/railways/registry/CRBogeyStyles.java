package com.railwayteam.railways.registry;

import com.railwayteam.railways.Railways;
import com.railwayteam.railways.content.custom_bogeys.monobogey.MonoBogeyRenderer;
import com.simibubi.create.AllBogeyStyles;
import com.simibubi.create.content.logistics.trains.BogeySizes;
import com.simibubi.create.content.logistics.trains.entity.BogeyStyle;
import com.simibubi.create.foundation.utility.Components;
import net.minecraft.resources.ResourceLocation;

public class CRBogeyStyles {

    public static final BogeyStyle MONOBOGEY = create("monobogey")
        .displayName(Components.translatable("railways.bogeys.styles.standard"))
        .size(BogeySizes.SMALL, MonoBogeyRenderer.SmallMonoBogeyRenderer::new, CRBlocks.MONO_BOGEY)
        .build();

    public static AllBogeyStyles.BogeyStyleBuilder create(String name) {
        return create(Railways.asResource(name));
    }

    public static AllBogeyStyles.BogeyStyleBuilder create(ResourceLocation name) {
        return new AllBogeyStyles.BogeyStyleBuilder(name);
    }

    public static void register() {
        Railways.LOGGER.info("Registered bogey styles from " + Railways.MODID);
    }
}
