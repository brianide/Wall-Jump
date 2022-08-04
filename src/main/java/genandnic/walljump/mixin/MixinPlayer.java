package genandnic.walljump.mixin;

import genandnic.walljump.Config;
import genandnic.walljump.client.DoubleJumpLogic;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class MixinPlayer {

    @Shadow public abstract boolean isLocalPlayer();

    @Inject(at = @At(value = "HEAD"), method = "tryToStartFallFlying", cancellable = true)
    public void walljump$tryToStartFallFlying(CallbackInfoReturnable<Boolean> ci) {
        if (isLocalPlayer() && Config.COMMON.deferElytra.get() && DoubleJumpLogic.getRemainingJumps() > 0) {
            ci.setReturnValue(false);
            ci.cancel();
        }
    }

}
