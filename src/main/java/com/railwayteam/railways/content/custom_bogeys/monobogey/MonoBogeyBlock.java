package com.railwayteam.railways.content.custom_bogeys.monobogey;

import com.google.common.collect.ImmutableList;
import com.railwayteam.railways.registry.CRBlockEntities;
import com.railwayteam.railways.registry.CRBogeyStyles;
import com.railwayteam.railways.registry.CRTrackMaterials;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.logistics.trains.AbstractBogeyBlock;
import com.simibubi.create.content.logistics.trains.BogeySizes;
import com.simibubi.create.content.logistics.trains.TrackMaterial;
import com.simibubi.create.content.logistics.trains.entity.BogeyStyle;
import com.simibubi.create.content.schematics.ISpecialBlockItemRequirement;
import com.simibubi.create.foundation.block.ITE;
import com.simibubi.create.foundation.block.ProperWaterloggedBlock;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MonoBogeyBlock extends AbstractBogeyBlock<MonoBogeyTileEntity> implements ITE<MonoBogeyTileEntity>, ProperWaterloggedBlock, ISpecialBlockItemRequirement {
    public static final BooleanProperty UPSIDE_DOWN = BooleanProperty.create("upside_down");

    public MonoBogeyBlock(Properties pProperties) {
        super(pProperties, BogeySizes.SMALL);
        registerDefaultState(defaultBlockState().setValue(UPSIDE_DOWN, false));
    }

    @Override
    public BlockState getVersion(BlockState base, boolean upsideDown) {
        if (!base.hasProperty(UPSIDE_DOWN))
            return base;
        return base.setValue(UPSIDE_DOWN, upsideDown);
/*        if (upsideDown) {
            return CRBlocks.MONO_BOGEY_UPSIDE_DOWN.getDefaultState().setValue(AXIS, base.getValue(AXIS))
                .setValue(WATERLOGGED, base.getOptionalValue(WATERLOGGED).orElse(false));
        } else {
            return CRBlocks.MONO_BOGEY.getDefaultState().setValue(AXIS, base.getValue(AXIS))
                .setValue(WATERLOGGED, base.getOptionalValue(WATERLOGGED).orElse(false));
        }*/
    }

    @Override
    public TrackMaterial.TrackType getTrackType(BogeyStyle style) {
        return CRTrackMaterials.CRTrackType.MONORAIL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UPSIDE_DOWN);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public double getWheelPointSpacing() {
        return 2;
    }

    @Override
    public double getWheelRadius() {
        return 6 / 16d;
    }

    @Override
    public Vec3 getConnectorAnchorOffset(boolean upsideDown) {
        return new Vec3(0, upsideDown ? 26 / 32f : 5 / 32f, 25 / 32f);
    }

    @Override
    protected Vec3 getConnectorAnchorOffset() {
        return getConnectorAnchorOffset(false);
    }

    @Override
    public boolean allowsSingleBogeyCarriage() {
        return true;
    }

    @Override
    public BogeyStyle getDefaultStyle() {
        return CRBogeyStyles.MONOBOGEY;
    }

/*    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(@Nullable BlockState state, float wheelAngle, PoseStack ms, float partialTicks, MultiBufferSource buffers,
                       int light, int overlay) {
        if (state != null) {
            ms.translate(.5f, .5f, .5f);
            if (state.getValue(AXIS) == Direction.Axis.X)
                ms.mulPose(Vector3f.YP.rotationDegrees(90));
        }

        ms.translate(0, (-1.5 - 1 / 128f) * (upsideDown ? (state == null ? 1 : -1) : 1), 0);

        VertexConsumer vb = buffers.getBuffer(RenderType.cutoutMipped());
        BlockState air = Blocks.AIR.defaultBlockState();

        renderBogey(wheelAngle, ms, light, vb, air, state != null && upsideDown);
    }

    private void renderBogey(float wheelAngle, PoseStack ms, int light, VertexConsumer vb, BlockState air, boolean renderUpsideDown) {
        CachedBufferer.partial(CRBlockPartials.MONOBOGEY_FRAME, air)
            .rotateZ(renderUpsideDown ? 180 : 0)
            .scale(1 - 1 / 512f)
            .light(light)
            .renderInto(ms, vb);

//        wheelAngle = (Minecraft.getInstance().level.getGameTime() % 40) / 40f * 360;

        for (boolean left : Iterate.trueAndFalse) {
            for (int front : Iterate.positiveAndNegative) {
                ms.pushPose();
                CachedBufferer.partial(CRBlockPartials.MONOBOGEY_WHEEL, air)
                    .translate(left ? -12 / 16f : 12 / 16f, renderUpsideDown ? -13 /16f : 3 / 16f, front * 15 / 16f) //base position
                    .rotateY(left ? wheelAngle : -wheelAngle)
                    .translate(15/16f, 0, 0/16f)
                    .light(light)
                    .renderInto(ms, vb);
                ms.popPose();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public BogeyInstance createInstance(MaterialManager materialManager, CarriageBogey bogey) {
        return ((IBogeyFrameCanBeMonorail<BogeyInstance.Frame>) (Object) new BogeyInstance.Frame(bogey, materialManager))
            .setMonorail(
                upsideDown,
                bogey.carriage.leadingBogey().isUpsideDown()
            );
    }*/

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos,
                                       Player player) {
        return AllBlocks.RAILWAY_CASING.asStack();
    }

    @Override
    public Class<MonoBogeyTileEntity> getTileEntityClass() {
        return MonoBogeyTileEntity.class;
    }

    @Override
    public BlockEntityType<? extends MonoBogeyTileEntity> getTileEntityType() {
        return CRBlockEntities.MONO_BOGEY.get();
    }

    @Override
    public BlockState getRotatedBlockState(BlockState state, Direction targetedFace) {
        return state;
    }

    @Override
    public boolean canBeUpsideDown() {
        return true;
    }

    @Override
    public boolean isUpsideDown(BlockState state) {
        return state.hasProperty(UPSIDE_DOWN) && state.getValue(UPSIDE_DOWN);
    }

    @Override
    public List<Property<?>> propertiesToCopy() {
        return ImmutableList.<Property<?>>builder().addAll(super.propertiesToCopy()).add(UPSIDE_DOWN).build();
    }
}
