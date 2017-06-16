package fiskfille.tf.common.api.item;

public interface RegisterItemModel
{
    default String getResource(String unlocalizedName)
    {
        return unlocalizedName;
    }
}
