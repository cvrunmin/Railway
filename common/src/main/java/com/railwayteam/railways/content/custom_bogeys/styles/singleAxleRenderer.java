package com.railwayteam.railways.content.custom_bogeys.styles;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.util.transform.Transform;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.railwayteam.railways.registry.CRBlockPartials;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import net.minecraft.nbt.CompoundTag;

public class singleAxleRenderer extends BogeyRenderer {
    @Override
    public BogeySizes.BogeySize getSize() {
        return BogeySizes.SMALL;
    }

    @Override
    public void render(CompoundTag bogeyData, float wheelAngle, PoseStack ms, int light, VertexConsumer vb, boolean inContraption) {

        Transform<?> frame = this.getTransformFromPartial(CRBlockPartials.SINGLEAXLE_FRAME, ms, inContraption)
                .translate(0, 0, 0)
                .scale(1 - 1 / 512f);
        finalize(frame, ms, light, vb);

        Transform<?> wheels = this.getTransformFromPartial(AllPartialModels.SMALL_BOGEY_WHEELS, ms, inContraption)
                .scale(1 - 1 / 512f);
        finalize(wheels, ms, light, vb);

        ms.pushPose();
    }

    @Override
    public void initialiseContraptionModelData(MaterialManager materialManager) {
        this.createModelInstances(materialManager, CRBlockPartials.SINGLEAXLE_FRAME);
        this.createModelInstances(materialManager, AllPartialModels.SMALL_BOGEY_WHEELS);
    }
}
