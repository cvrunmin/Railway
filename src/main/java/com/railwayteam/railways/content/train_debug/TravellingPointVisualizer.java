package com.railwayteam.railways.content.train_debug;

import com.mojang.math.Vector3f;
import com.simibubi.create.CreateClient;
import com.simibubi.create.content.logistics.trains.entity.Carriage;
import com.simibubi.create.content.logistics.trains.entity.CarriageBogey;
import com.simibubi.create.content.logistics.trains.entity.Train;
import com.simibubi.create.foundation.utility.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;

public class TravellingPointVisualizer {
    public static void debugTrain(Train train) {
        Minecraft mc = Minecraft.getInstance();
        Entity cameraEntity = mc.cameraEntity;
        if (cameraEntity == null)
            return;
        for (Carriage carriage : train.carriages) {
            Color color = Color.rainbowColor(carriage.hashCode());
            AABB box = (carriage.getDimensional(cameraEntity.level).entity.get() == null) ? null : carriage.getDimensional(cameraEntity.level).entity.get().getBoundingBox();
            if (true || box == null || box.intersects(cameraEntity.getBoundingBox()
                .inflate(50))) {
                for (CarriageBogey bogey : carriage.bogeys) {
                    if (bogey != null && bogey.leading() != null && bogey.trailing() != null && bogey.leading().edge != null && bogey.trailing().edge != null) {
                    /*for (TravellingPoint travellingPoint : new TravellingPoint[]{bogey.leading(), bogey.trailing()}) {
                        if (travellingPoint.node1.getLocation().getDimension() == cameraEntity.level.dimension() &&
                            travellingPoint.node2.getLocation().getDimension() == cameraEntity.level.dimension()) {
                            CreateClient.OUTLINER.showLine()
                        }
                    }*/
                        /*CreateClient.OUTLINER.showLine(Integer.valueOf(carriage.id * 2 + (bogey == carriage.leadingBogey() ? 0 : 1)), bogey.leading().getPosition(), bogey.trailing().getPosition())
                            .colored(color)
                            .lineWidth(18/16f);*/
                        int extent = 2;
                        int yRaise = 0;
                        cameraEntity.level.addParticle(ParticleTypes.END_ROD, bogey.getAnchorPosition().x, bogey.getAnchorPosition().y, bogey.getAnchorPosition().z, 0, 0, 0);
                        cameraEntity.level.addParticle(new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 2.0f), bogey.leading().getPosition().x, bogey.leading().getPosition().y, bogey.leading().getPosition().z, 0, bogey.leading().upsideDown ? 2 : -2, 0);
                        cameraEntity.level.addParticle(new DustParticleOptions(new Vector3f(0.0f, 0.0f, 1.0f), 2.0f), bogey.trailing().getPosition().x, bogey.trailing().getPosition().y, bogey.trailing().getPosition().z, 0, bogey.trailing().upsideDown ? 2 : -2, 0);
                        CreateClient.OUTLINER.showLine(Integer.valueOf(carriage.id * 8 + (bogey == carriage.leadingBogey() ? 0 : 1) * 4 + 0),
                                bogey.getAnchorPosition().add(0, extent+yRaise, 0), bogey.getAnchorPosition().add(0, -extent+yRaise, 0))
                            .colored(color)
                            .lineWidth(4/16f);
                        CreateClient.OUTLINER.showLine(Integer.valueOf(carriage.id * 8 + (bogey == carriage.leadingBogey() ? 0 : 1) * 4 + 1),
                                bogey.getAnchorPosition().add(extent, 0+yRaise, 0), bogey.getAnchorPosition().add(-extent, 0+yRaise, 0))
                            .colored(color)
                            .lineWidth(4/16f);
                        CreateClient.OUTLINER.showLine(Integer.valueOf(carriage.id * 8 + (bogey == carriage.leadingBogey() ? 0 : 1) * 4 + 2),
                                bogey.getAnchorPosition().add(0, 0+yRaise, extent), bogey.getAnchorPosition().add(0, 0+yRaise, -extent))
                            .colored(color)
                            .lineWidth(4/16f);
                    }
                }
            }
        }
    }
}
