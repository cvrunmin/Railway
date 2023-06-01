package com.railwayteam.railways.content.custom_bogeys.styles;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.util.transform.Transform;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.railwayteam.railways.registry.CRBlockPartials;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.nbt.CompoundTag;

public class DoubleAxleBogeyRenderer {
    public static class SmallDoubleAxleBogeyRenderer extends BogeyRenderer {
        @Override
        public void render(CompoundTag bogeyData, float wheelAngle, PoseStack ms, int light, VertexConsumer vb, boolean inContraption) {
            Transform<?>[] wheels = getTransformsFromPartial(AllPartialModels.SMALL_BOGEY_WHEELS,  ms, inContraption, 2);

            for (int side : Iterate.positiveAndNegative) {
                wheels[(side+1) / 2]
                        .translate(0, 12 / 16f, side -2);
                finalize(wheels[(side+1) / 2], ms, light, vb);
            }

            Transform<?> frame = getTransformFromPartial(CRBlockPartials.SINGLEAXLE_FRAME, ms, inContraption)
                    .translateY(0.2);
            finalize(frame, ms, light, vb);
        }

        @Override
        public BogeySizes.BogeySize getSize() {
            return BogeySizes.SMALL;
        }

        @Override
        public void initialiseContraptionModelData(MaterialManager materialManager) {
            this.createModelInstances(
                materialManager,
                CRBlockPartials.SINGLEAXLE_FRAME
            );
            this.createModelInstances(
                materialManager, AllPartialModels.SMALL_BOGEY_WHEELS, 2
            );
        }
    }
}
