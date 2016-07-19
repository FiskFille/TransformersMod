package fiskfille.tf.asm;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import fiskfille.tf.asm.transformers.ClassTransformerModelBiped;

@MCVersion("1.7.10")
@TransformerExclusions("fiskfille.tf.asm")
public class TFLoadingPlugin implements IFMLLoadingPlugin
{
	private static final String[] transformers = new String[]
	{
		ClassTransformerModelBiped.class.getName()
	};

	@Override
	public String[] getASMTransformerClass()
	{
		return transformers;
	}

	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}

	@Override
	public String getModContainerClass()
	{
		return null;
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
		TFTranslator.obfuscatedEnv = Boolean.class.cast(data.get("runtimeDeobfuscationEnabled"));
	}
}
