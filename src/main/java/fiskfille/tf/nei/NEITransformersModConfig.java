package fiskfille.tf.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import fiskfille.tf.TransformersMod;

public class NEITransformersModConfig implements IConfigureNEI
{
	@Override
	public void loadConfig()
	{
		API.registerRecipeHandler(new EnergonProcessorRecipeHandler());
		API.registerUsageHandler(new EnergonProcessorRecipeHandler());
		API.registerUsageHandler(new PowerSourceRecipeHandler());
		API.registerRecipeHandler(new AssemblyTableRecipeHandler());
		API.registerUsageHandler(new AssemblyTableRecipeHandler());
	}

	@Override
	public String getName()
	{
		return "TransformersMod NEI Plugin";
	}

	@Override
	public String getVersion()
	{
		return TransformersMod.version;
	}
}
