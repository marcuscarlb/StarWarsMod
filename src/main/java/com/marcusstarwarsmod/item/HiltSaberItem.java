package com.marcusstarwarsmod.item;

import com.marcusstarwarsmod.util.SaberRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class HiltSaberItem extends SwordItem implements GeoItem {
    public static final String COLOUR_KEY = "LightsaberColour";
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public HiltSaberItem(Tier toolMaterial, int attackDamage, float attackSpeed, Properties settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack stack = user.getItemInHand(hand);

        if (user.isShiftKeyDown()) {
            if (!world.isClientSide) {
                CompoundTag nbt = stack.getOrCreateTag();

                if (!nbt.contains("Sheathed")) {
                    nbt.putBoolean("Sheathed", true);
                    user.displayClientMessage(Component.literal("Saber sheathed"), true);
                } else if (nbt.getBoolean("Sheathed")) {
                    nbt.putBoolean("Sheathed", false);
                    user.displayClientMessage(Component.literal("Saber unsheathed"), true);
                } else {
                    nbt.putBoolean("Sheathed", true);
                    user.displayClientMessage(Component.literal("Saber sheathed"), true);
                }
            }

            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.pass(stack);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.getOrCreateTag().getBoolean("Sheathed")) {
            return false;
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getItemInHand().getOrCreateTag().getBoolean("Sheathed")) {
            return InteractionResult.FAIL;
        }
        return super.useOn(context);
    }
    public static void setBladeColour(ItemStack stack) {
        float r = 1.0f;
        float g = 0.0f;
        float b = 0.0f;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean bl) {
        if (!stack.hasTag()) {
            stack.getOrCreateTag().putBoolean("Sheathed", false); // Default to unsheathed
        }
    }
    public int getIntFromColor(float Red, float Green, float Blue){
        int R = Math.round(255 * Red);
        int G = Math.round(255 * Green);
        int B = Math.round(255 * Blue);

        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;

        return 0xFF000000 | R | G | B;
    }

    public float[] getColorFromInt(int colorInt) {
        float red = ((colorInt >> 16) & 0xFF) / 255.0f;
        float green = ((colorInt >> 8) & 0xFF) / 255.0f;
        float blue = (colorInt & 0xFF) / 255.0f;
        return new float[]{ red, green, blue };
    }
    public static int getBladeColour(ItemStack stack) {
        return stack.getOrCreateTag().getInt(COLOUR_KEY);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private final SaberRenderer renderer = new SaberRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return this.renderer;
                }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return this.renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
