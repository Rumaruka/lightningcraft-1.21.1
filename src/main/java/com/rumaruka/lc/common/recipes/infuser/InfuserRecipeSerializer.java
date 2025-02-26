package com.rumaruka.lc.common.recipes.infuser;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rumaruka.lc.api.recipe.AbstractSingleIngredientRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class InfuserRecipeSerializer<T extends AbstractSingleIngredientRecipe> implements RecipeSerializer<T> {

    private final CookieBaker<T> factory;
    private final MapCodec<T> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

    public InfuserRecipeSerializer(CookieBaker<T> p_44330_, int pCookingTime) {
        this.factory = p_44330_;
        this.codec = RecordCodecBuilder.mapCodec(
                p_300831_ -> p_300831_.group(
                                Codec.STRING.optionalFieldOf( "group", "").forGetter(p_300832_ -> p_300832_.group),
                                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(p_300833_ -> p_300833_.ingredient),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(o -> o.result),
                                Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(p_300826_ -> p_300826_.experience),
                                Codec.INT.fieldOf("cookingtime").orElse(pCookingTime).forGetter(p_300834_ -> p_300834_.cookingTime)
                        )
                        .apply(p_300831_, factory::create)
        );
        this.streamCodec = StreamCodec.of(this::toNetwork, this::fromNetwork);
    }

    @Override
    public MapCodec<T> codec() {
        return codec;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
        return streamCodec;
    }



    public T fromNetwork(RegistryFriendlyByteBuf pBuffer) {
        String group = pBuffer.readUtf();
        Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(pBuffer);
        ItemStack result =  ItemStack.STREAM_CODEC.decode(pBuffer);
        float f = pBuffer.readFloat();
        int i = pBuffer.readVarInt();
        return this.factory.create(group, ingredient, result, f, i);
    }


    public void toNetwork(RegistryFriendlyByteBuf pBuffer, T pRecipe) {
        pBuffer.writeUtf(pRecipe.group);
        Ingredient.CONTENTS_STREAM_CODEC.encode(pBuffer,pRecipe.ingredient);
        ItemStack.STREAM_CODEC.encode(pBuffer,pRecipe.result);
        pBuffer.writeFloat(pRecipe.experience);
        pBuffer.writeVarInt(pRecipe.cookingTime);
    }


    public interface CookieBaker<T extends AbstractSingleIngredientRecipe> {
        T create(String p_44354_, Ingredient p_44355_, ItemStack p_44356_, float pExperience, int pCookingTime);
    }
}
