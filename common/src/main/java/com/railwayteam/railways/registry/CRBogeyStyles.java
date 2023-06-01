package com.railwayteam.railways.registry;

import com.railwayteam.railways.Railways;
import com.railwayteam.railways.content.custom_bogeys.monobogey.MonoBogeyRenderer;
import com.railwayteam.railways.content.custom_bogeys.styles.DoubleAxleBogeyRenderer;
import com.railwayteam.railways.content.custom_bogeys.styles.singleAxleRenderer;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllBogeyStyles;
import com.simibubi.create.Create;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.foundation.utility.Components;

public class CRBogeyStyles {

    public final BogeyStyle SINGLE_AXLE = create("single_axle", "railways_cylce_group")
            .displayName(Components.translatable("railways.bogey.style.standard"))
            .size(BogeySizes.SMALL, () -> singleAxleRenderer::new, AllBlocks.SMALL_BOGEY)
            .build();

    public static final BogeyStyle DOUBLE_AXLE = create("double_axle", "railways_cylce_group")
            .displayName(Components.translatable("extendedbogeys.bogey.style.double_axle"))
            .size(BogeySizes.SMALL, () -> DoubleAxleBogeyRenderer.SmallDoubleAxleBogeyRenderer::new, AllBlocks.SMALL_BOGEY)
            .build();

    public static final BogeyStyle MONOBOGEY = create("monobogey", "monobogey")
        .displayName(Components.translatable("railways.bogeys.styles.monobogey"))
        .size(BogeySizes.SMALL, () -> MonoBogeyRenderer.SmallMonoBogeyRenderer::new, CRBlocks.MONO_BOGEY)
        .build();

    public static AllBogeyStyles.BogeyStyleBuilder create(String name, String cycleGroup) {
        return AllBogeyStyles.create(Railways.asResource(name), Railways.asResource(cycleGroup));
    }

    public static AllBogeyStyles.BogeyStyleBuilder create(String name) {
        return AllBogeyStyles.create(Railways.asResource(name), Create.asResource(AllBogeyStyles.STANDARD_CYCLE_GROUP));
    }

    public static void register() {
        Railways.LOGGER.info("Registered bogey styles from " + Railways.MODID);
    }
}