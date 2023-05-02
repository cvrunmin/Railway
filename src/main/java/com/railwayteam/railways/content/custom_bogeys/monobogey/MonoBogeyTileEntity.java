package com.railwayteam.railways.content.custom_bogeys.monobogey;

import com.railwayteam.railways.registry.CRBogeyStyles;
import com.simibubi.create.content.logistics.trains.entity.BogeyStyle;
import com.simibubi.create.content.logistics.trains.track.AbstractBogeyTileEntity;
import com.simibubi.create.content.logistics.trains.track.StandardBogeyTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MonoBogeyTileEntity extends AbstractBogeyTileEntity {
    public MonoBogeyTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public BogeyStyle getDefaultStyle() {
        return CRBogeyStyles.MONOBOGEY;
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return super.createRenderBoundingBox().inflate(2);
    }
}
