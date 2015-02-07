package fiskfille.tf.transformer.base;

import fiskfille.tf.model.transformer.ModelChildBase.Biped;

public class TransformerModel 
{
	private Biped model;
	
	public TransformerModel(Biped model)
	{
		this.model = model;
	}
	
	public Biped getModel()
	{
		return model;
	}
}
