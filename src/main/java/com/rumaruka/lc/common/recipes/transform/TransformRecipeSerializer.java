package com.rumaruka.lc.common.recipes.transform;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rumaruka.lc.api.recipe.APITransferRecipe;
import com.rumaruka.lc.api.recipe.AbstractCustomRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.stream.IntStream;

public class TransformRecipeSerializer implements RecipeSerializer<TransformRecipe> {
    private final MapCodec<TransformRecipe> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, TransformRecipe>  streamCodec;

    public TransformRecipeSerializer() {

        this.codec = RecordCodecBuilder.mapCodec((p_296927_) -> {
            return p_296927_.group(Codec.STRING.optionalFieldOf( "group", "").forGetter((p_296921_) -> {
                        return p_296921_.group;
                    }),
                    Ingredient.CODEC_NONEMPTY.listOf()


                            .fieldOf("base")

                            .flatXmap(ingredients -> {
                                return DataResult
                                        .success(NonNullList.of(Ingredient.EMPTY, ingredients.toArray(Ingredient[]::new)));
                            }, DataResult::success)
                            .forGetter(t -> t.ingredients),


                    ItemStack.CODEC.fieldOf("result").forGetter((p_301142_) -> {
                        return p_301142_.result;


                    })


            ).apply(p_296927_, TransformRecipe::new);
        });
        this.streamCodec =  StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.collection(NonNullList::createWithCapacity)),
                APITransferRecipe::getIngredients,
                ItemStack.STREAM_CODEC,
                APITransferRecipe::getResult,
                TransformRecipe::new);

    }


    public MapCodec<TransformRecipe> codec() {
        return this.codec;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf,TransformRecipe> streamCodec() {
        return  streamCodec;
    }



}