package com.rumaruka.lc.ntw.packet;

import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.misc.LCUtils;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.rumaruka.lc.misc.LCUtils.rl;

public record LEPacket(int le) implements CustomPacketPayload {

    public static final Type<LEPacket> TYPE = new Type<>(rl("lunar"));


    public static final StreamCodec<FriendlyByteBuf, LEPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, LEPacket::le,

            LEPacket::new);
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {



                FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
                LEStorage data =new LEStorage(LCUtils.getMaxEnergyTools());

                byteBuf.writeInt(data.getLE());







        });

    }

}
