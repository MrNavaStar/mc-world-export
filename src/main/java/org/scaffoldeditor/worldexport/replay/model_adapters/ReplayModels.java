package org.scaffoldeditor.worldexport.replay.model_adapters;

import org.scaffoldeditor.worldexport.replay.model_adapters.ReplayModelAdapter.ReplayModelAdapterFactory;
import org.scaffoldeditor.worldexport.replay.models.ArmatureReplayModel;
import org.scaffoldeditor.worldexport.replay.models.MultipartReplayModel;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

/**
 * Contains replay models for vanilla Minecraft entities.
 */
public final class ReplayModels {
    private ReplayModels() {
    };

    public static final float BIPED_Y_OFFSET = 1.5f;
    public static final float QUADRUPED_Y_OFFSET = 1.5f;
    static MinecraftClient client = MinecraftClient.getInstance();


    public static class AnimalModelFactory<T extends LivingEntity> implements ReplayModelAdapterFactory<T, ArmatureReplayModel> {

        public Identifier tex;
        public float y_offset;

        public AnimalModelFactory(Identifier tex, float y_offset) {
            this.tex = tex;
            this.y_offset = y_offset;
        }

        @Override
        public AnimalModelAdapter<T> create(T entity) {
            return new AnimalModelAdapter<T>(entity, tex, y_offset);
        }

    }

    @SuppressWarnings("rawtypes")
    public static void registerDefaults() {

        ReplayModelAdapter.REGISTRY.put(new Identifier("player"), new ReplayModelAdapterFactory<AbstractClientPlayerEntity, MultipartReplayModel>() {
            public ReplayModelAdapter<AbstractClientPlayerEntity, MultipartReplayModel> create(AbstractClientPlayerEntity entity) {
                return PlayerModelAdapter.newInstance(entity);
            }   
        });

        /**
         * QUADRIPEDS
         */
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:cow"),
                new AnimalModelFactory(new Identifier("textures/entity/cow/cow.png"), QUADRUPED_Y_OFFSET));
                
        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:goat"),
                new AnimalModelFactory(new Identifier("textures/entity/goat/goat.png"), QUADRUPED_Y_OFFSET));

        // TODO: write custom model adapter that updates texture situationally.
        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:panda"), 
                new AnimalModelFactory(new Identifier("textures/entity/panda/panda.png"), QUADRUPED_Y_OFFSET));
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:pig"),
                new AnimalModelFactory(new Identifier("textures/entity/pig/pig.png"), QUADRUPED_Y_OFFSET));
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:polar_bear"),
                new AnimalModelFactory(new Identifier("textures/entity/bear/polarbear.png"), QUADRUPED_Y_OFFSET));
        
        // TODO: Make this render wool properly.
        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:sheep"),
                new AnimalModelFactory(new Identifier("textures/entity/sheep/sheep.png"), QUADRUPED_Y_OFFSET));
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:turtle"), 
                new AnimalModelFactory(new Identifier("textures/entity/turtle/big_sea_turtle.png"), QUADRUPED_Y_OFFSET));
            
        /**
         * BIPEDS
         */
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:zombie"),
                new AnimalModelFactory(new Identifier("textures/entity/zombie/zombie.png"), BIPED_Y_OFFSET));
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:drowned"),
                new AnimalModelFactory(new Identifier("textures/entity/zombie/drowned.png"), BIPED_Y_OFFSET));

        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:enderman"), 
                new AnimalModelFactory(new Identifier("textures/entity/enderman/enderman.png"), BIPED_Y_OFFSET));
            
        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:skeleton"),
                new AnimalModelFactory(new Identifier("textures/entity/skeleton/skeleton.png"), BIPED_Y_OFFSET));

        ReplayModelAdapter.REGISTRY.put(new Identifier("minecraft:vex"), 
                new AnimalModelFactory(new Identifier("textures/entity/illager/vex.png"), BIPED_Y_OFFSET));
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("zombie_villager"), 
                new AnimalModelFactory(new Identifier("textures/entity/zombie_villager/zombie_villager.png"), BIPED_Y_OFFSET));

        /**
         * MISC
         */

        // TODO: Axolotl's varients make implementation non-trivial

        ReplayModelAdapter.REGISTRY.put(new Identifier("bee"),
                new AnimalModelFactory(new Identifier("textures/entity/bee/bee.png"), 0));
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("chicken"),
                new AnimalModelFactory(new Identifier("textures/entity/chicken.png"), 0));
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("fox"),
                new AnimalModelFactory(new Identifier("textures/entity/fox/fox.png"), 0));
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("hoglin"), 
                new AnimalModelFactory(new Identifier("textures/entity/hoglin/hoglin.png"), 0));

        // TODO: Horse variant types
        ReplayModelAdapter.REGISTRY.put(new Identifier("horse"),
                new AnimalModelFactory(new Identifier("textures/entity/horse/horse_brown.png"), 0));
        
        ReplayModelAdapter.REGISTRY.put(new Identifier("donkey"),
                new AnimalModelFactory(new Identifier("textures/entity/horse/donkey.png"), 0));

    }
}
